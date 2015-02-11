/**
 * @author attilax 老哇的爪子
	@since  o9p h_45_c$
 */
package com.attilax.meta;
import com.attilax.core;
import com.attilax.ref.refx;
import com.attilax.util.DateUtil;
 import static  com.attilax.core.*;
import java.sql.Timestamp;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9p h_45_c$
 */
public class SimpleExpressionAti {

	private String left;
	private String mid;
	private Object rit;

	/**
	@author attilax 老哇的爪子
		@since  o9p h_49_x$
	
	 * @param propertyName
	 * @param gt
	 * @param ts1
	 */
	public SimpleExpressionAti(String propertyName, String gt, Object ts1) {
		//  attilax 老哇的爪子 h_49_x   o9p   
		this.left=propertyName;
		this.mid=gt;
		this.rit=ts1;
	}
	//  attilax 老哇的爪子 h_45_c   o9p   

	/**
	@author attilax 老哇的爪子
		@since  o9p h_52_1   
	
	 * @return
	 */
	public Object toSqlFmt() {
		// attilax 老哇的爪子  h_52_1   o9p 
	//	String actRigFmt=;
		return this.left+this.mid+fmtRit(this.rit);
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o9p h_55_5   
	
	 * @param rit2
	 * @return
	 */
	private String fmtRit(Object rit2) {
		// attilax 老哇的爪子  h_55_5   o9p 
		if(refx.isTimestampType(rit2))
		{
			Timestamp ts=(Timestamp) rit2;
			return "'"+DateUtil.date2str_wzTime(ts)+"'";
		} 
		if(refx.isStrType(rit2))
		{
			return   "'"+rit2.toString()+"'";
		}
		 
			return   rit2.toString() ;
		 
		
	}
}

//  attilax 老哇的爪子