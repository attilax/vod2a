/**
 * @author attilax 老哇的爪子
	@since  o9p 3_9_s$
 */
package com.attilax.count;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9p 3_9_s$
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface reduce {

	/**
	@author attilax 老哇的爪子
		@since  o9p j_7_6   
	
	 * @return
	 */
	String reduceExp() default " count(*) ";
	//  attilax 老哇的爪子 3_9_s   o9p   
}

//  attilax 老哇的爪子