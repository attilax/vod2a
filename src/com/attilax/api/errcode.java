/**
 * @author attilax 老哇的爪子
	@since  o83 j_38_5$
 */
package com.attilax.api;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o83 j_38_5$
 */
public class errcode {

	protected static String miss_meth_param="00701";
	//  attilax 老哇的爪子 j_38_5   o83   
	public static String miss_param_param="00702";
	
	
	/**
@author attilax 老哇的爪子
	@since  o83 j_39_1$

 * @param miss_meth_param
 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
 */
	public static  String errcode(String code) {
		// attilax 老哇的爪子 j_39_1 o83
		String cfgPath = HandlerChain.class.getResource("./errcode.json").getPath();
		Properties props = new Properties();
		// InputStreamReader isr = null;

		try {
			props.load(new InputStreamReader(new FileInputStream(cfgPath), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// attilax 老哇的爪子 j_52_58 o83
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			// attilax 老哇的爪子 j_52_58 o83
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			// attilax 老哇的爪子 j_52_58 o83
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return "{\"errcode\":@c,\"errmsg\":\"@err\" }".replace("@err", props.getProperty(code)).replace("@c", code);

	}
}

//  attilax 老哇的爪子