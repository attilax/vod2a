/**
 * @author attilax 老哇的爪子
	@since  o8s 0_e_k$
 */
package com.focusx.listener;
import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.util.god;
import com.focusx.push.pushX;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o8s 0_e_k$
 */
public class itfsHistoryX {

	public static void main(String[] args) {
		com.focusx.listener.itfsHistoryX.start();
	}
	/**
	@author attilax 老哇的爪子
		@since  o8s 0_e_y   
	
	 */
	public static void start() {
		// attilax 老哇的爪子  0_e_y   o8s 
	core.execMeth_Ays (new Runnable() {
		
		@Override public void run() {
			// attilax 老哇的爪子  0_h_38   o8s 
			while(true)
			{
				String s="pushX.invokeTimes::"+String.valueOf(pushX.invokeTimes);
				core.log(s);
				try {
					filex.append("c:\\pushX.invokeTimes_hstr.txt", s+"\r\n");
				} catch (IOException e) {
					//  attilax 老哇的爪子 0_k_t   o8s   
					e.printStackTrace();
					core.warn(e);
				}
				core.sleep(3000);
			}
			
		}
	},"---thrd::itfsHistoryX ");
		
	}
	//  attilax 老哇的爪子 0_e_k   o8s   
}

//  attilax 老哇的爪子