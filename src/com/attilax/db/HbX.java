package com.attilax.db;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;

import com.attilax.core;
import com.attilax.io.pathx;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html }.
 *///   X55s o7d  老哇的爪子  Attilax
@Deprecated
public class  HbX {
	
	//public  

    /** 
     * Location of hibernate.cfg.xml file. 
     * Location should be on the classpath as Hibernate uses  
     * #resourceAsStream style lookup for its configuration file. 
     * The default classpath location of the hibernate config file is 
     * in the default package. Use #setConfigFile() to update 
     * the location of the configuration file for the current session.   
     */
    public   String CONFIG_FILE_LOCATION ="";
   
    		//"/vodx/WebRoot/WEB-INF/hibernate.cfg.vd.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private    Configuration configuration = new AnnotationConfiguration();    
    public   org.hibernate.SessionFactory sessionFactory;
    private   String configFile = CONFIG_FILE_LOCATION;
       File propertyFile = new File(CONFIG_FILE_LOCATION); 
@SuppressWarnings("all")
public HbX(String cfgFile) {
    //	try {
	synchronized (HbX.class) {
		propertyFile=new File(cfgFile);
		configuration.configure(propertyFile);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	
		
		
		
//		} catch (Exception e) {
//			System.err
//					.println("%%%% Error Creating SessionFactory %%%%");
//			e.printStackTrace();
//		}
    }

public HbX( Configuration configuration) {
    //	try {
	synchronized (HbX.class) {
		sessionFactory = configuration.buildSessionFactory();
	}
}
	
//    private HbX() {
//    }
	
	/**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *
     *  @return Session
     *  @throws HibernateException
     */
 synchronized   public   Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

        return session;
    }

	/**
     *  Rebuild hibernate session factory
     *
     */
synchronized	public   void rebuildSessionFactory() {
		try {
			configuration.configure(propertyFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			String x = "%%%% Error Creating SessionFactory %%%%";
			core.err("---o7e2");
			core.err(x);
			core.err(e);
			System.err
					.println(x);
			e.printStackTrace();
		}
	}

	/**
     *  Close the single hibernate session instance.
     *
     *  @throws HibernateException
     */
    public   void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

	/**
     *  return session factory
     *
     */
	public   org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
     *  return session factory
     *
     *	session factory will be rebuilded in the next call
     */
	public   void setConfigFile(String configFile) {
		this.configFile = configFile;
		sessionFactory = null;
	}

	/**
     *  return hibernate configuration
     *
     */
	public   Configuration getConfiguration() {
		return configuration;
	}

}