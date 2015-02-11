/**
 * @author attilax 老哇的爪子
\t@since  Aug 16, 2014 11:10:06 PM$
 */
package com.attilax.Stream;
import com.attilax.core;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Aug 16, 2014 11:10:06 PM$
 */
public class baseIredusImp  implements Iredus {
	//  attilax 老哇的爪子 11:10:06 PM   Aug 16, 2014   
	/**
	 * output col name
	 */
	public String colName;
	/**
	 * calc fld
	 */
	private String fld;  
	 
	public baseIredusImp()
	{
		
	}
	/**
	@author attilax 老哇的爪子
	\t@since  Aug 16, 2014 11:16:13 PM$
	
	 * @param fld
	 */
	public baseIredusImp(String fld) {
		//  attilax 老哇的爪子 11:16:13 PM   Aug 16, 2014   
		this.fld=fld;
	}

	/**
	@author attilax 老哇的爪子
		@since  o8d l_44_47   
	
	 * @param string
	 * @return
	 */
	public Iredus as(String colName) {
		// attilax 老哇的爪子  l_44_47   o8d 
		this.colName=colName;
		return (Iredus) this;
		
	}
	/* (non-Javadoc)
	 * @see com.attilax.Stream.Iredus#exec(java.util.List)
	 * @author  attilax 老哇的爪子
	 *@since  Aug 17, 2014 12:28:19 AM$
	 */
	@Override
	public reduceCalcRzt_singleExprs exec(List singTab) {
		// attilax 老哇的爪子  12:28:19 AM   Aug 17, 2014 
		
		{ 
		return null;
		 } 
		
		
	}
	/* (non-Javadoc)
	 * @see com.attilax.Stream.Iredus#getOutputColname()
	 * @author  attilax 老哇的爪子
	 *@since  Aug 17, 2014 12:28:19 AM$
	 */
	@Override
	public String getOutputColname() {
		// attilax 老哇的爪子  12:28:19 AM   Aug 17, 2014 
		
		{ 
		return this.colName;
		 } 
		
		
	}
}

//  attilax 老哇的爪子