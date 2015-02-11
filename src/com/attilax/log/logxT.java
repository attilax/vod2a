/**
 * @author attilax 老哇的爪子
\t@since  Sep 1, 2014 5:59:55 AM$
 */
package com.attilax.log;
import com.attilax.core;
import com.attilax.db.baseDAO;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Sep 1, 2014 5:59:55 AM$
 */
public class logxT {

	/**
	@author attilax 老哇的爪子
	\t@since  Sep 1, 2014 5:59:55 AM$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  5:59:55 AM   Sep 1, 2014 

		{
			logRec o=new logRec();
			o.setLevel(Level.info);
			new com.focusx.playRec.baseDAO().save(o);
			System.out.println("---");
		}

	}
	//  attilax 老哇的爪子 5:59:55 AM   Sep 1, 2014   
}

//  attilax 老哇的爪子