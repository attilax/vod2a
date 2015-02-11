/**
 * @author attilax 老哇的爪子
	@since  o0c h_j_43$
 */
package com.attilax.web;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
/**
 * @author  attilax 老哇的爪子
 *@since  o0c h_j_43$
 */
public class ReqX {

	/**
	@author attilax 老哇的爪子
	 * @param request 
		@since  o0c h_j_50   
	
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(HttpServletRequest request, String param) {
		// attilax 老哇的爪子  h_j_50   o0c 
		if(request.getParameter(param)!=null && request.getParameter(param).length()>0)
		return true;
		return false;
		
	}
	//  attilax 老哇的爪子 h_j_43   o0c   
}

//  attilax 老哇的爪子