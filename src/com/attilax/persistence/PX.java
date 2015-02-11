/**
 * @author attilax 老哇的爪子
\t@since  Sep 3, 2014 5:45:50 AM$
 */
package com.attilax.persistence;
import com.attilax.core;
import com.focusx.elmt.GvMaterial;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
/**
 * @author  attilax 老哇的爪子
 *@since  Sep 3, 2014 5:45:50 AM$
 */
public class PX extends Hbx {

	/**
	@author attilax 老哇的爪子
	\t@since  Sep 3, 2014 5:45:50 AM$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  5:45:50 AM   Sep 3, 2014 

		{
		}

	}
	
	public void save(Object o)
	{
		throw new RuntimeException("not imp");
	}
	//  attilax 老哇的爪子 5:45:50 AM   Sep 3, 2014   

	/**
	@author attilax 老哇的爪子
		@since  o93 h_f_h   
	
	 * @param obj
	 * @return 
	 */
	public boolean del(Object obj) {
		// attilax 老哇的爪子  h_f_h   o93 
		throw new RuntimeException("not imp PX.del()  ");
	}
	
	  public List findByProperty(Class<?> cls,String propertyName, Object value) {
	    	// synchronized( baseDAO.class)
//	   	  {
//	   //  log.debug("finding Object instance with property: " + propertyName
//	            + ", value: " + value);
		  Session s = null;
	      try {
	         String queryString = "from "+cls.getName()+" as model where model." 
	         						+ propertyName + "= ?";
	         core.log(queryString);
	        s=getSession();
	         System.out.println("--get sess:"+String.valueOf(s.hashCode()));
	         Query queryObject = s.createQuery(queryString);
			 queryObject.setParameter(0, value);
			 List list = queryObject.list();
			// getSession().close();
			return list;
	      } catch (Exception re) {
	       //  log.error("find by property name failed", re);
	    	 
				closeSession(s);
		 
	    	 
	         throw new RuntimeException(re);
	      }
	   	  }
		//}

		/**
		@author attilax 老哇的爪子
		@since   oaq f_41_t
		 
		 */
	public List findBySql(String sql) {
		SQLQuery SQLQueryx =  getSession().createSQLQuery(sql);
		//SQLQueryx.addEntity(GvPlayRecord.class) ;		
		SQLQueryx.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List list=	SQLQueryx.list();
		return list;
	}

	
}

//  attilax 老哇的爪子