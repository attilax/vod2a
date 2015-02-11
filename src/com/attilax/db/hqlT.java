/**
 * @author attilax 老哇的爪子
	@since  o9l i_u_3$
 */
package com.attilax.db;
import com.attilax.core;
import com.attilax.api.HandlerChain;
import com.focusx.util.HbX4vod;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.hibernate.Query;
/**
 * @author  attilax 老哇的爪子
 *@since  o9l i_u_3$
 */
public class hqlT {

	/**
	@author attilax 老哇的爪子
		@since  o9l i_u_3   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  i_u_3   o9l 
String hql="from  GvPublish where publishId=1904";
HandlerChain.sess = HbX4vod.getSession();
Query q =HandlerChain.sess.createQuery(hql); 

List li =q.list();
core.print_wzFmt(li);

	}
	//  attilax 老哇的爪子 i_u_3   o9l   
}

//  attilax 老哇的爪子