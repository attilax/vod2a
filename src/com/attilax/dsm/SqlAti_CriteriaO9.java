/**
 * @author attilax 老哇的爪子
	@since  o9q 5_53_40$
 */
package com.attilax.dsm;
import com.attilax.core;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
import com.attilax.count.CountRelt;
import com.attilax.count.CountX;
import com.attilax.count.GroupBy;
import com.attilax.count.GroupByDate;
import com.attilax.lang.ref.None;

 import static  com.attilax.core.*;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
 
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
/**
 * @author  attilax 老哇的爪子
 *@since  o9q 5_53_40$
 */
public class SqlAti_CriteriaO9 extends SqlAti_Criteria {

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
	public SqlAti_CriteriaO9 setCriteria(Criteria criteria) {
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
		CountRelt gbd = fld.getAnnotation(CountRelt.class);
		
		return null;
		
	}



	/** 
	 * deprecate cause CriteriaRelt too simple ...ref  SqlAti_Criteria
	 * @author attilax 老哇的爪子
	 * @since o9p 5_53_0
	 * 
	 * @param fld */
	 
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

//			List<Criterion> exprsLi = getExprs(fldName, cdt.op(), reqMap);
//			core.log(String.format("--o7f1: dbg fldinfo: %s---%s ", fldName, reqMap.get(fldName)));
//			addExpresss(c, exprsLi);
		}
 		
		return this;
	}

}

//  attilax 老哇的爪子