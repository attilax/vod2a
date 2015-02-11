package com.attilax.io;


import java.io.File;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

//import net.sf.ehcache.CacheException;


import com.attilax.core;
import com.attilax.util.classpathGetter;
//import com.chenlb.mmseg4j.Dictionary;

/**
 * 
 * 
 *  /usr/java/jdk1.6.0_21/bin/java   -Djava.ext.dirs=/imServer/lib -classpath  .:/usr/java/jdk1.6.0_21/lib/dt.jar:/usr/java/jdk1.6.0_21/lib/tools.jar:/imServer/WebRoot/WEB-INF/classes com.attilax.io.pathx
 * @author caixian
 *
 */
public class pathx {

	/**linux
	 * file:/root/

file:/root/
null
file:/root/
-------1-------
file:/root/
null
file:/root/
-------2-------
file:/root/
null
file:/root/
----
file:/imServer/WebRoot/WEB-INF/classes/com/attilax/io/
file:/root/
file:/imServer/WebRoot/WEB-INF/classes/com/attilax/io/


------//////////////windows
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
null
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
-------1-------
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
null
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
-------2-------
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
null
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
----
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/com/attilax/io/
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/
file:/D:/workspace/imServer/WebRoot/WEB-INF/classes/com/attilax/io/

	 * @param args
	 */
	public static void main1(String[] args) {
		   System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));  
			
		   System.out.println(Thread.currentThread().getContextClassLoader().getResource("."));  
			
		   System.out.println("-------1-------");
		 
	    //    System.out.println(Thread.class.getClassLoader().getResource("")); 
	       
	        System.out.println(pathx.class.getClassLoader().getResource("."));
	        System.out.println("-------2-------");
	        System.out.println(ClassLoader.getSystemResource(""));  
	        
	        System.out.println(ClassLoader.getSystemResource(".")); 
	        System.out.println("----");
	        System.out.println(pathx.class.getResource(""));  
	        System.out.println(pathx.class.getResource("/"));
	        System.out.println(pathx.class.getResource("."));
	        System.out.println("=============");
//	        ----
//	        file:/D:/workspace/vodx/WebRoot/WEB-INF/classes/com/attilax/io/
//	        file:/D:/workspace/vodx/WebRoot/WEB-INF/classes/
//	        file:/D:/workspace/vodx/WebRoot/WEB-INF/classes/com/attilax/io/
//	        =============
	        
	        
	        if("1"=="1")return;
	  
	    //  System.out.println(path_sub);  

	        System.out.println(  classPath());
	   //--------------------------------------------------     
	        System.out.println("===mmseg path:");
	        System.out.println("o11:"+classPath());
	        //linux path ok ,but windows   /d:/xxx.dat. daeleig / in front
	        System.out.println("o9:"+pathx.classPath()+"/QQWry.dat");
	   //     System.out.println(  CacheException.class.getClassLoader().getResource(""));
	        //this null in win and in linux at o3
//	        System.out.println( "o6:"+ Dictionary.class.getClassLoader().getResource("data"));
//	        Dictionary .getDefalutPath();
	 //       System.out.println("o4:"+mycls.getpath());
	        
	        
	        //flow cant use in windows
	 	   System.out.println(Thread.currentThread().getContextClassLoader().getResource("/"));  
	 	  System.out.println(pathx.class.getClassLoader().getResource("/"));
	 	  System.out.println(ClassLoader.getSystemResource("/"));	 	
	 	  System.out.println("web app path:"+webAppPath());
	 		
	}
	
	public static void main(String[] args) {
		System.out.println(  pathx.class.getResource("").getPath() );
		System.out.println(  pathx.class.getResource(".").getPath() );
		System.out.println(  pathx.class.getResource("./").getPath() );
		System.out.println(classPath());
	}
	
	 /**
	@author attilax 老哇的爪子
	\t@since  Aug 3, 2014 4:39:13 AM$
	
	 * @param f
	 * @return
	 */
	public static boolean isFile(String f) {
		// attilax 老哇的爪子  4:39:13 AM   Aug 3, 2014 
		
		{ 
			
		return !f.endsWith("\\");
		 } 
		
		
	}
	public static String classpath;
	//todox class path o5
	public static String classPath()
	{
		
	//	new File("/").getAbsolutePath()
//		if(isWindows())
//		{
//	String s=		pathx.class.getClassLoader().getResource("/").getPath();
//		     URL resource = pathx.class.getResource("/");
//			String path_x = resource.getPath();
//			String path=path_x.substring(1,path_x.length()-1);
//			return path;
//		}
		//URL u=new URL()
		//jeoig linux hamyar cheng leig ***.jar! le ..
	//	return new File(  classpathGetter.class.getResource("").getPath()).getParent() ;  
	    String path ;
	    	try {
	    		path=pathx.class.getResource("").getPath();
			} catch (Exception e) {
				try {
					 path= pathx.class.getResource(".").getPath();
				} catch (Exception e2) {
					 path= pathx.class.getResource("./").getPath();
					 core.log(e2);
				}
			
			}	
	    	classpath=path;
	    		
	    
//	    System.out.println(  );
//		System.out.println(  );
		int index=path.lastIndexOf("/");
		String path_sub=path.substring(0, index-15);
		return path_sub;

	}
	
	/**
	 *    or /C:/workspace/vodx/WebRoot/WEB-INF/classes/
	@author attilax 老哇的爪子
		@since  2014-9-1 上午01:12:52   
	
	 * @param cls
	 * @return
	 */
	public static String classPath(Class<?> cls)
	{
	return 	cls.getResource("").getPath();
	}
	

	private static boolean isWindows() {
		core.logger.info("System.getProperty(os.name)"+System.getProperty("os.name"));
	String os=	System.getProperty("os.name").toLowerCase().trim();
	if(os.contains("window"))
		return true;
	else
		return false;
	}

	/**
	  /webapp
	@author attilax 老哇的爪子
		@since  o8h m_u_47   
	
	 * @param request
	 * @return
	 */
	public static String webAppPath_webfmt(HttpServletRequest request) {
		
		return ""+request.getContextPath();
	}
	
	/**
	  http://host/webapp
	@author attilax 老哇的爪子
		@since  o8h m_u_47   
	
	 * @param request
	 * @return
	 */
	public static String webAppPath_httpFmt(HttpServletRequest request) {
		// request.getScheme()+
		String path = request.getContextPath();
		String basePath ="http://"+request.getServerName()+":"+request.getServerPort()+path+"";
		return  basePath;
	}


	
	public static String webAppPath(HttpServletRequest request) {
		
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * c:/aaaa/ fmt
	@author attilax 老哇的爪子
		@since  o8h m_s_d   
	
	 * @return
	 */
	public static String webAppPath() {
		String path= classPath();
		File file = new File(path);
		
		return file.getParentFile().getParent();
	}
	/**
	 *   replace sepection cchar
	@author attilax 老哇的爪子
		@since  o7d Y3749$
	
	 * @return
	 */
	public static String webAppPath_jensyegeor() {
		String path= classPath();
		File file = new File(path);
		
		String parent = file.getParentFile().getParent();
		String s=parent.replaceAll("\\\\", "\\/");
		return s;
	}


}
