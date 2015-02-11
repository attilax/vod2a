/**
 * @author attilax 老哇的爪子
\t@since  Aug 16, 2014 9:43:10 PM$
 */
package com.attilax.Stream;
import com.attilax.core;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Aug 16, 2014 9:43:10 PM$
 */
public interface Iredus {

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 16, 2014 10:16:08 PM$
	
	 * @param singTab
	 * @return
	 */
	 reduceCalcRzt_singleExprs exec(List singTab);
	//  attilax 老哇的爪子 9:43:10 PM   Aug 16, 2014   

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 16, 2014 11:13:35 PM$
	
	 * @param string
	 * @return
	 */
	Iredus as(String string);

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 17, 2014 12:28:03 AM$
	
	 * @return
	 */
	String getOutputColname();

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 16, 2014 10:50:51 PM$
	
	 * @param GroupTable
	 * @param outputFldName
	 * @return
	 */
//	reduceCalcRzt_singleExprs exec(List GroupTable, String outputFldName);

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 16, 2014 10:52:13 PM$
	
	 * @param GroupTable
	 * @return
	 */
 
}

//  attilax 老哇的爪子