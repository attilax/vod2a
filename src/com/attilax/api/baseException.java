/**
 * @author attilax 老哇的爪子
	@since  o83 k_2_c$
 */
package com.attilax.api;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o83 k_2_c$
 */
public class baseException extends Exception {
	//  attilax 老哇的爪子 k_2_c   o83   
	public Exception ex;
	
	public String getMessage()
	{
		return this.ex.getMessage();
	}
}

//  attilax 老哇的爪子