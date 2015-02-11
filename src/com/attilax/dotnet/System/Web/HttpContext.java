/**
 * 
 */
package com.attilax.dotnet.System.Web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ASIMO
 *
 */
public class   HttpContext {
	
	public static	 ThreadLocal< HttpContext> Current=new ThreadLocal<HttpContext>() ;
	
	public static  ThreadLocal<ServletRequest>	Request=new ThreadLocal<ServletRequest>();

}
