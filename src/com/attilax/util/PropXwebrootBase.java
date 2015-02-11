package com.attilax.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;
import com.attilax.core;
import com.attilax.io.pathx;

/**
 * 工具类
 * @author freeteam
 *
 */
public class PropXwebrootBase  {
	@Deprecated
	private String getConfig(String fname,String name,String encode) {
		try {
			String f =  pathx.webAppPath() + "/"+fname;
			Properties props = new Properties();

			props.load(new InputStreamReader(new FileInputStream(f), encode));

			String retime =  (props.getProperty(name));
			return retime;
		} catch (Exception e) {
			return "7";
		}
	}
	@Deprecated
	public String getConfig(String fname,String name ) {
	return	getConfig(fname,name,"gbk");
	}
	
	private String getConfig_o99(String fname,String name,String encode) {
		try {
			String f =  pathx.webAppPath() + "/"+fname;
			Properties props = new Properties();

			props.load(new InputStreamReader(new FileInputStream(f), encode));

			String retime =  (props.getProperty(name));
			return retime;
		} catch (Exception e) {
			core.warn(e);
			throw new RuntimeException(e);
		}
	}
	
	public String getConfig_o99(String fname,String name ) {
	return	getConfig_o99(fname,name,"gbk");
	}
	//获取配置文件的配置
//	public static String getConfig(String path,String name){
//		FileInputStream sins=null;
//		String value="";
//		try {
//			Properties loginprop = new Properties();
//			sins = new FileInputStream(path);
//			loginprop.load(sins);
//			value=loginprop.getProperty(name);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			if (sins!=null) {
//		        try {
//					sins.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			return value;
//		}
//	}
}
