/**
 * @author attilax 老哇的爪子
	@since  o07 m_47_55$
 */
package com.attilax.corePkg;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o07 m_47_55$
 */
public class CvtX {

	/**
	@author attilax 老哇的爪子
		@since  o07 m_49_t   
	
	 * @param cvtEdVal
	 */
	public static Integer[] toIntArr(Object cvtEdVal) {
		// attilax 老哇的爪子  m_49_t   o07 
		String[] oa=cvtEdVal.toString().split(",");
		
		List li=new ArrayList();
for (String s : oa) {
			if(s.trim().length()>0)
				li.add(  Integer.parseInt(s.trim()));
		}


return   (Integer[]) li.toArray(new Integer[li.size()]);
		 
		
	}
	//  attilax 老哇的爪子 m_47_55   o07   

	/**
	@author attilax 老哇的爪子
		@since  o07 m_52_t   
	
	 * @param li
	 * @return
	 */
	private static Object toArr(List li) {
		// attilax 老哇的爪子  m_52_t   o07 
		
		return null;
		
	}
}

//  attilax 老哇的爪子