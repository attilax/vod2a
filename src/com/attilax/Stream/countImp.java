/**
 * @author attilax 老哇的爪子
	@since  o8d l_44_v$
 */
package com.attilax.Stream;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollectionUtils;

import static  com.attilax.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o8d l_44_v$
 */
public class countImp  extends baseIredusImp implements Iredus{

	public countImp()
	{
		
	}
	/**
	@author attilax 老哇的爪子
	\t@since  Aug 16, 2014 11:16:55 PM$
	
	 * @param fld
	 */
	public countImp(String fld) {
		super(fld);
		//  attilax 老哇的爪子 11:16:55 PM   Aug 16, 2014   
	}

	//  attilax 老哇的爪子 l_44_v   o8d   
	/**
	 *  	CollectionUtils.each_RE(grpbyRztLi_MpFmt, new Closure() {

					@Override public Object execute(Object arg0) throws Exception {
						// attilax 老哇的爪子 0_e_47 o8e
						final Entry e = (Entry) arg0;
						final List li = (List) e.getValue();
						return new HashMap() {
							{
								put(e.getKey(), li.size());
							}
						};

					}
				});
	 */

	/* (non-Javadoc)
	 * @see com.attilax.Stream.Iredus#exec(java.util.Map)
	 * @author  attilax 老哇的爪子
	 *@since  Aug 16, 2014 10:16:21 PM$
	 */
	@Override
	public reduceCalcRzt_singleExprs exec(final List GroupTable ) {
		// attilax 老哇的爪子  10:16:21 PM   Aug 16, 2014 
		reduceCalcRzt_singleExprs o=new reduceCalcRzt_singleExprs();
		o.map=new HashMap(){
			{
				put(colName,GroupTable.size());
			}
		};
		{ 
		return o;
		 } 
		
		
	}
}

//  attilax 老哇的爪子