package com.focusx.programme.action;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.StrutsUtil;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.attilax.Closure;
import com.attilax.Closure2;
import com.attilax.core;
import com.attilax.collection.CollX;
import com.attilax.collection.Ireduce;
import com.attilax.designpatter.parterr.ErrorItem;
import com.attilax.designpatter.parterr.PartErrX;
import com.attilax.designpatter.parterr.PartProcessErrEx;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.json.JsonUtil4jackjson;
import com.attilax.persistence.PX;
import com.attilax.review.AlreadyReviewPassedEx;
import com.attilax.review.IReview;
import com.attilax.review.NoFiltEx;
import com.attilax.review.Review;
import com.attilax.review.ReviewBackEx;
import com.attilax.review.ReviewHistry;
import com.attilax.review.ReviewState;
import com.attilax.review.ReviewX;
import com.attilax.review.TargetObj;
import com.attilax.spri.SpringUtil;
import com.attilax.sso.loginUtil;
import com.attilax.time.timeUtil;
import com.attilax.util.StrutsX;
import com.attilax.web.ReqX;
import com.focusx.dao.impl.baseDao;
import com.focusx.elmt.GvMaterial;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.pager.PageUtil;
import com.focusx.pager.Pager;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.entity.GvProgrammeDetail;
import com.focusx.programme.service.IProgrammeService;
import com.focusx.util.Constant;
import com.focusx.util.MyCacher;
import com.focusx.util.OperLogUtil4vod;
import com.focusx.util.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
@RemoteProxy(name = "prgrmX")

/**
 * com.focusx.programme.action.ProgrammeAction
 * @author ASIMO
 *
 */
public class ProgrammeAction  implements  IReview<GvProgramme> {
	protected static Logger logger = Logger.getLogger(ProgrammeAction.class);

	//数据字典服务对象
	private IProgrammeService  programmeService;
	//分页对象
	private Pager<GvProgramme> pager; 
	private Integer currentPage;
	private Integer pageSize;
	//返回数据对象
	private boolean status;
	//返回数据结果
	private String result;
	//返回数据列表
	private List dataList;
	//返回数据集
	private Map<String,Object> dataMap; //返回数据集
	//数据字典对象
	private GvProgramme programme;
	
    //查询条件属性
	private Integer progarmmeId;//节目单id
	private String describe;//描述
	private String screen;//分屏区
	private Double totalDuration; // 总时长
	private Integer materialNumber; //素材数量
	private Integer createMan;//创建人 
	private String createManName;//创建人名称
	private String groupid; //门店id
	private String del;
	private String beginTime;
	private String endTime;
	private String startTime;
	private String overTime;
	
	
	private String deleteList;//批量删除Id
	private String selIdStr;//选择中的素材IDS
	private String materialIds;//素材列表
	
	private String materialDescription; //描述
	private String applicationType;//应用分类
	private String materialType;//文件分类
	private String playtime_end;
	private String playtime_start;
	 

	 
	public ProgrammeAction(){
		dataMap = new HashMap<String,Object>();
		dataList = new ArrayList(); 
		this.px=IocX.getBean(PX.class);
	}
	
	/**normal query in progrm mana list
	 * query oa1 add anno
	  * @author：luojun
	  *
	  * @Description:获取数据列表(分页)
	  *  
	  * @CreateTime: 2014-7-14 下午03:03:52
	  *
	  * @return
	 */
	public String queryProgrammeList(){ 
		if("1".length()>10)
		throw new RuntimeException("tttt");
		try {
			final HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Object> conditionMap = new HashMap<String, Object>();   
			if(Utils.isNotEmpty(describe)){
				conditionMap.put("describe", describe.trim());
			}  
			if(Utils.isNotEmpty(screen)){
				conditionMap.put("screen", screen.trim());
			}
			if(Utils.isNotEmpty(createManName)){
				conditionMap.put("createManName", createManName.trim());
			} 
			if(materialNumber!=null){
				conditionMap.put("materialNumber", materialNumber);
			} 
			if(Utils.isNotEmpty(beginTime)){
				conditionMap.put("beginTime", beginTime.trim());
			}
			if(Utils.isNotEmpty(endTime)){
				conditionMap.put("endTime", endTime.trim());
			}

			pager = new Pager<GvProgramme>();
			PageUtil.init(pager, request);
			
			baseDao.proFltr.set(new PropFilter() {
				
				@Override
				public Object getExp() {
					 String fltByLogicDel=" and logicDel is null ";
					try {
						return fltByLogicDel+" and progarmme_id in (@ids) ".replaceAll("@ids", reviStateFiltedIds(request.getParameterMap())  );
					} catch (NoFiltEx e) {
						 
						return fltByLogicDel;
					}catch(Exception e)
					{
						return  fltByLogicDel;
					}
				}
			}) ;
			List<GvProgramme> data = programmeService.getProgrammeList(pager, conditionMap);
			setReviewObj4List(data);
			
			pager.setData(data); 
			return "list";
		} catch (Exception e) {
			throw new RuntimeException(core.toJsonStrO88(e));
		}
		
	}
	

	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:获取数据列表(分页--发布管理页面用来选择界面单)
	  *  
	  * @CreateTime: 2014-7-14 下午03:03:52
	  *
	  * @return
	 */
	public String queryPublishProgrammeList(){ 
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionMap = new HashMap<String, Object>();   
		if(Utils.isNotEmpty(describe)){
			conditionMap.put("describe", describe.trim());
		}  
		if(Utils.isNotEmpty(screen)){
			conditionMap.put("screen", screen.trim());
		}
		if(createMan!=null){
			conditionMap.put("createMan", createMan);
		} 
		if(materialNumber!=null){
			conditionMap.put("materialNumber", materialNumber);
		} 
		if(Utils.isNotEmpty(beginTime)){
			conditionMap.put("beginTime", beginTime.trim());
		}
		if(Utils.isNotEmpty(endTime)){
			conditionMap.put("endTime", endTime.trim());
		}
		//	pageParamsMp.put("revistat",ReviewState.Passed);
		baseDao.proFltr.set(new PropFilter() {

			@Override
			public Object getExp() {
				 String fltByLogicDel=" and logicDel is null ";
				try {
					return fltByLogicDel+" and progarmme_id in (@ids) ".replaceAll("@ids",
							reviStateFiltedIds(new HashMap<String, String[]>() {
								{
									String[] s=new String[]{ReviewState.Passed};
									put("revistat", s);
								}
							}));
				} catch (NoFiltEx e) {
					 
					return fltByLogicDel;
				} catch (Exception e) {
					return fltByLogicDel;
				}
			}
		});
		pager = new Pager<GvProgramme>();
		PageUtil.init(pager, request);
		List<GvProgramme> data = programmeService.getProgrammeList(pager, conditionMap);
		pager.setData(data); 
		return "programmeList";
	}
	
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:添加节目单
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String add(){  
		Map<String, Object> conditionMap = new HashMap<String, Object>();  
		
		//ob0
		Integer createMan =new Closure2 <Object, Integer>() {

			@Override
			public Integer execute(Object arg0)   {
				
			 try {
				 Integer Login_UserID = (Integer) ActionContext.getContext().getSession().get(Constant.Login_UserID); 
				if(Login_UserID==null)
						return  Integer.parseInt(	loginUtil.getuid(StrutsX.getReq()));
				return Login_UserID;
			} catch (Exception e) {
				return null;
			}
				
				
			}
			
		}.execute(null);
		//Login_UserID;//插入人ID 
		programme.setCreateMan(createMan);
		if(materialIds.endsWith(",")){
			materialIds = materialIds.substring(0, materialIds.length() - 1);
		}
		OperLogUtil4vod.log("添加节目单:"+programme.getDescribe());//+"  素材ids:"+);
		if(Utils.isNotEmpty(materialIds)){
			conditionMap.put("materialIds", materialIds);
		}
		status = programmeService.insert(programme,conditionMap);
		return queryProgrammeList();
	}
	
	/**
	 *  jeig sh edit save....open edit page is showEdit
	  * @author：luojun
	  *
	  * @Description:编辑节目单
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String edit(){ 
		
	    //oa4 edie save as
	//	AsynCallBack
		
		if( ServletActionContext.getRequest().getParameter("saveAsFlag").equals("1"))
		{
		//	 filena
		// 	StringUtils
			return add();
		}
		
		Map<String, Object> conditionMap = new HashMap<String, Object>();   
		//节目单详情
		if(materialIds.endsWith(",")){
			materialIds = materialIds.substring(0, materialIds.length() - 1);
		}
		if(Utils.isNotEmpty(materialIds)){
			conditionMap.put("materialIds", materialIds);
		}
		
		try {
			OperLogUtil4vod.log("编辑节目单:"+programme.getDescribe());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		status = programmeService.update(programme,conditionMap);
		programme = null;
		conditionMap.clear();
		return queryProgrammeList(); 
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:删除节目单
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String delete(){  

		try {
		GvProgramme p=	programmeService.getProgramme(progarmmeId);
			OperLogUtil4vod.log("删除节目单:"+p.getDescribe());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		result = programmeService.delete(progarmmeId);  
		return Action.SUCCESS; 
	}
	
	public String deleteBatch(){
		
		OperLogUtil4vod.log("删除节目单:"+deleteList);
		
		String[] strs = deleteList.split(","); 
		List<Integer> ids = new ArrayList<Integer>();
		final PartErrX pex = new PartErrX();
		for (int i = 0; i < strs.length; i++) {
			ids.add(new Integer(strs[i].trim()));
 
		} 
		
		//except revied progrm ids  add coll error prgrm id
//		List<Integer> waittoDelList=new   ArrayList<Integer>();
//		ReviewX  rx=IocX.getBean(ReviewX.class);
//		for (Integer id : ids) {
//		
//			if( rx.reviewPassedCheck(id,"prgrm"))
//			{
//				waittoDelList.add(id);
//				ErrorItem ei = new ErrorItem();
//				ei.id = String.valueOf(id);
//				ei.msg ="已经通过审核";
//				pex.add(ei);
//			}
//		}
//		 ids.removeAll(waittoDelList);
		 
		 
		 result = programmeService.deleteBatch(ids);
	 
		 //programmeService.deleteBatch(ids);
		
//		if (pex.li.size()>0){			
//		
//			try {
//				throw (new PartProcessErrEx(JsonUtil4jackjson.buildNormalBinder()
//						.toJson(pex.li)));
//			} catch (PartProcessErrEx e) {
//				e.setTypex("PartProcessErrEx");
//				result= core.toJsonStrO88(e);
//			}
//		}
		
		return Action.SUCCESS; 
		
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:打开添加页面
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String showAdd(){ 
		//清除缓存
		Integer Login_UserID = (Integer) ActionContext.getContext().getSession().get(Constant.Login_UserID);
		MyCacher myCacher = MyCacher.getInstance();
		myCacher.removeCache(Constant.MATERIAL + Login_UserID);
		return "add";
	}
	
	
	/**
	 * 未添加的素材
	 * @return
	 */
	public String getNotConfigData(){
		try {
			dataMap.clear();
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Object> conditionMap = new HashMap<String, Object>();  
		 
			if(Utils.isNotEmpty(materialDescription)){
				conditionMap.put("materialDescription", materialDescription);
			} 
			if(Utils.isNotEmpty(applicationType)){
				conditionMap.put("applicationType", applicationType);
			} 
			if(Utils.isNotEmpty(materialType)){
				conditionMap.put("materialType", materialType);
			} 
			if(Utils.isNotEmpty(playtime_start)){
				conditionMap.put("playtime_start", playtime_start);
			}
			if(Utils.isNotEmpty(playtime_end)){
				conditionMap.put("playtime_end", playtime_end);
			}
			if(ReqX.isNotEmpty(request,"createTime")){
				conditionMap.put("createTime", request.getParameter("createTime"));
			}
			//oa3
			if(ReqX.isNotEmpty(request,"subprgrmSwitch")){
				conditionMap.put("subprgrmSwitch", request.getParameter("subprgrmSwitch"));
			}else
				conditionMap.put("subprgrmSwitch", "");
			conditionMap.put("logicDel", 8);
		//	conditionMap.put("failureTime_end", timeUtil.Now_CST());
			conditionMap.put("failureTime_start", timeUtil.Now_CST());
			pager = new Pager();
			PageUtil.init(pager, request);
			Map rsMap = programmeService.getAlternativeData(pager,conditionMap);
			if(rsMap.containsKey("rows")){
				dataMap.put("Rows", rsMap.get("rows"));
			}
			if(rsMap.containsKey("total")){ 
				pager.setTotal(Integer.valueOf(rsMap.get("total").toString())) ;
			}
			dataMap.put("Pager", pager);
			return Action.SUCCESS;
		} catch (Exception e) {
			filex.save_SF(core.toJsonStrO88(e), "c:\\getNotConfigData"+filex.getUUidName()+".txt");
		}
		return null;
		
	}
	
	/**
	 * 已添加的素材
	 * @return
	 */
	public String getConfigedData(){  
		Map<String, Object> conditionMap = new HashMap<String, Object>();   
		conditionMap.put("progarmmeId", progarmmeId);
		dataList = programmeService.getSelectedData(conditionMap); 
		//dataMap.put("Rows", list);
		return Action.SUCCESS;
	} 
	
	public static void main(String[] args) {
		IProgrammeService programmeService=	(IProgrammeService) SpringUtil.getBean(IProgrammeService.class);
		GvProgramme	programme = programmeService.getProgramme(2);  
		System.out.println("--");
	
	}
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:打开编辑页面
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String showEdit(){ 
		System.out.println("--");
		programme = programmeService.getProgramme(progarmmeId);  
		return "edit";
	}
	
	public String showEdit4rvw(){ 
		System.out.println("--");
		programme = programmeService.getProgramme(progarmmeId);  
		return "Edit4rvw";
	}
	
	
	@RemoteMethod
	@SuppressWarnings("all")
	/**
	 * 
		@author attilax 老哇的爪子
		@since   oa5 g_3_u
	 */
	public GvProgramme getPrgrm(Integer progarmmeId){ 
		
		try {
			IProgrammeService programmeService=	(IProgrammeService) SpringUtil.getBean(IProgrammeService.class);
			GvProgramme	programme = programmeService.getProgramme(progarmmeId);  
		
			List li=programme.getList();
			 CollX.each_safe(li, new Closure<GvProgrammeDetail, GvProgrammeDetail>() {

				@Override
				public GvProgrammeDetail execute(GvProgrammeDetail arg0)
						throws Exception {
					 int mtrlId=arg0.getMaterialId();
					 GvMaterialSvs c=new GvMaterialSvs();
					 GvMaterial mtrl=c.findById(mtrlId);
					 arg0.setMtrl(mtrl);
					return arg0;
				}
			});
			return programme;
		} catch (Exception e) {
			filex.save_SF(core.toJsonStrO88(e), "c:\\oa5_2.txt");
			return null;
		}
		
	}
	
	/**
	 * @author leo
	 * 查询节目单门店是否收到
	 * @return 
	 */
	public String receiveProgrammeList(){ 
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Object> conditionMap = new HashMap<String, Object>();  
			if(progarmmeId!=null){
				conditionMap.put("progarmmeId", progarmmeId);
			} 
			if(Utils.isNotEmpty(del)){
				conditionMap.put("del", del);
			}
			if(Utils.isNotEmpty(groupid)){
				conditionMap.put("groupid", groupid);
			} 
			if(Utils.isNotEmpty(startTime)){
				conditionMap.put("startTime", startTime.trim());
			}
			if(Utils.isNotEmpty(overTime)){
				conditionMap.put("overTime", overTime.trim());
			}
			pager = new Pager<GvProgramme>();
			PageUtil.init(pager, request);
			List<GvProgramme> data = programmeService.getProgrammeReceiveList(pager, conditionMap);
			pager.setData(data);
			return "receive";
		} catch (Exception e) {
			throw new RuntimeException(core.toJsonStrO88(e));
		}
	}
	
	public String receiveProgrammeList2(){ 
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Object> conditionMap = new HashMap<String, Object>();  
			if(progarmmeId!=null){
				conditionMap.put("progarmmeId", progarmmeId);
			} 
			if(Utils.isNotEmpty(del)){
				conditionMap.put("del", del);
			}
			if(Utils.isNotEmpty(groupid)){
				conditionMap.put("groupid", groupid);
			} 
			if(Utils.isNotEmpty(startTime)){
				conditionMap.put("startTime", startTime.trim());
			}
			if(Utils.isNotEmpty(overTime)){
				conditionMap.put("overTime", overTime.trim());
			}
			pager = new Pager<GvProgramme>();
			PageUtil.init(pager, request);
			List<GvProgramme> data = programmeService.getProgrammeReceiveList(pager, conditionMap);
			pager.setData(data);
			return "receive2";
		} catch (Exception e) {
			throw new RuntimeException(core.toJsonStrO88(e));
		}
	}
	
	/**
	 * 跳转到xls下载页面
	 */
	public String showXls() {
		//获取所有数据
		dataList = getAllReceiveData();
		return "showXls";
	}
	
	/**
	 * 跳转到txt下载页面
	 */
	public String showTxt() {
		//获取所有数据
		dataList = getAllReceiveData();
		return "showTxt";
	}
	
	public List<GvProgramme> getAllReceiveData(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Object> conditionMap = new HashMap<String, Object>();  
			if(progarmmeId!=null){
				conditionMap.put("progarmmeId", progarmmeId);
			}  
			if(Utils.isNotEmpty(del)){
				conditionMap.put("del", del);
			}
			if(Utils.isNotEmpty(groupid)){
				conditionMap.put("groupid", groupid);
			} 
			if(Utils.isNotEmpty(startTime)){
				conditionMap.put("startTime", startTime.trim());
			}
			if(Utils.isNotEmpty(overTime)){
				conditionMap.put("overTime", overTime.trim());
			}
			List<GvProgramme> data = programmeService.getAllProgrammeReceiveList(conditionMap);
			return data;
		} catch (Exception e) {
			throw new RuntimeException(core.toJsonStrO88(e));
		}
	}
	
	/**
	 * @author leo
	 * 查询节目单门店是否收到xls导出
	 * @return 
	 */
	public String xlsExportProgrammeList(List<GvProgramme> list,OutputStream os){ 
		try {
			//查询出数据集合
			//数据导出
			//第一步，创建一个webbook，对应一个Excel文件   
			HSSFWorkbook wb = new HSSFWorkbook();   
			//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
			HSSFSheet sheet = wb.createSheet();
			wb.setSheetName(0,"节目单接收统计列表");
			//第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short    
			HSSFRow row = sheet.createRow((int)0);    
			//设置行高
			row.setHeight((short)500);
			//合并单元格
			sheet.addMergedRegion(new Region(0, (short)0, 0, (short)7));
			//设置时间显示列、名称列的宽度
			sheet.setColumnWidth(0, 1500);
			sheet.setColumnWidth(1, 2500);
			sheet.setColumnWidth(2, 7000);
			sheet.setColumnWidth(3, 6000);
			sheet.setColumnWidth(4, 2000);
			sheet.setColumnWidth(5, 5000);
			sheet.setColumnWidth(6, 3000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 3000);
			//第四步，创建单元格，并设置值表头  设置表头居中      
			HSSFCellStyle style = wb.createCellStyle();     
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中     
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					
			HSSFCellStyle style1 = wb.createCellStyle();     
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中     
			style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			//加粗、字体大小
			HSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short)20); //字号 
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//加粗
			style1.setFont(font); 
				
			HSSFCell cell;
			cell = row.createCell((short)0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("节目单接收统计列表");
			cell.setCellStyle(style1);   
			
			row = sheet.createRow((int)1);
			cell = row.createCell((short)0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("序号");
			cell.setCellStyle(style);  
			cell = row.createCell((short)1);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("节目单id");
			cell.setCellStyle(style);  
			cell = row.createCell((short)2);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("节目单描述");
			cell.setCellStyle(style);  
			cell = row.createCell((short)3);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("发布时间");
			cell.setCellStyle(style);
			cell = row.createCell((short)4);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("门店id");
			cell.setCellStyle(style);  
			cell = row.createCell((short)5);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("门店");
			cell.setCellStyle(style);  
			cell = row.createCell((short)6);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("接收设备id");
			cell.setCellStyle(style);  
			cell = row.createCell((short)7);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("接收设备描述");
			cell.setCellStyle(style);
			cell = row.createCell((short)8);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("是否接收");
			cell.setCellStyle(style);  
			//gp.progarmme_id,gp.describe,gp.create_time,g.groupid,g.groupname,e.equipment_id,e.mome,c.del
			//第五步，写入实体数据 实际应用中这些数据从数据库得到，    
				for(int i=0;i<list.size();i++){        
					row = sheet.createRow((int)i+2);  
					GvProgramme t =  list.get(i);      
					//第四步，创建单元格，并设置值  
					cell = row.createCell((short)0);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(i+1);
					cell.setCellStyle(style); 
					
					cell = row.createCell((short)1);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(t.getProgarmmeId());
					cell.setCellStyle(style);
					
					cell = row.createCell((short)2);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(t.getDescribe());
					cell.setCellStyle(style);
					
					DateFormat fomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					cell = row.createCell((short)3);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(sdf.format(t.getCreateTime()));
					cell.setCellStyle(style);
					
					cell = row.createCell((short)4);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(t.getGroupid());
					cell.setCellStyle(style);
					
					cell = row.createCell((short)5);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(t.getGroupname());
					cell.setCellStyle(style);
					
					cell = row.createCell((short)6);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(t.getEquipmentid());
					cell.setCellStyle(style);
					
					cell = row.createCell((short)7);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(t.getMome());
					cell.setCellStyle(style);
					
					cell = row.createCell((short)8);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					if(t.getDel().toString().equals("1")){
						cell.setCellValue("已接收");
					}else{
						cell.setCellValue("未接收");
					}
					cell.setCellStyle(style);
					
				}
				wb.write(os);
				os.flush();
			}catch (Exception e) {     
				e.printStackTrace();    
				return "false";
			}    
			return "true";
	}
	
	/**
	 * @author leo
	 * 查询节目单门店是否收到txt导出
	 * @return 
	 */
	public String txtExportProgrammeList(List<GvProgramme> list,OutputStream os){ 
		BufferedOutputStream buff = new BufferedOutputStream(os); 
		StringBuffer write = new StringBuffer();  
		try{
			//写数据
			write.append("序号,节目单id,节目单描述,发布时间,门店id,门店,接收设备id,接收设备描述,是否接收\r\n");  
			for (int i = 0; i < list.size(); i++) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				write.append((i+1)+",");
				write.append(list.get(i).getProgarmmeId()+",");
				write.append(list.get(i).getDescribe()+",");
				write.append(sdf.format(list.get(i).getCreateTime())+",");
				write.append(list.get(i).getGroupid()+",");
				write.append(list.get(i).getGroupname()+",");
				write.append(list.get(i).getEquipmentid()+",");
				write.append(list.get(i).getMome()+",");
				if(list.get(i).getDel().toString().equals("1")){
					write.append("已接收\r\n");
				}else{
					write.append("未接收\r\n");
				}
			}
			buff.write(write.toString().getBytes("utf-8"));   
			buff.flush();   
			buff.close();   
		} catch (Exception e) {   
			e.printStackTrace();   
		} finally {   
			try {   
				buff.close();  
				os.close();   
			} catch (Exception e) {   
				e.printStackTrace();   
			}   
		}   
		return "true";
	}


	public Pager<GvProgramme> getPager() {
		return pager;
	}


	public void setPager(Pager<GvProgramme> pager) {
		this.pager = pager;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public List getDataList() {
		return dataList;
	}


	public void setDataList(List dataList) {
		this.dataList = dataList;
	}


	public Integer getProgarmmeId() {
		return progarmmeId;
	}


	public void setProgarmmeId(Integer progarmmeId) {
		this.progarmmeId = progarmmeId;
	}


	public String getDescribe() {
		return describe;
	}


	public void setDescribe(String describe) {
		this.describe = describe;
	}


	public String getScreen() {
		return screen;
	}


	public void setScreen(String screen) {
		this.screen = screen;
	}


	public Double getTotalDuration() {
		return totalDuration;
	}


	public void setTotalDuration(Double totalDuration) {
		this.totalDuration = totalDuration;
	}


	public Integer getMaterialNumber() {
		return materialNumber;
	}


	public void setMaterialNumber(Integer materialNumber) {
		this.materialNumber = materialNumber;
	}


	public Integer getCreateMan() {
		return createMan;
	}


	public void setCreateMan(Integer createMan) {
		this.createMan = createMan;
	}


	public String getDeleteList() {
		return deleteList;
	}


	public void setDeleteList(String deleteList) {
		this.deleteList = deleteList;
	}


	public GvProgramme getProgramme() {
		return programme;
	}


	public void setProgramme(GvProgramme programme) {
		this.programme = programme;
	}


	public IProgrammeService getProgrammeService() {
		return programmeService;
	}


	public void setProgrammeService(IProgrammeService programmeService) {
		this.programmeService = programmeService;
	}


	public Map<String, Object> getDataMap() {
		return dataMap;
	}


	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getSelIdStr() {
		return selIdStr;
	}

	public void setSelIdStr(String selIdStr) {
		this.selIdStr = selIdStr;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getCreateManName() {
		return createManName;
	}

	public void setCreateManName(String createManName) {
		this.createManName = createManName;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getMaterialIds() {
		return materialIds;
	}

	public void setMaterialIds(String materialIds) {
		this.materialIds = materialIds;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getPlaytime_end() {
		return playtime_end;
	}

	public void setPlaytime_end(String playtime_end) {
		this.playtime_end = playtime_end;
	}

	public String getPlaytime_start() {
		return playtime_start;
	}

	public void setPlaytime_start(String playtime_start) {
		this.playtime_start = playtime_start;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#setReviewObj4List()
	 */
	@Override
	public void setReviewObj4List(List<GvProgramme> list_sub) {
		//ob2 set reviewStat
				CollX.each_safe(list_sub, new Closure<GvProgramme,Object> () {

					@Override
					public Object execute(GvProgramme mtrl) throws Exception {
					//	 GvMaterial mtrl=(GvMaterial) arg0;
						 ReviewX rx=IocX.getBean(ReviewX.class);
							Criteria c= rx.px.getSession().createCriteria(Review.class);
							c.add(Restrictions.eq("targetid",  mtrl.getProgarmmeId() ));
							c.add(Restrictions.eq("targettype", "prgrm"));
							Review r=	(Review) c.uniqueResult();
							if(r==null)
								r=new Review();
							mtrl.setRevi(r);
						return null;
					}
				});
		
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#convertObj2Targetobj(java.lang.Object)
	 */
	@Override
	public TargetObj convertObj2Targetobj(ReviewHistry revwHistorItem) {
//		GvMaterialSvs mtc = IocX.getBean(GvMaterialSvs.class);
//		PX px = IocX.getBean(PX.class);
//		GvMaterial mt = (GvMaterial) px.findById(
//				GvMaterial.class,
//				Integer.parseInt(m.get("targetid")));
		IProgrammeService prgc=SpringUtil.getBean(IProgrammeService.class);
		GvProgramme prog=prgc.getProgramme(revwHistorItem.getTargetid());
		TargetObj to = new TargetObj();
		to.setId(prog.getProgarmmeId());
		to.setName(prog.getDescribe());
		return to;
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#reviStateFiltedIds()
	 */
 

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ProgrammeAction.logger = logger;
	}

	 
	
//	public ProgrammeAction()
//	{
//		
//	}
	PX px;

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#reviStateFiltedIds(java.lang.Object)
	 */
	@Override
	public String reviStateFiltedIds(Object p) throws NoFiltEx {
		Map m = (Map) p;
		String[] stat_a=(String[]) m.get("revistat");
		String sql = filex.read(pathx.classPath()
				+ "\\com\\focusx\\programme\\action\\revifilt_prgrm.sql");
		if (stat_a[0].equals("-1"))
			throw new NoFiltEx("");
		if (stat_a[0].equals("2"))
			sql = sql.replaceAll("@where", "   and r.state is null ");
		//normal
		sql = sql.replaceAll("@where", "     and r.state="+stat_a[0]);
		List li = this.px.findBySql(sql);
		if (li.size() == 0)
			return "888898";
		String ids = CollX.reduceO6(li, "88888", new Ireduce<Map, String>() {

			@Override
			public String $(Map o, String lastRetOBj) {

				return lastRetOBj + "," + o.get("progarmme_id");
			}
		});
		return ids;
	}

	/* (non-Javadoc)  	pageParamsMp.put("revistat",ReviewState.Passed);
	 * @see com.attilax.review.IReview#setReviewPassedPropFilter(java.lang.Object)
	 */
	@Override
	public Object setReviewPassedPropFilter(Object p) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#hasReviewAuth(java.lang.Object)
	 */
	@Override
	public boolean hasReviewAuth(Object p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#reviewPassedCheck(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean reviewPassedCheck(Object obj, String dataType)
			throws AlreadyReviewPassedEx, ReviewBackEx {
		// TODO Auto-generated method stub
		return false;
	}
}
