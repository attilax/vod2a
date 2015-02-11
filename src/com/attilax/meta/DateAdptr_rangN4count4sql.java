/**
 * @author attilax 老哇的爪子
\t@since  Jul 18, 2014 10:10:38 PM$
 */
package com.attilax.meta;

import com.attilax.core;
import com.attilax.MDA.IAdapter;
import com.attilax.count.CountX;
import com.attilax.dsm.SqlAti;
import com.attilax.time.timeUtil;
import com.attilax.util.DateUtil;
import static com.attilax.core.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
/** @author attilax 老哇的爪子
 * @since Jul 18, 2014 10:10:38 PM$ */
public class DateAdptr_rangN4count4sql implements IAdapter {
	// attilax 老哇的爪子 10:10:38 PM Jul 18, 2014
	public static void main(String[] args) {

	}

	private Class<?> cls;

	/** @author attilax 老哇的爪子 \t@since Jul 18, 2014 10:16:53 PM$ */
	public List convert(String propertyName, String valFrmUi) {
		// attilax 老哇的爪子 10:16:53 PM Jul 18, 2014
		java.util.List li = new ArrayList();
		// Object[] a=(Object[]) obj;
		// String propertyName=(String) a[0];
		// String valFrmUi=(String) a[1];
		{
			String s = valFrmUi.toString();
			String s1 = s + " 00:00:01";
			String s2 = s + " 23:59:59";
			;
			Timestamp ts1;
			Timestamp ts2;

			try {
				ts1 = DateUtil.toTimeStamp(s1, true);

				ts2 = DateUtil.toTimeStamp(s2, true);
				SimpleExpressionAti startCondt = RestrictionsAti.gt(propertyName, ts1);
				li.add(startCondt.toSqlFmt());
				li.add(RestrictionsAti.lt(propertyName, ts2).toSqlFmt());
			} catch (ParseException e) {
				// attilax 老哇的爪子 1:28:28 AM Jul 19, 2014
				core.log(e);
			}

		}
		return li;

	}

	/* (non-Javadoc)
	 * 
	 * @see com.attilax.MDA.IAdapter#convert(java.lang.reflect.Field,
	 * java.util.Map)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since o9p i_42_56$ */
	@Override public List convert(Field fld, Map queryPropertyssMap) {
		// attilax 老哇的爪子 i_42_56 o9p
		this.cls=SqlAti.cls_thrdLoc.get();
		java.util.List li = new ArrayList();
		//String propertyName = fld.getName();
		String colName = CountX.getColumnName( fld.getName(),this.cls);
		String	propertyName=colName;
		
		try {
			Object startFldVal = queryPropertyssMap.get(fld.getName() + "_start");
			if (startFldVal == null || startFldVal.equals("")) {

			} else {
				String s1 = (String) startFldVal + " 00:00:00";
				SimpleExpressionAti startCondt = RestrictionsAti.gt(propertyName, s1);
				li.add(startCondt.toSqlFmt());
			}
		} catch (Exception e) {
			// attilax 老哇的爪子 1:28:28 AM Jul 19, 2014
			core.log(e);
		}

		try {
			Object endFldV = queryPropertyssMap.get(fld.getName() + "_end");
			if (endFldV != null && endFldV.toString().trim().length() > 0) {
				String s2 = (String) endFldV + " 23:59:59";
				li.add(RestrictionsAti.lt(propertyName, s2).toSqlFmt());
			}

		} catch (Exception e) {
			// attilax 老哇的爪子 1:28:28 AM Jul 19, 2014
			core.log(e);
		}

		return li;

	}
}

// attilax 老哇的爪子