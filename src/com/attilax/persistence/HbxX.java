/**
 * @author attilax 老哇的爪子
\t@since  Sep 3, 2014 5:47:22 AM$
 */
package com.attilax.persistence;

import com.attilax.core;
import com.attilax.api.HandlerChain;
import com.attilax.collection.handleChainUtil;
import com.attilax.util.Handler;

import static com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;

/**
 * @author attilax 老哇的爪子
 * @since Sep 3, 2014 5:47:22 AM$
 */
public class HbxX extends PX {

	com.attilax.util.HandlerChain chain;
	List<Hbx> li = new ArrayList<Hbx>();

	public HbxX() {
		li.add(new Hbx());
		li.add(new Hbx());
		li.add(new Hbx());
		chain = new com.attilax.util.HandlerChain(new Handler() {

			@Override
			public Object handleReq(Object arg) throws Exception {
				// attilax 老哇的爪子 6:15:44 AM Sep 3, 2014

				{
					return null;
				}

			}
		}, new Handler() {

			@Override
			public Object handleReq(Object arg) throws Exception {
				// attilax 老哇的爪子 6:15:49 AM Sep 3, 2014

				{
					return null;
				}

			}
		}, new Handler() {

			@Override
			public Object handleReq(Object arg) throws Exception {
				// attilax 老哇的爪子 6:15:49 AM Sep 3, 2014

				{
					return null;
				}

			}
		});
	}

	/**
	 * @author attilax 老哇的爪子 \t@since Sep 3, 2014 5:47:22 AM$
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子 5:47:23 AM Sep 3, 2014

		{
		}

	}

	public void save(Object o) {

		for (Hbx hbx : li) {

			try {
				hbx.save(o);
				break;
			} catch (Exception e) {
				// attilax 老哇的爪子 6:18:24 AM Sep 3, 2014
				e.printStackTrace();
				core.warn(e);
				if(new File("c:\\debugMode").exists())
					throw new RuntimeException(e);
			}
		}

	}
	
	public boolean del(Object o) {

		for (Hbx hbx : li) {

			try {
					hbx.delete(o);return true;
			 
			} catch (Exception e) {
				// attilax 老哇的爪子 6:18:24 AM Sep 3, 2014
				e.printStackTrace();
				core.warn(e);
			}
		}
		return false;

	}
	// attilax 老哇的爪子 5:47:22 AM Sep 3, 2014
}

// attilax 老哇的爪子