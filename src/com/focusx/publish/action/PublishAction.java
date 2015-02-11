package com.focusx.publish.action;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.StrutsUtil;
import org.apache.tools.ant.taskdefs.condition.IsLastModified;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.TransactionException;
import org.springframework.transaction.TransactionSystemException;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.anno.DefVal;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.collection.CollX;
import com.attilax.dsm.BaseSvs;
import com.attilax.dsm.BaseSvs4cri_oa7;
import com.attilax.dsm.BaseSvsO9o4Cri;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.net.cookieUtil;
import com.attilax.office.excelUtil;
import com.attilax.persistence.PX;
import com.attilax.spri.SpringUtil;
import com.attilax.sso.loginOverTimeEx;
import com.attilax.time.timeUtil;
import com.attilax.util.DateUtil;
import com.attilax.util.StrutsX;
import com.attilax.util.god;
import com.focusx.auth.authx;
import com.focusx.entity.TMbGroup;
import com.focusx.entity.TUserUsers;
import com.focusx.eq.CantFindAnyDeviceEx;
import com.focusx.eq.EqX;
import com.focusx.pager.PageUtil;
import com.focusx.pager.Pager;
import com.focusx.playRec.GvPlayRecord4waterQuery;
import com.focusx.pojo.Equipment;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.service.IProgrammeService;
import com.focusx.publish.dao.Impl.PublishDaoImpl;
import com.focusx.publish.entity.GvPublish;
import com.focusx.publish.entity.GvPublish4Checkover;
import com.focusx.publish.service.IPublishService;
import com.focusx.service.IEquipmentService;
import com.focusx.util.Constant;
import com.focusx.util.DateConverter;
import com.focusx.util.MyCacher;
import com.focusx.util.OperLogUtil4vod;
import com.focusx.util.TxtUtil;
import com.focusx.util.Utils;
import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;

/**
 *  com.focusx.publish.action.PublishAction
 * @author  attilax 老哇的爪子
 *@since  o01 0_48_w$
 */
@RemoteProxy(name = "pubX")
public class PublishAction {
	
	/**
	 * @author ASIMO
	 *
	 */
	private final class OrgFilter extends PropFilter {
		@Override
		public Object getExp() {
			try {
				HttpServletRequest request = ServletActionContext
						.getRequest();
			  String eqIds=EqX.FindByLoginUid(request);			
			
				 return " and p.equipment_id in (@ids) ".replaceAll("@ids", eqIds);
				
			}   catch (isSuperAdminEx e) {
				return "  ";
			} catch (CantFindAnyDeviceEx e) {
				 
				return "   and p.equipment_id=8888  ";
			}
			
		}
	}

	private static final long serialVersionUID = 1L;
	
	protected static Logger logger = Logger.getLogger(PublishAction.class);

	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	//数据字典服务对象
	private IPublishService  publishService;
	//节目单列表
	private IProgrammeService programmeService;
	//分页对象
	private Pager pager; 
	//返回数据对象
	private boolean status;
	//返回数据列表
	private List dataList;
	//返回数据集
	private Map<String,Object> dataMap; //返回数据集
	//数据字典对象
	private GvPublish publish;
	
    //查询条件属性 
	private Integer publishId;//发布ID
	
	private Integer equipmentId;//设备
	
	private String deleteList;//删除字符串列表
	
	/**
	 * dept ids
	 */
	private String equipmentIds;//组织架构ids
	
	private String beginTime;//发布开始时间
	
	private String endTime;//发布结束时间
	
	private Integer interruptedTimeType;//插播 时间类型1：表示现在,2：表示预设
	
	private String mome;//描述

	private Integer publishType;//发布类型
	/**
	 * 
	 */
	private Integer publishStatus;//原节目执行状态
	/**
	 * 发布人名字
	 */
	private String publishManName;
	/**
	 * 设备名称
	 */
	private String equipmentName;
	/**
	 * 节目单
	 */
	private String progarmmeName;
	
	/**
	 * 发布来源
	 */
	private String publishSource;
	

	 
	public PublishAction(){
		dataMap = new HashMap<String,Object>();
		dataList = new ArrayList(); 
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:获取数据列表(分页)
	  *  
	  * @CreateTime: 2014-7-14 下午03:03:52
	  *
	  * @return
	 */
	public String queryPublishList(){ 
		
		try {
			final HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Object> conditionMap = new HashMap<String, Object>();   
			if(Utils.isNotEmpty(mome)){
				conditionMap.put("mome", mome.trim());
			} 
			if(Utils.isNotEmpty(equipmentName)){
				conditionMap.put("equipmentName", equipmentName.trim());
			}
			if(publishType!=null){
				conditionMap.put("publishType", publishType);
			}
			if(publishStatus!=null){
				conditionMap.put("publishStatus", publishStatus);
			}
			if(Utils.isNotEmpty(publishManName)){
				conditionMap.put("publishManName", publishManName.trim());
			}
			if(Utils.isNotEmpty(progarmmeName)){
				conditionMap.put("progarmmeName", progarmmeName.trim());
			}
			if(Utils.isNotEmpty(beginTime)){
				conditionMap.put("beginTime", beginTime.trim());
			}
			if(Utils.isNotEmpty(endTime)){
				conditionMap.put("endTime", endTime.trim());
			}

			pager = new Pager<GvPublish>();
			PageUtil.init(pager, request);
			//oaf org filter
		  	PublishDaoImpl.proFltr.set(new OrgFilter());
			List<GvPublish> data = publishService.getPublishList(pager, conditionMap);
			grpX.setProvNsubBrach(data, new Closure<Object, TMbGroup>() {

				@Override
				public TMbGroup execute(Object arg0) throws Exception {
					GvPublish item=(GvPublish) arg0;
					IEquipmentService eqx=	   SpringUtil.getBean(IEquipmentService.class)	;
					Equipment eq=eqx.getEquipment(item.getEquipmentId());
					item.setEq(eq);
					item.setEqx(eq);
					return  item.getEq().getDpt();
				}
			});
			pager.setData(data); 
			return "list";
		} catch (Exception e) {
			filex.saveLog(e, "c:\\e");
			return null;
		}
		
	}

	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:获取数据列表(分页)
	  *  
	  * @CreateTime: 2014-7-14 下午03:03:52
	  *
	  * @return
	 */
	public String queryProgrammeList(){ 
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionMap = new HashMap<String, Object>();    
		pager = new Pager();
		PageUtil.init(pager, request);
		List data = programmeService.getProgrammeList(pager, conditionMap);
		pager.setData(data); 
		return "listProgramme";
	}
	/**
	 *   "equipmentIds": "2756,",
  "page_page": "1",
  "rdoPublishType": "1",
  "publish.endTime": "2014-11-28 09:20:02",
  "publish.startTime": "2014-11-27 09:15:59",
		@author attilax 老哇的爪子
		@since   oaq 10_3_44
	 */
	public static void main(String[] args) {
		//{startTime=2014-12-02 14:44:48, equipmentId=1, endTime=2014-12-03 15:00:54}
		Map<String,String> m =Mapx.<String,String>$().add("equipmentIds", "2756,").add("publish.startTime", "2014-12-02 14:44:48").add("publish.endTime", "2014-12-03 15:00:54").toMap();
		PublishAction pa=IocX.getBean(PublishAction.class);
		System.out.println(  pa. checkOverwrite_onlyquery( m).size());
		 System.out.println("=====");
	}
	
	/**
	 * is check pass???
	 * equipmentIds=2751%2C&publishSource=2&publish.progarmmeName=%E5%9B%BE%E7%89%87111&
	 * publish.progarmmeId=22&publish.publishType=1&rdoPublishType=1&
	 * publish.status=1&interruptedTimeType=1&rdoInterruptedTimeType=1&
	 * publish.interruptedStartTime=&
	 * publish.startTime=2014-11-01+15%3A38%3A00&
	 * publish.endTime=2014-11-25+15%3A40%3A00&
	 * time1=15%3A38%3A00&
	 * time2=15%3A40%3A00&publish.mome=
		@author attilax 老哇的爪子
		@since   oao d_52_50
	 */
	@SuppressWarnings("all")
	@RemoteMethod public Map checkOverwrite(Map<String, String> m)

	{		// Struts
		try {
			List li=checkOverwrite_onlyquery(m);
			List li1=checkOverwrite_checkOverdue(m);
			
			if (li.size()==0 && li1.size()==0) return Mapx.$().add("checkPass", true).toMap();
			else {
				HttpServletRequest request = ServletActionContext.getRequest();
				request.getSession().setAttribute("wait2confirmDataDetailList", (li));
				request.getSession().setAttribute("wait2confirmDataDetailList1", (li1));
				return Mapx.$().add("checkPass", false).add("data", "wait2confirmDataDetailList").add("data1", "wait2confirmDataDetailList1").toMap(); // def
			}
		} catch (Exception e) {
			filex.saveLog(e, "c:\\e");
			 return null;
		}
		
	}
	@Deprecated
	@RemoteMethod public String checkOverwrite2(Map<String, String> m)

	{		// Struts
		List li=checkOverwrite_onlyquery(m);
		
		if (li.size()==0) return "";
		else {
			 List li2=li.subList(0, 20);
			 return core.toJsonStrO88(li2);
		}
	}
	
	  PX px;
  @SuppressWarnings("all")
  public List checkOverwrite_onlyquery(Map<String,String> m2)
	  
	  {
	  filex.saveLog(core.toJsonStrO88(m2), "c:\\checkPublish");
	  String dptIds=m2.get("equipmentIds");
		String equipmentIds;
		try {
			equipmentIds = EqX.FindByDptIds(dptIds);
		} catch (CantFindAnyDeviceEx e) {
			equipmentIds="888";
			 
		}
		Map m=new HashMap ();
		m.put("equipmentId",equipmentIds);
		String startTime=m2.get("startTime");
		if(startTime==null || startTime.isEmpty())
		{
			startTime=m2.get("publish.startTime");
			m.put("startTime", startTime);
		}
			
		String endTime=m2.get("endTime");
		if(endTime==null || endTime.isEmpty())
		{
			endTime=m2.get("publish.endTime");
			m.put("endTime", endTime);	
		}
		 px=IocX.getBean(PX.class);
	String hql = " from  GvPublish4Checkover where  startTime<='@t1' and endTime>='@t2' and  equipmentId in (@e)";
	hql=hql.replaceAll("@t1", m.get("startTime").toString());hql=hql.replaceAll("@t2", m.get("endTime").toString());
	hql=hql.replaceAll("@e", m.get("equipmentId").toString());
	List li=px.findByHql(hql); 	
			//this.basesvs.findByPropertyss(m, GvPublish4Checkover.class);
	  String s=core.toJsonStrO88(li);
	  filex.save_SF(s, "c:\\confirmboxdata\\"+filex.getUUidName()+".txt");
	return li;
//	HttpServletRequest request = ServletActionContext.getRequest();
//	request.getSession().setAttribute("wait2confirmDataDetailList", (li));
//	
//
//	//Struts
//	
//	if( li.size()==0)  return false;
//		
//		return true; //def
	//com.microsoft.sqlserver.jdbc.SQLServerDriver
	  }
  
  //发布节目单中会过期的素材，即素材过期时间小于发布结束时间
  public List checkOverwrite_checkOverdue(Map<String,String> m2){
	  filex.saveLog(core.toJsonStrO88(m2), "c:\\checkPublish");
	  String progarmmeId=m2.get("progarmmeId");  //节目单id 
	  if(progarmmeId==null || progarmmeId.isEmpty()){
		  progarmmeId=m2.get("publish.progarmmeId");
	  }
	  String endTime=m2.get("endTime");       //发布时间段的结束日期
	  if(endTime==null || endTime.isEmpty()){
		  endTime=m2.get("publish.endTime");
	  }
	  px=IocX.getBean(PX.class);
	  List li=px.getOverdue(Integer.parseInt(progarmmeId), endTime); 	
	  String s=core.toJsonStrO88(li);
	  filex.save_SF(s, "c:\\confirmboxdata\\"+filex.getUUidName()+".txt");
	  return li;
  }
  
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:添加发布
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String add() {
		
		PageContext pageContext = null;
		//   GvPublish pub=(GvPublish)pageContext.getAttribute("publish");
		//  DateUtil.date2str_wzTime(pub.getStartTime());
	
		try {
			OperLogUtil4vod.log("发布节目单:"+publish.getMome());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			try {
				
				String rt = add_ori();
				System.out.println("dbg");
				return rt;
				
			} catch (TransactionSystemException e) {
				filex.save_SF(god.getTrace(e), "c:\\pubSaveWarn1\\pubSaveWarn1_" + filex.getUUidName() + ".txt");
				return try2();
			} catch (TransactionException e) {
				filex.save_SF(god.getTrace(e), "c:\\pubSaveWarn1\\pubSaveWarn1_" + filex.getUUidName() + ".txt");
				return try2();
			}

		} catch (Exception e) {

			filex.save_SF(god.getTrace(e), "c:\\pubSaveErr\\pubSaveErr" + filex.getUUidName() + ".txt");
			core.warn(e);
		}
		return "";
	}

	private String try2() {
	
		try {
			return add_ori();
		} catch (TransactionSystemException e2) {
			filex.save_SF(god.getTrace(e2), "c:\\pubSaveWarn2\\pubSaveWarn2_" + filex.getUUidName() + ".txt");

			return add_ori();   //try3

		}
	}
	
	@Inject  @DefVal(value = String.class)
	BaseSvs basesvs=new BaseSvs();

	private String add_ori() {
		
		//oa9
		if(publish!=null)
		{
			if(publish.getStartTime()==null)
				publish.setStartTime(  DateUtil.timestampO9( StrutsX.getReq().getParameter("publish.startTime")) );
			if(publish.getEndTime()==null)
				publish.setEndTime(  DateUtil.timestampO9( StrutsX.getReq().getParameter("publish.endTime")) );
			
			if(equipmentIds.endsWith(",")){
				equipmentIds  = equipmentIds.substring(0,equipmentIds.length()-1);
			} 
		}
		Integer Login_UserID = (Integer) ActionContext.getContext().getSession().get(Constant.Login_UserID);
		publish.setPublishManId(Login_UserID);
		fixPuberIsnull(publish);
		publish.setPublishTime(new Timestamp(new Date().getTime()));
		//发布播放类型publishType=1:普通publishType=2:插播
		Integer publishType = publish.getPublishType();
		if(publishType == Constant.PUBLISHTYPE_SPOTS){
			publish.setInterruptedTimeType(interruptedTimeType);
			 //插播时间类型 1:表示现在,2表示预设
			 if(interruptedTimeType==1){ 
				 //插播时间格式选择现在 插入当前时间
				 SimpleDateFormat df = new SimpleDateFormat(DateConverter.DATETIME_PATTERN);//设置日期格式
				 publish.setInterruptedStartTime(Timestamp.valueOf(df.format(new Date())));
			 }
		}
		status = publishService.insert(publish,equipmentIds);
		//发布成功
		if(status){
			if(ispubAftNotJump())
				return "none";
			//节目单发布
			if("1".equals(publishSource)){
				return queryProgrammeList();
			}else{
				//发布管理发布
				return queryPublishList();
			}
		}else{
			logger.error("发布失败");
		}
	
		return null;
	}
	
		/**
		@author attilax 老哇的爪子
		@since   oac e_58_k
		 
		 */
	private void fixPuberIsnull(GvPublish publish2) {
		// TODO Auto-generated method stub
	//	/vodx/src/com/focusx/action/AdminAction.java
		try {
			if(publish2.getPublishManId()==null)
			{
				Integer uid=   Integer.parseInt(cookieUtil.getCookie("uid", StrutsX.getReq()));
				publish2.setPublishManId(uid);
				//	cookieUtil.add("uid",id.toString(),httpServletResponse);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o08 j_58_46   
	
	 * @return
	 */
	private boolean ispubAftNotJump() {
		// attilax 老哇的爪子  j_58_46   o08 
		return new File("C:\\pubAftNotJump").exists();
		
	}

	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:编辑发布
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String edit(){  
		status = publishService.update(publish);
		return queryPublishList();
	}
	
	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:删除发布
	  *  
	  * @CreateTime: 2014-7-14 下午05:12:48
	  *
	  * @return
	 */
	public String delete(){  
		status = publishService.delete(publishId);
		publishId = null;
		return queryPublishList();
	}
	
	public String batchdelete(){
		String[] strs = deleteList.split(","); 
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < strs.length; i++) {
			ids.add(new Integer(strs[i].trim()));
		} 
		status = publishService.deleteBatch(ids);
		return queryPublishList(); 
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
/*		Integer Login_UserID = (Integer) ActionContext.getContext().getSession().get(Constant.Login_UserID);
		MyCacher myCacher = MyCacher.getInstance();
		myCacher.removeCache(Constant.MATERIAL + Login_UserID);*/
		return "add";
	}

	public IPublishService getPublishService() {
		return publishService;
	}

	public void setPublishService(IPublishService publishService) {
		this.publishService = publishService;
	}

	public Pager<GvPublish> getPager() {
		return pager;
	}

	public void setPager(Pager<GvPublish> pager) {
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

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public GvPublish getPublish() {
		return publish;
	}

	public void setPublish(GvPublish publish) {
		this.publish = publish;
	}

	public Integer getPublishId() {
		return publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}

	public String getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(String deleteList) {
		this.deleteList = deleteList;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(String equipmentIds) {
		this.equipmentIds = equipmentIds;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

 

	public String getMome() {
		return mome;
	}

	public void setMome(String mome) {
		this.mome = mome;
	}

	public Integer getPublishType() {
		return publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

	public String getPublishManName() {
		return publishManName;
	}

	public void setPublishManName(String publishManName) {
		this.publishManName = publishManName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getProgarmmeName() {
		return progarmmeName;
	}

	public void setProgarmmeName(String progarmmeName) {
		this.progarmmeName = progarmmeName;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getPublishSource() {
		return publishSource;
	}

	public void setPublishSource(String publishSource) {
		this.publishSource = publishSource;
	}

	public IProgrammeService getProgrammeService() {
		return programmeService;
	}

	public void setProgrammeService(IProgrammeService programmeService) {
		this.programmeService = programmeService;
	}

	public Integer getInterruptedTimeType() {
		return interruptedTimeType;
	}

	public void setInterruptedTimeType(Integer interruptedTimeType) {
		this.interruptedTimeType = interruptedTimeType;
	}
	
}
