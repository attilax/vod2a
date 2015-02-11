/**
 * 
 */
package com.attilax.web;

import javax.servlet.http.HttpServletRequest;

import com.attilax.core;
import com.attilax.io.filex;

/**
 * @author ASIMO
 *
 */
public class StrutsX {

	/**
	@author attilax 老哇的爪子
	@since   oag h_k_h
	 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void saveERRLog(HttpServletRequest req) {
		try {
			 Exception e=(Exception) req.getAttribute("exception");
			 filex.saveLog(e, "c:\\e","gbk");
		} catch (Exception e) {
			 filex.saveLog(e, "c:\\e","gbk");
		}
		

	}

}
