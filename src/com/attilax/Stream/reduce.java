/**
 * @author attilax 老哇的爪子
	@since  o8e 1_p_5$
 */
package com.attilax.Stream;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o8e 1_p_5$
 */
public class reduce {
	//  attilax 老哇的爪子 1_p_5   o8e   
	
	/** @author attilax 老哇的爪子
	 * @since o8d l_43_5
	 * 
	 * @return */
	public static countImp count() {
		// attilax 老哇的爪子 l_43_5 o8d
		return new countImp();

	}
	/**
	@author attilax 老哇的爪子
		@since  o8e 1_g_49   
	
	 * @param string
	 * @return
	 */
	public  static countImp max(String string) {
		// attilax 老哇的爪子  1_g_49   o8e 
		return null;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o8e 1_g_45   
	
	 * @param string
	 * @return
	 */
	public  static countImp min(String string) {
		// attilax 老哇的爪子  1_g_45   o8e 
		return null;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o8e 1_e_44   
	
	 * @param string
	 * @return
	 */
	public  static countImp sum(String string) {
		// attilax 老哇的爪子  1_e_44   o8e 
		return null;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o8e 1_d_52   
	
	 * @param string
	 * @return
	 */
	public  static Iredus avg(String fld) {
		// attilax 老哇的爪子  1_d_52   o8e 
		  return new avgImp(fld); 
		
	}

}

//  attilax 老哇的爪子