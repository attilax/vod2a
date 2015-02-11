/**
 * @author attilax 老哇的爪子
	@since  o9q 10_9_r$
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
 *@since  o9q 10_9_r$
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CountRelt {

	String fld() default "";
	//  attilax 老哇的爪子 10_9_r   o9q   

	String uiFld() default "";
}

//  attilax 老哇的爪子