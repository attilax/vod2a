/**
 * @author attilax 老哇的爪子
	@since  o0l j_5_u$
 */
package com.attilax.net;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.apache.http.client.utils.URLEncodedUtils;
import weibo4j.util.URLEncodeUtils;
/**
 * @author  attilax 老哇的爪子
 *@since  o0l j_5_u$
 */
public class urlEncode {

	/**
	@author attilax 老哇的爪子
		@since  o0l j_5_u   
	
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("deprecation") public static void main(String[] args) throws UnsupportedEncodingException {
		// attilax 老哇的爪子  j_5_u   o0l 
		URLEncoder.encode("");
	String s=	URLEncoder.encode("a  b", "utf-8");//a++b
	   s = org.tuckey.web.filters.urlrewrite.utils.URLEncoder.encodePathParam("a  b", "utf-8");//a%20%20b
	 //  s=URLEncodedUtils.   mayon
	 s=  URLEncodeUtils.encodeURL("a  b");// not cant  a++b
	System.out.println(s);
	System.out.println(URLDecoder.decode("a++b%20c"));   //a  b c 
	System.out.println(URLEncoder.encode("a+b"));  //a%2Bb

	}
	//  attilax 老哇的爪子 j_5_u   o0l   
}

//  attilax 老哇的爪子