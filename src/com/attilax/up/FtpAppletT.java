/**
 * @author attilax 老哇的爪子
	@since  o9s g_54_r$
 */
package com.attilax.up;
import com.attilax.core;
import com.attilax.io.pathx;
import com.attilax.net.websitex;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9s g_54_r$
 */
public class FtpAppletT {

	/**
	@author attilax 老哇的爪子
		@since  o9s g_54_s   
	
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// attilax 老哇的爪子  g_54_s   o9s 
//		String ftp="E:\\FlashFXP_4.3.1.1969\\flashfxp.exe";
//		Runtime.getRuntime().exec(ftp);
		String cabzip="http://localhost/vod/upx_upx.zip";
		websitex.down(cabzip,"c:\\upx_upx.zip");
		
		String ftp="c:\\FlashFXP_4.3.1.1969_ati\\flashfxp.exe";
		try {
		//	Runtime.getRuntime().exec(ftp);
		} catch (Exception e) {
			//  attilax 老哇的爪子 h_3_53   o9s   
			e.printStackTrace();
			System.out.println("----");
			System.out.println(getTrace(e));
			
		}
		System.out.println("--");

	}
	
	 public static String getTrace(Throwable t) {
	        StringWriter stringWriter= new StringWriter();
	        PrintWriter writer= new PrintWriter(stringWriter);
	        t.printStackTrace(writer);
	        StringBuffer buffer= stringWriter.getBuffer();
	        return buffer.toString();
	    }
	//  attilax 老哇的爪子 g_54_r   o9s   
}

//  attilax 老哇的爪子