/**
 * @author attilax 老哇的爪子
	@since  o9q 9_58_52$
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
 *@since  o9q 9_58_52$
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GroupByFld {

	String value();
	//  attilax 老哇的爪子 9_58_52   o9q   
}

//  attilax 老哇的爪子