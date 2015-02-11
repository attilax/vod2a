/**
 * @author attilax 老哇的爪子
	@since  o86 2_38_58$
 */
package com.focusx.push;
import com.attilax.core;
import com.attilax.spri.SpringUtil;
import com.focusx.publish.service.IPublishService;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o86 2_38_58$
 */
public class skedT {

	/**
	@author attilax 老哇的爪子
		@since  o86 2_38_58$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  2_38_58   o86 
	 	// IPublishService pc=(IPublishService) SpringUtil.getBean("publishService");
		PrgrmNoticer pn = (PrgrmNoticer) SpringUtil.getBean("prgrmNoticer");
		System.out.println(pn); 
		pn.t();
		 for (int i = 0; i >-1; i++) {
			 
			 System.out.println("---wake...");
			 core.sleep(5000);
			
		}
		 
	}
	//  attilax 老哇的爪子 2_38_58   o86   
}

//  attilax 老哇的爪子