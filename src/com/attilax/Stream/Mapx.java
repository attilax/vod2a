/**
 * @author attilax 老哇的爪子
	@since  o8e 2_5_m$
 */
package com.attilax.Stream;
import com.attilax.core;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @author  attilax 老哇的爪子
 *@since  o8e 2_5_m$
 */
public class Mapx<k,v> {

	private Map<k,v> mp;

	/**
	@author attilax 老哇的爪子
		@since  o8e 2_6_a$
	
	 * @param m
	 */
	public Mapx(Map<k,v> m) {
		//  attilax 老哇的爪子 2_6_a   o8e   
		this.mp=m;
	}

	/**
	@author attilax 老哇的爪子
		@since  o8e 2_5_v   
	
	 * @param m
	 * @return
	 */
	public static <k2,v2> Mapx<k2,v2> $(Map<k2,v2> m) {
		// attilax 老哇的爪子  2_5_v   o8e 
		return new Mapx<k2,v2>(m);
		
	}
	//  attilax 老哇的爪子 2_5_m   o8e   

	/**
	@author attilax 老哇的爪子
		@since  o8e 2_6_x   
	
	 * @param fld
	 * @return
	 */
	public Val get(final String[] fld) {
		// attilax 老哇的爪子  2_6_x   o8e 
		return new Val(){{
		//	Object[] v=new Object[fld.length];
		
			for (String f : fld) {
				li. add( mp.get(f));
			}
			
			
		}};
		
	}
	
	public String  toString()
	{
		return JSONObject.fromObject(mp).toString(2);
		
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 5:11:44 PM$
	
	 * @return
	 */
	public static <k,v> Mapx<k,v> $() {
		// attilax 老哇的爪子  5:11:44 PM   Aug 23, 2014 
		
		return new Mapx<k,v>(new HashMap<k,v>());
		
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 5:13:06 PM$
	
	 * @param string
	 * @param string2
	 * @return
	 */
	public Mapx<k,v> add(k key, v value) {
		// attilax 老哇的爪子  5:13:06 PM   Aug 23, 2014 
		
		{ 
			this.mp.put(key, value);
		return this;
		 } 
		
		
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 5:13:45 PM$
	
	 * @return
	 */
	public Map<k,v> toMap() {
		// attilax 老哇的爪子  5:13:45 PM   Aug 23, 2014 
		
		{ 
		return this.mp;
		 } 
		
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o01 3_c_45   
	
	 * @param map
	 * @param string
	 * @return
	 */
	public static Object get(Map map, String key) {
		// attilax 老哇的爪子 3_c_45 o01
		try {
			String[] sa = key.split("\\.");
			Map now = map;
			int n = 0;
			for (String s : sa) {
				//System.out.println("--key:"+s);
				//System.out.println("--now:"+core.toJsonStrO88(now));
				if (isLast(sa, n)) 
					return now.get(s);
				else 
					now = (Map) now.get(s);
				n++;
			}
			return null;
		} catch (Exception e) {
			core.warn(e);return "";
		}
		

	}

	/**
	@author attilax 老哇的爪子
		@since  o01 3_g_s   
	
	 * @param sa
	 * @param n
	 * @return
	 */
	private static boolean isLast(String[] sa, int n) {
		// attilax 老哇的爪子  3_g_s   o01 
		if(n==sa.length-1) return true;
		return false;
		
	}
}

//  attilax 老哇的爪子