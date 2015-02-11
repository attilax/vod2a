/**
 * @author attilax 老哇的爪子
	@since  o08 j_52_3$
 */
package com.attilax.util;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
/**
 * @author  attilax 老哇的爪子
 *@since  o08 j_52_3$
 */
public class StrutsX {

	/**
	@author attilax 老哇的爪子
		@since  o08 j_52_a   
	
	 * @return
	 */
	public static HttpServletRequest getReq() {
		// attilax 老哇的爪子  j_52_a   o08 
		
		return ServletActionContext.getRequest();
		
	}
	//  attilax 老哇的爪子 j_52_3   o08   
}

//  attilax 老哇的爪子