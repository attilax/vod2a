/**
 * @author attilax 老哇的爪子
	@since  o9l 0_m_g$
 */
package com.focusx.action.equipment;
import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.net.websitex;
import com.attilax.spri.SpringUtil;
import com.focusx.listener.MonitorListener;
import com.focusx.pojo.EquipmentMonitor;
import com.focusx.util.MD5;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9l 0_m_g$
 */
public class EqMntrT {

	/**
	@author attilax 老哇的爪子
		@since  o9l 0_m_g   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  0_m_g   o9l 
		
	
		//	O9F();
	//	o0lk();
		String SerialCode="357952006920160"; //BD6FB287EE491BDA4F29579ABEB257F4
		System.out.println( MD5.getMD5(SerialCode));
		System.out.println("BD6FB287EE491BDA4F29579ABEB257F4".length());
		

	}
	//  attilax 老哇的爪子 0_m_g   o9l   

	private static void o0lk() {
		String f=pathx.classPath()+"/com/focusx/action/equipment/upmonitorData.json";
		String json=filex.read(f);
		String host="localhost";
	//	host="200.140.235.199:8888";
		String url="http://"+host+"/vod/ajaxMonitors!putCacher?monitorJson="+URLEncoder.encode(json);
		System.out.println("url:"+url);
		System.out.println(websitex.WebpageContent(url));
		System.out.println("--");
	}

	private static void O9F() {
		EquipmentMonitor  e=new EquipmentMonitor();
		e.setAsmContent(" scr a");
		List li=new ArrayList();
		li.add(e);
		String json_li=core.toJsonStrO88(li);
		System.out.println(json_li);
		
		MonitorAction ma= (MonitorAction) SpringUtil.getBean(MonitorAction.class);
		ma.setMonitorJson(json_li);
	//	ma.putCacher();
		
		MonitorListener ml=new MonitorListener();
		ml.contextInitialized(null);
	}
}

//  attilax 老哇的爪子