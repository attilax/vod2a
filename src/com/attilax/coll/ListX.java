/**
 * @author attilax 老哇的爪子
\t@since  Aug 23, 2014 5:15:12 PM$
 */
package com.attilax.coll;
import com.attilax.core;
import com.attilax.Stream.Mapx;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Aug 23, 2014 5:15:12 PM$
 */
public class ListX<atiType> {
	//  attilax 老哇的爪子 5:15:12 PM   Aug 23, 2014   
	
	public static void main(String[] args) {
		ListX.<String>$();
	}
	private List<atiType> li;

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 5:16:30 PM$
	
	 * @param arrayList
	 */
	public ListX(ArrayList<atiType> arrayList) {
		//  attilax 老哇的爪子 5:16:30 PM   Aug 23, 2014 
this.li=arrayList;  
	}

	/** todox o8f generic
	 * ListX.<GvDownloadTask>$()
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 5:15:40 PM$
	 * @return 
	 
	 * @return
	 */
	public  static <atiType> ListX<atiType>  $() { 
		// attilax 老哇的爪子  5:15:40 PM   Aug 23, 2014 
		
		{ 
		return   new ListX<atiType>(new ArrayList<atiType>());
		 } 
		
		
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 5:17:02 PM$
	
	 * @param Obj
	 * @return
	 */
	public ListX<atiType> add(atiType Obj) {
		// attilax 老哇的爪子  5:17:02 PM   Aug 23, 2014 
		
		{ 
			this.li.add(Obj);
		return this;
		 } 
		
		
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 5:19:05 PM$
	
	 * @return
	 */
	public List<atiType> toLi() {
		// attilax 老哇的爪子  5:19:05 PM   Aug 23, 2014 
		
		{ 
		return li;
		 } 
		
		
	}
}

//  attilax 老哇的爪子