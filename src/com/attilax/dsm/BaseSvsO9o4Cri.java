/**
 * @author attilax 老哇的爪子
	@since  o9n 4_9_i$
 */
package com.attilax.dsm;
import com.attilax.core;
import com.attilax.MDA.CantFindConditionalExcept;
import com.attilax.MDA.CantFindConverterExcept;
import com.attilax.MDA.IAdapter;
import com.attilax.MDA.MdaUtil;
import com.attilax.Stream.Mapx;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
import com.attilax.anno.displayType;
import com.attilax.anno.ignore4query;
import com.attilax.db.BaseHibernateDAO;
import com.attilax.db.BaseHibernateDAO2;
import com.attilax.io.filex;
import com.attilax.lang.ref.None;
import com.attilax.pagging.PagingUtil;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.focusx.downRec.GvDownloadRecord;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.elmt.GvMaterial;
import com.focusx.playRec.GvPlayRecord;
 import static  com.attilax.core.*;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
 
import org.directwebremoting.annotations.RemoteMethod;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
/** base on criteria objQuery   ,query by criteria  base  ,not suit for count  * 
 * if count use BaseCountSvs4Criteria1
 *relate baseSvc::  baseSvc as a itfs..
 * @author  attilax 老哇的爪子
 *@since  o9n 4_9_i$
 */
public class BaseSvsO9o4Cri extends BaseSvs{
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
	 public static void main(String[] args) {
//	o9o();
	//	 Map m=Mapx.$().add("departId", 117).toMap();
	//	 Map m=Mapx.$().add("groupid", 117).toMap();	 Class  cls = GvPlayRecord.class;
	//	 Map m=Mapx.$().add("createTime", "2014-09-22").toMap();	 Class  cls = GvDownloadRecord.class;
		 Map m=Mapx.$().add("groupid", "117").add("materialId", 3).add( "downloadCreateTime","2014-09-09").toMap();
		 Class  cls = GvDownloadTask.class;
		List li=new BaseSvsO9o4Cri(). findByPropertyss(m,cls);
	 	 core.print_wzFmt(li);
		 System.out.println(li.size());
			System.out.println("--");
			
	}

	private static void o9o() {
		//	 Map m=Mapx.$().add("", value)
				Criteria c = new BaseSvsO9o4Cri(). getSession().createCriteria(GvPlayRecord.class);
			Criteria eqCri=c.createCriteria("eq");
		//	Criteria dptCri=eqCri.createCriteria("dpt");
			eqCri.add(Restrictions.eq("departId",   117 ));
			//	SimpleExpression se= Restrictions.eq("eq.dpt.groupid",   114 );
			//	c.add(se);
				List  list = c.list();
				System.out.println(list.size());
			//	core.print_wzFmt(list);
	}
	 
	  /** 
	   * with page support
	   * @author attilax 老哇的爪子
		 * @since o7e m3l$
		 *  desc,cate,cate2,crtDt,plytim
		 * @param propertyName
		 * @param value
		 * @return */
	    @RemoteMethod @SuppressWarnings("all") 
		 
		public List findByPropertyss(Map QueryPropertyssMap,Class<?> cls) {
	    	this.saveObjClass=cls;
			// log.debug("finding GvMaterial instance with property: " +
			// propertyName + ", value: " + value);
			 core. log("---o720--");
			core.logMap(QueryPropertyssMap);
			filex.save_safe( core.toJsonStr(QueryPropertyssMap) , "c:\\json2.txt");
			String propertyName;
			Object value;
			Criteria c = getSession().createCriteria(cls);
			Field[] flds = cls.getDeclaredFields();
			for (Field fld : flds) {
				try {

					String fldName = fld.getName();
					if(fldName.equals("serialVersionUID"))
						continue;
					if (fldName.equals("equipmentId")) {
						String s = "dbg";
					}
				 
					////def except condit   k_y_q o8h  老哇的爪子  Attilax  
					if(filtProcess(fld,QueryPropertyssMap))
						continue;
					
				
			 		
			 		//yash condition process. def
			 		SqlAti_Criteria sql=new SqlAti_Criteria();
			 		sql.reqMap=QueryPropertyssMap;
			 		sql.Criteria=c;
			 		sql.setCls(cls). addWhereExp(fld);
			 	//	@CriteriaRelt
			 		
			 		
			 		Conditional cdt = fld.getAnnotation(Conditional.class);
					if (cdt == null)
						continue;
					if(isIngroreFld(fld) )
						continue;
				 
			 		if (cdt.adptr() != None.class) // cstm mode

					{
						List<Criterion> li = fld2critAdapt(QueryPropertyssMap,  fld,cdt);
						addExpresss(c, li);
					} else {   //def mode  common mode :::   fld>=value  fld_op_val

						List<Criterion> exprsLi = getExprs(fldName, cdt.op(), QueryPropertyssMap);
						core.log(String.format("--o7f1: dbg fldinfo: %s---%s ", fldName, QueryPropertyssMap.get(fldName)));
						addExpresss(c, exprsLi);
					}
			 		
						 
					 
					 

				} catch (Exception e) {
					core.log(e);
				}

				// c.add(Restrictions.eq("aname",name));//eq是等于，gt是大于，lt是小于,or是或
			}
		//	c.addOrder(Order.desc("materialId"));

			List  list = c.list();
			threadLocal_rowsCount.set(list.size());
			Object page = QueryPropertyssMap.get("page_page");
			List  list_sub = PagingUtil.getList(list,
					QueryPropertyssMap.get("pagesize"), page);

			return list_sub;

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
		public List fld2critAdapt(Map queryPropertyssMap, Field fld,Conditional cdt) {
 

			IAdapter adpt = (IAdapter) core.newx(cdt.adptr());

 
			return adpt.convert(fld.getName(), queryPropertyssMap.get(fld.getName()).toString());

 
			 
			
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
			if(isCriteriaReltFld(fld))
				return false;
			
			if(fld.getName().equals("playtime"))
				return false;
			if(fld.getName().equals("createTime"))
				System.out.println("xx");
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
				SimpleExpression se=(SimpleExpression) object;
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
		public BaseSvsO9o4Cri setSaveObjClass(Class<?> saveObjClass) {
			this.saveObjClass = saveObjClass;return this;
		}
		Class<?> saveObjClass ;
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
		public org.apache.commons.beanutils.Converter getConvert(String fldName) throws CantFindConverterExcept, cantFindMatchFieldException  {
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
			@since  o9r f_c_f   
		
		 */
		public void modifyLevel() {
			// attilax 老哇的爪子  f_c_f   o9r 
			
		}
}

//  attilax 老哇的爪子