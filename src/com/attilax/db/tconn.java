/**
 * @author attilax 老哇的爪子
	@since  o8a j_41_k$
 */
package com.attilax.db;
import com.attilax.core;
import com.focusx.dao.impl.EquipmentDAOImpl;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o8a j_41_k$
 */
public class tconn {

	/**
	@author attilax 老哇的爪子
		@since  o8a j_41_k   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  j_41_k   o8a 
		final EquipmentDAOImpl equipmentDAOImpl = new EquipmentDAOImpl();
		int num = (equipmentDAOImpl.getCount("from Equipment"));
		System.out.println(num);
	}
	//  attilax 老哇的爪子 j_41_k   o8a   
}

//  attilax 老哇的爪子