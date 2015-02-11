/**
 * @author attilax 老哇的爪子
	@since  o8k g_46_56$
 */
package com.attilax.util;
import com.attilax.core;
import com.attilax.time.jsonTimeFmtr;
import com.focusx.downtask.GvDownloadTask;
 import static  com.attilax.core.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.*;
import java.io.*;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * @author  attilax 老哇的爪子
 *@since  o8k g_46_56$
 */
public class tJsonTimeFmt {

	/**
	 * @author  attilax 老哇的爪子
	 *@since  o8k g_49_55$
	 */


	/**
	@author attilax 老哇的爪子
		@since  o8k g_46_56   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  g_46_56   o8k 
	
		GvDownloadTask o=new GvDownloadTask();
		o.setEffectieTime(core.toTimeStamp(new Date()));
		JSONObject jsonObject = JSONObject.fromObject(o,jsonTimeFmtr.jsonConfig());
		System.out.println(jsonObject.toString(2));

	}
	//  attilax 老哇的爪子 g_46_56   o8k   
}

//  attilax 老哇的爪子