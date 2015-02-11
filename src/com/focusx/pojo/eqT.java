/**
 * @author attilax 老哇的爪子
	@since  o9n 5_49_l$
 */
package com.focusx.pojo;
import com.attilax.core;
import com.attilax.ioc.IocX;
import com.attilax.persistence.Hbx;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9n 5_49_l$
 */
public class eqT {

	/**
	@author attilax 老哇的爪子
		@since  o9n 5_49_l   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  5_49_l   o9n 
		
		Hbx hbx=IocX.getBean(Hbx.class);
		Equipment e=	(Equipment) hbx.getSession().get(Equipment.class, 1075);
		core.print_wzFmt(e);
		System.out.println("--");

	}
	//  attilax 老哇的爪子 5_49_l   o9n   
}

//  attilax 老哇的爪子