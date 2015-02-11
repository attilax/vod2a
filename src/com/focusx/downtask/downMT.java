/**
 * @author attilax 老哇的爪子
	@since  o9o h_x_44$
 */
package com.focusx.downtask;
import com.attilax.core;
import com.attilax.ioc.IocX;
import com.attilax.persistence.Hbx;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9o h_x_44$
 */
public class downMT {

	/**
	@author attilax 老哇的爪子
		@since  o9o h_x_44   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  h_x_44   o9o 
		Hbx c=IocX.getBean(Hbx.class);
		DownmanaItem d=	(DownmanaItem) c.getSession().get(DownmanaItem.class, 1356);
		core.print_wzFmt(d);
		System.out.println("--ok");
	 

	}
	//  attilax 老哇的爪子 h_x_44   o9o   
}

//  attilax 老哇的爪子