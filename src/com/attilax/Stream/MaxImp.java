/**
 * @author attilax 老哇的爪子
\t@since  Aug 16, 2014 11:09:18 PM$
 */
package com.attilax.Stream;

import com.attilax.core;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.Ireduce;

import static com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;

import javax.enterprise.inject.Instance;

/**
 * @author attilax 老哇的爪子
 * @since Aug 16, 2014 11:09:18 PM$
 */
public class MaxImp extends baseIredusImp implements Iredus {

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 16, 2014 11:15:52 PM$
	 * 
	 * @param fld
	 */
	public MaxImp(String fld) {
		// attilax 老哇的爪子 11:15:52 PM Aug 16, 2014
		super(fld);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.Stream.Iredus#exec(java.util.List)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 16, 2014 11:11:22 PM$
	 */
	@Override
	public reduceCalcRzt_singleExprs exec(List singTab) {
		// attilax 老哇的爪子 11:11:22 PM Aug 16, 2014

		final Object v = CollectionUtils.reduce(singTab, 0, new Ireduce() {

			@Override
			public Object $(Object o, Object lastRetOBj) {
				// attilax 老哇的爪子 11:12:10 PM Aug 16, 2014
				Object r = null;
				if(lastRetOBj  instanceof Integer)
				{
					if((Integer)lastRetOBj>(Integer)o)
					{
						return lastRetOBj;
					}				 
					return r;
				 
				}
				return r;

			}
		});

		reduceCalcRzt_singleExprs o = new reduceCalcRzt_singleExprs();
		o.map = new HashMap() {
			{
				put(colName, v);
			}
		};

		return o;
	}

	// attilax 老哇的爪子 11:09:18 PM Aug 16, 2014
}

// attilax 老哇的爪子