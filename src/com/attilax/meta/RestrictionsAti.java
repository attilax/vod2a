/**
 * @author attilax 老哇的爪子
	@since  o9p h_45_k$
 */
package com.attilax.meta;
import com.attilax.core;
import com.attilax.anno.op;
 import static  com.attilax.core.*;
import java.sql.Timestamp;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9p h_45_k$
 */
public class RestrictionsAti {
	//SimpleExpressionAti se
	/**
	@author attilax 老哇的爪子
		@since  o9p h_45_r   
	
	 * @param propertyName
	 * @param ts1
	 * @return
	 */
	public static SimpleExpressionAti gt(String propertyName, Object  ts1) {
		// attilax 老哇的爪子  h_45_r   o9p 
		return new SimpleExpressionAti(propertyName,op.gt,ts1);
		
	}
	//  attilax 老哇的爪子 h_45_k   o9p   

	/**
	@author attilax 老哇的爪子
		@since  o9p h_45_50   
	
	 * @param propertyName
	 * @param ts2
	 * @return
	 */
	public static SimpleExpressionAti lt(String propertyName, Object ts2) {
		// attilax 老哇的爪子  h_45_50   o9p 
		return 	  new SimpleExpressionAti(propertyName,op.lt,ts2);
		
	}
}

//  attilax 老哇的爪子