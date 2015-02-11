/**
 * @author attilax 老哇的爪子
	@since  o85 i_55_54$
 */
package com.attilax.collection;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o85 i_55_54$
 */
public interface CycleQueue extends Queue  {
	//  attilax 老哇的爪子 i_55_54   o85   
	
	public void moveToTail(Object obj);
	public List peek(int fetchCount);
}

//  attilax 老哇的爪子