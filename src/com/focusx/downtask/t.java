/**
 * @author attilax 老哇的爪子
	@since  o8r 1_48_57$
 */
package com.focusx.downtask;
import com.attilax.core;
import com.attilax.ioc.IocX;
import com.attilax.persistence.Hbx;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o8r 1_48_57$
 */
public class t {

	/**
	@author attilax 老哇的爪子
		@since  o8r 1_48_57   
	
	 * @param args
	 */
	public static void main(String[] args) {
		
		GvDownloadTaskSvs c=IocX.getBean(GvDownloadTaskSvs.class);
Map m=c.findByPropertyss_page(new HashMap());
System.out.println(core.toJsonStrO88(m));
		// attilax 老哇的爪子  1_48_57   o8r 
	//	ob9();

	}
	//  attilax 老哇的爪子 1_48_57   o8r   

	private static void ob9() {
		IocX. update=true;
		Hbx c=IocX.getBean(Hbx.class);
		GvDownloadTask d=	(GvDownloadTask) c.getSession().get(GvDownloadTask.class, 1356);
		core.print_wzFmt(d);
		System.out.println("--ok");
	}
}

//  attilax 老哇的爪子