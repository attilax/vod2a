/**
 * @author attilax 老哇的爪子
	@since  o06 j_t_e$
 */
package com.attilax.util;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o06 j_t_e$
 */
public class DbgX {

	/**
	@author attilax 老哇的爪子
		@since  o06 j_t_l   
	
	 * @return
	 */
	public static boolean isLocal() {
		// attilax 老哇的爪子  j_t_l   o06 
		//the func switch
		  if(new File("c:\\vercodeStop").exists()) return true;
		  else
		  {   // def is enable
			//glob switch
			if(new File("c:\\localFlag").exists()) return true;
			
			return false;
		  }
		
	}
	//  attilax 老哇的爪子 j_t_e   o06   

	/**
	@author attilax 老哇的爪子
		@since  o06 j_x_48   
	
	 * @return
	 */
	public static boolean isVercodeStop() {
		
		// attilax 老哇的爪子  j_x_48   o06 
		  if(new File("c:\\vercodeStop").exists()) return true;
		  else
		  {   // def is enable
			//glob switch
		//	if(new File("c:\\localFlag").exists()) return true;
			
			return false;
		  }
		
	}
}

//  attilax 老哇的爪子