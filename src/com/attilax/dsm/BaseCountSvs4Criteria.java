/**
 * @author attilax 老哇的爪子
	@since  o9n 4_9_i$
 */
package com.attilax.dsm;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.MDA.CantFindConditionalExcept;
import com.attilax.MDA.CantFindConverterExcept;
import com.attilax.MDA.IAdapter;
import com.attilax.MDA.MdaUtil;
import com.attilax.Stream.Linq;
import com.attilax.Stream.Mapx;
import com.attilax.anno.Conditional;
import com.attilax.anno.Orderby;
import com.attilax.anno.displayType;
import com.attilax.anno.op;
import com.attilax.coll.ListX;
import com.attilax.collection.CollX;
import com.attilax.count.CountRelt;
import com.attilax.count.GroupByDate;
import com.attilax.count.OrderByTimeType;
import com.attilax.count.reduce;
import com.attilax.db.BaseHibernateDAO;
import com.attilax.db.BaseHibernateDAO2;
import com.attilax.dsm.adapt.DateAdptr_UiRang;
import com.attilax.io.filex;
import com.attilax.pagging.PagingUtil;
import com.attilax.persistence.Hbx;
import com.attilax.persistence.HbxX;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.elmt.GvMaterial;
import com.focusx.playRec.GvPlayRecord;
import com.focusx.pojo.Equipment;
import com.google.inject.Inject;
 import static  com.attilax.core.*;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;

import javax.persistence.Transient;

import  com.attilax.lang.ref.None;
import org.directwebremoting.annotations.RemoteMethod;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
/**
 * baseCountSvs
 * @author  attilax 老哇的爪子
 *@since  o9n 4_9_i$
 */
public class BaseCountSvs4Criteria extends  BaseSvs{
	//  attilax 老哇的爪子 4_9_i   o9n   

	public static void main(String[] args) {

	//	List list = o9o1();  
		 Map m=Mapx.$().add("groupid", 114).toMap();
		 List li=new BaseCountSvs4Criteria(). findByPropertyss(m,GvDownloadTask.class);
		System.out.println(li.size());

		System.out.println("--");

	}

	private static List o9o1() {
		Criteria c = new BaseSvs().getSession().createCriteria(GvDownloadTask.class);
		Criteria eqCri = c.createAlias("eq", "equ", JoinType.LEFT_OUTER_JOIN);

		ProjectionList projectionList1 = Projections.projectionList();
		projectionList1.add(Projections.groupProperty("noticeFlag"));
		// group by this_.notice_flag
		// projectionList1.add(Projections.groupProperty("worktype"));
		// projectionList1.add(Projections.rowCount());
		// projectionList1.add(Projections.sqlGroupProjection(sql, groupBy,
		// columnAliases, types));

		// jeig sqlGroupProjection nen geng normal d groupProperty yda use l ..
		projectionList1.add(Projections.sqlGroupProjection(" count(*) as shouldDown, count(*) as actDown, timRang CONVERT(varchar(10), download_create_time ,23 ) as timRang ", "  CONVERT(varchar(10), download_create_time ,23 ) ", new String[] { "shouldDown", "actDown", "timRang" }, new Type[] { IntegerType.INSTANCE, IntegerType.INSTANCE, StringType.INSTANCE }));

		projectionList1.add(Projections.groupProperty("equ.departId").as("departId"));
		c.setProjection(projectionList1);
		// c. setFetchMode("eq", FetchMode.SELECT);
		// c. setFetchMode("equ", FetchMode.SELECT);

		// c.createCriteria("eq");

		List list = c.list();
		return list;
	}

	private Class<?> cls;
	private Map reqMap;
	
	public BaseCountSvs4Criteria setSaveObjClass(Class<?> saveObjClass) {
		this.saveObjClass = saveObjClass;return this;
	}
	
	 public static final ThreadLocal<Integer> threadLocal_rowsCount = new ThreadLocal<Integer>();
	  /** @author attilax 老哇的爪子
		 * @since o7e m3l$
		 *  desc,cate,cate2,crtDt,plytim
		 * @param propertyName
		 * @param value
		 * @return */
	    @RemoteMethod @SuppressWarnings("all") 
		public List findByPropertyss(Map QueryPropertyssMap,Class<?> cls) {
	    	this.saveObjClass=cls;
	    	super.saveObjClass=cls;
	    	this.cls=cls;
	    	this.reqMap=QueryPropertyssMap;
//	    	String sql2 = getSql(QueryPropertyssMap, cls);
//	    	core.log("---sql:"+sql2);
//			SQLQuery SQLQueryx = hbx.getSession().createSQLQuery(sql2);
//			//SQLQueryx.addEntity(GvPlayRecord.class) ;		
//			SQLQueryx.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	    	
	    Criteria cri=	getCriteria();
	    cri.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List list=	cri.list();
			setNoTransFldOrder(list);
			
			
		
			threadLocal_rowsCount.set(list.size());
			Object page = QueryPropertyssMap.get("page_page");
			List  list_sub = PagingUtil.getList(list,
					QueryPropertyssMap.get("pagesize"), page);
			
		 	setCountRelt2(list_sub);   
			
			return list_sub;

		}
	    	/**
		@author attilax 老哇的爪子
		@since   oaj i_1_q
		 
		 */
	private void setNoTransFldOrder(List list) {
		Field fld=Dsmx.getOrderbyfld(this.cls);
		Transient cdt= fld.getAnnotation(Transient.class);
		Orderby odby= fld.getAnnotation(Orderby.class);
		if(cdt!=null)  // is trans fld
		{
		list=Linq.from(list).orderby(fld.getName(),odby.value());
		}
		
	}

		/**
	@author attilax 老哇的爪子
		@since  o9r 2_47_6   
	
	 * @param list_sub
	 */
	private void setCountRelt2(List<Map> list_sub) {
		// attilax 老哇的爪子  2_47_6   o9r 
	//	List<String> CountReltFldsList=getCountReltFldsList();
		for (Map map : list_sub) {
		//	for (String fldName : CountReltFldsList) {
		//		Class rltCls = getFldClass(fldName);      equmnet :eq  
//				Field idFld = null;
//				try {
//					idFld = refx.getIdFld_EXO9o(rltCls);
//				} catch (cantFindMatchFieldException e) {
//					//  attilax 老哇的爪子 10_58_h   o9q   
//					//e.printStackTrace();
//					core.warn(e);
//				}
				Object idVal = map.get("equipmentId");  //eqid
				if(idVal!=null)
				{
					Object rltObj = getSession().get(Equipment.class,(Integer) idVal);
					map.put("eq", rltObj );
				}
		//	}
		}
	}

		/**
	@author attilax 老哇的爪子
		@since  o9r 1_0_d   
	
	 * @return
	 */
	private Criteria getCriteria() {
		// attilax 老哇的爪子  1_0_d   o9r 
		
		Criteria c = new BaseSvs().getSession().createCriteria(this.saveObjClass);
		Criteria eqCri = c.createAlias("eq", "equ", JoinType.LEFT_OUTER_JOIN);
	 //	Criteria eqCri2=c.createCriteria("eq","eq2");

		ProjectionList projectionList1 = Projections.projectionList();
		//projectionList1.add(Projections.groupProperty("noticeFlag"));
 
		// jeig sqlGroupProjection nen geng normal d groupProperty yda use l ..
	//	Projections.
		projectionList1.add(Projections.sqlGroupProjection(" count(*) as shouldDown, count(*) as actDown,  CONVERT(varchar(10), download_create_time ,23 ) as timRang ", "  CONVERT(varchar(10), download_create_time ,23 ) ", new String[] { "shouldDown", "actDown", "timRang" }, new Type[] { IntegerType.INSTANCE, IntegerType.INSTANCE, StringType.INSTANCE }));

	 
		Field[] flds = cls.getDeclaredFields();
	    filex.save_SF(core.toJsonStrO88(CollX.toList(flds)), "c:\\oaf2.txt");	
		for (Field fld : flds) {
			try {

				String fldName = fld.getName();
				if (fldName.equals("eq")) {
					String s = "dbg";
				}
				if (fldName.equals("equipmentId")) {
					System.out.println("dbg");
				}
			 
			 
				////def except condit   k_y_q o8h  老哇的爪子  Attilax  
				if(filtProcess(fld,this.reqMap))
					continue;
				
			
		 		
		 		//yash condition process. def
		 		SqlAti_Criteria sql=new SqlAti_CriteriaO9();
		 		sql.reqMap=this.reqMap;
		 		sql.Criteria=c;
		 		sql.setCls(cls).addGroupByItem(fld). addWhereExp(fld);
		 	//	@CriteriaRelt
		 		
		 		if(MdaUtil.isDsplyRange(fldName,this.saveObjClass) && refx.isTimestampType(fld))
		 		{
		 			 
						List li = new DateAdptr_UiRang().convert(fld, this.reqMap);
						Hbx.addExpresss(c, li);
		 			//DateAdptr_UiRang
		 		}
		 		
		 	 
		 		if(refx.isIdsType(fld,this.reqMap))
		 		{
					c.add( Restrictions.in(fldName, CollX. covertToIntList(reqMap.get(fldName)) ));
					continue;
		 		}
		 		//  //def mode  common mode :::   fld>=value  fld_op_val
		 	//	c.add( Restrictions.eq(fldName,  (reqMap.get(fldName)) ));
		 		String op=getOp(fld);
		 		List<Criterion> exprsLi = getExprs(fldName, op, reqMap,fld);
				core.log(String.format("--o7f1: dbg fldinfo: %s---%s ", fldName, reqMap.get(fldName)));
				addExpresss(c, exprsLi);	 
				 
				 

			} catch (Exception e) {
				filex.saveLog(e, "c:\\logOa");
				core.log(e);
			}

			// c.add(Restrictions.eq("aname",name));//eq是等于，gt是大于，lt是小于,or是或
		}
		if(this.reqMap.get("groupid")!=null && this.reqMap.get("groupid").toString().trim().length()>0 )
		{
			//Projections.groupProperty(propertyName) 
			projectionList1.add(  Projections.groupProperty("equ.departId").as("departId"));
			projectionList1.add(  Projections.groupProperty("equ.equipmentId").as("equipmentId"));
		//	projectionList1.add(  Projections.groupProperty("equ.mome").as("mome"));
			int grpid = Integer.parseInt(   this.reqMap.get("groupid").toString());
			eqCri.add( Restrictions.eq("equ.departId", grpid));
		}
		c.setProjection(projectionList1);
		
		//oaf2 process order 
		Field orderbyFld=Dsmx.getOrderbyfld(GvDownloadTask.class);
		Dsmx.setOrder(c,orderbyFld);
		
	
		
		return c;
		
	}

			/**
			@author attilax 老哇的爪子
			@since   oaj c_38_z
			 
			 */
		private String getOp(Field fld) {
			Conditional cdt = fld.getAnnotation(Conditional.class);
			if (cdt == null)
				return op.eq;
			return cdt.op();
		}
		
		
		public List<Criterion> getExprs(final String fldName, String op, final Map valMap, Field fld2)
		 {
		if(fldName.equals("materialType"))
			System.out.println("");
		if(fldName.equals("playtime"))
			System.out.println("");
		
		// attilax 老哇的爪子  7:19:04 AM   Jul 20, 2014 
		List<Criterion> li=new ArrayList<Criterion>();
		 Object oriVal=		valMap.get(fldName);
		 Object cvtEdVal = oriVal;
		 
		 
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
		 
			Field fld = null;
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
				if(cvt!=null)
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
			
		 
			
			
			expLiAddExp(fldName, op, li, cvtEdVal,fld);
			return li;
		 } 
		
		
	}

		/** list_sub  >>list_sub
	@author attilax 老哇的爪子
		@since  o9q 10_0_53   
	
	 * @param list
	 */
	    @SuppressWarnings("all")
	private void setCountRelt( List<Map> list) {
		// attilax 老哇的爪子  10_0_53   o9q 
		List<String> CountReltFldsList=getCountReltFldsList();
		for (Map map : list) {
			for (String fldName : CountReltFldsList) {
				Class rltCls = getFldClass(fldName);
				Field idFld = null;
				try {
					idFld = refx.getIdFld_EXO9o(rltCls);
				} catch (cantFindMatchFieldException e) {
					//  attilax 老哇的爪子 10_58_h   o9q   
					//e.printStackTrace();
					core.warn(e);
				}
				Object idVal = map.get(idFld.getName());
				if(idVal!=null)
				{
					Object rltObj = getSession().get(rltCls,(Integer) idVal);
					map.put(fldName, rltObj );
				}
			}
		}
		
		
	}
 
		/**
		@author attilax 老哇的爪子
			@since  o9q 10_q_b   
		
		 * @param fldName
		 * @return
		 */
		private Class getFldClass(String fldName) {
			// attilax 老哇的爪子  10_q_b   o9q 
		Field fld = null;
		try {
			fld = refx.getField(fldName, this.saveObjClass);
		} catch (cantFindMatchFieldException e) {
			//  attilax 老哇的爪子 10_45_47   o9q   
			e.printStackTrace();
		}
			return fld.getType();
			
		}
		/**
		@author attilax 老哇的爪子
			@since  o9q 10_o_h   
		
		 * @return
		 */
		private List getCountReltFldsList() {
			// attilax 老哇的爪子  10_o_h   o9q 
			
			return refx.getFlds( this.saveObjClass, CountRelt.class);
			
		}
		@SuppressWarnings("all")
	public String getSql(Map QueryPropertyssMap, Class<?> cls) {
	    //	java.
		this.saveObjClass=cls;
		// log.debug("finding GvMaterial instance with property: " +
		// propertyName + ", value: " + value);
		 core. log("---o720--");
		core.logMap(QueryPropertyssMap);
		filex.save_safe( core.toJsonStr(QueryPropertyssMap) , "c:\\json2.txt");
		String propertyName;
		Object value;
	 
		SqlAti sql=new SqlAti();
		sql.reqMap=QueryPropertyssMap;
		sql.cls=cls;
		sql.saveObjClass=sql.cls;
		Field[] flds =cls.getDeclaredFields();
		for (Field fld : flds) {
			try {

				String fldName = fld.getName();
				if (fldName.startsWith("playTime")) {
					 String s = "";
				}
			 
				////def except condit   k_y_q o8h  老哇的爪子  Attilax  
				if(filtProcess(fld,QueryPropertyssMap))
					continue;
				
				sql.addSelectItem(fld).addGroupByItem(fld).addWhereExp(fld);
				
			
					 
				 
				 

			} catch (Exception e) {
				core.log(e);
			}

			 
		}
//	c.addOrder(Order.desc("materialId"));

		String sql2 = sql.toSql();
		return sql2;
	}
	    

@Inject
Hbx hbx=new HbxX(); 
		
	    
	    /**
		 * def filtr  todox o8h should fld>>MapToUiFld>> then judge..
		@author attilax 老哇的爪子
			@since  o8h k_z_n   
		
		 * @param fld
		 * @param queryPropertyssMap 5
		 * @return
		 */
	@SuppressWarnings("all") private boolean filtProcess(Field fld, Map queryPropertyssMap) {
		// attilax 老哇的爪子 k_z_n o8h
		if (fld.getName().equals("playTime"))
			System.out.println("4dbg");
		//if condition fld 
		List<String> uiFldList = getUiFldList(fld);
		for (String uiFld : uiFldList) {
			if (isUiFldHasVal(uiFld, queryPropertyssMap, fld)) { return false; }
		}
		reduce rds =	fld.getAnnotation(reduce.class);
		if(rds!=null)
			return false;
		if(isOrderbyTimetypeFld(fld))
			return false;
		if(isGroupByDate(fld)) return false;
		return true; // jump/ filted / not to nextSetp process..

	}
		
	/**
		@author attilax 老哇的爪子
			@since  o9p j_z_v   
		
		 * @param fld
		 * @return
		 */
		private boolean isGroupByDate(Field fld) {
			// attilax 老哇的爪子  j_z_v   o9p 
			GroupByDate anno=fld.getAnnotation(GroupByDate.class);
			if(anno!=null) return true;
			return false;
			
		}
	/**
		@author attilax 老哇的爪子
			@since  o9p j_m_0   
		
		 * @param fld
		 * @return
		 */
		private boolean isOrderbyTimetypeFld(Field fld) {
			// attilax 老哇的爪子  j_m_0   o9p 
			OrderByTimeType obtt=fld.getAnnotation(OrderByTimeType.class);
			if(obtt!=null) return true;
			return false;
			
		}
	/** @author attilax 老哇的爪子
	 * @since o9p 8_40_8	 * 
	 * @param uiFld
	 * @param queryPropertyssMap
	 * @return */
		private boolean isUiFldHasVal(String uiFld, Map queryPropertyssMap,Field fld) {
			// attilax 老哇的爪子  8_40_8   o9p 
			String uiFldName =uiFld;
//			if(uiFldName.equals("playtime"))
//				return false;
			if(uiFldName.equals("createTime"))
				System.out.println("xx");
		Object oriVal=queryPropertyssMap.get(uiFldName);
			//	Field fld=refx.getField(fldName, this.saveObjClass);
			if(refx.isIntType(fld))
			{
				
				if(oriVal==null || oriVal.toString().equals("") || oriVal.toString().equals("0") )
					return false;  //jump
			}
			if(refx.isStrType(fld))
			{
				if(oriVal==null || oriVal.toString().equals("") )
					return false;//jump
			}
			if(refx.isTimeType(fld))
			{
				if(oriVal==null || oriVal.toString().equals("")||  oriVal.toString().equals("0") )
					return false;//jump
			}
			return true;
			
		}
		/**
		@author attilax 老哇的爪子
			@since  o9p 8_v_m   
		
		 * @param fld
		 * @return
		 */
		private List getUiFldList(Field fld) {
			// attilax 老哇的爪子  8_v_m   o9p 
			List UiFldList=new ArrayList();
			try {
				Conditional cdt=	MdaUtil.getConditional(fld.getName(), this.saveObjClass);
				if(cdt==null)  return UiFldList;
				if(	cdt.displayType().equals(displayType.rang))
				{
					UiFldList.add(fld.getName()+"_"+cdt.rangStart() );
					UiFldList.add(fld.getName()+"_"+cdt.rangEnd() );
				}
				if(	cdt.displayType().equals(displayType.single))
				{
					UiFldList.add(fld.getName() );
				}
				return UiFldList;
			} catch (cantFindMatchFieldException e) {
				//  attilax 老哇的爪子 8_x_39   o9p   
				return  new ArrayList();
			}
		//	return  new ArrayList();
			
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
		Class<?> saveObjClass ;
	
	
		
	
}

//  attilax 老哇的爪子