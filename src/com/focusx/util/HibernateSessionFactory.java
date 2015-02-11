package com.focusx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;

import cn.freeteam.util.MybatisSessionFactory;
import com.attilax.core;
import com.attilax.retryO7;
import com.attilax.io.pathx;
import com.focusx.elmt.GvMaterial;
import com.foksda.mass.retryRzt;

/**   ====HibernateSessionFactory4vod
 * \r\n<p>
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html }.
 *///   X55s o7d  老哇的爪子  Attilax
@Deprecated
public class HibernateSessionFactory {

    /** 
     * Location of hibernate.cfg.xml file. 
     * Location should be on the classpath as Hibernate uses  
     * #resourceAsStream style lookup for its configuration file. 
     * The default classpath location of the hibernate config file is 
     * in the default package. Use #setConfigFile() to update 
     * the location of the configuration file for the current session.   
     */
    public static String CONFIG_FILE_LOCATION =pathx.webAppPath_jensyegeor()+"/WEB-INF/hibernate.cfg.vd.xml";
   
    		//"/vodx/WebRoot/WEB-INF/hibernate.cfg.vd.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private  static Configuration configuration = new AnnotationConfiguration();    
    public static org.hibernate.SessionFactory sessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;
    static  File propertyFile = new File(CONFIG_FILE_LOCATION);

	//private   
    //void ini()
	static  {
    	try {
    		
			configuration.configure(propertyFile);
			// o8j
			Properties properties = new Properties();
			properties.load(new InputStreamReader(new FileInputStream(pathx
					.classPath() + "/jdbc.properties"), "utf-8"));

			String username = properties.getProperty("jdbc.username").trim();
			String password = properties.getProperty("jdbc.password");
			String driverClass = properties.getProperty("jdbc.driverClassName")
					.trim();
			String connection_url = properties.getProperty("jdbc.url").trim();

		//	configuration.setProperties(properties);
			configuration.setProperty("connection.driver_class", driverClass);
			configuration.setProperty("connection.url", connection_url);
			configuration.setProperty("connection.username", username);
			configuration.setProperty("connection.password", password);

			configuration.setProperty("hibernate.connection.url",
					connection_url);

			configuration.setProperty("hibernate.connection.driver_class",
					driverClass);
			configuration
					.setProperty("hibernate.connection.username", username);

			configuration
					.setProperty("hibernate.connection.password", password);
			//end o8j
			//当然，除了指定的XML文件外，还可以指定被映射的类，让Hibernate帮你寻找映射定义文件：
		//	configuration.addClass(GvMaterial.class);
	//		configuration.addAnnotatedClass(GvMaterial.class);
	//	 configuration.set
	//		configuration.addResource(resourceName) 
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err
					.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
			 
				throw new RuntimeException(e);
			  //ati o8j
		}
    }
    public HibernateSessionFactory() {
    }
	
	/**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *
     *  @return Session
     *  @throws HibernateException
     */
    public static Session getSessionOri() throws HibernateException {
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
     * with retry spt
    @author attilax 老哇的爪子
    	@since  o92 m_r_j   
    
     * @return
     */
    public static Session getSession() {
		// attilax 老哇的爪子 i4148 o78
		retryRzt rzt = new retryRzt();
		return new retryO7<Session>(5, rzt) {

			@Override
			public Boolean item(Object t) throws Exception {
				// attilax 老哇的爪子 下午11:49:37 2014年6月9日
				final Session sess = getSessionOri();
				if(sess==null)return false;
				this.setResetObj(sess);

				Query query = sess.createQuery(" from  GvCycleQueue ");		 
				query.setMaxResults(1);
				List li =query.list();
				// core.ex4test();

				if (li.size() >= 0) {
					this.setResult(sess);
					return true;  //ret suc flag
				}

				return false;

			}
			// return null;

			@Override
			public void reset(final Object sessObj) {

				core.log("---o79: conn is close ,now startclose session..");
				 Session sess = (Session) sessObj;
				 if(sess!=null)
				sess.close();

			}
		}.$O69();
	}

	/**
     *  Rebuild hibernate session factory
     *
     */
	public static void rebuildSessionFactory() {
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
    public static void closeSession() throws HibernateException {
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
	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
     *  return session factory
     *
     *	session factory will be rebuilded in the next call
     */
	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	/**
     *  return hibernate configuration
     *
     */
	public static Configuration getConfiguration() {
		return configuration;
	}

}