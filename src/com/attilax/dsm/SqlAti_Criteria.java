/**
 * @author attilax 老哇的爪子
	@since  o9q 5_53_40$
 */
package com.attilax.dsm;
import com.attilax.core;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
import com.attilax.count.CountX;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
 import static  com.attilax.core.*;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import  com.attilax.lang.ref.None;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
/**
 * @author  attilax 老哇的爪子
 *@since  o9q 5_53_40$
 */
@Deprecated
public class SqlAti_Criteria extends SqlAti {

/**
 * 
 */
	public String toSql() {
		 try {
			 System.out.println("a");
			 Field idfld=refx.getIdFld_RE(this.cls);
				this.Criteria.addOrder(Order.desc(idfld.getName()));
		} catch (Exception e) {
			core.log(e);
		}
	 
			
	 
		
		return "";
		
	}
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
	public SqlAti_Criteria setCriteria(Criteria criteria) {
		Criteria = criteria;return this;
	}

	public Criteria Criteria;
	//  attilax 老哇的爪子 5_53_40   o9q   
	
	/** @author attilax 老哇的爪子
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
			String subobjFldName = an.fld();
			if(subobjFldName.length()==0)
			{
				Class subObjType=fld.getType();
				 
					subobjFldName= 	refx.getIdFld_RE(subObjType).getName();
			 
			}
			String uifld=an.uiFld();
			if(uifld.length()==0)
				uifld=subobjFldName;
				
		
			
			Criteria  crSub=	this.Criteria.createCriteria(fld.getName(),JoinType.LEFT_OUTER_JOIN);
			
			Object object = this.reqMap.get(uifld);
		 
			if(isValidVal(fld, object))
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
					crSub.add( Restrictions.in(subobjFldName, covertToIntList(object) ));
				else //def
				{
					Object obj= covertToFldtypeFmt(subObjfld,object);
					crSub.add( Restrictions.eq(subobjFldName,  obj));
				}
					
			 
			}
			
		}
	 
		return this;
	}
	
	
		/**1,2,3
		@author attilax 老哇的爪子
		@since   oab i_54_37
		 
		 */
	private List  covertToIntList(Object object) {
		List li=new ArrayList ();
		 String[] a=object.toString().split(",");
		 for (String id_s : a) {
			 try {
				 Integer id= Integer.parseInt(id_s);
				 li.add(id);
			} catch (Exception e) {
				core.err(e);
			}
			
		}
		 
		return li;
		//return null;
	}
	/**
	@author attilax 老哇的爪子
		@since  o9r h_x_51   
	
	 * @param subObjfld
	 * @param object
	 * @return
	 */
	private Object covertToFldtypeFmt(Field fld, Object object) {
		// attilax 老哇的爪子  h_x_51   o9r 
		if(refx.isIntType(fld))
			return Integer.parseInt(object.toString());
		return null;
		
	}

	public static boolean isValidVal(Field fld, Object oriVal)
	{
		 
			// attilax 老哇的爪子  k_z_n   o8h 
			 
	 
		//=queryPropertyssMap.get(fld.getName());
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

}

//  attilax 老哇的爪子