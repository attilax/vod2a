/**
 * @author attilax 老哇的爪子
	@since  o09 i_41_a$
 */
package com.focusx.downRec;
import com.attilax.core;
import com.attilax.ioc.IocX;
import com.attilax.net.HttpRequest;
import com.attilax.persistence.PX;

 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;

import org.hibernate.Query;
/**
 * @author  attilax 老哇的爪子
 *@since  o09 i_41_a$
 */
public class postDownTaskBackfeedT {

	/**
	@author attilax 老哇的爪子
		@since  o09 i_41_a   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  i_41_a   o09 
		
		//oaf();
		
		 
			//oaa();
			
		//	oaf();
 
		
					
			 
			
	 
		

	}
	//  attilax 老哇的爪子 i_41_a   o09   

	private static void oaf() {
		String url="http://localhost/vod/api.jsp";
		// url="http://192.168.1.33/vod/api.jsp";
		//http://localhost/vod/api.jsp?submethod=postPlayRec&appid=1234507y767676&param=xx
		String s="{\"dsId\":1597}";
		String param="submethod=postDownTaskBackfeed&appid=1234507y767676&param="+s;
	String r=	HttpRequest.sendGet(url, param);
	core.log("--ret:"+r);
	}
}

//  attilax 老哇的爪子