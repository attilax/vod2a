/**
 * @author attilax 老哇的爪子
	@since  2014-9-1 上午02:03:40$
 */
package com.focusx.util;
import com.attilax.core;
import com.attilax.up.hbx4vod;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 * @author  attilax 老哇的爪子
 *@since  2014-9-1 上午02:03:40$
 */
public class hbsessFTest {

	/**
	@author attilax 老哇的爪子
		@since  2014-9-1 上午02:03:40   
	
	 * @param args
	 */
	public static void main(String[] args) {
	// attilax 老哇的爪子  上午02:03:40   2014-9-1 
		
		Session sess=HbX4vod.getSession();
		
		Query query = sess.createQuery(" from  GvCycleQueue");		 
		query.setMaxResults(1);
		List li =query.list();
		System.out.println(li.size());

	}
	//  attilax 老哇的爪子 上午02:03:40   2014-9-1   
}

//  attilax 老哇的爪子