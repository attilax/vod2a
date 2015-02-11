package com.focusx.playRec;

import com.attilax.Closure;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.MDA.MdaUtil;
import com.attilax.anno.Conditional;
import com.attilax.anno.displayType;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.listUtil;
import com.attilax.dsm.BaseSqlSvs;
import com.attilax.dsm.BaseSvs;
import com.attilax.dsm.Iformatter;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.office.excelUtil;
import com.attilax.pagging.PageX;
import com.attilax.pagging.PagingUtil;
import com.attilax.persistence.Hbx;
import com.attilax.persistence.HbxX;
import com.attilax.ref.NoThisAnnoEx;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.attilax.sso.loginOverTimeEx;
import com.attilax.text.strUtil;
import com.attilax.util.DwrX;
import com.focusx.downRec.GvDownloadRecord;
import com.focusx.elmt.BaseHibernateDAO;
import com.focusx.elmt.GvMaterial;
import com.focusx.entity.TMbGroup;
import com.focusx.eq.CantFindAnyDeviceEx;
import com.focusx.eq.EqX;
import com.focusx.pojo.Equipment;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.util.JxlUtils;
import com.google.inject.Inject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import  com.attilax.lang.ref.None;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 	* A data access object (DAO) providing persistence and search support for GvPlayRecord entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.focusx.playRec.GvPlayRecord
  * @author MyEclipse Persistence Tools 
 */
@RemoteProxy(name = "playwaterX")
public class GvPlayRecordSvs extends BaseSvs  {
	
	private final class OrgFilter extends PropFilter {
		@Override
		public Object getExp() {
			try {
				HttpServletRequest request = ServletActionContext
						.getRequest();
			  final String eqIds=EqX.FindByLoginUid(request);
			
				 return  new HashMap (){{
					 this.put("equipmentId", eqIds);
				 }};
				
			 
			} catch (isSuperAdminEx e) {
				return   new HashMap ();
			} catch (CantFindAnyDeviceEx e) {
				 return  new HashMap (){{
					 this.put("equipmentId", 8889);
				 }};
			}
			
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
//				 return  new HashMap (){{
//					 this.put("equipmentId", eqIds);
//				 }};
				
			 
			} catch (isSuperAdminEx e) {
				return   "";
			} catch (CantFindAnyDeviceEx e) {
			 
				return   "  and rec.equipment_id in (8888)  ";
			}
			
		}
	}
	
	
	     private static final Logger log = LoggerFactory.getLogger(GvPlayRecordSvs.class);
		//property constants
	public static final String SCREEN = "screen";
	public static final String PUBLISH_TYPE = "publishType";
	public static final String MATERIAL_ID = "materialId";
	public static final String EQUIPMENT_ID = "equipmentId";
	@SuppressWarnings("all")
	public static void main(String[] args)   {
	//	o9f();
		try {
			GvPlayRecordSvs gs = new GvPlayRecordSvs();
	
		List li=	gs.findByPropertyss(new HashMap (){{
				this.put("prId", 8542);
			}});
		System.out.println(core.toJsonStrO88(li));
			//gs.findByPropertyssExp(null, request, response);
		/*	Field fld=	refx.getField("playTime", GvPlayRecord.class);
			Column column=refx. getAnno("playTime", GvPlayRecord.class,Column.class);
			System.out.println(column);*/
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		

 //	GvPlayRecordSvs c = IocX.getBean(GvPlayRecordSvs.class);
 //	core.print_wzFmt(c.findByPropertyss_count_day(new HashMap()));
//		List li = c.findByPropertyss(new HashMap());
//		GvPlayRecord o2=(GvPlayRecord) li.get(0);
//		core.print_wzFmt(li);
//		System.out.println(o2.getEq().getDpt().getGroupname());
		System.out.println("===f");

	}


	private static void o9f() {
		GvPlayRecord o = new GvPlayRecord();
		o.setEquipmentId(10);
		o.setMaterialId(3);
		o.setScreen(4);

		  new baseDAO().save(o);
		String s = filex.read("c:\\playrec.json");
	//	postPlayRec(s);
	}

	
	/**
	@author attilax 老哇的爪子
		@since  o0f 2_s_39   

	 */
	private void exp(HttpServletRequest req,HttpServletResponse response) {
		// attilax 老哇的爪子  2_s_39   o0f 
		
		//excelUtil.toExcel(titles, filds, li, response);
	}

/**
 * jeig b jjjye invok ,hesh findByPropertyss_page first by dwr
 *  @author attilax 老哇的爪子
 * @since o7e m3l$
 *  desc,cate,cate2,crtDt,plytim
 * @param propertyName
 * @param value
 * @return */
@RemoteMethod @SuppressWarnings("all")  
public List findByPropertyss(Map QueryPropertyssMap ) {
 
	return super.findByPropertyss(QueryPropertyssMap, GvPlayRecord4waterQuery.class);
	
}
/**
 * jeig b jjjye invok ,hesh findByPropertyss_page first by dwr
 *  @author attilax 老哇的爪子
 * @since o7e m3l$
 *  desc,cate,cate2,crtDt,plytim
 * @param propertyName
 * @param value
 * @return 
 * @throws IOException */
@RemoteMethod @SuppressWarnings("all")  
public List findByPropertyssExp(Map QueryPropertyssMap){
	return super.findByPropertyss(QueryPropertyssMap, GvPlayRecord4waterQuery.class);
}
PropFilter orgFilter=new OrgFilter();

private Iformatter fmtr=new Iformatter() {
	
 

	@Override
	public List fmt(List li, Map queryPropertyssMap) {
		 for (Object mo : li) {
			 try {
				 Map m=(Map) mo;
					String rang = strUtil.toStr(queryPropertyssMap.get("playTime_start")) +"---"+strUtil.toStr(queryPropertyssMap.get("playTime_end"));
				if(rang.length()>3)
					m.put("playTimeRang",rang);
		
			} catch (Exception e) {
				 
			}
						}
			return li;
		}
 
};
@RemoteMethod @SuppressWarnings("all")
public Map findByPropertyss_page(Map QueryPropertyssMap){
	
	try{
	 super.req=DwrX.getReq();
	 //add org filter
	 QueryPropertyssMap.putAll((Map) orgFilter.getExp())  ;
	List list_sub=findByPropertyss(QueryPropertyssMap);
//	for (Object object : list_sub) {
//		GvPlayRecord4waterQuery wtr=(GvPlayRecord4waterQuery) object;
//		GvProgramme gv=new GvProgramme();
//		gv.setProgarmmeId(1);
//		gv.setDescribe("xxx");
//		wtr.setPrgrm_id(1);
//		wtr.setPrgrm_str("aa");
//		wtr.setPrgrm(gv);
//	}
	grpX.setProvNsubBrach(list_sub, new Closure<Object, TMbGroup>() {

		@Override
		public TMbGroup execute(Object arg0) throws Exception {
			GvPlayRecord4waterQuery item=(GvPlayRecord4waterQuery) arg0;
			 
			return  item.getEq().getDpt();
		}
	});
	Map mp=new HashMap();
	mp.put("total" ,SafeVal.val( threadLocal_rowsCount.get(),list_sub.size()));
	mp.put("rows",list_sub);
//	mp.put("pageSize",10);
	
	return mp;
	}catch(Exception e)
	{
		
		filex.saveLog(e, "c:\\playwater");
		return PageX.getEmptyPageData_EasyuiFmt();
	}
}

@RemoteMethod @SuppressWarnings("all")
/**
 * 4 count only
@author attilax 老哇的爪子
	@since  o9q 4_0_59   

 * @param QueryPropertyssMap
 * @return
 */
public Map findByPropertyss_page_count(Map QueryPropertyssMap){
	if("1".equals("1"))
	{
	//	throw new RuntimeException("tt2");
	}
	 try{
		 //add org filter
		 QueryPropertyssMap.putAll((Map) orgFilter.getExp())  ;
	List list_sub=findByPropertyss_count(QueryPropertyssMap);
	if(this.fmtr!=null)
		fmtr.fmt(list_sub,QueryPropertyssMap);
	grpX.setProvNsubBrach(list_sub, new Closure<Map, TMbGroup>() {

		@Override
		public TMbGroup execute(Map arg0) throws Exception {
		//	GvPlayRecord item=(Map) arg0;
			 
			return ((Equipment) arg0.get("eq")).getDpt();
		}
	});
	Map mp=new HashMap();
	mp.put("total" ,SafeVal.val( BaseSqlSvs.  threadLocal_rowsCount.get(),list_sub.size()));
	mp.put("rows",list_sub);
//	mp.put("pageSize",10);
	
	return mp;
	 }catch (Exception e) {
		filex.save_SF(core.getTrace(e), "c:\\PlayRec\\"+filex.getUUidName()+".txt");
		return PageX.getEmptyPageData_EasyuiFmt();
	}
}
  
@RemoteMethod @SuppressWarnings("all")
/**
 * 4 count only
@author attilax 老哇的爪子
	@since  o9q 4_0_59   

 * @param QueryPropertyssMap
 * @return
 */
public Map findByPropertys_byArea(Map QueryPropertyssMap){
	if("1".equals("1"))
	{
	//	throw new RuntimeException("tt2");
	}
	 try{
		 //add org filter
		 QueryPropertyssMap.putAll((Map) orgFilter.getExp())  ;
		 //C:\workspace\vodx\WebRoot\WEB-INF\classes\com\focusx\playRec\countByArea.sql
		 String sql=filex.read_SF(pathx.classPath()+"\\com\\focusx\\playRec\\countByArea.sql", "");
		 String condit="";
		 //   and rec.download_create_time>'@p'
		 if(strUtil.isNotEmpty(QueryPropertyssMap.get("playTime_start")))
			 condit=condit+" and rec.play_time>'@t1' ".replaceAll("@t1", QueryPropertyssMap.get("playTime_start").toString());
		 if(strUtil.isNotEmpty(QueryPropertyssMap.get("playTime_end")))
			 condit=condit+" and rec.play_time<='@t2' ".replaceAll("@t2", QueryPropertyssMap.get("playTime_end").toString());
		 if(strUtil.isNotEmpty(QueryPropertyssMap.get("materialId")))
			 condit=condit+"   and rec.material_id=@mid ".replaceAll("@mid", QueryPropertyssMap.get("materialId").toString());
		 if(strUtil.isNotEmpty(QueryPropertyssMap.get("groupid")))
			 condit=condit+"    and eq.depart_id in (@grpIds)  ".replaceAll("@grpIds", QueryPropertyssMap.get("groupid").toString());
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
		
	//findByPropertyss_count(QueryPropertyssMap);
	grpX.setSubBrach(list_sub, new Closure<Map, TMbGroup>() {

		@Override
		public TMbGroup execute(Map arg0) throws Exception {
		//	GvPlayRecord item=(Map) arg0;
			 
			return ((Equipment) arg0.get("eq")).getDpt();
		}
	});
	if(this.fmtr!=null)
		fmtr.fmt(list_sub,QueryPropertyssMap);
	Map mp=new HashMap();
	mp.put("total" ,SafeVal.val( list.size(),list_sub.size()));
	mp.put("rows",list_sub);
//	mp.put("pageSize",10);
	
	return mp;
	 }catch (Exception e) {
		filex.save_SF(core.getTrace(e), "c:\\PlayRec\\"+filex.getUUidName()+".txt");
		return PageX.getEmptyPageData_EasyuiFmt();
	}
}
  

  @Inject
  BaseSqlSvs basesqlx=new BaseSqlSvs();
    /**  4 count only
@author attilax 老哇的爪子
	@since  o9p 0_i_53   

 * @param queryPropertyssMap
 * @return
 */
public List findByPropertyss_count(Map queryPropertyssMap) {
	// attilax 老哇的爪子  0_i_53   o9p 
//	if(queryPropertyssMap.get("timeType").equals("d"))
//		return	findByPropertyss_count_day(queryPropertyssMap);
	List list_sub = basesqlx.findByPropertyss(queryPropertyssMap, GvPlayRecord.class);
	
	return list_sub;
	
}

@Inject
Hbx hbx=new HbxX();

	/** jeig zafei le
	@author attilax 老哇的爪子
		@since  o9p 0_v_39   
	
	 * @param queryPropertyssMap
	 * @return
	 */
@Deprecated
	private List findByPropertyss_count_day(Map queryPropertyssMap) {
		// attilax 老哇的爪子  0_v_39   o9p 
		String s="SELECT  CONVERT(varchar(10), play_time ,23 )  as  timeRang,      sum([mtrTimLen])  timlens  FROM  [playcount_day]    group by   CONVERT(varchar(10), play_time ,23 )  ";
		SQLQuery SQLQueryx = hbx.getSession().createSQLQuery(s);
		//SQLQueryx.addEntity(GvPlayRecord.class) ;	
		SQLQueryx.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
	 
//		DetachedCriteria beautyCriteria = DetachedCriteria.forClass(GvPlayRecord.class, "b") ;
//		 
//		beautyCriteria.add(Restrictions.le("b.age", new Long(20)));	
//		beautyCriteria.
		
		return SQLQueryx.list();
		
	}


	public void save(GvPlayRecord transientInstance) {
        log.debug("saving GvPlayRecord instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(GvPlayRecord persistentInstance) {
        log.debug("deleting GvPlayRecord instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public GvPlayRecord findById( java.lang.Integer id) {
        log.debug("getting GvPlayRecord instance with id: " + id);
        try {
            GvPlayRecord instance = (GvPlayRecord) getSession()
                    .get("com.focusx.playRec.GvPlayRecord", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<GvPlayRecord> findByExample(GvPlayRecord instance) {
        log.debug("finding GvPlayRecord instance by example");
        try {
            List<GvPlayRecord> results = (List<GvPlayRecord>) getSession()
                    .createCriteria("com.focusx.playRec.GvPlayRecord")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding GvPlayRecord instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from GvPlayRecord as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    
    
  
	public List<GvPlayRecord> findByScreen(Object screen
	) {
		return findByProperty(SCREEN, screen
		);
	}
	
	public List<GvPlayRecord> findByPublishType(Object publishType
	) {
		return findByProperty(PUBLISH_TYPE, publishType
		);
	}
	
	public List<GvPlayRecord> findByMaterialId(Object materialId
	) {
		return findByProperty(MATERIAL_ID, materialId
		);
	}
	
	public List<GvPlayRecord> findByEquipmentId(Object equipmentId
	) {
		return findByProperty(EQUIPMENT_ID, equipmentId
		);
	}
	

	public List findAll() {
		log.debug("finding all GvPlayRecord instances");
		try {
			String queryString = "from GvPlayRecord";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public GvPlayRecord merge(GvPlayRecord detachedInstance) {
        log.debug("merging GvPlayRecord instance");
        try {
            GvPlayRecord result = (GvPlayRecord) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(GvPlayRecord instance) {
        log.debug("attaching dirty GvPlayRecord instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(GvPlayRecord instance) {
        log.debug("attaching clean GvPlayRecord instance");
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
		@since  o7n j_o_52$
	
	 * @param arg
	 * @return
	 */
	public static Object postPlayRec(Object arg) {
		// attilax 老哇的爪子  j_o_52   o7n 
		try {
			JSONArray ja=JSONArray.fromObject(arg);
			CollectionUtils.each_safe(ja, new Closure() {
				
				@Override public Object execute(Object jo) throws Exception {
					GvPlayRecord obj=new GvPlayRecord();
					// attilax 老哇的爪子  j_v_1   o7n 
					core.Map2obj_safeO7(jo, obj);
					try {
						JSONObject jo2=(JSONObject) jo;
						obj.setPrgrmId(jo2.getInt("prgrmId"));
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						JSONObject jo2=(JSONObject) jo;
						obj.setScreen(jo2.getInt("screen"));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
					new baseDAO().save(obj);
					return null;
				 
					
				}
			});
			 
			
			return "{\"errcode\":0,\"errmsg\":\"ok\" }";
		} catch (Exception e) {
			return "{\"errcode\":1,\"errmsg\":\""+e.getMessage()+"\" }";
			//return core.toJsonStrO88(e);
		}
	
	 
		
	}
	
 
}