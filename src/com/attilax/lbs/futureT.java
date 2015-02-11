/**
 * 
 */
package com.attilax.lbs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.attilax.core;

/**
 * @author ASIMO
 *
 */
public class futureT {

	/**
	@author attilax 老哇的爪子
	@since   obm a_i_6
	 
	 */
	public static void main(String[] args) {
		System.out.println("==atsart");
//		if("1".equals("1"))
//			return;
		//ExecutorService.shutdown
		ExecutorService es = Executors.newSingleThreadExecutor();  
		 Future<String> fut=	es.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				core.sleep(5000);
				System.out.println("after slepp  5s");
				return null;
			}
		});
		try {
			System.out.println(fut.get(2, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			 fut.cancel(true);
			e.printStackTrace();
		}
System.out.println("==main thread finish ");
es.shutdownNow();
	}

}
