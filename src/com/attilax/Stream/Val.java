/**
 * @author attilax 老哇的爪子
	@since  o8e 1_59_46$
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
 *@since  o8e 1_59_46$
 */
public class Val {
	List li=new ArrayList();
	

	public String  toString()
	{
		return JSONArray.fromObject(li).toString(2);
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o8e 1_59_55   
	
	 * @param fld
	 * @param m 
	 * @return
	 */
//	public static Val get(final String[] fld, final Map m) {
//		// attilax 老哇的爪子  1_59_55   o8e 
//		return new Val(){{
//			Object[] v=new Object[fld.length];
//		
//			for (String f : fld) {
//				li.add(m.get(f));
//			}
//			
//			
//		}};
//		
//	}
	//  attilax 老哇的爪子 1_59_46   o8e   
}

//  attilax 老哇的爪子