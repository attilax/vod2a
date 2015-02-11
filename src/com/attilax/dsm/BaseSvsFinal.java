/**
 * @author attilax 老哇的爪子
	@since  o9n 4_9_i$
 */
package com.attilax.dsm;
import com.attilax.Closure;
import com.attilax.ClosureNoExcpt;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.MDA.CantFindConditionalExcept;
import com.attilax.MDA.CantFindConverterExcept;
import com.attilax.MDA.IAdapter;
import com.attilax.MDA.IDefVal;
import com.attilax.MDA.MdaUtil;
import com.attilax.Stream.Mapx;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
import com.attilax.anno.DefVal;
import com.attilax.anno.displayType;
import com.attilax.anno.ignore4query;
import com.attilax.collection.CollX;
import com.attilax.collection.listUtil;
import com.attilax.db.BaseHibernateDAO;
import com.attilax.db.BaseHibernateDAO2;
import com.attilax.dsm.adapt.DateAdptr_UiRang;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.page.PageUtil;
import com.attilax.pagging.PagingUtil;
import com.attilax.persistence.HbxX;
import com.attilax.persistence.PX;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.attilax.util.DateUtil;
import com.focusx.downRec.GvDownloadRecord;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.elmt.GvMaterial;
import com.focusx.playRec.GvPlayRecord;
import com.google.inject.Inject;
 import static  com.attilax.core.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ecs.xhtml.base;
import  com.attilax.lang.ref.None;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.sql.JoinType;
/** base on criteria objQuery   ,query by criteria  base  ,not suit for count 
 * if count use BaseCountSvs4Criteria1
 * @author  attilax 老哇的爪子
 *@since  o9n 4_9_i$
 */
public class BaseSvsFinal extends BaseHibernateDAO2{
	//  attilax 老哇的爪子 4_9_i   o9n   
	
	 public static final ThreadLocal<Integer> threadLocal_rowsCount = new ThreadLocal<Integer>();
	 
	 /**
	  *  "materialId": "3",
 "element11": "false",
 "page_page": "1",
 "groupname": "花都店",
 "txt2": "图片促销1",
 "idsCheck": "false",
 "downloadOverTime": "2014-09-16",
 "element4": "2014-09-16",
	 @author attilax 老哇的爪子
	 	@since  o9r i_59_44   
	 
	  * @param args
	  */
	 public static void main(String[] args)  throws Exception {
//	o9o();
	//	 Map m=Mapx.$().add("departId", 117).toMap();
	//	 Map m=Mapx.$().add("groupid", 117).toMap();	 Class  cls = GvPlayRecord.class;
	//	 Map m=Mapx.$().add("createTime", "2014-09-22").toMap();	 Class  cls = GvDownloadRecord.class;
		 	PX px=IocX.getBean(PX.class);
		 	Session session=px.getSession();
			Criteria c = session.createCriteria(GvDownloadTask.class);
			c.add(Restrictions.isNull("downloadStatus"));
			c.add(Restrictions.isNull("startdownFlag"));
		List li=	c.list();
		 
		 
		 //	 oaa();
			System.out.println("--");
			
	}

	private static void oaa() {
		Map m=Mapx.$().add("groupid", "117").add("materialId", 3).add( "downloadCreateTime","2014-09-09").toMap();
		 Class  cls = GvDownloadTask.class;
		List li=new BaseSvsFinal(). findByPropertyss(m,cls);
	 	 core.print_wzFmt(li);
		 System.out.println(li.size());
	}

	private static void o9o() {
		//	 Map m=Mapx.$().add("", value)
				Criteria c = new BaseSvsFinal(). getSession().createCriteria(GvPlayRecord.class);
			Criteria eqCri=c.createCriteria("eq");
		//	Criteria dptCri=eqCri.createCriteria("dpt");
			eqCri.add(Restrictions.eq("departId",   117 ));
			//	SimpleExpression se= Restrictions.eq("eq.dpt.groupid",   114 );
			//	c.add(se);
				List  list = c.list();
				System.out.println(list.size());
			//	core.print_wzFmt(list);
	}
	private Map reqMap;
	 
	  /**with page spt 
	   * 
	   * @author attilax 老哇的爪子
		 * @since o7e m3l$
		 *  desc,cate,cate2,crtDt,plytim
		 * @param propertyName
		 * @param value
		 * @return */
	    @RemoteMethod @SuppressWarnings("all") 
		 
		public List findByPropertyss(Map QueryPropertyssMap,Class<?> cls) {
	    	
	    //	org.apache.taglibs.standard.tlv.JstlCoreTLV
	    //	org.apache.taglibs.standard.tlv.JstlCoreTLV


	    	this.reqMap=QueryPropertyssMap;
	    	this.saveObjClass=cls;
			// log.debug("finding GvMaterial instance with property: " +
			// propertyName + ", value: " + value);
			 core. log("---o720--");
			core.logMap(QueryPropertyssMap);
			filex.save_safe( core.toJsonStr(QueryPropertyssMap) , "c:\\json2.txt");
			String propertyName;
			Object value;
			Session session = getSession();
			System.out.println(session.isOpen());
			Criteria c = session.createCriteria(cls);
			Field[] flds = cls.getDeclaredFields();
			try {
				 
				List liOa1=new ArrayList();
				liOa1=CollX.toList(flds);
				filex.save_SF(core.toJsonStrO88(liOa1), "c:\\flds.txt");
			} catch (Exception e) {
				core.err(e);
			}
			
			for (Field fld : flds) {
				try {

					String fldName = fld.getName();
					if (fldName.equals("downloadCreateTime")) {
						String s = "dbg";
					}
					if (fldName.equals("downloadStatus")) {
						String s = "dbg";
					}
					if (fldName.equals("playTime")) {
						String s = "dbg";
					}
					
					if (fldName.equals("groupid")) {
						String s = "dbg";
					}
					
					if (fldName.equals("eq")) {
						System.out.println("dbg");
					}
					
					if (fldName.equals("equipmentId")) {
						System.out.println("dbg");
					}
				 
					if (fldName.startsWith("failureTime")) {
						String s = "dbg";
					}
				 
					////def except condit   k_y_q o8h  老哇的爪子  Attilax  
//					if(filtProcess(fld,QueryPropertyssMap))
//						continue;
					
				
			 		
			 		//yash condition process. def
//			 		SqlAti_Criteria sql=new SqlAti_Criteria();
//			 		sql.reqMap=QueryPropertyssMap;
//			 		sql.Criteria=c;
//			 		sql.setCls(cls). addWhereExp(fld);
			 	//	@CriteriaRelt
					//oab add  for stroe multi select	 
//			 		SqlAti_Criteria sql=new SqlAti_Criteria();
//		 	 		sql.reqMap=QueryPropertyssMap;
//			  		sql.Criteria=c;
//			 	 	sql.cls=this.saveObjClass;
//			  		if(this.saveObjClass==null)
//			 	 			sql.cls=cls;
//			  		// only condition relt 
//			  		sql.addWhereExp(fld);
			 		
			  
				 //
			 		if (Dsmx.getAdptr4Field(fld)!=null) // cstm mode

					{
						List<Criterion> li = fld2critAdapt(QueryPropertyssMap,  fld,Dsmx.getAdptr4Field(fld));
						addExpresss(c, li);
						continue;
					}
				    //def mode  common mode :::   fld>=value  fld_op_val
			 		if( refx.isIntType(fld) && SafeVal.val(QueryPropertyssMap.get(fldName), "")  .contains(","))
			 		{
						c.add( Restrictions.in(fldName, CollX. covertToIntList(QueryPropertyssMap.get(fldName)) ));
						continue;
			 		}
			 		Conditional cdt = fld.getAnnotation(Conditional.class);
				//	if (cdt == null)
					List<Criterion> exprsLi = getExprs(fldName, cdt.op(), QueryPropertyssMap,fld);
					core.log(String.format("--o7f1: dbg fldinfo: %s---%s ", fldName, QueryPropertyssMap.get(fldName)));
					addExpresss(c, exprsLi);
					 
			 		
			//	    parseJoin(c,fld);
					 

				} catch (Exception e) {
					filex.saveLog(e, "c:\\e");
					core.log(e);
				}

				// c.add(Restrictions.eq("aname",name));//eq是等于，gt是大于，lt是小于,or是或
			}
			//oab remm
 	
			
  		
		//	c.addOrder(Order.desc("materialId"));
			//oab add def order
			try {
				 Field idfld=refx.getIdFld_RE(this.saveObjClass);
				 c.addOrder(Order.desc(idfld.getName()));
			} catch (Exception e) {
				core.err(e);
			}
	 		
	 		int pageSize = PageUtil.getPageSize(QueryPropertyssMap.get("pagesize"));
	 		//PageUtil.getStartIndexFirstResult( QueryPropertyssMap.get("page_page"),pageSize)
	 		c.setFirstResult(0); 		 	
			c.setMaxResults(999); //should be pageSize 
			List  list = c.list();
			
			//	this.req.getSession().setAttribute("", list);
		  	threadLocal_rowsCount.set(list.size());
			Object page = QueryPropertyssMap.get("page_page");
 		List  list_sub = PagingUtil.getList(list,
 				QueryPropertyssMap.get("pagesize"), page);

			return list_sub;

		}
	    
	    
			/**
		@author attilax 老哇的爪子
		@since   oad a_46_51
		 
		 */
	private void parseJoin(Criteria c, Field fld) {
		CriteriaRelt an=fld.getAnnotation(CriteriaRelt.class);
		if(an!=null)
		{
			String subobjFldName = an.fld();
			if(subobjFldName.length()==0)
			{
				Class subObjType=fld.getType();
				 
					subobjFldName= 	refx.getIdFld_RE(subObjType).getName();
			 
			}
			String uifld=an.uiFld();
			if(uifld.length()==0)
				uifld=subobjFldName;
				
		
			
			Criteria  crSub=	c.createCriteria(fld.getName(),JoinType.LEFT_OUTER_JOIN);
			
			Object object = this.reqMap.get(uifld);
		 
			if( Dsmx.isValidVal(fld, object))
			{
				
				Class subObjType=fld.getType();
				
				Field subObjfld = null;
			
				try {
					subObjfld = refx.getField(subobjFldName, subObjType);
				} catch (cantFindMatchFieldException e) {
					//  attilax 老哇的爪子 h_y_2   o9r   
					core.err(e);
				}catch(Exception e)
				{
					core.err(e);
				}
				
				if( refx.isIntType(subObjfld) &&  object.toString().contains(","))
					crSub.add( Restrictions.in(subobjFldName,CollX. covertToIntList(object) ));
				else //def
				{
					Object obj= Dsmx. covertToFldtypeFmt(subObjfld,object);
					crSub.add( Restrictions.eq(subobjFldName,  obj));
				}
					
			 
			}
			
		}
	 
		
	}

		/**
	@author attilax 老哇的爪子
		@since  o9r h_5_50   
	
	 * @param fld
	 * @return
	 */
	private boolean isIngroreFld(Field fld) {
		// attilax 老哇的爪子  h_5_50   o9r 
		ignore4query a=fld.getAnnotation(ignore4query.class);
		if(a==null)return false;
		return true;
		
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
		private List fld2critAdapt(Map queryPropertyssMap, Field fld,Conditional cdt) {
 

			IAdapter adpt = (IAdapter) core.newx(cdt.adptr());

 
			return adpt.convert(fld.getName(), queryPropertyssMap.get(fld.getName()).toString());

 
			 
			
		}
		
		private List fld2critAdapt(Map queryPropertyssMap, Field fld,IAdapter adpt) {
			 

		//	 = (IAdapter) core.newx(cdt.adptr());

 try{
	 List li= adpt.convert(fld , queryPropertyssMap );
	 if(li==null)
			return new ArrayList();
	 return li;
 }catch (Exception e) {
	 return new ArrayList();
}

 
			 
			
		}
	    
	    /**
		 * def filtr  todox o8h should fld>>MapToUiFld>> then judge..
		@author attilax 老哇的爪子
			@since  o8h k_z_n   
		
		 * @param fld
		 * @param queryPropertyssMap 
		 * @return
		 */
//		private boolean filtProcess(Field fld, Map queryPropertyssMap) {
//			// attilax 老哇的爪子  k_z_n   o8h 
//			if(isCriteriaReltFld(fld))
//				return false;
//			if(Dsmx.isNotFilt4condQueryFld(fld))
//				return false;
//			
//			if(fld.getName().equals("playtime"))
//				return false;
//			if(fld.getName().equals("createTime"))
//				System.out.println("xx");
//		Object oriVal=queryPropertyssMap.get(fld.getName());
//			//	Field fld=refx.getField(fldName, this.saveObjClass);
//			if(refx.isIntType(fld))
//			{
//				
//				if(oriVal==null || oriVal.toString().equals("") || oriVal.toString().equals("0") )
//					return true;  //jump
//			}
//			if(refx.isStrType(fld))
//			{
//				if(oriVal==null || oriVal.toString().equals("") )
//					return true;//jump
//			}
//			if(refx.isTimeType(fld))
//			{
//				if(oriVal==null || oriVal.toString().equals("")||  oriVal.toString().equals("0") )
//					return true;//jump
//			}
//			return false;
//			
//		}
//		
		
		public static boolean isValidVal(Field fld, Map queryPropertyssMap)
		{
			 
				// attilax 老哇的爪子  k_z_n   o8h 
				 
		 
			Object oriVal=queryPropertyssMap.get(fld.getName());
			if(oriVal==null)
				return false;
			 
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
			@since  o9q 6_9_p   
		
		 * @param fld
		 * @return
		 */
		private boolean isCriteriaReltFld(Field fld) {
			// attilax 老哇的爪子  6_9_p   o9q 
			
			return fld.getAnnotation(CriteriaRelt.class)!=null;
			
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
				 Criterion se=(Criterion) object;
				c.add(se);
			}
			 
			
		}
		/**
		//  attilax 老哇的爪子 10_59_q   o9q   
		 * @return the saveObjClass
		 */
		public Class<?> getSaveObjClass() {
			return saveObjClass;
		}

		/**
		//  attilax 老哇的爪子 10_59_q   o9q   
		 * @param saveObjClass the saveObjClass to set
		 */
		public BaseSvsFinal setSaveObjClass(Class<?> saveObjClass) {
			this.saveObjClass = saveObjClass;return this;
		}
	public	Class<?> saveObjClass ;
		/**
		@author attilax 老哇的爪子
		\t@since  Jul 20, 2014 7:19:04 AM$
		
		 * @param fldName
		 * @param op
		 * @param fld 
		 * @param object
		 * @return
		 * @throws cantFindMatchFieldException 
		 * @throws CantFindConverterExcept 
		 */
		private List<Criterion> getExprs(final String fldName, String op, final Map valMap, Field fld2)
			 {
			if(fldName.equals("materialType"))
				System.out.println("");
			if(fldName.equals("playtime"))
				System.out.println("");
			
			// attilax 老哇的爪子  7:19:04 AM   Jul 20, 2014 
			List<Criterion> li=new ArrayList<Criterion>();
			 Object oriVal=		valMap.get(fldName);
			 Object cvtEdVal = oriVal;
			 
//			 try {
//				if(MdaUtil.isDsplyRange(fldName,this.saveObjClass))
//				 {
//					if (!refx.isTimestampType(fld2)) {
//	
//						try {
//							if (valMap.get(fldName + "_start").toString()
//									.equals(valMap.get(fldName + "_end")))
//								return new ArrayList<Criterion>();
//	
//						} catch (Exception e) {
//							// return new ArrayList<Criterion>();
//						}
//	
//					}
//				 
//					
//					if(refx.isTimestampType(fld2))
//						return new DateAdptr_UiRang().convert(fld2, valMap);
//					 List<Criterion> fstExprs=fstExprs(fldName,op,valMap);
//					 
//					 List<Criterion> sekdExprs=sekdExprs(fldName,op,valMap);
//					 li.addAll(fstExprs);
//					 li.addAll(sekdExprs);
//					 return li;
//				 }
//			} catch (cantFindMatchFieldException e2) {
//				//  attilax 老哇的爪子 10:28:01 AM   Jul 20, 2014   
//				e2.printStackTrace();
//			} catch (CantFindConditionalExcept e) {
//				//  attilax 老哇的爪子 10:29:59 AM   Jul 20, 2014   
//				e.printStackTrace();
//			}
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
public static boolean isInIde=false;
	private void expLiAddExp(final String fldName, String op,
			List<Criterion> li, Object cvtEdVal, Field fld) {
		//if(isInIde)
		{
			if (fldName.equals("downloadStatus"))
				System.out.println("dbg");
		}
		if (fldName.equals("playTime"))
			System.out.println("dbg");
		if (op.equals(com.attilax.anno.op.like))
			li.add(Restrictions.like(fldName, "%" + cvtEdVal.toString() + "%"));
		else if (op.equals(com.attilax.anno.op.range)) {
			li.add(Restrictions.gt(fldName, cvtEdVal));
			li.add(Restrictions.lt(fldName, cvtEdVal));
		} else {
			if (refx.isTimestampType(fld)) {
				Criterion cri1 = getCri1_4timestamp(fld, cvtEdVal);
				Criterion cri2 = getCri2_4timestamp(fld, cvtEdVal);
				li.add(cri1);
				li.add(cri2);
			//	Restrictions.is
			} else if (isNullval(cvtEdVal, fld)) {
				li.add(Restrictions.isNull(fldName));
			} else

				li.add(Restrictions.eq(fldName, cvtEdVal));
		}
	}
		
			/**
		@author attilax 老哇的爪子
		@since   oaa f_o_56
		 
		 */
	private boolean isNullval(Object cvtEdVal, Field fld) {
		 Conditional cdt=fld.getAnnotation(Conditional.class);
		 if(cdt!=null)
		 {
			 if(cvtEdVal.toString().equals( String.valueOf(cdt.nullval())))
					 return true;
		 }
		return false;
	}

		/**
		@author attilax 老哇的爪子
			@since  o0c m_46_n   
		
		 * @param fld
		 * @param cvtEdVal
		 * @return
		 */
		private Criterion getCri2_4timestamp(Field fld, Object valFrmUi) {
			// attilax 老哇的爪子  m_46_n   o0c 
			String s=valFrmUi.toString();
		 
			String s2=s+" 23:59:59";
			;
		 
			Timestamp ts2;
			 
			try {
		 
			 
		
				ts2 = DateUtil.toTimeStamp(s2, true);
				SimpleExpression endCondtExp = Restrictions.lt(fld.getName(), ts2);
				 return endCondtExp;
			} catch (ParseException e) {
				//  attilax 老哇的爪子 1:28:28 AM   Jul 19, 2014   
				core.log(e);
			}
			return null;
		 
		}

		/**
		@author attilax 老哇的爪子
			@since  o0c m_46_5   
		
		 * @param fld
		 * @param cvtEdVal
		 * @return
		 */
		private Criterion getCri1_4timestamp(Field fld, Object valFrmUi) {
			// attilax 老哇的爪子  m_46_5   o0c 
			String s=valFrmUi.toString();
			String s1=s+" 00:00:01";
			String s2=s+" 23:59:59";
			;
			Timestamp ts1;
			Timestamp ts2;
			 
			try {
				ts1 = DateUtil.toTimeStamp(s1, true);
			 
		
				ts2 = DateUtil.toTimeStamp(s2, true);
				SimpleExpression startCondtExp = Restrictions.gt(fld.getName(), ts1);
				 return startCondtExp;
			} catch (ParseException e) {
				//  attilax 老哇的爪子 1:28:28 AM   Jul 19, 2014   
				core.log(e);
			}
			return null;
			
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
						
					 
						
						
//						if(op.equals(com.attilax.anno.op.like))
//							li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%")); 
//						else if(op.equals(com.attilax.anno.op.range))
//						{
							li.add(Restrictions.gt(fldName,   cvtEdVal )); 
						//	li.add(Restrictions.lt(fldName,   cvtEdVal )); 
//						}
//						else
//						{
//							li.add(  Restrictions.eq(fldName,  cvtEdVal)); 
//						}
						return li;
					 
			
			
		}

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
						
					 
						
						
//						if(op.equals(com.attilax.anno.op.like))
//							li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%")); 
//						else if(op.equals(com.attilax.anno.op.range))
//						{
						//	li.add(Restrictions.gt(fldName,   cvtEdVal )); 
					 	li.add(Restrictions.lt(fldName,   cvtEdVal )); 
//						}
//						else
//						{
//							li.add(  Restrictions.eq(fldName,  cvtEdVal)); 
//						}
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
//				com.attilax.anno.Converter cvtAnno=fld.getAnnotation(com.attilax.anno.Converter.class);
//				if(cvtAnno==null)throw new CantFindConverterExcept();
//				Class<?>	cvtCls= cvtAnno.value();
//				Object cvtClsObj=core.newx(cvtCls);
//	 		return (org.apache.commons.beanutils.Converter) cvtClsObj;
			 
			 
			
			
		}

		/**
		@author attilax 老哇的爪子
		 * @param ids 
			@since  o9r f_c_f   
		
		 */
		public void modifyLevel(String ids) {
			// attilax 老哇的爪子  f_c_f   o9r 
			try {
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String[] a=ids.split(",");
				for (String id_int : a) {
					int id=Integer.parseInt(id_int);
					GvDownloadTask t=new GvDownloadTask();
					t.setDsId(id);
					t.setCurCursor(0);
					new base().notifyAll();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		
		@SuppressWarnings("all") @RemoteMethod public void save_map(Map mp) {
			core.logMap(mp);
		//	log.debug("saving GvMaterial instance");
			//mp=format_befSave(mp);
			Object elmt = core.newx(saveObjClass);
			// if (obj.getMaterialId() != null) //edit mode
			// elemt=findById((Integer) mp.get("materialId"));
			// todox o7f jeig bg map null >>> 0 (id ,null convert to zero)
			Object obj = core.Map2obj_safe(mp, elmt);
		//	obj.setCreateTime(DateUtil.timestampO7());
		//    serverValide(mp,obj);
		    
		    core.logObj(obj);
		    
			exeConvert(mp,obj);
			 core.logObj(obj);
			 filex.save_safe(obj, "c:\\vodDbg\\defltValBefJson.txt");
			setDefVal(obj);
			 filex.save_safe(obj, "c:\\vodDbg\\defltValAftJson.txt");
			
			 core.logObj(obj);
		 
				this.px.merge(obj);
 
			 
		}
		public Object merge(Object detachedInstance)
		{
			return this.px.merge(detachedInstance);
		}
		@Inject  
	public	PX px=new HbxX();
		public HttpServletRequest req;
		
		/**
		 * @author attilax 老哇的爪子 \t@since Jul 19, 2014 9:38:18 PM$
		 * 
		 * @param obj
		 * @param obj 
		 */
		@SuppressWarnings("all") 
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

		/**
		 * 4 dwr get
		@author attilax 老哇的爪子
			@since  o03 0_u_a   
		
		 * @return
		 */
		public String getErr() {
			// attilax 老哇的爪子  0_u_a   o03 
			HttpSession session = WebContextFactory.get().getSession();
			return (String) session.getAttribute("errO9");
			
		}
		
}

//  attilax 老哇的爪子