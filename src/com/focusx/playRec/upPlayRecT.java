/**
 * @author attilax 老哇的爪子
	@since  o7l hh46$
 */
package com.focusx.playRec;
import com.attilax.core;
import com.attilax.api.Handler;
import com.attilax.api.HandlerChain;
import com.attilax.io.filex;
import com.attilax.net.HttpRequest;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o7l hh46$
 */
public class upPlayRecT {

	/**
	@author attilax 老哇的爪子
		@since  o7l hh47$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  hh47   o7l 
		String file = "c:\\playrec.json";
	///	geneJson(file);
		upRecT(file);
		System.out.println("---");
	}
	/**
	@author attilax 老哇的爪子
		@since  o7l ho58$
	
	 * @param file
	 */
	private static void upRecT(String file) {
	//	core.getUUidName();
		// attilax 老哇的爪子  ho58   o7l 
		String s=filex.read(file);
		try {
			s= java.net.URLEncoder.encode(s, "utf-8");
		} catch (UnsupportedEncodingException e) {
			//  attilax 老哇的爪子 hys   o7l   
			e.printStackTrace();
		}
		String url="http://localhost/vod/api.jsp";
		url="http://192.168.1.33/vod/api.jsp";
		//http://localhost/vod/api.jsp?submethod=postPlayRec&appid=1234507y767676&param=xx
		String param="submethod=postPlayRec&appid=1234507y767676&param="+s;
	String r=	HttpRequest.sendGet(url, param);
	core.log("--ret:"+r);
		
	}
	//  attilax 老哇的爪子 hh47   o7l   

	private static void geneJson(String file) {
		GvPlayRecord o = new GvPlayRecord();
		o.setEquipmentId(10);
		o.setMaterialId(3);
		o.setScreen(4);
	
		filex.save(o, file);
	}
}

//  attilax 老哇的爪子