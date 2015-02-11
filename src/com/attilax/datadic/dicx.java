/**
 * @author attilax 老哇的爪子
	@since  o8h m_d_j$
 */
package com.attilax.datadic;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.Ireduce;
import com.attilax.io.pathx;
import com.attilax.net.NetUtil;
import com.attilax.net.requestImp;
import com.attilax.net.websitex;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @author  attilax 老哇的爪子
 *@since  o8h m_d_j$
 */
public class dicx {

	/**
	@author attilax 老哇的爪子
		@since  o8h m_d_j   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  m_d_j   o8h 
		requestImp ri=new requestImp();
		ri.setServerName("localhost");
		ri.setServerPort(80);
		ri.setContextPath("/vod");
		NetUtil.req_ThreadLocal.set(ri);
		System.out.println( dicdata("WJLX"));

	}
	
	public static String dicdata(String cate)
	{
		String apppath=pathx.webAppPath_httpFmt(NetUtil.req_ThreadLocal.get());
		String url=apppath+"/dictionaryJson/ajaxDictionaryList?code="+cate;
		String webpageContent =websitex. WebpageContent(url);
		JSONArray ja=JSONArray.fromObject(webpageContent);
	Map m=	(Map) CollectionUtils.reduce(ja, new HashMap(),new  Ireduce<JSONObject, Map>() {

			@Override public Map $(JSONObject o, Map lastRetOBj) {
				// attilax 老哇的爪子  m_h_51   o8h 
				lastRetOBj.put(o.get("DCode"), o.get("DName"));
				return lastRetOBj;
				
			}}  ) ;
	return  core.toJsonStrO88(m);
	}
	
	//  attilax 老哇的爪子 m_d_j   o8h   
}

//  attilax 老哇的爪子