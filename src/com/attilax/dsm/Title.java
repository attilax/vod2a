/**
 * @author attilax 老哇的爪子
	@since  o02 3_a_56$
 */
package com.attilax.dsm;
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
 *@since  o02 3_a_56$
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Title {

	String value () default "";
	//  attilax 老哇的爪子 3_a_56   o02   
}

//  attilax 老哇的爪子