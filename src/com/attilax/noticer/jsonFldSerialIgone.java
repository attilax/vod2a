/**
 * @author attilax 老哇的爪子
	@since  o8r j_p_n$
 */
package com.attilax.noticer;
import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.time.jsonTimeFmtr;
import com.attilax.util.DateUtil;

 import static  com.attilax.core.*;
import java.sql.Timestamp;
import java.util.*;
import java.net.*;
import java.io.*;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * @author  attilax 老哇的爪子
 *@since  o8r j_p_n$
 */
public class jsonFldSerialIgone {
	
 
	/**
	@author attilax 老哇的爪子
		@since  o8r j_p_n   ,"lastSuccTime
	
	 * @param args
	 */
	public static void main(String[] args) {
		Timestamp ts1=DateUtil.timestampO7();
		JSONObject ts=JSONObject.fromObject(ts1);
		JSONObject  jo=new JSONObject();
		jo.put("ts",DateUtil.timeStamp2Str(    ts));
		core.print_wzFmt(jo);
		// attilax 老哇的爪子  j_p_n   o8r 
//		String s=filex.read("c:\\task.txt");
//		JSONObject jo=JSONObject.fromObject(s);
//		System.out.println(core.toJsonStrO7(jo, jsonFldSerialIgone.jsonConfig_timefmtNinoneRetmsg()));
//		System.out.println("--");
		
	}
	
	
	public static JsonConfig jsonConfig_timefmt() {
		// attilax 老哇的爪子  g_53_x   o8k 
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new jsonTimeFmtr());
		// jsonConfig.setExcludes(new String[]{"lastSuccRetMsg","sendMsg","sendRetMsg","sendMsgA","eq","mtrl","pub","prgrm","sendMsgContext","curCursorMaxIdx","curCursor","lastSuccTime","optim","opr"});
		return jsonConfig;
		
	}
	public static JsonConfig jsonConfig_timefmtNinoneRetmsg() {
		// attilax 老哇的爪子  g_53_x   o8k 
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new jsonTimeFmtr());
		 jsonConfig.setExcludes(new String[]{"lastSuccRetMsg","sendMsg","sendRetMsg","sendMsgA","eq","mtrl","pub","prgrm","sendMsgContext","curCursorMaxIdx","curCursor","lastSuccTime","optim","opr"});
		return jsonConfig;
		
	}
	
	public static JsonConfig jsonConfig_4reqPubFromTVB() {
		// attilax 老哇的爪子  g_53_x   o8k 
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new jsonTimeFmtr());
		 jsonConfig.setExcludes(new String[]{"lastSuccRetMsg","sendMsg","sendRetMsg","sendMsgA","eq","mtrl","pub","sendMsgContext","curCursorMaxIdx","curCursor","lastSuccTime","optim","opr"});
		return jsonConfig;
		
	}
	//  attilax 老哇的爪子 j_p_n   o8r   

	/**
	@author attilax 老哇的爪子
		@since  o09 0_50_l   
	
	 * @return
	 */
	public static JsonConfig jsonConfig_simp4prgrm() {
		// attilax 老哇的爪子  0_50_l   o09 
		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new jsonTimeFmtr());
		 jsonConfig.setExcludes(new String[]{"lastSuccRetMsg","sendMsg","sendRetMsg","sendMsgA","eq","pub","sendMsgContext","curCursorMaxIdx","curCursor","lastSuccTime","optim","opr"});
		return jsonConfig;
		
	}
}

//  attilax 老哇的爪子