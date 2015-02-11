/**
 * @author attilax 老哇的爪子
	@since  2014-9-1 上午01:33:27$
 */
package com.attilax.db;
import com.attilax.core;
import com.attilax.retryO7;
import com.attilax.util.HibernateSessionFactory;
import com.foksda.mass.retryRzt;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
/**
 * @author  attilax 老哇的爪子
 *@since  2014-9-1 上午01:33:27$
 */
public class DBX extends baseDAO {
	//  attilax 老哇的爪子 上午01:33:27   2014-9-1   
	 public static Session sess;
	 public DBX(){}
	/**
	@author attilax 老哇的爪子
		@since  2014-9-2 上午02:06:23$
	
	 * @param session
	 */
	public DBX(Session session) {
		//  attilax 老哇的爪子 上午02:06:23   2014-9-2   
		this.session=session;
	 
	}
	public List<Map> execSql(String sql)
	{
	Query q =sess.createSQLQuery(sql); 
	 q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
    List<Map> li=q.list();
	return li;
	}
	
//	public   final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
//	  public   Session getSessionOri() throws HibernateException {
//	        Session session = (Session) threadLocal.get();
//
//			if (session == null || !session.isOpen()) {
//				if (sessionFactory == null) {
//					rebuildSessionFactory();
//				}
//				session = (sessionFactory != null) ? sessionFactory.openSession()
//						: null;
//				threadLocal.set(session);
//			}
//
//	        return session;
//	    }
	
	 
	 
}

//  attilax 老哇的爪子