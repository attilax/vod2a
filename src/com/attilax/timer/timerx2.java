/**
 * @author attilax 老哇的爪子
	@since  2014-9-2 下午07:54:35$
 */
package com.attilax.timer;
import com.attilax.core;
import com.focusx.dao.impl.TaskDaoImpl;
import com.focusx.job.CheckTaskJober;
 import static  com.attilax.core.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  2014-9-2 下午07:54:35$
 */
public class timerx2 {
	public static ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(2);
	/**
	@author attilax 老哇的爪子
		@since  2014-9-2 下午07:54:35   
	
	 * @param args
	 */
	public static void main(String[] args) {
	// attilax 老哇的爪子  下午07:54:35   2014-9-2 
		CheckTaskJober ctJober = new CheckTaskJober(new TaskDaoImpl());
		schedulePool.scheduleWithFixedDelay(ctJober,0, 5, TimeUnit.SECONDS);
	}
	//  attilax 老哇的爪子 下午07:54:35   2014-9-2   
}

//  attilax 老哇的爪子