/**
 * @author attilax 老哇的爪子
	@since  2014-9-1 上午01:31:53$
 */
package com.focusx;
import com.attilax.core;
import com.attilax.collection.GvCycleQueueSvs;
import com.attilax.db.DBX;
import com.attilax.db.DbxMybatis;
import com.attilax.db.dbLockKiller;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.up.hbx4vod;
import com.attilax.util.HibernateSessionFactory;
import com.attilax.util.PropXwebrootBase;
import com.focusx.util.HbX4vod;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
/**
 * @author  attilax 老哇的爪子
 *@since  2014-9-1 上午01:31:53$
 */
public class ServiceLoctor4vod {

	/**
	@author attilax 老哇的爪子
		@since  2014-9-1 上午01:32:05   
	
	 * @return
	 */
	public static GvCycleQueueSvs newGvCycleQueueSvs() {
		// attilax 老哇的爪子  上午01:32:05   2014-9-1 
		GvCycleQueueSvs c = new GvCycleQueueSvs();
		c.sess2=HbX4vod.getSession();
		c.dbx=new DBX();
		return c;
		
	}
	
 
	//  attilax 老哇的爪子 上午01:31:53   2014-9-1   

	/**
	@author attilax 老哇的爪子
		@since  2014-9-1 上午03:12:42   
	
	 * @return
	 */
	public static int getMtrfetchCount() {
		// attilax 老哇的爪子  上午03:12:42   2014-9-1 
	 String s= new  PropXwebrootBase().getConfig("noticerCfg.txt", "mtrfetchCount");
		return Integer.parseInt(s);
		
	}
	
	public static String getSn() {
		// attilax 老哇的爪子  上午03:12:42   2014-9-1 
	 String s= new  PropXwebrootBase().getConfig_o99("noticerCfg.txt", "sn");
		return  (s);
		
	}
	

	/**
	@author attilax 老哇的爪子
		@since  2014-9-1 上午03:28:48   
	
	 * @return
	 */
	public static int getPrgrmfetchCount() {
		// attilax 老哇的爪子  上午03:28:48   2014-9-1 
		 String s= new  PropXwebrootBase().getConfig("noticerCfg.txt", "prgrmfetchCount");
			return Integer.parseInt(s);
		
	}

	/**
	@author attilax 老哇的爪子
		@since  2014-9-1 下午11:46:20   
	
	 */
	public static dbLockKiller getDbLockKiller() {
		// attilax 老哇的爪子  下午11:46:20   2014-9-1 
		DBX dbx=new DbxMybatis();
		dbx.sess=HbX4vod.getSession();
	//	Session ss=
		dbLockKiller k=new dbLockKiller();
		k.dbx=dbx;
		return k;
		
		
	}
	  private  static Configuration configuration = new AnnotationConfiguration();    
	  public static String CONFIG_FILE_LOCATION =pathx.webAppPath_jensyegeor()+"/WEB-INF/hibernate.cfg.vd.xml";
	  static  File propertyFile = new File(CONFIG_FILE_LOCATION);
	/**
	@author attilax 老哇的爪子
		@since  2014-9-2 上午02:05:43   
	
	 * @return
	 */
	public static DBX getDbx() {
		// attilax 老哇的爪子  上午02:05:43   2014-9-2 
		//DBX dbx=
		inidb();
		return new DBX(HbX4vod.getSession());
		
	}


	synchronized public static void inidb() {
		if(HibernateSessionFactory.sessionFactory!=null)return;
		try {
		    		
					configuration.configure(propertyFile);
					core.log("---oa22: load cfg :"+propertyFile);
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
					
					configuration.setProperty("hibernate.dialect",properties.getProperty("hibernate.dialect"));
					configuration.setProperty("hibernate.show_sql","true");
					
					
					if(IocX. update)
						configuration
						.setProperty("hibernate.hbm2ddl.auto", "update");
					
					
					//end o8j
					//当然，除了指定的XML文件外，还可以指定被映射的类，让Hibernate帮你寻找映射定义文件：
				//	configuration.addClass(GvMaterial.class);
			//		configuration.addAnnotatedClass(GvMaterial.class);
			//	 configuration.set
			//		configuration.addResource(resourceName) 
			//		sessionFactory = configuration.buildSessionFactory();
					HibernateSessionFactory.configuration=configuration;
					HibernateSessionFactory.ini(configuration);
				} catch (Exception e) {
					System.err
							.println("%%%% Error Creating SessionFactory %%%%");
					e.printStackTrace();
					 
						throw new RuntimeException(e);
					  //ati o8j
				}
	}
}

//  attilax 老哇的爪子