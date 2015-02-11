/**
 * @author attilax 老哇的爪子
	@since  o9n m_42_41$
 */
package com.attilax.sysmana;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9n m_42_41$
 */
public class SqlSvsX {

	/**
	@author attilax 老哇的爪子
		@since  o9n m_42_41   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  m_42_41   o9n 
			stop();
			start();
			System.out.println("==f");
	}
	//  attilax 老哇的爪子 m_42_41   o9n   

	/**
	@author attilax 老哇的爪子
		@since  o9n m_43_4   
	
	 */
	private static void start() {
		// attilax 老哇的爪子  m_43_4   o9n 
		String c=" cmd /c net start MSSQLSERVER";
		 try {
			Runtime.getRuntime().exec(c);
		} catch (IOException e) {
			//  attilax 老哇的爪子 m_45_z   o9n   
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}

	/**
	@author attilax 老哇的爪子
		@since  o9n m_42_56   
	
	 */
	private static void stop() {
		// attilax 老哇的爪子  m_42_56   o9n 
		
	}
}

//  attilax 老哇的爪子