/**
 * 
 */
package com.attilax.dsm.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ASIMO
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Type {

	String value();

	 

}
