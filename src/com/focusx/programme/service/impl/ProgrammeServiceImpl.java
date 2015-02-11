package com.focusx.programme.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ecs.html.A;
import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.attilax.Closure;
import com.attilax.Closure2;
import com.attilax.ClosureNoExcpt;
import com.attilax.core;
import com.attilax.collection.CollX;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.pagging.PagingUtil;
import com.attilax.persistence.PX;
import com.attilax.text.strUtil;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.pager.Pager;
import com.focusx.programme.dao.IProgrammeDao;
import com.focusx.programme.dao.IProgrammeDetailDao;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.entity.GvProgrammeDetail;
import com.focusx.programme.service.IProgrammeService;
import com.focusx.publish.dao.IPublishDao;
import com.focusx.push.spot;
import com.focusx.util.Constant;
import com.focusx.util.MyCacher;
import com.focusx.util.Utils;

public class ProgrammeServiceImpl implements IProgrammeService {

	protected static Logger logger = Logger
			.getLogger(ProgrammeServiceImpl.class);

	protected MyCacher myCacher = MyCacher.getInstance();

	/**
	 * 节目单
	 */
	private IProgrammeDao programmeDao;

	/**
	 * 节目单详情
	 */
	private IProgrammeDetailDao programmeDetailDao;
	
	/**
	 * 发布管理
	 */
	private IPublishDao publishDao;
	
	
	
	/**
	 * 查询节目单门店接收情况所有数据,导出数据
	 */
	public List<GvProgramme> getAllProgrammeReceiveList(
			Map<String, Object> conditionMap) {
		return programmeDao.getAllProgrammeReceiveList(conditionMap);
	}

	/**
	 * 查询节目单门店接收情况
	 */
	public List<GvProgramme> getProgrammeReceiveList(Pager<GvProgramme> pager,
			Map<String, Object> conditionMap) {
		return programmeDao.getProgrammeReceiveList(pager, conditionMap);
	}

	@Override
	public List<GvProgramme> getProgrammeList(Pager<GvProgramme> pager,
			Map conditionMap) {
		// TODO Auto-generated method stub
		return programmeDao.getProgrammeList(pager, conditionMap);
	}

	@Override
	public GvProgramme getProgramme(Integer id) {
		// TODO Auto-generated method stub
		return programmeDao.getProgramme(id);
	}

	@Override
	public boolean insert(GvProgramme programme,
			Map<String, Object> conditionMap) { 
		
		programme.setCreateTime(new Timestamp(new Date().getTime()));
		// 插入节目单
		boolean msg = programmeDao.insert(programme);
		Integer programmeId = programme.getProgarmmeId();
		String materialIds = "";
		if(conditionMap.containsKey("materialIds")){
			materialIds = conditionMap.get("materialIds").toString();
		} 
		List<String> materialIdList = Arrays.asList(materialIds.split(",")); 
		// 插入节目单详情
		if (msg) {
			programmeId = programme.getProgarmmeId();
			msg = insertORupdateGvProgrammeDetail(programmeId,
					materialIdList);
		}
		
		
		return msg;
	}

	@Override
	public boolean update(GvProgramme programme,
			Map<String, Object> conditionMap) {
		Integer programmeId = programme.getProgarmmeId(); 
		//obo del pro detail 
		try {
			String hql = "delete from GvProgrammeDetail d where d.programmeId ="+programmeId.toString();
			 PX px=IocX.getBean(PX.class);
			 px.findByHql(hql);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 更新节目单
		boolean msg = programmeDao.update(programme);
	
		String materialIds = "";
		if(conditionMap.containsKey("materialIds")){
			materialIds = conditionMap.get("materialIds").toString();
		} 
		List<String> materialIdList = new ArrayList<String>();
		if(Utils.isNotEmpty(materialIds)){
			materialIdList = Arrays.asList(materialIds.split(",")); 
		}
		// 更新节目单详情
		if (msg) {
			programmeId = programme.getProgarmmeId();
			msg = insertORupdateGvProgrammeDetail(programmeId,materialIdList);
		}
		return msg;
	}

	@Override
	public String delete(Integer id) {
		// TODO Auto-generated method stub
		String result = Constant.RESULT_SUCCESS;
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(id);
		boolean msg;
	//	msg = publishDao.isExist(ids);
		msg = findPrgrmWhereExpire(ids);
		if(!msg){ 
			msg = programmeDetailDao.deleteByProgrammeId(id);
			logger.info("节目单详情【programmeId=" + id + "】,删除" + msg);
			msg = programmeDao.delete(id);
			logger.info("节目单【programmeId=" + id + "】,删除" + msg);
			if(msg){
				result = Constant.RESULT_SUCCESS;
			}else{
				result = Constant.RESULT_ERROR;
			}
		}else{
			result = Constant.RESULT_NONE;
		}
		return result;
	}

	private Boolean findPrgrmWhereExpire(List<Integer> ids) {
		return new Closure2<List<Integer>, Boolean>() {

			@Override
			public Boolean execute(List<Integer> ids2) {
				String ids_s = CollX.toString(ids2);
				String s = "SELECT TOP 1000 *    FROM [gialweixin].[dbo].[gv_publish]    where progarmme_id in ("
						+ ids_s + ") and end_time>GETDATE()";
				filex.saveLog(s, "c:\\prgrm\\del_findPrgrmWhereExpire");
				PX px = IocX.getBean(PX.class);
				List li = px.findBySql(s);
				if (li.size() > 0)
					return true;
				return false;
			}

		}.execute(ids);
	}

	@Override
	public String deleteBatch(List<Integer> ids) {
		try {
			String result = Constant.RESULT_SUCCESS;
			boolean msg;
			msg = findPrgrmWhereExpire(ids);// publishDao.isExist(ids);
			if (!msg) {
				try {
					programmeDetailDao.deleteBatch(ids);
				} catch (Exception e) {
					filex.saveLog(e, "c:\\prgrm\\deleteBatch");
				}

				logger.info("节目单详情1【programmeIds=" + ids + "】,删除" + msg);
				try {
					msg = programmeDao.deleteBatch(ids);
				} catch (Exception e) {
					filex.saveLog(e, "c:\\prgrm\\deleteBatch");
				}

				logger.info("节目单2【programmeIds=" + ids + "】,删除" + msg);
				if (msg) {
					result = Constant.RESULT_SUCCESS;
				} else {
					result = Constant.RESULT_ERROR;
				}
			} else {
				result = Constant.RESULT_NONE;
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
public static ThreadLocal<Integer> totalRowsCount=new ThreadLocal<Integer>();
	/**
	 * 获取未选择的数素材
	 */
	@Override
	@SuppressWarnings("all")
	public Map getAlternativeData( final Pager pager, final Map<String, Object> conditionMap) {
	try {
		GvMaterialSvs c = new GvMaterialSvs(); 
		Integer[] materials = {};  
		conditionMap.put("pagesize", pager.getPageSize());
		conditionMap.put("page", pager.getCurrentPage());
		Map dataList = new HashMap ();
		if( conditionMap.get("subprgrmSwitch")==null || !conditionMap.get("subprgrmSwitch").equals("open"))
			  dataList = c.findByIdsReverse_notExist(materials, conditionMap);
		if(conditionMap.get("subprgrmSwitch").equals("open"))
		{
			 
			dataList=new HashMap() {
				{
					put("total", totalRowsCount.get());
					//condition convert quwery
					List<GvProgramme> programmeList = programmeDao.getProgrammeList(pager, new ClosureNoExcpt<Object,Map>() {

						@Override
						public Map execute(Object arg0)  {
							//describe
							if(strUtil.isNotEmpty( conditionMap.get("materialDescription")))
							conditionMap.put("describe", conditionMap.get("materialDescription"));
							Object o=conditionMap.get("createTime");
							if(strUtil.isNotEmpty(conditionMap.get("createTime")))
							{
								conditionMap.put("beginTime", conditionMap.get("createTime")+" 00:00:01");
								conditionMap.put("endTime", conditionMap.get("createTime")+" 23:59:59");
							}
							return conditionMap;
						}
					}.execute(null));
					//output convert
					List<Map> li=CollX.each_safe(programmeList, new Closure<GvProgramme, Map>() {

						@Override
						public Map execute(GvProgramme arg0) throws Exception {
							 Map map=new HashMap();
							 map= core.toMap(arg0);
							 map.put("materialDescription",arg0.getDescribe());
							 map.put("playtime", arg0.getTotalDuration());
							 map.put("materialId", arg0.getProgarmmeId());
							 map.put("rectype", "prgrm");
								
							 
							 
							return map;
						}
					});
					put("rows",li);
				 

				}};

					 
		}
			// dataList = c.findByIdsReverse_notExist(materials, conditionMap);
		return dataList;
	} catch (Exception e) {
		core.err(e);
		filex.save_SF( core.toJsonStrO88( (e)),"c:\\00.txt");
		System.out.println("ss");
	}
	throw new RuntimeException("getAlternativeData err");
		
		
	}

	
 
	@Override
	/**
	 * @category for mtrl query
	 */	 
	public List getSelectedData(Map<String, Object> conditionMap) {
		GvMaterialSvs c = new GvMaterialSvs(); 
		Integer[] materials = {}; 
		Integer programmeId; 
		if(conditionMap.containsKey("progarmmeId")){
			programmeId =Integer.valueOf(conditionMap.get("progarmmeId").toString()); 
			List<GvProgrammeDetail> lis = programmeDetailDao.getProgrammeDetail(programmeId);
			List<Integer> lisMaterials = new ArrayList<Integer>();
			for (GvProgrammeDetail gvProgrammeDetail : lis) {
				lisMaterials.add(gvProgrammeDetail.getMaterialId());
			}
			materials = (Integer[]) lisMaterials.toArray(new Integer[lis.size()]);
		}else{
			logger.error("programmeId字段不能为空");
		} 
		List dataList = c.findByIds(materials); 
		return dataList; 
	}

 

	/**
	 * 
	  * Description: 插入或者更新节目详情 数据来源于缓存
	  *  
	  * CreateTime: 2014-7-21 下午3:51:32
	  *
	  * @param programmeId 节目ID
	  * @param Login_UserID 用户id (用于缓存)
	  * @param isUpdate 是否是更新操作
	  * @return
	 */
	private boolean insertORupdateGvProgrammeDetail(
			Integer programmeId, List<String> materialIdList) {
		boolean msg = true; 
		//清除节目单详情
		msg = programmeDetailDao.deleteByProgrammeId(programmeId);
		// 遍历缓存中的数据
		if (Utils.isNotEmpty(materialIdList)) {
			List<GvProgrammeDetail> listDetail = new ArrayList<GvProgrammeDetail>();
			for (int i = 0; i < materialIdList.size(); i++) {
				Integer materialId = new Integer(materialIdList.get(i));
				Integer playOrder = i;
				GvProgrammeDetail gvProgrammeDetail = new GvProgrammeDetail();
				gvProgrammeDetail.setPlayOrder(playOrder);
				gvProgrammeDetail.setMaterialId(materialId);
				gvProgrammeDetail.setProgrammeId(programmeId);
				listDetail.add(gvProgrammeDetail);
			}
			// 插入成功移除缓存
			msg = programmeDetailDao.insertBatch(listDetail); 
		}
		return msg;
	}

	public IProgrammeDetailDao getProgrammeDetailDao() {
		return programmeDetailDao;
	}

	public void setProgrammeDetailDao(IProgrammeDetailDao programmeDetailDao) {
		this.programmeDetailDao = programmeDetailDao;
	}

	public IProgrammeDao getProgrammeDao() {
		return programmeDao;
	}

	public void setProgrammeDao(IProgrammeDao programmeDao) {
		this.programmeDao = programmeDao;
	}

	public IPublishDao getPublishDao() {
		return publishDao;
	}

	public void setPublishDao(IPublishDao publishDao) {
		this.publishDao = publishDao;
	}

 
}
