/**
 * @author attilax 老哇的爪子
	@since  o7d Yr52$
 */
package com.focusx.elmt;
import com.attilax.core;
import com.focusx.util.HibernateSessionFactory;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o7d Yr52$
 */
public class t {

	/**
	@author attilax 老哇的爪子
		@since  o7d Yr52$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  Yr52   o7d 
		System.out.println(HibernateSessionFactory.CONFIG_FILE_LOCATION);
		GvMaterialDAO c=new GvMaterialDAO();
		System.out.println(c.findAll().size());
		System.out.println("---");

	}
	//  attilax 老哇的爪子 Yr52   o7d   
}

//  attilax 老哇的爪子