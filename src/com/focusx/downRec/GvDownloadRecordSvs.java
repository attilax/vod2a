package com.focusx.downRec;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;

import com.attilax.Closure;
import com.attilax.Idx;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.errEventProcess;
import com.attilax.api.HandlerChain;
import com.attilax.biz.orgGroup.OrgFilter;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.corePkg.RuntimeExceptionAti;
import com.attilax.db.Field;
import com.attilax.dsm.BaseCountSvs4Criteria;
import com.attilax.dsm.BaseSvs;
import com.attilax.dsm.Iformatter;
import com.attilax.dsm.SqlAti4view;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.log.Level;
import com.attilax.log.logRec;
import com.attilax.net.IReqParaConverter;
import com.attilax.noticer.jsonFldSerialIgone;
import com.attilax.pagging.PageX;
import com.attilax.pagging.PagingUtil;
import com.attilax.persistence.PX;
import com.attilax.spri.SpringUtil;
import com.attilax.text.strUtil;
import com.attilax.time.timeUtil;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.elmt.BaseHibernateDAO;
import com.focusx.elmt.GvMaterial;
import com.focusx.entity.TMbGroup;
import com.focusx.eq.CantFindAnyDeviceEx;
import com.focusx.eq.EqX;
 
import com.focusx.playRec.GvPlayRecord;
import com.focusx.playRec.baseDAO;
import com.focusx.playRec.GvPlayRecordSvs.OrgFilter4byArea;
 
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentVeriyf;
import com.focusx.push.pushX;
import com.focusx.service.BranchManagerService;
import com.focusx.service.IEquipmentService;
import com.focusx.util.OperLogUtil4vod;
import com.google.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**    com.focusx.downRec.GvDownloadRecordSvs   A data access object (DAO) providing persistence and search support for
 * GvDownloadRecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * @see com.focusx.downRec.GvDownloadRecord
 * @author MyEclipse Persistence Tools */
@RemoteProxy(name = "downwaterX")
public class GvDownloadRecordSvs extends  BaseSvs {
	private static final Logger log = LoggerFactory.getLogger(GvDownloadRecordSvs.class);
	// property constants
	public static final String DOWNLOAD_FLAG = "downloadFlag";
	public static final String PROGRAMME_ID = "programmeId";
	public static final String MATERIAL_ID = "materialId";
	public static final String EQUIPMENT_ID = "equipmentId";
	public static final String SJ_FILE_NAME = "sjFileName";
	public static ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(1);
	
	public static void main(String[] args) {
		//oaa();
		
	//	oaf();
Runnable command = new Runnable() {
			
			@Override
			public void run() {
				PX px=IocX.getBean(PX.class);
				Query qry=	 px.getSession().createQuery(" from  GvDownloadRecord where delFlag is null order by drId desc");
			qry.setFirstResult(0);
			qry.setMaxResults(1000);
				List<GvDownloadRecord> li=qry.list();
			for (GvDownloadRecord rec :li) {
					//	List<GvDownloadRecord> li2=px.findByHql(hql) 
			}
				
				
			}};
	//	schedulePool.scheduleWithFixedDelay(command,0, 15, TimeUnit.SECONDS);
		core.execMeth_Ays(command, "del_duli_down rec");
	//	test_thred_del_duli_downRec();
		
		
	}

	@Deprecated
	public boolean isExistWaterRec(GvDownloadTask tsk, int trueDownFlag)
	{
		return false;
//		if(AlreadyRecvMsg(tsk))	
//			return true;
//		PX px=IocX.getBean(PX.class);
////		String hql = " from  GvDownloadRecord where  materialId=@m and equipmentId=@e";
////		hql=hql.replaceAll("@m", tsk.getMaterialId().toString()).replaceAll("@e", tsk.getEquipmentId().toString());
//		
//		
//				Query qry=	 px.getSession().createQuery(hql);
//			qry.setFirstResult(0);
//			qry.setMaxResults(10);
//				List<GvDownloadRecord> li=qry.list();
//	//	List li2=(List) px.findByHql(" from  GvDownloadRecord where  taskId="+tsk.getDsId().toString());		
//	 	if(li.size()>0)
//			 return true;
//		 else
//			 return false;
			 
		
	}
		/**
		@author attilax 老哇的爪子
		@since   oao 10_m_x
		 
		 */
	public boolean AlreadyRecvMsg(GvDownloadTask tsk) {
		PX px=IocX.getBean(PX.class);
		String hql = " from  GvDownloadRecord where  taskId=@tid ".replaceAll("@tid", tsk.getDsId().toString());
		Query qry=	 px.getSession().createQuery(hql);
		qry.setFirstResult(0);
		qry.setMaxResults(10);
		if(qry.list().size()>0)
			return true;
		else
			return false;
		
	}

	private static void test_thred_del_duli_downRec() {
		//test thread del duli down rec
		Runnable command = new Runnable() {
			
			@Override
			public void run() {
				PX px=IocX.getBean(PX.class);
				Query qry=	 px.getSession().createQuery(" from  GvDownloadRecord where delFlag is null order by drId desc");
			qry.setFirstResult(0);
			qry.setMaxResults(1000);
				List<GvDownloadRecord> li=qry.list();
			for (GvDownloadRecord rec :li) {
					//	List<GvDownloadRecord> li2=px.findByHql(hql) 
			}
				
				
			}};
	//	schedulePool.scheduleWithFixedDelay(command,0, 15, TimeUnit.SECONDS);
		core.execMeth_Ays(command, "del_duli_down rec");
	}

	private static void oaf() {
		String s=filex.read(pathx.webAppPath()+"\\delFileFeedback.json");
		@SuppressWarnings("all")
		String param=JSONObject.fromObject(s).getString("param");
	GvDownloadRecordSvs gvDownloadRecordSvs =IocX.getBean(GvDownloadRecordSvs.class);
	System.out.println(gvDownloadRecordSvs.delFileFeedback(param));
	}

	private static void oaa() {
		GvDownloadRecordSvs c = IocX.getBean(GvDownloadRecordSvs.class);
		List li = c.findByPropertyss(new HashMap());
		//GvPlayRecord o2=(GvPlayRecord) li.get(0);
		List li2=li.subList(0, 7);
		core.print_wzFmt(li2);
	//	System.out.println(o2.getEq().getDpt().getGroupname());
		System.out.println("===f");
	}
	
	@RemoteMethod @SuppressWarnings("all")  
	public List RmtDdeleteAll(String ids ) {
		Idx.each(ids,new Closure<Integer, Object>() {

			@Override
			public Object execute(Integer arg0) throws Exception {
				RmtDdelete_ByID(arg0);
				return null;
			}
		});
		return null;
	
	}
	
	
	@Inject
	pushX pushX1=new pushX();
	@RemoteMethod @SuppressWarnings("all")  
	public List RmtDdelete_ByID(Integer id ) {
		System.out.println("");
		GvDownloadRecord gvdr=(GvDownloadRecord) this.px.get(GvDownloadRecord.class, id);
		GvMaterial mtrl = gvdr.getMtrl();
		if(mtrl==null)
		{
			throw new RuntimeExceptionAti("mtrl is null。素材关联为空");
		}
		String msgAti = HandlerChain.addActNSecuryINfo(core.toJsonStrO7(mtrl), "rmtDelMtrl");
		filex.save_safe(msgAti, "c:\\rmtdel.txt");
		
		
		String retMsg="";
		try {
			pushX1.push(msgAti, gvdr.getEquipmentId());
			retMsg=pushX1.respMsg;
		} catch (APIConnectionException e) {
			//  attilax 老哇的爪子 6_m_52   o05   
			e.printStackTrace();
			retMsg=core.getTrace(e);
		} catch (APIRequestException e) {
			//  attilax 老哇的爪子 6_m_52   o05   
			e.printStackTrace();
			retMsg=core.getTrace(e);
		}
		
		try {
			logRec o=new logRec();
			 o.setLevel(Level.info);
			 o.setIdx( gvdr.getDrId());
//			 o.setIdx2(tsk.getRltID2());
//			 o.setIdx3(tsk.getRltID3());
			 o.setCreateTime(timeUtil.getTimestamp());
			 o.setLogger("mtrrecv");
			 o.setMsg("rmtDelMtrl id:"+mtrl.getMaterialId().toString()+" ,name:"+mtrl.getMaterialDescription() );
			  o.setMsgA(msgAti);
			  o.setMsg2(retMsg);
			  o.setSend("");
			  o.setThread("rmtDelMtrl");
			  o.setCate("rmtDelMtrl");
		 	//this.dbx.save(o);
				new baseDAO().save(o);
		} catch (Exception e) {
			core.log(e);
		}
		
		
		try {
		 	OperLogUtil4vod.log("远程删除:"+mtrl.getMaterialDescription());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ArrayList();
		
	}
	
	private void log2db4RmtDdelete_ByID(GvDownloadTask tsk) {
		// attilax 老哇的爪子  上午01:49:53   2014-9-2 
		System.out.println("aaaaa");
		try {
			logRec o=new logRec();
			 o.setLevel(Level.info);
			 o.setIdx(tsk.getRltID());
			 o.setIdx2(tsk.getRltID2());
			 o.setIdx3(tsk.getRltID3());
			 o.setCreateTime(timeUtil.getTimestamp());
			 o.setLogger("mtrrecv");
			 o.setMsg(tsk.getSendMsg());
			  o.setMsgA(tsk.getSendMsgA());
			  o.setSend("recv");
			  o.setThread("mtrrecv");
			  o.setCate("mtrRecvBack");
		 	//this.dbx.save(o);
				new baseDAO().save(o);
		} catch (Exception e) {
			core.log(e);
		}
		
		 
	}
	
	PropFilter orgFilter=new OrgFilter();
/** @author attilax 老哇的爪子
 * @since o7e m3l$
 *  desc,cate,cate2,crtDt,plytim
 * @param propertyName
 * @param value
 * @return */
@RemoteMethod @SuppressWarnings("all")  
public List findByPropertyss(Map QueryPropertyssMap ) {
	// core.retry3(closure, errEventProcess, logRootDir)
	System.out.println("");
	 //add org filter
	 QueryPropertyssMap.putAll((Map) orgFilter.getExp())  ;
	return super.findByPropertyss(QueryPropertyssMap, GvDownloadRecord.class);
	
}


@RemoteMethod @SuppressWarnings("all")
public List findByPropertyss_4count(Map QueryPropertyssMap){
	return super.findByPropertyss(QueryPropertyssMap, GvDownloadTask.class);
}

@RemoteMethod @SuppressWarnings("all")
public Map findByPropertyss_page(Map QueryPropertyssMap){
	 
	try{
	List list_sub=findByPropertyss(QueryPropertyssMap);
	grpX.setProvNsubBrach(list_sub, new Closure<Object, TMbGroup>() {

		@Override
		public TMbGroup execute(Object arg0) throws Exception {
			GvDownloadRecord item=(GvDownloadRecord) arg0;
			 
			return  item.getEq().getDpt();
		}
	});
	Map mp=new HashMap();
	mp.put("total" ,SafeVal.val( threadLocal_rowsCount.get(),list_sub.size()));
	mp.put("rows",list_sub);
//	mp.put("pageSize",10);
	
	return mp;
	}catch (Exception e) {
		filex.saveLog(e, "c:\\downwater");
		return PageX.getEmptyPageData_EasyuiFmt();
	}
}

public class OrgFilter4byArea extends PropFilter {
	@Override
	public Object getExp() {
		try {
			HttpServletRequest request = ServletActionContext
					.getRequest();
		  final String eqIds=EqX.FindByLoginUid(request);
		return " and rec.equipment_id in ("+eqIds+" ) "  ;
//			 return  new HashMap (){{
//				 this.put("equipmentId", eqIds);
//			 }};
			
		 
		} catch (isSuperAdminEx e) {
			return   "";
		} catch (CantFindAnyDeviceEx e) {
		 
			return   "  and rec.equipment_id in (8888)  ";
		}
		
	}
}
@Inject
BaseCountSvs4Criteria BaseCountSvs4Criteria1=new BaseCountSvs4Criteria();
//PropFilter orgFilter=new OrgFilter();
@RemoteMethod @SuppressWarnings("all")
public Map findByPropertyss_page_4count(final Map QueryPropertyssMap){
	
	
	return	(Map) core.retry3(new Closure () {

		@Override
		public Object execute(Object arg0) throws Exception {
			 //add org filter
			 QueryPropertyssMap.putAll((Map) orgFilter.getExp())  ;
		//	List list_sub=BaseCountSvs4Criteria1.  findByPropertyss(QueryPropertyssMap,GvDownloadTask.class);
//			grpX.setProvNsubBrach(list_sub, new Closure<Object, TMbGroup>() {
//
//				@Override
//				public TMbGroup execute(Object arg0) throws Exception {
//					GvDownloadRecord item=(GvDownloadRecord) arg0;
//					 
//					return  item.getEq().getDpt();
//				}
//			});
			 String sql=filex.read_SF(pathx.classPath()+"\\com\\focusx\\downtask\\countBystore.sql", "");
			 String condit=getCondit_strFmt(QueryPropertyssMap);
			 //ob8
			 IReqParaConverter<Map,String> cvtr=new IReqParaConverter<Map,String>(){

					@Override
					public String convert(Map map) {
						 if(map.get("downloadStatus").toString().equals("0"))// watit down..
						 {
							return " and rec.download_Status is null and rec.startdownFlag is null   ";
						 }
						 if(map.get("downloadStatus").toString().equals("2"))// downing..
						 {
							 return " and rec.download_Status is null and rec.startdownFlag=1   ";
							 
						 }
						 if(map.get("downloadStatus").toString().equals("1"))// already downed..
						 {
							 return " and rec.trueDownFlag=1   ";
							 
						 }
						 if(map.get("downloadStatus").toString().equals("3"))// already exist no need down..
						 {
							 return " and rec.trueDownFlag=2   ";
							 
						 }
						 if(map.get("downloadStatus").toString().equals("9"))// all
						 {
							 return "";
						 }
//						 if(map.get("").toString().equals("44"))   //fail
//						 {// and rec.download_Status is null and    rec.trueDownFlag=3 
//							 return " ";
//						 }
						return map.get("downloadStatus").toString();
					}};
			condit=condit+	cvtr.convert(QueryPropertyssMap);
			 //end ob8
			 //ob0
			 PropFilter orgFilter=new OrgFilter4byArea();
			 condit=condit+orgFilter.getExp();
			 
			 sql=sql.replaceAll("@where", condit);
				// px.
				 filex.saveLog(sql, "c:\\downcount");
				 List list=px.findBySql(sql);
					Object page = QueryPropertyssMap.get("page_page");
						List  list_sub = PagingUtil.getList(list,
								QueryPropertyssMap.get("pagesize"), page);
 	
		if( fmtr!=null)
							fmtr.fmt(list_sub,QueryPropertyssMap);
			Map mp=new HashMap();
			mp.put("total" ,SafeVal.val( list.size(),list_sub.size()));
			mp.put("rows",list_sub);
//			mp.put("pageSize",10);
			
			return mp;
		}
	}, new errEventProcess(){

		@Override
		public Object execute(Object arg0) throws Exception {
			Map mp=new HashMap();
			mp.put("total",0);
			mp.put("rows",new ArrayList());
			return mp;
		}
		
	}
	//	filex.save_SF(core.getTrace(e), "c:\\downmanaQueryLog\\downmanaQueryE"+filex.getUUidName()+".txt");
	
	,	"c:\\downCountQueryLog");
	
//	String sql=getSqlFromUi(QueryPropertyssMap);
	
}
private Iformatter fmtr=new Iformatter() {
	
	 

	@Override
	public List fmt(List li, Map queryPropertyssMap) {
		 for (Object mo : li) {
			 try {
				 Map m=(Map) mo;
					String rang = strUtil.toStr(queryPropertyssMap.get("downloadCreateTime_start")) +"---"+strUtil.toStr(queryPropertyssMap.get("downloadCreateTime_end"));
				if(rang.length()>3)
					m.put("timRang",rang);
		
			} catch (Exception e) {
				 
			}
						}
			return li;
		}
 
};


@RemoteMethod @SuppressWarnings("all")
public Map findByPropertys4count4bybranch(final Map QueryPropertyssMap){
	
	
	return	(Map) core.retry3(new Closure () {

		@Override
		public Object execute(Object arg0) throws Exception {
			 //add org filter
			 QueryPropertyssMap.putAll((Map) orgFilter.getExp())  ;
			 //C:\workspace\vodx\src\com\focusx\downtask\countByBranch.sql
			 String sql=filex.read_SF(pathx.classPath()+"\\com\\focusx\\downtask\\countByBranch.sql", "");
			 String condit=getCondit_strFmt(QueryPropertyssMap);
			 
				//ob9
			 PropFilter orgFilter=new OrgFilter4byArea();
			 condit=condit+orgFilter.getExp();
			 
			 sql=sql.replaceAll("@where", condit);
				// px.
				 filex.saveLog(sql, "c:\\playrecByArea");
				 List list=px.findBySql(sql);
					Object page = QueryPropertyssMap.get("page_page");
						List  list_sub = PagingUtil.getList(list,
								QueryPropertyssMap.get("pagesize"), page);
		//	List list_sub=BaseCountSvs4Criteria1.  findByPropertyss(QueryPropertyssMap,GvDownloadTask.class);
//			grpX.setProvNsubBrach(list_sub, new Closure<Object, TMbGroup>() {
//
//				@Override
//				public TMbGroup execute(Object arg0) throws Exception {
//					GvDownloadRecord item=(GvDownloadRecord) arg0;
//					 
//					return  item.getEq().getDpt();
//				}
//			});
						
		if( fmtr!=null)
							fmtr.fmt(list_sub,QueryPropertyssMap);
			Map mp=new HashMap();
			mp.put("total" ,SafeVal.val( list.size(),list_sub.size()));
			mp.put("rows",list_sub);
//			mp.put("pageSize",10);
			
			return mp;
		}
	}, new errEventProcess(){

		@Override
		public Object execute(Object arg0) throws Exception {
			Map mp=new HashMap();
			mp.put("total",0);
			mp.put("rows",new ArrayList());
			return mp;
		}
		
	}
	//	filex.save_SF(core.getTrace(e), "c:\\downmanaQueryLog\\downmanaQueryE"+filex.getUUidName()+".txt");
	
	,	"c:\\downCountQueryLog");
	
//	String sql=getSqlFromUi(QueryPropertyssMap);
	
}

		/**
	@author attilax 老哇的爪子
	@since   oar c_57_s
	 
	 */
	protected String getCondit_strFmt(Map queryPropertyssMap) {
		Set<String> keys = queryPropertyssMap.keySet();
		Map<String, Object> MetaDatamp = new HashMap<String, Object>();
		// ini metaTree
		for (String k : keys) {
			try {
				String k2 = k.trim();
				if (k2.startsWith("@meta")) {
					k2 = strUtil.replaceAllMultiSpace(k2);
					String[] a = k2.split(" ");
					String fld = a[1];
					MetaDatamp.put(fld, queryPropertyssMap.get(k));
					continue;
				}
			} catch (Exception e) {
				filex.saveLog(e, "c:\\e");
			}

		}
		String condit = "";
		for (String k2 : keys) {
			try {
				String k = k2.trim();
				if (k.trim().startsWith("@meta")) {
					continue;
				}
				k = k.trim();

				if (strUtil.isNotEmpty(queryPropertyssMap.get(k2))) {
					try {
						String exp = MetaDatamp.get(k).toString();
						condit = condit
								+ exp.replaceAll("@p",
										queryPropertyssMap.get(k2).toString());
					} catch (Exception e) {

					}
				}
			} catch (Exception e) {
				filex.saveLog(e, "c:\\e");
			}

		}

		return condit;
	}

	/**
@author attilax 老哇的爪子
	@since  o9q l_54_48   

 * @param queryPropertyssMap
 * @return
 */
private String getSqlFromUi(Map queryPropertyssMap) {
	// attilax 老哇的爪子  l_54_48   o9q 
	SqlAti4view sql=new SqlAti4view();
	if(queryPropertyssMap.get("groupid")!=null)
	{
		
		Field fld = new Field("groupid");
//		sql.addSelectItem(fld);
//		sql.addGroupByItem(fld);
//		sql.addWhereExp(fld);
		
	}
	
	return null;
	
}

	public void save(GvDownloadRecord transientInstance) {
		log.debug("saving GvDownloadRecord instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GvDownloadRecord persistentInstance) {
		log.debug("deleting GvDownloadRecord instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GvDownloadRecord findById(java.lang.Integer id) {
		log.debug("getting GvDownloadRecord instance with id: " + id);
		try {
			GvDownloadRecord instance = (GvDownloadRecord) getSession().get("com.focusx.downRec.GvDownloadRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GvDownloadRecord> findByExample(GvDownloadRecord instance) {
		log.debug("finding GvDownloadRecord instance by example");
		try {
			List<GvDownloadRecord> results = (List<GvDownloadRecord>) getSession().createCriteria("com.focusx.downRec.GvDownloadRecord").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding GvDownloadRecord instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from GvDownloadRecord as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GvDownloadRecord> findByDownloadFlag(Object downloadFlag) {
		return findByProperty(DOWNLOAD_FLAG, downloadFlag);
	}

	public List<GvDownloadRecord> findByProgrammeId(Object programmeId) {
		return findByProperty(PROGRAMME_ID, programmeId);
	}

	public List<GvDownloadRecord> findByMaterialId(Object materialId) {
		return findByProperty(MATERIAL_ID, materialId);
	}

	public List<GvDownloadRecord> findByEquipmentId(Object equipmentId) {
		return findByProperty(EQUIPMENT_ID, equipmentId);
	}

	public List<GvDownloadRecord> findBySjFileName(Object sjFileName) {
		return findByProperty(SJ_FILE_NAME, sjFileName);
	}

	public List findAll() {
		log.debug("finding all GvDownloadRecord instances");
		try {
			String queryString = "from GvDownloadRecord";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GvDownloadRecord merge(GvDownloadRecord detachedInstance) {
		log.debug("merging GvDownloadRecord instance");
		try {
			GvDownloadRecord result = (GvDownloadRecord) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GvDownloadRecord instance) {
		log.debug("attaching dirty GvDownloadRecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GvDownloadRecord instance) {
		log.debug("attaching clean GvDownloadRecord instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	
		/**
		@author attilax 老哇的爪子
		@since   oa0 e_v_s
		 
		 */
	public Object delFileFeedback(Object arg) {
		JSONObject ja=JSONObject.fromObject(arg);
		String eqID=ja.getString("equipmentId");
		String mtrlId=ja.getString("materialId");
	 
		String hql = " from GvDownloadRecord where  equipmentId= "+eqID+" and materialId="+mtrlId;
		List<GvDownloadRecord> t=	(List) this.px.findByHql(hql );
//	      if(t==null)
//	    	  throw new RuntimeException("cant find rec by dsid:"+String.valueOf(eqID));
	      for (GvDownloadRecord rec : t) {
	    	  rec.setRmt_file_stat(1);
	    	  this.px.merge(rec);
			
	      }
	   //   t.setStartdownFlag(1);
	  //    t.setNoticeBackTim(timeUtil.getTimestamp());
	 //     dbx.merge_syn(t);
	      
	  //    log2db4DownTaskBackfeed4notice(t);
		return  "{\"errcode\":0,\"errmsg\":\"ok\" }";
	
	}
}