/**
 * @author attilax 老哇的爪子
	@since  o7m i_44_8$
 */
package com.focusx.util;

import com.attilax.core;
import com.attilax.db.HbX;
import com.attilax.db.hbPasItprtr;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;

import static com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
/** @author attilax 老哇的爪子
 * @since o7m i_44_8$ */
public class HbX4vod {
	/**
	 *  * @author  attilax 老哇的爪子
	 *@since  o7n h_9_58$
	 */
	private static final String hbf = "eXX8ByIgX6p8iolCppmmcONr7nM36bhjDUkSBw+70as=";
	
	// attilax 老哇的爪子 i_44_8 o7m
	static HbX hbxc ;//new HbX(pathx.webAppPath_jensyegeor() +hbPasItprtr.itprt( hbf));
	static {
		Configuration configuration = new AnnotationConfiguration();
		File propertyFile = new File(pathx.webAppPath_jensyegeor()
				+ hbPasItprtr.itprt(hbf));
		configuration.configure(propertyFile);
		// o8j
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(new FileInputStream(pathx
					.classPath() + "/jdbc.properties"), "utf-8"));

			String username = properties.getProperty("jdbc.username").trim();
			String password = properties.getProperty("jdbc.password");
			String driverClass = properties.getProperty("jdbc.driverClassName")
					.trim();
			String connection_url = properties.getProperty("jdbc.url").trim();

			configuration.setProperties(properties);
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
			if(IocX. update)
			configuration
			.setProperty("hibernate.hbm2ddl.auto", "update");
			
			//o8o20
		String sess_c_cls=	configuration.getProperty("hibernate.current_session_context_class");
		core.log("---o8o19:"+sess_c_cls);
		configuration.setProperty("hibernate.current_session_context_class", "thread");
		//end o8o
		
		String dialect=	configuration.getProperty("dialect");
		if(dialect==null)
		{
			configuration.setProperty("hibernate.dialect",properties.getProperty("hibernate.dialect"));
			configuration.setProperty("dialect", properties.getProperty("hibernate.dialect"));
		}
			
		core.log("---o8o19:"+dialect);
			
		//	hibernate.current_session_context_class
			// end o8j
			hbxc = new HbX(configuration);
		} catch (Exception e) {
			// attilax 老哇的爪子 12:17:23 AM Aug 23, 2014
			core.err(e);
			throw new RuntimeException(e);
		}

	}
	public static Session getSession() {
		return hbxc.getSession();
	}
	
	public static  org.hibernate.SessionFactory getSessionFactory() {
		return hbxc.getSessionFactory();
	}

}

// attilax 老哇的爪子