/**
 * @author attilax 老哇的爪子
	@since  o0m k_t_53$
 */
package com.attilax.io;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.util.dirx;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o0m k_t_53$
 */
public class delFileBatchUtil {

	/**
	@author attilax 老哇的爪子
		@since  o0m k_t_53   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  k_t_53   o0m 
		
		final String strPath="c:" ;
		trave(strPath , new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 3:46:39 AM Aug 2, 2014

				{
					String fname = arg0.toString();
					System.out.println(fname);
 				 if(fname.startsWith("eqmnt"))
 					 	new File(strPath+"\\"+fname).delete();
 					// System.out.println(fname);

					return null;
				}

			}
		});
		System.out.println("=---");

	}
	
	public static void trave(String strPath ,Closure closure) { 
	    List<String> filelist = getFilesCurrDir_reltPath(strPath); 
	    for (String f : filelist) {
			//new File(f).delete();
	    	try {
				closure.execute(f);
			} catch (Exception e) {
				//  attilax 老哇的爪子 k_40_a   o0m   
				e.printStackTrace();
			}
		}
	     
	}
	//  attilax 老哇的爪子 k_t_53   o0m   

	private static List<String> getFilesCurrDir(String strPath) {
		File dir = new File(strPath); 
	    File[] files = dir.listFiles(); 
	    List<String> filelist=new ArrayList();
	    for (int i = 0; i < files.length; i++) { 
	        if (files[i].isDirectory()) { 
	            
	        } else { 
	            String strFileName = files[i].getAbsolutePath().toLowerCase();
	         
	            filelist.add(files[i].getAbsolutePath());  
	            
	        } 
	    }
		return filelist;
	}
	  @SuppressWarnings("all") 
	private static List<String> getFilesCurrDir_reltPath(String strPath) {
		File dir = new File(strPath); 
	    File[] files = dir.listFiles(); 
	    List<String> filelist=new ArrayList();
	    for (int i = 0; i < files.length; i++) { 
	        if (files[i].isDirectory()) { 
	            
	        } else { 
	          String strFileName = files[i].getName();
	         String s=files[i].getPath();
	            filelist.add(  strFileName);  
	            
	        } 
	    }
		return filelist;
	}
}

//  attilax 老哇的爪子