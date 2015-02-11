/**
 * @author attilax 老哇的爪子
	@since  2014-9-1 上午12:35:20$
 */
package com.attilax.api;
import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.net.websitex;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.apache.bsf.util.event.adapters.java_awt_event_ActionAdapter;
/**
 * @author  attilax 老哇的爪子
 *@since  2014-9-1 上午12:35:20$
 */
public class feedbackTest {

	/**
	@author attilax 老哇的爪子
		@since  2014-9-1 上午12:35:20   
	
	 * @param args
	 * @throws MalformedURLException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
	//tUrlencode();
		mtrDownback() ;
	    System.out.println(java.net.URLEncoder.encode("a  b","gb2312") );
	    //a++b
//	    解决办法2：
//
//	    传递前，先做以下替换
//
//	    sContent=sContent.replaceAll("  ","%20");
	    
	    
	//mtrback();
 //	sqlback();
		

	}
	//  attilax 老哇的爪子 上午12:35:20   2014-9-1   

	private static void tUrlencode() throws MalformedURLException {
		// attilax 老哇的爪子  上午12:35:20   2014-9-1 
		//	URLEncoder
		//	org.apache.commons.httpclient.URI
			//System.out.println(java);
			
			String urlString = "http://192.168.21.77:8080/swp/mainPage?aa=11&bb%3D22";  
		    URI uri = URI.create(urlString);  
		  //  uri.
		    System.out.println(uri.getPath());  
		    System.out.println(uri.getQuery());//解码  
		    URL url2 = new URL(urlString);  
		    System.out.println(url2.getQuery());//不解码
		//    /swp/mainPage
		//    aa=11&bb=22
		//    aa=11&bb%3D22
	}

	/**
	@author attilax 老哇的爪子
		@since  o9i 3_0_k   
	
	 */
	private static void sqlback() {
		// attilax 老哇的爪子  3_0_k   o9i 
	//	String url="http://localhost/vod/api.jsp?submethod=sql&appid=1234507y767676&sign=xxxxxx&param=select * from   xxxTAble wherer fld1=11 order by fld2";
		String hql = " ";   //select * from gv_publish where publish_id=1897
		hql=" from  GvPublish where publishId=3157";
		String t_urlencode=URLEncoder.encode(hql);
		String hostip;//localhost
		hostip="200.140.235.199:8888";
		String url="http://"+hostip+"/vod/api.jsp?submethod=hql&appid=1234507y767676&sign=xxxxxx&param="+t_urlencode;
		core.log(url);
	//	url=url.replace("localhost", "192.168.1.33");
		System.out.println(websitex.WebpageContent(url));;
	}

	private static void mtrback() {
		String f=pathx.classPath(feedbackTest.class)+"mtrBack.txt";
		String t=filex.read(f);
		String t_urlencode=URLEncoder.encode(t);
		String meth="postDownTaskBackfeed4notice";
		String url="http://localhost/vod/api.jsp?submethod="+meth+"&appid=1234507y767676&sign=xxxxxx&param="+t_urlencode;
		url="http://192.168.1.33/vod/api.jsp?sign=xxxx&submethod=postDownTaskBackfeed4notice&param=%7B%22dsId%22%3A1554%2C%22equipmentId%22%3A1081%7D&appid=863778010205109";
		System.out.println(websitex.WebpageContent(url));;
	}
	
	
	private static void mtrDownback() {
		String f=pathx.classPath(feedbackTest.class)+"downBack.json";
		String t=filex.read(f);
		String t_urlencode=URLEncoder.encode(t);
		String meth="postDownTaskBackfeed";
		String url="http://localhost/vod/api.jsp?submethod="+meth+"&appid=1234507y767676&sign=xxxxxx&param="+t_urlencode;
	//	url="http://192.168.1.33/vod/api.jsp?sign=xxxx&submethod=postDownTaskBackfeed4notice&param=%7B%22dsId%22%3A1554%2C%22equipmentId%22%3A1081%7D&appid=863778010205109";
		System.out.println(websitex.WebpageContent(url));;
	}
	
	private static void prgrmback() {
		String f=pathx.classPath(feedbackTest.class)+"prgrmBack.txt";
		String t=filex.read(f);
		String t_urlencode=URLEncoder.encode(t);
		String url="http://localhost/vod/api.jsp?submethod=pushProgramBackfeed&appid=1234507y767676&sign=xxxxxx&param="+t_urlencode;
		System.out.println(websitex.WebpageContent(url));;
	}
}

//  attilax 老哇的爪子