/**
 * @author attilax 老哇的爪子
	@since  o83 k_0_58$
 */
package com.attilax.api;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o83 k_0_58$
 */
public class paramCheckorFailEx extends baseException {

	

	/**
	@author attilax 老哇的爪子
		@since  o83 k_1_b$
	
	 * @param errcode
	 */
	public paramCheckorFailEx(String errMsgJson) {
		//  attilax 老哇的爪子 k_1_b   o83 
	this.ex=	new Exception(errMsgJson); 
	}
	
 
	//  attilax 老哇的爪子 k_0_58   o83   
}

//  attilax 老哇的爪子