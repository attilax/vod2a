/**
 * @author attilax 老哇的爪子
\t@since  Aug 16, 2014 10:40:44 PM$
 */
package com.attilax.Stream;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollectionUtils;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Aug 16, 2014 10:40:44 PM$
 */
public class redusUtil {

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 16, 2014 10:40:55 PM$
	
	 * @param li
	 * @return
	 */	@SuppressWarnings("all")
	public static reduceCalcRecord leftjoin(List<reduceCalcRzt_singleExprs> li) {
		// attilax 老哇的爪子  10:40:55 PM   Aug 16, 2014 
		reduceCalcRecord o=new reduceCalcRecord();
		final Map m=new HashMap();
		o.map=m;
		CollectionUtils.each_NS(li, new Closure<reduceCalcRzt_singleExprs, Object>() {

		
			@Override
			public Object execute(reduceCalcRzt_singleExprs rzt)
					throws Exception {
				// attilax 老哇的爪子  10:59:47 PM   Aug 16, 2014 
				
				{ 
					m.putAll(rzt.map);
				return null;
				 } 
				
				
			}
		});
		{ 
		return o;
		 } 
		
		
	}
	//  attilax 老哇的爪子 10:40:44 PM   Aug 16, 2014   
}

//  attilax 老哇的爪子