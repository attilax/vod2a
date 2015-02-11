/**
 * @author attilax 老哇的爪子
	@since  o8r m_m_53$
 */
package com.attilax.collection;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.junit.Test;
/**
 * @author  attilax 老哇的爪子
 *@since  o8r m_m_53$
 */
public class GvCycleQueueSvsTest {

	/**
	@author attilax 老哇的爪子
		@since  o8r m_m_53   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  m_m_53   o8r 
	//	testAdd();

	}
	//  attilax 老哇的爪子 m_m_53   o8r   

	/**
	@author attilax 老哇的爪子
		@since  o8r m_n_40   
	
	 */
	@Test
	public   void testAdd() {
		// attilax 老哇的爪子  m_n_40   o8r 
		GvCycleQueue o=new GvCycleQueue();
		o.setLastSuccRetMsg("ttt");
		GvCycleQueueSvs c=new GvCycleQueueSvs();
		c.save(o);
		c.delete(o);
		
	}
}

//  attilax 老哇的爪子