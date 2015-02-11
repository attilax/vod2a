/**
 * @author attilax 老哇的爪子
\t@since  Jul 18, 2014 10:10:38 PM$
 */
package com.attilax.dsm.adapt;
 

import com.attilax.core;
import com.attilax.MDA.IAdapter;
import com.attilax.text.strUtil;
import com.attilax.time.timeUtil;
import com.attilax.util.DateUtil;

import static  com.attilax.core.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
/**
 * @author  attilax 老哇的爪子
 *@since  Jul 18, 2014 10:10:38 PM$
 */
public class TimestampStartAdptr implements IAdapter   {
	//  attilax 老哇的爪子 10:10:38 PM   Jul 18, 2014   
	public static void main(String[] args) {
		 
		
	}
	
	/**
	@author attilax 老哇的爪子
	\t@since  Jul 18, 2014 10:16:53 PM$

	 */
	@Deprecated
	public List convert(	String propertyName,String valFrmUi) {
		// attilax 老哇的爪子  10:16:53 PM   Jul 18, 2014 
		java.util.List  li=new ArrayList ();
 
	 	try {
				Timestamp ts1;
				ts1 = DateUtil.toTimeStamp(valFrmUi, true);
			 
		
			 
				SimpleExpression startCondt = Restrictions.gt(propertyName, ts1);
				li.add(startCondt);
			 
			} catch (ParseException e) {
				//  attilax 老哇的爪子 1:28:28 AM   Jul 19, 2014   
				core.log(e);
			}
 
		return li;
		 

	}

	/* (non-Javadoc)
	 * @see com.attilax.MDA.IAdapter#convert(java.lang.reflect.Field, java.util.Map)
	 * @author  attilax 老哇的爪子
	 *@since  o9p i_42_46$
	 */
	@Override
	public List convert(Field fld, Map queryPropertyssMap) {
		// attilax 老哇的爪子 i_42_46 o9p
		// attilax 老哇的爪子 10:16:53 PM Jul 18, 2014
		java.util.List li = new ArrayList();

		{
			String uiName_start = fld.getName() + "_start";
			String uiName_end = fld.getName() + "_end";
			String valFrmUi_start=strUtil.val(   queryPropertyssMap.get(uiName_start));
			String valFrmUi_end=strUtil.val( queryPropertyssMap.get(uiName_end));
			// String s=valFrmUi.toString();
			String s1 = valFrmUi_start + " 00:00:01";
			String s2 = valFrmUi_end + " 23:59:59";
			;
			Timestamp ts1 = null;
			Timestamp ts2;

			try {
				if (valFrmUi_start!=null && valFrmUi_start.trim().length() > 0)
				{
					ts1 = DateUtil.toTimeStamp(s1, true);
					SimpleExpression startCondt = Restrictions.gt(fld.getName(),
						ts1);
					li.add(startCondt);
				}

			} catch (ParseException e) {
				 
				core.log(e);
			}

			try {
				if (valFrmUi_end!=null && valFrmUi_end.trim().length() > 0) {
					ts2 = DateUtil.toTimeStamp(s2, true);

					li.add(Restrictions.lt(fld.getName(), ts2));
				}
			} catch (Exception e) {
				core.log(e);
			}

		}
		return li;

	}
}

//  attilax 老哇的爪子