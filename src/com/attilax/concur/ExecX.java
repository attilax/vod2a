/**
 * @author attilax 老哇的爪子
	@since  o0a l_n_41$
 */
package com.attilax.concur;
import com.attilax.Closure;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o0a l_n_41$
 */
public class ExecX {

	/**
	@author attilax 老哇的爪子
		@since  o0a l_n_42   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  l_n_42   o0a 
		ExecX.exeAsyn(new Closure<Object, Object>() {

			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子  l_o_50   o0a 
				return null;
				
			}});
		ExecX.BeginInvoke(new Closure<Object, Object>() {

			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子  l_o_50   o0a 
				return null;
				
			}});

	}
	//  attilax 老哇的爪子 l_n_42   o0a   

	/**
	@author attilax 老哇的爪子
		@since  o0a l_v_0   
	
	 * @param closure
	 */
	public static void BeginInvoke(Closure<Object, Object> closure) {
		// attilax 老哇的爪子  l_v_0   o0a 
		exeAsyn(closure);
	}

	/**
	@author attilax 老哇的爪子
		@since  o0a l_o_46   
	
	 * @param closure
	 */
	public static void exeAsyn(final Closure<Object, Object> closure) {
		 String a="";
		
		 a.getClass().getCanonicalName();
		
		// attilax 老哇的爪子  l_o_46   o0a 
		Executors.newSingleThreadExecutor().submit(new Callable<Object>() {

			@Override public Object call() throws Exception {
				// attilax 老哇的爪子  l_r_l   o0a 
				closure.execute(null);
				return null;
				
			}});
		
	}
}

//  attilax 老哇的爪子