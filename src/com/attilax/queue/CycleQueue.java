/**
 * @author attilax 老哇的爪子
	@since  o8r 0_43_r$
 */
package com.attilax.queue;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 * @param <E>
 *@since  o8r 0_43_r$
 */
public interface CycleQueue<T > extends Queue<T> {

	Object iniCursor() throws   NoRecEx;
	
	boolean next();
	
	
	boolean isLastPostion();
	
	boolean gotoTop();
	/**
	@author attilax 老哇的爪子
		@since  o8r 0_46_58   
	
	 * @param o
	 * @return
	 */
	boolean removeNappend(Object o);
	
	
	//  attilax 老哇的爪子 0_43_r   o8r   
}

//  attilax 老哇的爪子的爪子