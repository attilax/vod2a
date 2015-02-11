/**
 * @author attilax 老哇的爪子
	@since  o7f iuz$
 */
package com.attilax.crud;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.listUtil;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o7f iuz$
 */
public class ListPageUtil {

	/**
	@author attilax 老哇的爪子
		@since  o7f iu52$
	
	 * @param ids
	 * @param closure
	 */
	public static void delete_ByIDs(Object ids, Closure closure) {
		// attilax 老哇的爪子  iu52   o7f 
		List<String> li=listUtil.fromStr( ids);
		CollectionUtils.each_safe(li, closure );
		
	}
	//  attilax 老哇的爪子 iuz   o7f   
}

//  attilax 老哇的爪子