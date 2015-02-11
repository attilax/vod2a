package com.focusx.elmt;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.json.JSONException;
import  com.attilax.lang.ref.None;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.attilax.Closure;
import com.attilax.Closure2;
import com.attilax.FuncSwitch;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.tryX;
import com.attilax.MDA.CantFindConditionalExcept;
import com.attilax.MDA.CantFindConverterExcept;
import com.attilax.MDA.IAdapter;
import com.attilax.MDA.IDefVal;
import com.attilax.MDA.MdaUtil;
import com.attilax.Stream.Mapx;
import com.attilax.anno.Conditional;
//import com.attilax.anno.DataConverte;
import com.attilax.anno.DefVal;
import com.attilax.anno.Validate;
import com.attilax.anno.ValidateSvrside;
import com.attilax.anno.displayType;
import com.attilax.anno.pojo;
import com.attilax.collection.CollX;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.Ireduce;
import com.attilax.collection.listUtil;
//import com.attilax.convert.Converter;
import com.attilax.crud.ListPageUtil;
import com.attilax.designpatter.parterr.ErrorItem;
import com.attilax.designpatter.parterr.PartErrX;
import com.attilax.designpatter.parterr.PartProcessErrEx;
import com.attilax.dsm.BaseSvs;
import com.attilax.dsm.BaseSvsFinal;
import com.attilax.dsm.Dsmx;
import com.attilax.frontchk.IFrontCheck;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.json.JsonUtil4jackjson;
import com.attilax.lang.CantDelDataEX;
import com.attilax.pagging.PagingUtil;
import com.attilax.persistence.PX;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
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
import com.attilax.text.strUtil;
import com.attilax.time.timeUtil;
import com.attilax.util.DateUtil;
import com.focusx.dictionary.service.IGvDictionaryServcie;
import com.focusx.programme.dao.IProgrammeDao;
import com.focusx.programme.entity.GvProgrammeDetail;
import com.focusx.util.OperLogUtil4vod;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
 

/** A data access object (DAO) providing persistence and search support for
 * GvMaterial entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * @see com.focusx.elmt.GvMaterial
 *  com.focusx.elmt.GvMaterialSvs
 * @author MyEclipse Persistence Tools */
@SuppressWarnings("all")
@RemoteProxy(name = "elmtC") public class GvMaterialSvs extends GvMaterialDAO  implements IReview<GvMaterial> {
	private static final Logger log = LoggerFactory.getLogger(GvMaterialSvs.class);
	// property constants
	public static final String MATERIAL_DESCRIPTION = "materialDescription";
	public static final String MATERIAL_TYPE = "materialType";
	public static final String CREATE_USER = "createUser";
	public static final String SIZE = "size";
	public static final String FILE_PATH = "filePath";
	public static final String CAN_DOWN_ORG = "canDownOrg";
	public static final String UPDATE_USER = "updateUser";
	public static final String APPLICATION_TYPE = "applicationType";
	
	public static void main(String[] args) throws JSONException {
		// org.directwebremoting.annotations.RemoteProxy
	//	SpringCreator

//		//to8f1();
//		GvMaterialSvs c=new GvMaterialSvs();
//		System.out.println(c.findByPropertyss(new HashMap()).size());
//		System.out.println("---ff");
	}

     public GvMaterialSvs(){
    	 if(this.px==null)
    		 this.px=IocX.getBean(PX.class);
     }
 @RemoteMethod
	public Object fileChangeEvent(String f) {
		Map mp = new HashMap();
		try {
			String f2 = pathx.webAppPath() + "\\" + f;
			f2 = f2.replaceAll(strUtil.slashChar, strUtil.backslashChar);
			File fil = new File(f2);
			Encoder encoder = new Encoder();
			MultimediaInfo m;
			try {
				m = encoder.getInfo(fil);
			} catch (InputFormatException e) {

				e.printStackTrace();
				// throw new RuntimeException(e);
			//	mp.put("msg", "InputFormatException" + e.getMessage());
				return core.toJsonStrO88(e);
			} catch (EncoderException e) {
				// throw new RuntimeException(e);
				//mp.put("msg", "EncoderException" + e.getMessage());
				//return mp;
				return core.toJsonStrO88(e);
			}
			long ls = m.getDuration();// millSecs
			ls = ls / 1000;
			mp.put("Duration", ls);
			return mp;
		} catch (Exception e) {
			return core.toJsonStrO88(e);
		}

	}
	private static void to8f1() {
		Map mp=new HashMap();
		mp.put("materialDescription","你");
		mp.put("materialType",0);
		mp.put("idsCheckVals", "68,67,");
 		mp.put("createTime", "2014-5-6");
//		mp.put("effectieTime", "1990-01-02 03:04:05");
 		mp.put("playtime", "00:02:05");
 		mp.put("materialId", 1);
		
		GvMaterialSvs c=new GvMaterialSvs();
	//	System.out.println(c.findByPropertyss(mp).size());;
		//c.delete_ByIDss(mp);
		
// 	String js=filex.read("c:\\json2.txt");
// 		mp=core.json2map(js);
// 		core.print_wzFmt(mp);
		
  //	c.save_map(mp);
	//	GvMaterial obj = core.Map2obj(mp, elmt);   84, 85, 86 
		Integer[] ids = {};
		Map obj = c.findByIdsReverse_notExist(ids,new HashMap() {
			{
				put("pagesize", 10);
				put("page",2);
			}
		});
		core.print(obj);
		//System.out.println(obj .size());
	//	 System.out.println(c.findByIdsReverse_notExist(ids).size());
	//  	c.findByPropertyss(mp);
	}
	
 
	@Deprecated 
	@RemoteMethod public void save(GvMaterial transientInstance) {
		log.debug("saving GvMaterial instance");
		try {
			this.px.save(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
Class<?> saveObjClass=GvMaterial.class;
	@RemoteMethod public void save_map(Map mp) {
		core.logMap(mp);
		log.debug("saving GvMaterial instance");
		//mp=format_befSave(mp);
		Object elmt = core.newx(saveObjClass);
		// if (obj.getMaterialId() != null) //edit mode
		// elemt=findById((Integer) mp.get("materialId"));
		// todox o7f jeig bg map null >>> 0 (id ,null convert to zero)
		Object obj = core.Map2obj_safe(mp, elmt);
	//	obj.setCreateTime(DateUtil.timestampO7());
	    serverValide(mp,obj);
	    
	    core.logObj(obj);
	    
		exeConvert(mp,obj);
		 core.logObj(obj);
		 filex.save_safe(obj, "c:\\vodDbg\\defltValBefJson.txt");
		setDefVal(obj);
		 filex.save_safe(obj, "c:\\vodDbg\\defltValAftJson.txt");
		
		 core.logObj(obj);
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.merge(obj);
//			if(core.isIdValEmpty(obj))
//				session.save(obj);
//			else {
//				GvMaterial elmt4edt = findById(obj.getMaterialId());
//				GvMaterial obj2 = core.Map2obj(mp, elmt4edt);
//
//				session.update(obj2);
//
//			}
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
		try {
			OperLogUtil4vod.log("保存素材:"+((GvMaterial)obj).getMaterialDescription());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	@author attilax 老哇的爪子
	\t@since  Jul 19, 2014 10:14:43 PM$
	
	 * @param obj
	 * @param obj 
	 */
	private void serverValide(@SuppressWarnings("all") final Map mp, final Object obj) {
		// attilax 老哇的爪子 10:14:43 PM Jul 19, 2014

		refx.eachFld(obj, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 9:39:18 PM Jul 19, 2014

				Field fld = (Field) arg0;
				String fldName = fld.getName();
				ValidateSvrside vs = fld.getAnnotation(ValidateSvrside.class);
				if(vs!=null)
					System.out.println("---hs vs");
				if (refx.isIntType(fld)) {
					core.logObj(obj);
					 
					Object property = mp.get(fldName);
					int v = Integer.parseInt(  property.toString());
							//fieldX.setAccessible(true); Integer.parseInt(fld.get(obj).toString());
					if (vs.min() != -1)
						if (v < vs.min())
						{
							core.setProperty(obj, fldName, null);
							System.out.println(BeanUtils.getProperty(obj, fldName));
							core.logObj(obj);
						}
					if (vs.max() != -1)
						if (v > vs.max())
							BeanUtils.setProperty(obj, fldName, null);
					

				}
				if (refx.isStrType(fld)) {

				}

				return null;

			}
		});

	}

	/**
	 * @author attilax 老哇的爪子 \t@since Jul 19, 2014 9:38:18 PM$
	 * 
	 * @param obj
	 * @param obj 
	 */
	private void exeConvert(final Map mp, final Object obj) {
		// attilax 老哇的爪子  9:38:18 PM   Jul 19, 2014 
		refx.eachFld(obj, new Closure() {
			
			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子  9:39:18 PM   Jul 19, 2014 
				
				 
					Field fld=(Field) arg0;
					String fldName = fld.getName();
					if(fldName.equals("playtime"))
						System.out.println("");
				 
					org.apache.commons.beanutils.Converter cvtImp=  core.getConverter(fld);
					 core.logObj(obj);
					Object propertyVal = mp.get(fldName);
					core.log("==="+propertyVal);
					Object convertEdVal = cvtImp.convert(null,  propertyVal);
					core.setProperty(obj, fldName, convertEdVal);
					return convertEdVal;
				 
				
			}
		});
		 
		
	}
	
	
	
	/**
	@author attilax 老哇的爪子
	\t@since  Jul 19, 2014 8:15:54 PM$
	
	 * @param obj
	 * @return 
	 */
	private Object setDefVal(Object obj) {
		// attilax 老哇的爪子  8:15:54 PM   Jul 19, 2014 
		
		{ 
			Field[] flds =this.saveObjClass.getDeclaredFields();
			for (Field fld : flds) {
				try {
					//note  o8f
					if(BeanUtils.getProperty(obj,  fld.getName())!=null)
						continue;
						//end o8f
					String fldName = fld.getName();
					if(fldName.equals("createTime"))
						System.out.println("");
					DefVal dv=fld.getAnnotation(DefVal.class);
					if(dv==null)continue;
					if(dv!=null)
						System.out.println("--");
					Class<?> dvImpCls=dv.value();
					IDefVal dvimp=(IDefVal) core.newx(dvImpCls);
					Object defVal= dvimp.val("");
					core.setProperty(obj, fldName, defVal);
				} catch (Exception e) {
					 core.log(e);
				}
				
			}
		 }
		return obj; 
		
		
	}
	/**if time is not invaid ,,not  add to target mp..
	@author attilax 老哇的爪子
		@since  o7f ma3$
	
	 * @param mp
	 * @return
	 */
	private Map format_befSave(Map mp) {
		// attilax 老哇的爪子 ma3 o7f

		return CollectionUtils.each_safe_o7(mp, new Closure() {

			@Override public Object execute(Object arg0) throws  Exception {
				 
				// attilax 老哇的爪子 mbm o7f
				Object[] oa = (Object[]) arg0;
				String key = oa[0].toString();
				Object val = oa[1];
				
			 
//				if (key.equals("effectieTime") || (key.equals("failureTime"))) { return DateUtil.toTimeStamp(val, true); }
//
//				if (key.equals("playtime")) {
//					try {
//						return timeUtil.str2secs(val);
//					} catch (ParseException e) {
//						// attilax 老哇的爪子 g54b o7g
//						core.log(e);
//						try {
//							return refx.getFieldDefVal(key, GvMaterial.class);
//
//						} catch (cantFindMatchFieldException e1) {
//							// attilax 老哇的爪子 g56m o7g
//							core.log(e);
//							return 0;
//						}
//
//					}
//				}
//				//
//				if (key.equals("materialType")) {
//					System.out.println("");
//				}

				
////				try{
//				ValidateSvrside vldCfg = refx.getFieldValidateSvrside(key, GvMaterial.class);
//				if (vldCfg != null) {
//					Field fld = refx.getField(key, GvMaterial.class);
//					if (refx.isIntType(fld)) {
//						if (core.toInt(val) < vldCfg.min()) if (vldCfg.valideFailOp_Querymode().equals(valideFailOp.NotAdd2condt)) throw new NotAdd2condtExcept();
//					}
//				}
////				}catch (cantFindMatchFieldException e)
////				{
////					core.log(e);
////				}

				return val;

			}
		});

	}
	@com.google.inject.Inject
	PX px;
	
	@SuppressWarnings("all") @RemoteMethod public void deleteFile_ByID(Integer id) {
		
		if(px==null){
			GvMaterialSvs c =  IocX.getBean(GvMaterialSvs.class);
			px=c.px;
			}
			
			
			
		//	String hql=" from  GvProgrammeDetail where " 
			List li=px.findByProperty(GvProgrammeDetail.class, "materialId", id);
//			if(li.size()>0)
//				throw new CantDelDataEX("有节目单关联数据，不能删除");
			
			GvMaterial obj = findById(id);
			log.debug("deleting GvMaterial instance");
			try {
				delLogicOaL(obj);
				filex.del(pathx.webAppPath()+"\\"+obj.getFilePath());
				
			} catch (Exception e) {
				throw new RuntimeException(  e);
			}

			try {
			//	OperLogUtil4vod.log("删除素材:"+((GvMaterial)obj).getMaterialDescription());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		
	}

	private void delLogicOaL(GvMaterial obj) {
		try {
			obj.setLogicDel(1);
			px.merge(obj);
		} catch (Exception e) {
		 
			}
		try {
			OperLogUtil4vod.log("删除素材:"+((GvMaterial)obj).getMaterialDescription());
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
	}
	IFrontCheck frtchk;
	/**
	 * @throws Exception 
	 * 
	 */
	@SuppressWarnings("all") @RemoteMethod public void delete_ByID(Integer id) throws Exception {
		
		try {
		if(px==null){
		GvMaterialSvs c =  IocX.getBean(GvMaterialSvs.class);
		px=c.px;
		}
		if (frtchk != null) //  default can b use front chkx...only exist chkor ,then use chkor.. todox ob7
			frtchk.execute(id);
	//	if(reviewPassedCheck(id, "mtrl"))
		//	throw new AlreadyReviewPassedEx("此素材已经通过审核");
		
	//	String hql=" from  GvProgrammeDetail where " 
		String sql=filex.read(pathx.classPath()+"\\com\\focusx\\elmt\\queryMtrlReltPrgrmInfo.sql");
		sql=sql.replaceAll("@id", id.toString());
		List li=px.findBySql(sql);
				//px.findByProperty(GvProgrammeDetail.class, "materialId", id);
		if(li.size()>0)
			throw new CantDelDataEX("有节目单关联数据，不能删除");
		
		GvMaterial obj = findById(id);
		log.debug("deleting GvMaterial instance");
		delLogicOaL(obj);
		
	
		//	OperLogUtil4vod.log("删除素材:"+obj.getMaterialDescription());
		}catch(CantDelDataEX e )
		{
			throw new CantDelDataEX("有节目单关联数据，不能删除");
		}
		catch (Exception e) {
			filex.saveLog(e, "c:\\e");
			//if("aa".contains("Windows NT 5.1"))
		}
		
		
	}
	@RemoteMethod public void delete_ByIDss4expMtrl(Map mp) {
		System.out.println(FuncSwitch.getState("pubAftJmpNo"));
		Object ids = mp.get("idsCheckVals");
		ListPageUtil.delete_ByIDs(ids, new Closure() {

			@Override public Object execute(Object arg0) {
				// attilax 老哇的爪子 h57s o7f

				int id = core.toInt(arg0);
				core.log("-- o7f2 del " + String.valueOf(id));
				deleteFile_ByID(id);

				return arg0;

			}
		});
		
		System.out.println();
 	 
	}
	/**
	 * multi del
		@author attilax 老哇的爪子
		@since   ob7 j_38_b
	 */
	@RemoteMethod public String delete_ByIDss(Map mp) {
		Object ids = mp.get("idsCheckVals");
		final PartErrX pex = new PartErrX();
		ListPageUtil.delete_ByIDs(ids, new Closure() {

			@Override public Object execute(Object arg0) {
				// attilax 老哇的爪子 h57s o7f

				try {
					int id = core.toInt(arg0);
					core.log("-- o7f2 del " + String.valueOf(id));
					delete_ByID(id);

					return arg0;
				} catch (Exception e) {
					int id = core.toInt(arg0);
					ErrorItem ei = new ErrorItem();
					ei.id = String.valueOf(id);
					ei.msg = e.getMessage();
					pex.add(ei);
				}
				return arg0;
				

			}
		});
		if (pex.li.size() == 0)
			return "ok";

		
		try {
			throw (new PartProcessErrEx(JsonUtil4jackjson.buildNormalBinder()
					.toJson(pex.li)));
		} catch (PartProcessErrEx e) {
			e.setTypex("PartProcessErrEx");
			return core.toJsonStrO88(e);
		}
	
		
	//	System.out.println();
//		GvMaterial obj = findById(id);
//		log.debug("deleting GvMaterial instance");
//		try {
//
//			Session session = getSession();
//			Transaction tx = session.beginTransaction();
//			session.delete(obj);
//			tx.commit();
//			log.debug("delete successful");
//		} catch (RuntimeException re) {
//			log.error("delete failed", re);
//			throw re;
//		}
	}
	
	public void delete(GvMaterial persistentInstance) {
		log.debug("deleting GvMaterial instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@RemoteMethod public GvMaterial findById(java.lang.Integer id) {
		log.debug("getting GvMaterial instance with id: " + id);
		try {
			GvMaterial instance = (GvMaterial) getSession().get("com.focusx.elmt.GvMaterial", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * already select ....no page
	 * 
	@author attilax 老哇的爪子
		@since  o7u l_57_59$
	
	 * @param ids
	 * @param pageParamsMp
	 * @return
	 */
//	@RemoteMethod public Map findByIds(java.lang.Integer[] ids, final Map pageParamsMp) {
//		log.debug("finding GvMaterial findByIds instance with property: ");// +
//																			// propertyName
//																			// +
//																			// ", value: "
//		final List li = (List) CollectionUtils.each_safe(ids, new Closure() {
//
//			@Override public Object execute(Object arg0) throws Exception {
//				// attilax 老哇的爪子 h572 o7g
//				Object o = findById((Integer) arg0);
//				return o;
//
//			}
//		}); // +
//		return new HashMap() {
//			{
//				put("total", li.size());
//				put("rows", PagingUtil.getList(li, pageParamsMp.get("pagesize"), pageParamsMp.get("page"))); // value);
//
//			}
//
//		};
//
//	}

	@RemoteMethod public List findByIds(java.lang.Integer[] ids) {
		log.debug("finding GvMaterial findByIds instance with property: ");// + propertyName + ", value: " + value);
 
			return (List) CollectionUtils.each_safe(ids, new Closure(){

				@Override public Object execute(Object arg0) throws Exception {
					// attilax 老哇的爪子  h572   o7g 
					Object o=findById((Integer) arg0);
					return o;
					 
					
				}});
 
	}
	
//	@RemoteMethod public List findByIds(java.lang.Integer[] ids, Map pageParamsMp) {
//		log.debug("finding GvMaterial findByIds instance with property: ");// +
//																			// propertyName
//																			// +
//																			// ", value: "
//																			// +
//																			// value);
//
//		return PagingUtil.getList((List) CollectionUtils.each_safe(ids, new Closure() {
//
//			@Override public Object execute(Object arg0) throws Exception {
//				// attilax 老哇的爪子 h572 o7g
//				Object o = findById((Integer) arg0);
//				return o;
//
//			}
//		}), pageParamsMp.get("pagesize"), pageParamsMp.get("page"));
//
//	}
//   0_r_n o81  老哇的爪子  Attilax
	/**
	 * not-select   ini ..yao pageing.
	@author attilax 老哇的爪子
		@since  o7u m_t_p$
	
	 * @param ids
	 * @param pageParamsMp
	 * @return
	 */
	@SuppressWarnings("all") @RemoteMethod public Map findByIdsReverse_notExist(java.lang.Integer[] ids,final Map pageParamsMp) {
		log.debug("finding GvMaterial findByIdsReverse_notExist instance with property: ");// +
				
		final Object pSize = pageParamsMp.get("pagesize");
		pageParamsMp.put("page_page", 1);
		pageParamsMp.put("pagesize",999);
		pageParamsMp.put("revistat",ReviewState.Passed);
		List li=	this.findByPropertyss(pageParamsMp);														// +
																			// ", value: "
																			// +
		final Set st = listUtil.toSet(ids);																// value);
		final List li2= (List) CollectionUtils.filter(  li, new Closure() {

			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 h572 o7g
				GvMaterial o = (GvMaterial) arg0;
				if (st.contains((int) o.getMaterialId())) return true;
				return false;

			}
		});
		
		
		
		// List li3= 
		return new HashMap() {
			{
				put("total", li2.size());
				put("rows", PagingUtil.getList (li2 ,pSize, pageParamsMp.get("page")));
			 

			}

		};

	}
@Deprecated
	@RemoteMethod public List findByIdsReverse_notExist(java.lang.Integer[] ids) {
		log.debug("finding GvMaterial findByIdsReverse_notExist instance with property: ");// +
																			// propertyName
																			// +
																			// ", value: "
																			// +
																			// value);
		final Set st = listUtil.toSet(ids);
		return (List) CollectionUtils.filter(findAll(), new Closure() {

			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 h572 o7g
				GvMaterial o = (GvMaterial) arg0;
				if (st.contains((int) o.getMaterialId())) return true;
				return false;

			}
		});

	}

	public List<GvMaterial> findByExample(GvMaterial instance) {
		log.debug("finding GvMaterial instance by example");
		try {
			List<GvMaterial> results = (List<GvMaterial>) getSession().createCriteria("com.focusx.elmt.GvMaterial").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding GvMaterial instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from GvMaterial as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	BaseSvsFinal queryX=new BaseSvsFinal();
	@RemoteMethod @SuppressWarnings("all")
	 public Map findByPropertys4expireMtrlMana(Map QueryPropertyssMap){
		 filex.saveLog(core.obj2json_wzFmt(QueryPropertyssMap), "c:\\mtrl\\query4expmtrlMana");
		 filex.saveObj((QueryPropertyssMap), "c:\\mtrl\\query4expmtrlMana");
		 if(strUtil.isEmpty( QueryPropertyssMap.get("failureTime_end"))	)			 
				 QueryPropertyssMap.put("failureTime_end", timeUtil.Now_CST());
		List list_sub=queryX.findByPropertyss(QueryPropertyssMap,GvMaterial.class);
		Map mp=new HashMap();
		mp.put("total" ,SafeVal.val( BaseSvsFinal.threadLocal_rowsCount.get(),list_sub.size()));
		mp.put("rows",list_sub);
	//	mp.put("pageSize",10);
		
		return mp;
	}
	
	
	
	@RemoteMethod @SuppressWarnings("all")
	 public Map findByPropertyss_page(Map QueryPropertyssMap){
		 filex.saveLog(core.obj2json_wzFmt(QueryPropertyssMap), "c:\\mtrl\\query4expmtrlMana");
		 filex.saveObj((QueryPropertyssMap), "c:\\mtrl\\query4expmtrlMana");
		List list_sub=findByPropertyss(QueryPropertyssMap);
		Map mp=new HashMap();
		mp.put("total" ,SafeVal.val( threadLocal_rowsCount.get(),list_sub.size()));
		mp.put("rows",list_sub);
	//	mp.put("pageSize",10);
		
		//ob2 set reviewStat
		CollX.each_safe(list_sub, new Closure () {

			@Override
			public Object execute(Object arg0) throws Exception {
				 GvMaterial mtrl=(GvMaterial) arg0;
				 ReviewX rx=IocX.getBean(ReviewX.class);
					Criteria c= rx.px.getSession().createCriteria(Review.class);
					c.add(Restrictions.eq("targetid",  mtrl.getMaterialId()  ));
					c.add(Restrictions.eq("targettype", "mtrl"));
					Review r=	(Review) c.uniqueResult();
				 
				 mtrl.setRevi(r);
				return null;
			}
		});
		
		return mp;
	}
	
	@RemoteMethod @SuppressWarnings("all")
	 public boolean checkMtrlDescExist(Map QueryPropertyssMap){
		
		try{
		String hql=" from GvMaterial where logicDel is null and materialDescription='"+QueryPropertyssMap.get("materialDescription")+"'";
		if(strUtil.isNotEmpty(QueryPropertyssMap.get("materialId")))
			hql =hql+ " and materialId!="+QueryPropertyssMap.get("materialId").toString();
		if(this.px==null)
			this.px=IocX.getBean(PX.class);
		List list_sub=this.px.findByHql(hql);
	 if(list_sub.size()>0)
		 return true;else return false;
		}catch (Exception e) {
			filex.save_SF(core.getTrace(e),"c:\\oab.txt");
			return false;
		}
	}
	
	
	 static final ThreadLocal<Integer> threadLocal_rowsCount = new ThreadLocal<Integer>();
	 
	/** @author attilax 老哇的爪子
	 * @since o7e m3l$
	 *  desc,cate,cate2,crtDt,plytim
	 * @param propertyName
	 * @param value
	 * @return */@RemoteMethod @SuppressWarnings("all") 
	 @Deprecated
	public List findByPropertyss(final Map QueryPropertyssMap) {
		
		 //setRevistateFilt   
		 		try {
						QueryPropertyssMap.put("materialId", reviStateFiltedIds(QueryPropertyssMap));
					} catch (NoFiltEx e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 			catch (Exception e) {
		 				
		 			}
	 
	 
		 
		 System.out.println("");
		 HttpServletRequest request;
		// request.getAttribute(arg0)
		// request.getParameter("").equals(anObject)
		// new GvMaterialSvs().mtr4autoKmplt(request);
		//
		// log.debug("finding GvMaterial instance with property: " +
		// propertyName + ", value: " + value);
		 core. log("---o720--");
		core.logMap(QueryPropertyssMap);
		filex.save_safe( core.toJsonStr(QueryPropertyssMap) , "c:\\json2.txt");
		String propertyName;
		Object value;
		Criteria c = getSession().createCriteria(GvMaterial.class);
		Field[] flds = GvMaterial.class.getDeclaredFields();
		for (Field fld : flds) {
			try {

				String fldName = fld.getName();
				if (fldName.startsWith("playtime")) {
					String s = "";
				}
				if (fldName.startsWith("failureTime")) {
					String s = "dbg";
				}
				if (fldName.startsWith("materialId")) {
					String s = "dbg";
				}
				if (fldName.startsWith("createTime")) {
					String s = "dbg";
				}
				
				if (!includeFldInfoPostMap(QueryPropertyssMap, fldName))
					continue;
				////def except condit   k_y_q o8h  老哇的爪子  Attilax  
				if(filtProcess(fld,QueryPropertyssMap))
					continue;
				if(refx.isIdsType(fld, QueryPropertyssMap))
				{
					c.add( Restrictions.in(fldName, CollX. covertToIntList(QueryPropertyssMap.get(fldName)) ));
					continue;
				}
				Conditional cdt = fld.getAnnotation(Conditional.class);
				if (cdt == null)
					continue;
				
				if (cdt.adptr() != None.class) // cstm mode

				{
					List<Criterion> li = fld2critAdapt(QueryPropertyssMap,  fld,cdt);
					addExpresss(c, li);
					continue;
				}
			
					
				if (cdt.displayType().equals(displayType.single)) {

					if (cdt.adptr() != None.class) // cstm mode

					{
						List li = adptrMOde(QueryPropertyssMap, c, fld, fldName, cdt);
						addExpresss(c, li);
					} else {
						//oal nullval process todox
						 if (Dsmx. isNullval(QueryPropertyssMap.get(fldName), fld)) {
								c.add(Restrictions.isNull(fldName));
								continue;
						}
						
						List<Criterion> exprsLi = getExprs(fldName, cdt.op(), QueryPropertyssMap);
						core.log(String.format("--o7f1: dbg fldinfo: %s---%s ", fldName, QueryPropertyssMap.get(fldName)));
						addExpresss(c, exprsLi);
					}
					// Restrictions.like(fldName, "%" + + "%")
				} else if (cdt.displayType().equals(displayType.rang)) {

					List<Criterion> exprsLi = getExprs(fldName, cdt.op(),
							QueryPropertyssMap);
					core.log(String.format("--o7f1: dbg fldinfo: %s---%s ",
							fldName, QueryPropertyssMap.get(fldName)));
					addExpresss(c, exprsLi);
				} 
				 

			} catch (Exception e) {
				filex.saveLog(e, "c:\\e");
				core.log(e);
			}

			// c.add(Restrictions.eq("aname",name));//eq是等于，gt是大于，lt是小于,or是或
		}
		c.addOrder(Order.desc("materialId"));
		String criteriaStr=c.toString();

		List<GvMaterial> list = c.list();
		threadLocal_rowsCount.set(list.size());
		Object page = QueryPropertyssMap.get("page_page");
		List<GvMaterial> list_sub = PagingUtil.getList(list,
				QueryPropertyssMap.get("pagesize"), page);

		return list_sub;

	}
		/**
		@author attilax 老哇的爪子
		@since   oap b_1_0
		 
		 */
	private List<Criterion> fld2critAdapt(Map queryPropertyssMap, Field fld,
			Conditional cdt) {
		IAdapter adpt = (IAdapter) core.newx(cdt.adptr());


		return adpt.convert(fld, queryPropertyssMap );
	}
	
	
 
	/**
	@author attilax 老哇的爪子
		@since  o9q 8_40_46   
	
	 * @param request
	 */
	public String mtr4autoKmplt(HttpServletRequest request) {
		// attilax 老哇的爪子  8_40_46   o9q 
		List li= findByPropertyss(Mapx.$().add("materialDescription", request.getParameter("key")).toMap());
	//	CollX.keepProp("materialDescription","materialId");
		return core.toJsonStrO88(li);
		
	}

	/**
	 * def filtr  todox o8h should fld>>MapToUiFld>> then judge..
	@author attilax 老哇的爪子
		@since  o8h k_z_n   
	
	 * @param fld
	 * @param queryPropertyssMap 
	 * @return
	 */
	private boolean filtProcess(Field fld, Map queryPropertyssMap) {
		// attilax 老哇的爪子  k_z_n   o8h 
		if(fld.getName().equals("playtime"))
			return false;
		if(fld.getName().equals("createTime"))
			System.out.println("xx");
		if(Dsmx.isNotFilt4condQueryFld(fld))
			return false;
	Object oriVal=queryPropertyssMap.get(fld.getName());
		//	Field fld=refx.getField(fldName, this.saveObjClass);
		if(refx.isIntType(fld))
		{
			
			if(oriVal==null || oriVal.toString().equals("") || oriVal.toString().equals("0") )
				return true;  //jump
		}
		if(refx.isStrType(fld))
		{
			if(oriVal==null || oriVal.toString().equals("") )
				return true;//jump
		}
		if(refx.isTimeType(fld))
		{
			if(oriVal==null || oriVal.toString().equals("")||  oriVal.toString().equals("0") )
				return true;//jump
		}
		return false;
		
	}



	/**
	@author attilax 老哇的爪子
	\t@since  Jul 20, 2014 7:19:04 AM$
	
	 * @param fldName
	 * @param op
	 * @param object
	 * @return
	 * @throws cantFindMatchFieldException 
	 * @throws CantFindConverterExcept 
	 */
	private List<Criterion> getExprs(final String fldName, String op, Map valMap)
		 {
		if(fldName.equals("materialType"))
			System.out.println("");
		if(fldName.equals("playtime"))
			System.out.println("");
		
		// attilax 老哇的爪子  7:19:04 AM   Jul 20, 2014 
		List<Criterion> li=new ArrayList<Criterion>();
		 Object oriVal=		valMap.get(fldName);
		 Object cvtEdVal = oriVal;
		 
		 try {
			if(MdaUtil.isDsplyRange(fldName,this.saveObjClass))
			 {
				if(valMap.get(fldName +"_start").toString().equals(valMap.get(fldName +"_end")))
					return new ArrayList<Criterion>();
				 List<Criterion> fstExprs=fstExprs(fldName,op,valMap);
				 
				 List<Criterion> sekdExprs=sekdExprs(fldName,op,valMap);
				 li.addAll(fstExprs);
				 li.addAll(sekdExprs);
				 return li;
			 }
		} catch (cantFindMatchFieldException e2) {
			//  attilax 老哇的爪子 10:28:01 AM   Jul 20, 2014   
			e2.printStackTrace();
		} catch (CantFindConditionalExcept e) {
			//  attilax 老哇的爪子 10:29:59 AM   Jul 20, 2014   
			e.printStackTrace();
		}
		//--------------------except 
		try {
			Field fld=refx.getField(fldName, this.saveObjClass);
			if(refx.isIntType(fld))
			{
				
				if(oriVal==null || oriVal.toString().equals("") || oriVal.toString().equals("0") )
					return li;  //jump
			}
			if(refx.isStrType(fld))
			{
				if(oriVal==null || oriVal.toString().equals("") )
					return li;//jump
			}
		} catch (cantFindMatchFieldException e1) {
			//  attilax 老哇的爪子 8:00:02 AM   Jul 20, 2014   
			e1.printStackTrace();
			return li;//jump
		}
		
		//------------covert default
		 
			Field fld;
			try {
				fld = refx.getField(fldName, this.saveObjClass);
				if(refx.isIntType(fld))
				{
					cvtEdVal=core.toInt(oriVal);
				}
			} catch (cantFindMatchFieldException e1) {
				//  attilax 老哇的爪子 9:59:29 AM   Jul 20, 2014   
				e1.printStackTrace();
			}
		
		//--------------covert
		{ 
			
			
			org.apache.commons.beanutils.Converter cvt;
			try {
				cvt = getConvert(fldName);
				  cvtEdVal=cvt.convert(null, oriVal);
			} catch (CantFindConverterExcept e) {
				//  attilax 老哇的爪子 7:50:33 AM   Jul 20, 2014   
				e.printStackTrace();
				//cvtEdVal=oriVal;
			} catch (cantFindMatchFieldException e) {
				//  attilax 老哇的爪子 7:50:33 AM   Jul 20, 2014   
				e.printStackTrace();
				//cvtEdVal=oriVal;
			}
			
		 
		
			   if(op.equals(com.attilax.anno.op.like))
				li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%")); 
			else if(op.equals(com.attilax.anno.op.range))
			{
				li.add(Restrictions.gt(fldName,   cvtEdVal )); 
				li.add(Restrictions.lt(fldName,   cvtEdVal )); 
			}
			else
			{
				li.add(  Restrictions.eq(fldName,  cvtEdVal)); 
			}
			return li;
		 } 
		
		
	}
	/**
	@author attilax 老哇的爪子
	\t@since  Jul 20, 2014 10:20:04 AM$
	
	 * @param fldName
	 * @param op
	 * @param valMap
	 * @return
	 */
 



	/**
	@author attilax 老哇的爪子
	\t@since  Jul 20, 2014 10:23:20 AM$
	
	 * @param fldName
	 * @param op
	 * @param valMap
	 * @return
	 */
	private List<Criterion> sekdExprs(String fldName, String op, Map valMap) {
		// attilax 老哇的爪子  10:23:20 AM   Jul 20, 2014 
		
		
		Object oriVal=valMap.get(fldName+"_end");
		List<Criterion> li=new ArrayList<Criterion>();
		Object cvtEdVal=oriVal;
		
				//--------------------except 
				try {
					Field fld=refx.getField(fldName, this.saveObjClass);
					if(refx.isIntType(fld))
					{
						
						if(oriVal==null || oriVal.toString().equals("") || oriVal.toString().equals("0") )
							return li;  //jump
					}
					if(refx.isStrType(fld))
					{
						if(oriVal==null || oriVal.toString().equals("") )
							return li;//jump
					}
				} catch (cantFindMatchFieldException e1) {
					//  attilax 老哇的爪子 8:00:02 AM   Jul 20, 2014   
					e1.printStackTrace();
					return li;//jump
				}
				
				//------------covert default
				 
					Field fld;
				
					try {
						fld = refx.getField(fldName, this.saveObjClass);
						if(refx.isIntType(fld))
						{
							cvtEdVal=core.toInt(oriVal);
						}
					} catch (cantFindMatchFieldException e1) {
						//  attilax 老哇的爪子 9:59:29 AM   Jul 20, 2014   
						e1.printStackTrace();
					}catch (Exception e) {
						e.printStackTrace();
					}
				
				//--------------covert
			 
					
					org.apache.commons.beanutils.Converter cvt;
					try {
						cvt = getConvert(fldName);
						  cvtEdVal=cvt.convert(null, oriVal);
					} catch (CantFindConverterExcept e) {
						//  attilax 老哇的爪子 7:50:33 AM   Jul 20, 2014   
						e.printStackTrace();
						//cvtEdVal=oriVal;
					} catch (cantFindMatchFieldException e) {
						//  attilax 老哇的爪子 7:50:33 AM   Jul 20, 2014   
						e.printStackTrace();
						//cvtEdVal=oriVal;
					}
					
				 
					
					
//					if(op.equals(com.attilax.anno.op.like))
//						li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%")); 
//					else if(op.equals(com.attilax.anno.op.range))
//					{
					//	li.add(Restrictions.gt(fldName,   cvtEdVal )); 
				 	li.add(Restrictions.lt(fldName,   cvtEdVal )); 
//					}
//					else
//					{
//						li.add(  Restrictions.eq(fldName,  cvtEdVal)); 
//					}
					return li;
				 
		
		
	}



	/**
	@author attilax 老哇的爪子
	\t@since  Jul 20, 2014 10:23:16 AM$
	
	 * @param fldName
	 * @param op
	 * @param valMap
	 * @return
	 */
	private List<Criterion> fstExprs(String fldName, String op, Map valMap) {
		// attilax 老哇的爪子  10:23:16 AM   Jul 20, 2014 
		
		Object oriVal=valMap.get(fldName+"_start");
		List<Criterion> li=new ArrayList<Criterion>();
		Object cvtEdVal=oriVal;
		
				//--------------------except 
				try {
					Field fld=refx.getField(fldName, this.saveObjClass);
					if(refx.isIntType(fld))
					{
						
						if(oriVal==null || oriVal.toString().equals("") || oriVal.toString().equals("0") )
							return li;  //jump
					}
					if(refx.isStrType(fld))
					{
						if(oriVal==null || oriVal.toString().equals("") )
							return li;//jump
					}
				} catch (cantFindMatchFieldException e1) {
					//  attilax 老哇的爪子 8:00:02 AM   Jul 20, 2014   
					e1.printStackTrace();
					return li;//jump
				}
				
				//------------covert default
				 //yaos time cant err  magwesyi..
					Field fld;
				
					try {
						fld = refx.getField(fldName, this.saveObjClass);
						if(refx.isIntType(fld))
						{
							cvtEdVal=core.toInt(oriVal);
						}
					} catch (cantFindMatchFieldException e1) {
						//  attilax 老哇的爪子 9:59:29 AM   Jul 20, 2014   
						e1.printStackTrace();
					}catch (Exception e) {
						e.printStackTrace();
					}
				
				//--------------covert
			 
					
					org.apache.commons.beanutils.Converter cvt;
					try {
						cvt = getConvert(fldName);
						  cvtEdVal=cvt.convert(null, oriVal);
					} catch (CantFindConverterExcept e) {
						//  attilax 老哇的爪子 7:50:33 AM   Jul 20, 2014   
						e.printStackTrace();
						//cvtEdVal=oriVal;
					} catch (cantFindMatchFieldException e) {
						//  attilax 老哇的爪子 7:50:33 AM   Jul 20, 2014   
						e.printStackTrace();
						//cvtEdVal=oriVal;
					}
					
				 
					
					
//					if(op.equals(com.attilax.anno.op.like))
//						li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%")); 
//					else if(op.equals(com.attilax.anno.op.range))
//					{
						li.add(Restrictions.gt(fldName,   cvtEdVal )); 
					//	li.add(Restrictions.lt(fldName,   cvtEdVal )); 
//					}
//					else
//					{
//						li.add(  Restrictions.eq(fldName,  cvtEdVal)); 
//					}
					return li;
				 
		
		
	}



	/**
	@author attilax 老哇的爪子
	\t@since  Jul 20, 2014 7:31:58 AM$
	
	 * @param fldName
	 * @return
	 * @throws CantFindConverterExcept 
	 * @throws cantFindMatchFieldException 
	 
	 */
	private org.apache.commons.beanutils.Converter getConvert(String fldName) throws CantFindConverterExcept, cantFindMatchFieldException  {
		// attilax 老哇的爪子  7:31:58 AM   Jul 20, 2014 
		 
			Field fld=refx.getField(fldName, this.saveObjClass);
					//this.saveObjClass.getDeclaredField(fldName);
			
		return	 core.getConverter(fld);
//			com.attilax.anno.Converter cvtAnno=fld.getAnnotation(com.attilax.anno.Converter.class);
//			if(cvtAnno==null)throw new CantFindConverterExcept();
//			Class<?>	cvtCls= cvtAnno.value();
//			Object cvtClsObj=core.newx(cvtCls);
// 		return (org.apache.commons.beanutils.Converter) cvtClsObj;
		 
		 
		
		
	}
	/**
	@author attilax 老哇的爪子
	\t@since  Jul 18, 2014 11:46:49 PM$
	
	 * @param c
	 * @param li
	 */
	private void addExpresss(Criteria c, List li) {
		// attilax 老哇的爪子  11:46:49 PM   Jul 18, 2014 
		 for (Object object : li) {
			SimpleExpression se=(SimpleExpression) object;
			c.add(se);
		}
		 
		
	}
	/**
	@author attilax 老哇的爪子
	\t@since  Jul 18, 2014 11:45:58 PM$
	
	 * @param queryPropertyssMap
	 * @param c
	 * @param fld
	 * @param fldName
	 * @param cdt
	 * @return
	 */
	private List adptrMOde(Map queryPropertyssMap, Criteria c, Field fld, String fldName, Conditional cdt) {
		// attilax 老哇的爪子 11:45:58 PM Jul 18, 2014
		// if(!cdt.adptrMode().equals(""))
		// {

		IAdapter adpt = (IAdapter) core.newx(cdt.adptr());

		// Class<?> adptImpClas=adpt.adptr();
		// com.attilax.MDA.IAdapter iad= (com.attilax.MDA.IAdapter)
		// core.newx(adptImpClas);
		return adpt.convert(fldName, queryPropertyssMap.get(fldName).toString());

		// }
		 
		
	}
	/**
	@author attilax 老哇的爪子
	\t@since  Jul 18, 2014 11:45:33 PM$
	
	 * @param queryPropertyssMap
	 * @param c
	 * @param fld
	 * @param fldName
	 * @param cdt
	 */
 
	private void adptrNullMOde(Map QueryPropertyssMap, Criteria c, Field fld,
 		String fldName, Conditional cdt) {
//		if (cdt.queryType().equals(queryType.rang)) {
//			if (refx.isIntType(fld)) {
//				String startCtrolName = fldName + "_" + cdt.rangStart();
//				String endCtrolName = fldName + "_" + cdt.rangEnd();
//				final Object startValOri = QueryPropertyssMap.get(startCtrolName);
//
//				if (startValOri != null) {
//					Object convertedVal;
//					convertedVal = convertFldVal(fld, startValOri);
//					c.add(Restrictions.ge(fldName, convertedVal));
//				}
//				setEndCause(QueryPropertyssMap, c, fld, fldName, endCtrolName);
//				
//				// if (QueryPropertyssMap.get(endCtrolName) != null)
//				// c.add(Restrictions.le(fldName,
//				// QueryPropertyssMap.get(endCtrolName)));
//			}
//		}
	}
	private void setEndCause(Map QueryPropertyssMap, Criteria c, Field fld, String fldName, String endCtrolName) {
		final Object endValOri = QueryPropertyssMap.get(endCtrolName);
		if (endValOri != null) {
			Object convertedVal_end=	  convertFldVal(fld, endValOri);
			c.add(Restrictions.le(fldName, convertedVal_end));
		}
	}
	private Object convertFldVal(Field fld, final Object startValOri) {
		//   g4459 o7g  老哇的爪子  Attilax
		Object convertedVal = null;
//		DataConverte dct = fld.getAnnotation(DataConverte.class);
//		Class cls = dct.converter();
//		Converter cvt = (Converter) core.newx(cls);
//		  convertedVal = cvt.convert(new Closure() {
//
//			@Override public Object execute(Object arg0) throws Exception {
//				// attilax 老哇的爪子 [m49 o7g
//				return timeUtil.str2secs(startValOri);
//
//			}
//		});
		return convertedVal;
	}

	/**
	@author attilax 老哇的爪子
		@since  o7g Y6p$
	
	 * @param queryPropertyssMap
	 * @param fldName
	 * @return
	 */
	private boolean includeFldInfoPostMap(Map QueryPropertyssMap, String fldName) {
		// attilax 老哇的爪子  Y6p   o7g 
		if(QueryPropertyssMap.get(fldName) == null)
			if(QueryPropertyssMap.get(fldName+"_start")==null)
				if(QueryPropertyssMap.get(fldName+"_end")==null)
					return false;
	return true;
		
	}
	public List<GvMaterial> findByMaterialDescription(Object materialDescription) {
		return findByProperty(MATERIAL_DESCRIPTION, materialDescription);
	}

	public List<GvMaterial> findByMaterialType(Object materialType) {
		return findByProperty(MATERIAL_TYPE, materialType);
	}

	public List<GvMaterial> findByCreateUser(Object createUser) {
		return findByProperty(CREATE_USER, createUser);
	}

	public List<GvMaterial> findBySize(Object size) {
		return findByProperty(SIZE, size);
	}

	public List<GvMaterial> findByFilePath(Object filePath) {
		return findByProperty(FILE_PATH, filePath);
	}

	public List<GvMaterial> findByCanDownOrg(Object canDownOrg) {
		return findByProperty(CAN_DOWN_ORG, canDownOrg);
	}

	public List<GvMaterial> findByUpdateUser(Object updateUser) {
		return findByProperty(UPDATE_USER, updateUser);
	}

	public List<GvMaterial> findByApplicationType(Object applicationType) {
		return findByProperty(APPLICATION_TYPE, applicationType);
	}
	@RemoteMethod public List findAll() {
		log.debug("finding all GvMaterial instances");
		try {
			String queryString = "from GvMaterial order by materialId desc";
			Query queryObject = getSession().createQuery(queryString);
			core.log("--size:" + String.valueOf(queryObject.list()));

			return queryObject.list();
		} catch (RuntimeException re) {
			core.log("---o7e");
			core.log(re);
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 
	@author attilax 老哇的爪子
		@since  o7f g472$
	
	 * @return
	 */
	@RemoteMethod public Map findAllWzPage() {
	
		log.debug("finding all GvMaterial instances");
		try {
			String queryString = "from GvMaterial order by materialId desc";
			Query queryObject = getSession().createQuery(queryString);
			List lst = queryObject.list();
			core.log("--size:" + String.valueOf(lst));
			List<GvMaterial> list_sub=PagingUtil.getList(lst,10,1);
		//	return list_sub;
			Map mp=new HashMap();
			mp.put("total",lst.size());
			mp.put("rows",list_sub);
		//	mp.put("pageSize",10);
			
			return mp;
		} catch (RuntimeException re) {
			core.log("---o7e");
			core.log(re);
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAll(int count, String kewword) {
		log.debug("finding all GvMaterial instances");
		try {
			String queryString = "from GvMaterial";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GvMaterial merge(GvMaterial detachedInstance) {
		log.debug("merging GvMaterial instance");
		try {
			GvMaterial result = (GvMaterial) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GvMaterial instance) {
		log.debug("attaching dirty GvMaterial instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GvMaterial instance) {
		log.debug("attaching clean GvMaterial instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#setReviewObj4List(java.util.List)
	 */
	@Override
	public void setReviewObj4List(List<GvMaterial> data) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#convertObj2Targetobj(com.attilax.review.ReviewHistry)
	 */
	@Override
	public TargetObj convertObj2Targetobj(ReviewHistry bizobj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#reviStateFiltedIds()
	 */
	@Override
	public String reviStateFiltedIds(Object p) throws NoFiltEx {
		
		Map m = (Map) p;
		String sql = filex.read(pathx.classPath()
				+ "\\com\\focusx\\elmt\\revifilt.sql");
		if (m.get("revistat").equals("-1"))
			throw new NoFiltEx("");
		if (m.get("revistat").equals("2"))
			sql = sql.replaceAll("@where", "   and r.state is null ");
		//normal
		sql = sql.replaceAll("@where", "     and r.state="+m.get("revistat"));
		List li = this.px.findBySql(sql);
		if (li.size() == 0)
			return "888898";
		String ids = CollX.reduceO6(li, "88888", new Ireduce<Map, String>() {

			@Override
			public String $(Map o, String lastRetOBj) {

				return lastRetOBj + "," + o.get("material_id");
			}
		});
		return ids;
	}

	/* (non-Javadoc)
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
	 * @see com.attilax.review.IReview#reviewPassedCheck(java.lang.Object)
	 */
 IReview<GvMaterial>  revx=IocX.getBean(ReviewX.class);

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#reviewPassedCheck(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean reviewPassedCheck(Object obj, String dataType)
			throws AlreadyReviewPassedEx, ReviewBackEx {
	    Integer id=(Integer)obj;
	    return (  revx. reviewPassedCheck(id,"mtrl"));
//	  {
//		  throw 
//	  }
//		return false;
	}
	
	
	public    JSONArray getCate()
	{	IGvDictionaryServcie ddc=	SpringUtil.getBean(IGvDictionaryServcie.class);
		  List li=	ddc.getDictionaryList("appcate");
		   JSONArray ja=JSONArray.fromObject(core.toJsonStrO88(li));
		   return ja;
	}
}