/**
 * @author attilax 老哇的爪子
	@since  o0d 3_51_q$
 */
package com.attilax.biz;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o0d 3_51_q$
 */
public class DefCaptchX {

	public static String defV="1314";

	/**
	@author attilax 老哇的爪子
		@since  o0d 3_51_40   
	
	 * @param lr
	 * @return
	 */
	public static boolean isDefCapt(String capt) {
		// attilax 老哇的爪子 3_51_40 o0d
		if (capt.equals("1314") || capt.equals("000")) return true;
		else return false;

	}
	//  attilax 老哇的爪子 3_51_q   o0d   
}

//  attilax 老哇的爪子