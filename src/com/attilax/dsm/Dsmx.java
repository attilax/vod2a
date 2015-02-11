/**
 * @author attilax 老哇的爪子
	@since  o02 l_n_n$
 */
package com.attilax.dsm;
import com.attilax.core;
import com.attilax.MDA.IAdapter;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
import com.attilax.anno.DataType;
import com.attilax.anno.Orderby;
import com.attilax.collection.CollX;
import com.attilax.dsm.anno.NoFilt4condQuery;
import com.attilax.io.filex;
import com.attilax.lang.ref.None;
import com.attilax.ref.refx;
import com.focusx.downtask.GvDownloadTask;

 import static  com.attilax.core.*;
import java.util.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;

import javax.persistence.Transient;
 
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
/**
 * @author  attilax 老哇的爪子
 *@since  o02 l_n_n$
 */
public class Dsmx {

	/**
	@author attilax 老哇的爪子
		@since  o02 l_n_s   
	
	 * @param fld
	 * @return
	 */
	public static String getDatatype(Field fld) {
		// attilax 老哇的爪子  l_n_s   o02 
		DataType dt=fld.getAnnotation(DataType.class);
		if(dt!=null)
		{
			return dt.value();
		}
		return "";
		
	}
	//  attilax 老哇的爪子 l_n_n   o02   

	/**
	@author attilax 老哇的爪子
		@since  o02 l_58_43   
	
	 * @param class1
	 * @return
	 */
	public static String getDwrx(Class<?> class1) {
		// attilax 老哇的爪子  l_58_43   o02 
		RemoteProxy a=class1.getAnnotation(RemoteProxy.class);
		
		return a.name();
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o08 2_t_52   
	
	 * @return
	 */
	public static String getUifld(Field fld) {
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

		/**
		@author attilax 老哇的爪子
		@since   oa7 a_0_m
		 
		 */
	public static boolean isNotFilt4condQueryFld(Field fld) {
		 	// attilax 老哇的爪子  6_9_p   o9q 
			
			return fld.getAnnotation(NoFilt4condQuery.class)!=null;
			
		 
	}
	
	public static Object covertToFldtypeFmt(Field fld, Object object) {
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

		/**
		@author attilax 老哇的爪子
		@since   oaj h_2_50
		 
		 */
	public static Field getOrderbyfld(Class<?> class1) {
		Field[] flds = class1.getDeclaredFields();
	    filex.save_SF(core.toJsonStrO88(CollX.toList(flds)), "c:\\oaf2.txt");	
		for (Field fld : flds) {
			Orderby cdt= fld.getAnnotation(Orderby.class);
			if(cdt!=null)
			{
				return fld;
			}
		}
		return null;
	}

			/**
			@author attilax 老哇的爪子
			@since   oaj h_j_k
			 
			 */
	public static void setOrder(Criteria c, Field orderbyFld) {
		Orderby cdt = orderbyFld.getAnnotation(Orderby.class);
		Annotation trans= orderbyFld.getAnnotation(Transient.class);
		if(trans!=null)return;
		if (cdt.value().equals("desc"))
			c.addOrder(Order.desc(orderbyFld.getName()));
		else
			c.addOrder(Order.asc(orderbyFld.getName()));

	}

				/**
				@author attilax 老哇的爪子
				@since   oap h_i_m
				 
				 */
			public static IAdapter getAdptr4Field(Field fld) {
				
						Conditional cdt = fld.getAnnotation(Conditional.class);
					if(	cdt.adptr() != None.class )
					{
						IAdapter adpt = (IAdapter) core.newx(cdt.adptr());
					
						return adpt;
					}
				return null;
			}

					/**
					@author attilax 老哇的爪子
					@since   ob2 9_47_58
					 
					 */
				public static boolean isNullval(Object cvtEdVal, Field fld) {
					 
						 Conditional cdt=fld.getAnnotation(Conditional.class);
						 if(cdt!=null)
						 {
							 if(cvtEdVal.toString().equals( String.valueOf(cdt.nullval())))
									 return true;
						 }
						return false;
					}

	
	 
}

//  attilax 老哇的爪子