/**
 * @author attilax 老哇的爪子
	@since  2014-9-2 上午01:33:20$
 */
package com.attilax.timer;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.util.DateUtil;
import com.attilax.util.god;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  2014-9-2 上午01:33:20$
 */
public class timerTask {
	public String heartbreakMsg;
	public   int run=0;
	/**
	@author attilax 老哇的爪子
		@since  2014-9-2 上午01:33:20   
	
	 * @param args
	 */
	public static void main(String[] args) {
	// attilax 老哇的爪子  上午01:33:20   2014-9-2 

	}
	//  attilax 老哇的爪子 上午01:33:20   2014-9-2   
	/**
	@author attilax 老哇的爪子
		@since  2014-9-2 上午01:37:40   
	
	 * @param closure
	 * @param thrdName 
	 */
	public static void startAtask(final Closure closure, String thrdName) {
		// attilax 老哇的爪子  上午01:37:40   2014-9-2 
		god.newThread(new Runnable(){

			@Override
			public void run() {
				// attilax 老哇的爪子  上午01:26:09   2014-9-2 
				while(true)
				{
					try {
						closure.execute(null);
					} catch (Exception e) {
						//  attilax 老哇的爪子 上午01:39:41   2014-9-2   
						e.printStackTrace();
						core.log(e);
					}
					core.sleep(20000);
				}
				
			}},thrdName );
		
	}
	
	public static void startAtask(final Closure closure, String thrdName,final int milSecs) {
		// attilax 老哇的爪子  上午01:37:40   2014-9-2 
		god.newThread(new Runnable(){

			@Override
			public void run() {
				// attilax 老哇的爪子  上午01:26:09   2014-9-2 
				while(true)
				{
					try {
						closure.execute(null);
					} catch (Exception e) {
						//  attilax 老哇的爪子 上午01:39:41   2014-9-2   
						e.printStackTrace();
						core.log(e);
					}
					core.sleep(milSecs);
				}
				
			}},thrdName );
		
	}
}

//  attilax 老哇的爪子