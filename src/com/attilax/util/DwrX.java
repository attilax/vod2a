/**
 * @author attilax 老哇的爪子
	@since  o03 1_52_9$
 */
package com.attilax.util;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContextFactory;
/**
 * @author  attilax 老哇的爪子
 *@since  o03 1_52_9$
 */
public class DwrX {

	/**
	@author attilax 老哇的爪子
		@since  o03 1_52_h   
	
	 */
	public static HttpSession getSession() {
		// attilax 老哇的爪子  1_52_h   o03 
		return  WebContextFactory.get().getSession();
	}
	public static HttpServletRequest getReq() {
		// attilax 老哇的爪子  1_52_h   o03 
		return  WebContextFactory.get().getHttpServletRequest();
	}
	
	public static HttpServletResponse getResp() {
		// attilax 老哇的爪子  1_52_h   o03 
		return  WebContextFactory.get().getHttpServletResponse();
	}
	//  attilax 老哇的爪子 1_52_9   o03   
}

//  attilax 老哇的爪子