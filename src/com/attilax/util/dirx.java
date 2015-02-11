package com.attilax.util;

import java.io.File;
import java.util.List;

//import org.jfree.chart.ChartFactory;




import com.attilax.Closure;

import m.dirPkg.travDir;

public class dirx {

	public static List<String> getFiles(String strPath) {
		travDir.refreshFileList(strPath);
		List<String> li = travDir.filelist;
		
		return li;
	}

	/**
	@author attilax ���۵�צ��
		@since  2014-5-27 ����02:42:43$
	
	 * @param s
	 * @return
	 */
	public static String getParentPath(String s) {
		// attilax ���۵�צ��  ����02:42:43   2014-5-27 
		File f=new File(s); 
		return f.getParent();
	}
	/**
	 * 
	@author attilax 老哇的爪子
	\t@since  Aug 2, 2014 3:40:56 AM$
	
	 * @param strPath
	 */
	public static void trave(String strPath ,Closure closure) { 
	    File dir = new File(strPath); 
	    File[] files = dir.listFiles(); 
	    
	    if (files == null) 
	        return; 
	    int length = files.length;
		for (int i = 0; i < length; i++) { 
	        if (files[i].isDirectory()) { 
	        	trave(files[i].getAbsolutePath(),closure); 
	        } else { 
	            String strFileName = files[i].getAbsolutePath().toLowerCase();
	            
	            try {
					closure.execute(files[i].getAbsolutePath());
				} catch (Exception e) {
					throw  new RuntimeException("",e);
				}  
	            
	        } 
	    } 
	}
	
	
	/**  
	 *  not safe ver
	@author attilax 老哇的爪子
	\t@since  Aug 2, 2014 3:40:56 AM$
	
	 * @param strPath
	 */
	public static void trave_NS(String strPath ,Closure closure) { 
	    File dir = new File(strPath); 
	    File[] files = dir.listFiles(); 
	    
	    if (files == null) 
	        return; 
	    for (int i = 0; i < files.length; i++) { 
	        if (files[i].isDirectory()) { 
	        	trave(files[i].getAbsolutePath(),closure); 
	        } else { 
	            String strFileName = files[i].getAbsolutePath().toLowerCase();
	            
	            try {
					closure.execute(files[i].getAbsolutePath());
				} catch (Exception e) {
					throw  new RuntimeException("",e);
				}  
	            
	        } 
	    } 
	}

}
