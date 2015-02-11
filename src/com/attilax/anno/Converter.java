/**
 * @author attilax 老哇的爪子
\t@since  Jul 19, 2014 5:21:05 AM$
 */
package com.attilax.anno;
import com.attilax.core;
import com.attilax.MDA.TimeConverterO7;
import com.attilax.lang.ref.None;

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
 *@since  Jul 19, 2014 5:21:05 AM$
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Converter {

	Class<?> value() default None.class;
	//  attilax 老哇的爪子 5:21:05 AM   Jul 19, 2014   
}

//  attilax 老哇的爪子