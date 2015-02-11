/**
 * @author attilax 老哇的爪子
	@since  o9q 5_53_40$
 */
package com.attilax.dsm;
import com.attilax.core;
import com.attilax.MDA.CantFindConditionalExcept;
import com.attilax.MDA.CantFindConverterExcept;
import com.attilax.MDA.MdaUtil;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
//import com.attilax.core.CvtX;
import com.attilax.count.CountRelt;
import com.attilax.count.CountX;
import com.attilax.count.GroupBy;
import com.attilax.count.GroupByDate;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.attilax.util.DateUtil;
// import static  com.attilax.core.*;
import java.sql.Timestamp;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import  com.attilax.lang.ref.None;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
/**
 * @author  attilax 老哇的爪子
 *@since  o9q 5_53_40$
 */
public class SqlAti_Criteria_pubCheckover extends SqlAti_Criteria {

	/**
	//  attilax 老哇的爪子 6_f_y   o9q   
	 * @return the criteria
	 */
	public Criteria getCriteria() {
		return Criteria;
	}

	
	
	/**
	//  attilax 老哇的爪子 6_f_y   o9q   
	 * @param criteria the criteria to set
	 */
	public SqlAti_Criteria_pubCheckover setCriteria(Criteria criteria) {
		Criteria = criteria;return this;
	}

	public Criteria Criteria;
	//  attilax 老哇的爪子 5_53_40   o9q   
	
	
	public SqlAti addGroupByItem(Field fld) {
		// attilax 老哇的爪子 5_52_49 o9p
	//	String colName = CountX.getColumnName( fld.getName(),this.cls);
	 	GroupBy grpby = fld.getAnnotation(GroupBy.class);
	//	if (grpby != null) this.GroupByItemList.add(colName);
	//	if (orderbyitemStr == null) orderbyitemStr = getOorderbyitemStr(fld);
		GroupByDate gbd = fld.getAnnotation(GroupByDate.class);

	//	if (gbd != null) this.GroupByItemList.add(orderbyitemStr);
		String uifld=getUifld(fld);
		
		return this;

	}
	
	/**
	@author attilax 老哇的爪子
		@since  o9r 1_43_59   
	
	 * @param fld
	 * @return
	 */
	private String getUifld(Field fld) {
		// attilax 老哇的爪子  1_43_59   o9r
	//	CountRelt gbd = fld.getAnnotation(CountRelt.class);
		
		Conditional cdt= fld.getAnnotation(Conditional.class);
		if(cdt!=null)
		{
			String uifld = cdt.uifld();
			if(uifld.length()==0)
				uifld=fld.getName();
			return uifld;	
		}
		return fld.getName();
		
	}



	/** @author attilax 老哇的爪子
	 * @since o9p 5_53_0
	 * 
	 * @param fld */
	@Deprecated
	@SuppressWarnings("all") public SqlAti addWhereExp(Field fld) {
		// attilax 老哇的爪子 5_53_0 o9p
		//String colName = CountX.getColumnName( fld.getName(),this.cls);
		//if (fld.getName().equals("playTime")) System.out.println("4dbg");
		CriteriaRelt an=fld.getAnnotation(CriteriaRelt.class);
		if(an!=null)
		{
			Criteria  crSub=	this.Criteria.createCriteria(fld.getName());
			crSub.add( Restrictions.eq(an.fld(),  this.reqMap.get(an.fld())));
			
		}
	 
		
		Conditional cdt = fld.getAnnotation(Conditional.class);
		if (cdt == null)
			return this;
	 
 		if (cdt.adptr() != None.class) // cstm mode
		{
//			List<Criterion> li = fld2critAdapt(reqMap,  fld,cdt);
//			addExpresss(c, li);
		} else {   //def mode  common mode :::   fld>=value  fld_op_val

 		List<Criterion> exprsLi = getExprs(fld, cdt.op(), reqMap);
//			core.log(String.format("--o7f1: dbg fldinfo: %s---%s ", fldName, reqMap.get(fldName)));
 			addExpresss(this.Criteria, exprsLi);
		}
 		
		return this;
	}
	
	
	/** @author attilax 老哇的爪子 \t@since Jul 20, 2014 7:19:04 AM$
	 * 
	 * @param fldName
	 * @param op
	 * @param object
	 * @return
	 * @throws cantFindMatchFieldException
	 * @throws CantFindConverterExcept */
	public List getExprs(Field fld, String op, Map valMap) {
		
		String colName = CountX.getColumnName( fld.getName(),this.cls);
		final String fldName=fld.getName();
		
		if (fldName.equals("equipmentId")) 
			System.out.println("dbg");
		if (fldName.equals("playtime")) System.out.println("");

		// attilax 老哇的爪子 7:19:04 AM Jul 20, 2014
		List li = new ArrayList();
		String uifld=getUifld(fld);
		Object oriVal = valMap.get(uifld);
		Object cvtEdVal = oriVal;

		// ---if rang processed.. o9o jyad anno
		if (MdaUtil.isDsplyRange_RE(fldName, this.cls))
			return   getExprs_rang(op, valMap, fldName, li);
 

		// def single mode process o9o
		// --------------------except is fld null/empty ret null
		try {
		//	Field fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {

				if (oriVal == null || oriVal.toString().equals("") || oriVal.toString().equals("0")) return li; // jump
			}
			if (refx.isStrType(fld)) {
				if (oriVal == null || oriVal.toString().equals("")) return li;// jump
			}
		} catch (Exception e1) {
			// attilax 老哇的爪子 8:00:02 AM Jul 20, 2014
			e1.printStackTrace();
			return li;// jump
		}

		// ------------covert default

	//	Field fld = null;
		try {
			fld = refx.getField(fldName, this.cls);
			// if(refx.isIntType(fld))
			// {
			// cvtEdVal=core.toInt(oriVal);
			// }
			// if(refx.isStrType(fld))
			// {
			// cvtEdVal=core.toInt(oriVal);
			// }
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 9:59:29 AM Jul 20, 2014
			e1.printStackTrace();
		}

		// --------------covert kastm
		{

			org.apache.commons.beanutils.Converter cvt;
			try {
				this.saveObjClass=this.cls;
				cvt = getConvert(fldName);
				 
				cvtEdVal = cvt.convert(null, oriVal);
			} catch (CantFindConverterExcept e) {
				// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
				// e.printStackTrace();
				//core.log(e);
				// cvtEdVal=oriVal;
			} catch (cantFindMatchFieldException e) {
				// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
				// e.printStackTrace();
				// cvtEdVal=oriVal;
				core.log(e);
			}

			if (op.equals(com.attilax.anno.op.like)) li.add(Restrictions.like(colName, "%" + cvtEdVal.toString() + "%"));
			else if (op.equals(com.attilax.anno.op.range)) {
				String ExpRitVal = "";
				if (refx.isStrType(fld)) ExpRitVal = "'" + cvtEdVal + "'";
				li.add((colName + op + ExpRitVal));
				li.add((colName + op + ExpRitVal));
			} else // def
			{
			 	Criterion crit= metaData2Criterion_Single(fldName,op,cvtEdVal,fld)  ;
				li.add(crit);
			}
			return li;
		}

	}



	/**
	@author attilax 老哇的爪子
		@since  o07 m_6_n   
	
	 * @param fldName
	 * @param op
	 * @param cvtEdVal
	 * @return
	 */
	private Criterion metaData2Criterion_Single(String fldName, String op, Object cvtEdVal,Field fld) {
		// attilax 老哇的爪子  m_6_n   o07 
	
		if(cvtEdVal.toString().split(",").length>0 )
		{
			if(refx.isIntType(fld))
			{
			 
			 return  Restrictions.in(fldName, com.attilax.corePkg.CvtX.toIntArr(cvtEdVal));
			}
		}
		//set def full fmt
		if(refx.isTimestampType(fld))
		{
			
			cvtEdVal=getCriterionFmtV(cvtEdVal,fld);
//			if(op.equals(com.attilax.anno.op.gtet))
//				return  Restrictions.gt(fldName, tst);
//			if(op.equals(com.attilax.anno.op.ltet))
//			  return  Restrictions.lt(fldName, tst);
		}
		if(op.equals(com.attilax.anno.op.gtet))
			return  Restrictions.gt(fldName, cvtEdVal);
		if(op.equals(com.attilax.anno.op.ltet))
		  return  Restrictions.lt(fldName, cvtEdVal);
			 
		return Restrictions.eq(fldName, cvtEdVal);
		
	}



	/**
	@author attilax 老哇的爪子
		@since  o08 2_42_58   
	
	 * @param cvtEdVal
	 * @param fld 
	 * @return
	 */
	private Object getCriterionFmtV(Object cvtEdVal, Field fld) {
		
		if(refx.isTimestampType(fld))
		{
		// attilax 老哇的爪子  2_42_58   o08 
		if(cvtEdVal.toString().trim().length()<=10)
			cvtEdVal=cvtEdVal+" 00:00:00";	
		Timestamp tst=DateUtil.timestampO9(cvtEdVal.toString());
		return tst;
		}
		return null;
		
	}



	private List getExprs_rang(String op, Map valMap, final String fldName, List li) {
		try {
		//	if (MdaUtil.isDsplyRange(fldName, this.saveObjClass)) {
				if (valMap.get(fldName + "_start").toString().equals(valMap.get(fldName + "_end"))) return new ArrayList();
				List fstExprs = fstExprs(fldName, valMap);

				List sekdExprs = sekdExprs(fldName, op, valMap);
				li.addAll(fstExprs);
				li.addAll(sekdExprs);
				return li;
		 	}catch(Exception e)
		 	{
		 		throw new RuntimeException(e);
		 	}
	 
	}
	
	
	/** @author attilax 老哇的爪子 \t@since Jul 20, 2014 10:23:16 AM$
	 * 
	 * @param fldName
	 * @param op
	 * @param valMap
	 * @return */
	private List fstExprs(String fldName, Map valMap) {
	 
		// attilax 老哇的爪子 10:23:16 AM Jul 20, 2014

		Object oriVal = valMap.get(fldName + "_start");
		List li = new ArrayList();
		Object cvtEdVal = oriVal;

		// --------------------except
		try {
			Field fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {

				if (oriVal == null || oriVal.toString().equals("") || oriVal.toString().equals("0")) return li; // jump
			}
			if (refx.isStrType(fld)) {
				if (oriVal == null || oriVal.toString().equals("")) return li;// jump
			}
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 8:00:02 AM Jul 20, 2014
			e1.printStackTrace();
			return li;// jump
		}

		// ------------covert default
		// yaos time cant err magwesyi..
		Field fld;

		try {
			fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {
				cvtEdVal = core.toInt(oriVal);
			}
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 9:59:29 AM Jul 20, 2014
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// --------------covert

		org.apache.commons.beanutils.Converter cvt;
		try {
			cvt = getConvert(fldName);
			cvtEdVal = cvt.convert(null, oriVal);
		} catch (CantFindConverterExcept e) {
			// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
			e.printStackTrace();
			// cvtEdVal=oriVal;
		} catch (cantFindMatchFieldException e) {
			// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
			e.printStackTrace();
			// cvtEdVal=oriVal;
		}

		// if(op.equals(com.attilax.anno.op.like))
		// li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%"));
		// else if(op.equals(com.attilax.anno.op.range))
		// {
		li.add(fldName + ">=" + cvtEdVal);
		// li.add(Restrictions.lt(fldName, cvtEdVal ));
		// }
		// else
		// {
		// li.add( Restrictions.eq(fldName, cvtEdVal));
		// }
		return li;

	}

	/** @author attilax 老哇的爪子 \t@since Jul 20, 2014 10:23:20 AM$
	 * 
	 * @param fldName
	 * @param op
	 * @param valMap
	 * @return */
	private List sekdExprs(String fldName, String op, Map valMap) {
		// attilax 老哇的爪子 10:23:20 AM Jul 20, 2014

		Object oriVal = valMap.get(fldName + "_end");
		List li = new ArrayList();
		Object cvtEdVal = oriVal;

		// --------------------except
		try {
			Field fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {

				if (oriVal == null || oriVal.toString().equals("") || oriVal.toString().equals("0")) return li; // jump
			}
			if (refx.isStrType(fld)) {
				if (oriVal == null || oriVal.toString().equals("")) return li;// jump
			}
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 8:00:02 AM Jul 20, 2014
			e1.printStackTrace();
			return li;// jump
		}

		// ------------covert default

		Field fld;

		try {
			fld = refx.getField(fldName, this.saveObjClass);
			if (refx.isIntType(fld)) {
				cvtEdVal = core.toInt(oriVal);
			}
		} catch (cantFindMatchFieldException e1) {
			// attilax 老哇的爪子 9:59:29 AM Jul 20, 2014
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("");
		}

		// --------------covert

		org.apache.commons.beanutils.Converter cvt;
		try {
			cvt = getConvert(fldName);
			cvtEdVal = cvt.convert(null, oriVal);
		} catch (CantFindConverterExcept e) {
			// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
			e.printStackTrace();
			// cvtEdVal=oriVal;
		} catch (cantFindMatchFieldException e) {
			// attilax 老哇的爪子 7:50:33 AM Jul 20, 2014
			e.printStackTrace();
			// cvtEdVal=oriVal;
		}

		// if(op.equals(com.attilax.anno.op.like))
		// li.add(Restrictions.like(fldName, "%" + cvtEdVal .toString()+ "%"));
		// else if(op.equals(com.attilax.anno.op.range))
		// {
		// li.add(Restrictions.gt(fldName, cvtEdVal ));
		li.add((fldName + "<=" + cvtEdVal));
		// }
		// else
		// {
		// li.add( Restrictions.eq(fldName, cvtEdVal));
		// }
		return li;

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

}

//  attilax 老哇的爪子