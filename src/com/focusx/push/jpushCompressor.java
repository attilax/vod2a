/**
 * @author attilax 老哇的爪子
	@since  o86 j_43_a$
 */
package com.focusx.push;
import com.attilax.core;
import com.attilax.compass.ZLibUtils;
import com.attilax.util.ZipStrUtil;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o86 j_43_a$
 */
public class jpushCompressor {
public static int kmprsLenMin = 860;
	/**
	@author attilax 老哇的爪子
		@since  o86 j_43_n$
	
	 * @param s
	 * @return
	 */
	public static String kmprs(String s) {
		// attilax 老哇的爪子  j_43_n   o86 
		
		System.out.println("");
		if(s.length()>kmprsLenMin) 
		{
			 
			try {
				return  ZLibUtils.compress_iso8859(s);
			} catch (UnsupportedEncodingException e) {
				//  attilax 老哇的爪子 1_54_58   o8a   
				e.printStackTrace();
				core.log(e);
			}
		}
		return s;
		
	}
	//  attilax 老哇的爪子 j_43_a   o86   
	
	
	
}

//  attilax 老哇的爪子