/**
 * @author attilax 老哇的爪子
\t@since  Sep 3, 2014 5:54:39 AM$
 */
package com.attilax.persistence;
import com.attilax.core;
import com.attilax.db.baseDAO;
import com.attilax.ref.NoThisAnnoEx;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.focusx.pojo.Equipment;
import com.google.inject.Inject;

import static  com.attilax.core.*;

import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import javax.persistence.Column;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
/**
 * @author  attilax 老哇的爪子
 *@since  Sep 3, 2014 5:54:39 AM$
 */
public class Hbx  extends baseDAO{

	/**
	@author attilax 老哇的爪子
	\t@since  Sep 3, 2014 5:54:39 AM$
	
	 * @param args
	 * @throws cantFindMatchFieldException 
	 */
	public static void main(String[] args)   {
		// attilax 老哇的爪子  5:54:39 AM   Sep 3, 2014 

		{
			Field f = null;
			try {
				f = refx.getField("equipmentId", Equipment.class);
			//	f.getClass()
			} catch (cantFindMatchFieldException e) {
				//  attilax 老哇的爪子 m_q_c   o9r   
				e.printStackTrace();
			}
		String c = null;
		 
			c = getColunmnName_RE(f);
		 
		System.out.println(c);
		}

	}
	
	/**
	@author attilax 老哇的爪子
		@since  o93 j_v_9   
	
	 * @param s
/  */
//	private void closeSession(Session s) {
//		// attilax 老哇的爪子  j_v_9   o93 
//		try {
//			 if(s!=null)
//		   		  s.close();
//		} catch (Exception e) {
//			core.warn(e);
//		}
//		
//	}
	
	@Inject
	public static org.hibernate.SessionFactory sessionFactory;
	
//	  public static Session getSession() throws HibernateException {
//	        Session session = null ;//= (Session) threadLocal.get();
//
//			if (session == null || !session.isOpen()) {
//				if (sessionFactory == null) {
//					//rebuildSessionFactory();
//				}
//				session = (sessionFactory != null) ? sessionFactory.openSession()
//						: null;
//				//threadLocal.set(session);
//			}
//
//	        return session;
//	    }
	
	//  attilax 老哇的爪子 5:54:39 AM   Sep 3, 2014   

	/**
	@author attilax 老哇的爪子
	\t@since  Sep 3, 2014 6:21:28 AM$
	
	 * @param o
	 */
	public void save(Object o) {
		// attilax 老哇的爪子  6:21:28 AM   Sep 3, 2014 
		
		// log.debug("saving GvPlayRecord instance");
	        try {
	        	Session session = getSession();
	        	Transaction tx = session.beginTransaction();
	        	session.save(o);
	        	
	        	tx.commit();
	         //   log.debug("save successful");
	        } catch (RuntimeException re) {
	           // log.error("save failed", re);
	            throw re;
	        }
		
		
	}
	
	public void delete(Object persistentInstance) {
      //  log.debug("deleting   instance");
        try {
        	Session session = getSession();
        	Transaction tx = session.beginTransaction();
        	session.delete(persistentInstance);        	
        	tx.commit();
           
        } catch (RuntimeException re) {
         //   log.error("delete failed", re);
            throw re;
        }
    }

	/**
	@author attilax 老哇的爪子
		@since  o90 i_g_58   
	
	 * @param session
	 */
	public static void closeSession(Session session) {
		// attilax 老哇的爪子  i_g_58   o90 
		if(session!=null)
		{
			try {
				session.close();
			} catch (Exception e) {
				core.log(e);
			}
		}
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o9l 4_52_1   
	
	 * @param hq
	 */
	public void deleteByHql(String hq) {
		// attilax 老哇的爪子  4_52_1   o9l 
		//try {
			List li= (List) getSession().createQuery(hq).list();
			for (Object object : li) {
				delete(object);
			}
//		} catch (Exception e) {
//			core.warn(e);
//		}
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o9r m_k_u   
	
	 * @param subobjFld
	 * @return
	 * @throws NoColunnAnnoEx 
	 * @throws NoThisAnnoEx 
	 * @throws cantFindMatchFieldException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static String getColunmnName(Field subobjFld) throws NoColunnAnnoEx, NoSuchMethodException, SecurityException, cantFindMatchFieldException, NoThisAnnoEx {
		// attilax 老哇的爪子  m_k_u   o9r 
		Class cls=	refx.class_4getAnno_inThrdLoc.get();
		Column a=    (Column) refx.getAnno (subobjFld.getName(), cls, Column.class);
		if(a==null) 
			throw new NoColunnAnnoEx(subobjFld.getName());
			//return "noColunnAnnoIn:"+subobjFld.getName();
		return a.name();
		
	}
	
	public static String getColunmnName_RE(Field subobjFld)   {
		// attilax 老哇的爪子  m_k_u   o9r 
		Class cls=	refx.class_4getAnno_inThrdLoc.get();
		Column a = null;
		try {
			a = (Column) refx.getAnno (subobjFld.getName(), cls, Column.class);
		} catch (NoSuchMethodException e) {
			//  attilax 老哇的爪子 0_7_9   o9s   
			throw new  RuntimeException("NoSuchMethodException");
		} catch (SecurityException e) {
			//  attilax 老哇的爪子 0_7_9   o9s   
			throw new  RuntimeException("SecurityException");
		} catch (cantFindMatchFieldException e) {
			//  attilax 老哇的爪子 0_7_9   o9s   
			throw new  RuntimeException("NoThisAnnoEx");
		} catch (NoThisAnnoEx e) {
			//  attilax 老哇的爪子 0_7_9   o9s   
			throw new  RuntimeException("NoThisAnnoEx");
		}
		if(a==null) 
			throw new  RuntimeException("NoColunnAnnoEx");
			//return "noColunnAnnoIn:"+subobjFld.getName();
		return a.name();
		
	}
	
	public static void addExpresss(Criteria c, List li) {
		// attilax 老哇的爪子  11:46:49 PM   Jul 18, 2014 
		 for (Object object : li) {
			 Criterion se=(Criterion) object;
			c.add(se);
		}
		 
		
	}
}

//  attilax 老哇的爪子