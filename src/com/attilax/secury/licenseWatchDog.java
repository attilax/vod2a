/**
 * @author attilax 老哇的爪子
	@since  o7m i_j_5$
 */
package com.attilax.secury;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.util.god;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o7m i_j_5$
 */
public class licenseWatchDog {

	/**
	@author attilax 老哇的爪子
		@since  o7m i_j_5$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  i_j_5   o7m 
		{}
		{}
		{}

	}
	//  attilax 老哇的爪子 i_j_5   o7m   

	/**
	@author attilax 老哇的爪子
	 * @param closure 
		@since  o7m i_k_p$
	
	 */
	public static void start(final Closure closure) {
		// attilax 老哇的爪子  i_k_p   o7m 
	god.newThread(new Runnable() {
		
		@Override public void run() {
			// attilax 老哇的爪子  h_g_52   o7n 
			try {
				closure.execute("");
			} catch (Exception e) {
				//  attilax 老哇的爪子 h_l_55   o7n   
				core.log(e);
			}
			
		}
	}, "lcsWatchDogThrd");
		
	}
}

//  attilax 老哇的爪子