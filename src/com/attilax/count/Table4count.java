/**
 * @author attilax 老哇的爪子
	@since  o9p 1_48_a$
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
 *@since  o9p 1_48_a$
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Table4count {

	String name();
	//  attilax 老哇的爪子 1_48_a   o9p   

	String daySuff()  default "day";

	String monthSuff() default "month";

	String yearSuff()  default "year";
}

//  attilax 老哇的爪子