/**
 * @author attilax 老哇的爪子
	@since  o8r 0_43_r$
 */
package com.focusx.downtask;
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
public interface QueueCycle<T > extends Queue<T> {

	/**
	@author attilax 老哇的爪子
		@since  o8r 0_46_58   
	
	 * @param o
	 * @return
	 */
	boolean removeNappend(Object o);
	//  attilax 老哇的爪子 0_43_r   o8r   
}

//  attilax 老哇的爪子