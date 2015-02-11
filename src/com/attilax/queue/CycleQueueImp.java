/**
 * @author attilax 老哇的爪子
\t@since  Aug 30, 2014 7:30:12 AM$
 */
package com.attilax.queue;

import com.attilax.core;
import com.attilax.concur.dataISEmptyEx;
import com.attilax.db.baseDAO;
import com.attilax.db.ix;
import com.attilax.ref.cantFindIDFieldEx;
import com.attilax.up.hbx4vod;
import com.attilax.util.numUtil;
import com.attilax.util.nx;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.downtask.GvDownloadTaskSvs;

import com.focusx.util.HbX4vod;

import static com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;

import org.apache.commons.collections.Closure;
import org.hibernate.Session;

/**
 * @author attilax 老哇的爪子
 * @since Aug 30, 2014 7:30:12 AM$
 */
public class CycleQueueImp<T> extends GvDownloadTaskSvs<T> implements
		CycleQueue<T> {

	public static void main(String[] args) {

		Session sess = HbX4vod.getSession();
		baseDAO.sessThrdloc.set(sess);
		nx.$(9).times(new Closure() {

			@Override
			public void execute(Object arg0) {
				// attilax 老哇的爪子 7:50:27 AM Aug 30, 2014
				core.log("===cur times:"+arg0.toString());
				CycleQueueImp<GvDownloadTask> q = new CycleQueueImp<GvDownloadTask>();
				// q.iniCursor();
				GvDownloadTask t = q.peek();
				if (t != null) {
					System.out.println(t.getDsId());
					q.next();
				}
			//	core.sleep(10000);

			}
		});

	}

	GvDownloadTask curObj;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#peek()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public T peek() {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			try {
				curObj = (GvDownloadTask) iniCursor();
			} catch (NoRecEx e) {
				// attilax 老哇的爪子 8:39:28 AM Aug 30, 2014
				return null;
			}

			return (T) curObj;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#remove()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
 

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public boolean isEmpty() {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#iterator()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
 

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#remove(java.lang.Object)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public boolean remove(Object arg0) {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#size()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public int size() {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			return 0;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public Object[] toArray() {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.queue.CycleQueue#removeNappend(java.lang.Object)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@Override
	public boolean removeNappend(Object o) {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			return false;
		}

	}

	// attilax 老哇的爪子 7:30:12 AM Aug 30, 2014

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.queue.CycleQueue#iniCursor()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:40:19 AM$
	 */
	@Override
	public Object iniCursor() throws NoRecEx {
		// attilax 老哇的爪子 7:40:19 AM Aug 30, 2014

		 
			com.attilax.db.baseDAO baseDAO = new com.attilax.db.baseDAO();
			GvDownloadTask t = null;
			try {
				t = (GvDownloadTask) baseDAO.findByProperty(
						GvDownloadTask.class, "curCursor", 1).get(0);

			} catch (IndexOutOfBoundsException e) {

				String hql = " from GvDownloadTask order by dsId ";

				t = baseDAO.getTopOne(hql);
				if (t == null)
					throw new NoRecEx();
				t.setCurCursor(1);
				baseDAO.merge(t);

				return t;
			}

		 
			return t;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.queue.CycleQueue#next()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:40:19 AM$
	 */
	@Override
	public boolean next() {
		// attilax 老哇的爪子 7:40:19 AM Aug 30, 2014

		{
			com.attilax.db.baseDAO c = new com.attilax.db.baseDAO();

			//
			try {
				set_next_record_cursurStae();
				// del cur rec stat
				clrCurRecStat();
			} catch (NoNextRecEx e) {
				// attilax 老哇的爪子 8:29:27 AM Aug 30, 2014
				try {
					set_top_record_cursurStae();
					clrCurRecStat();
				} catch (NoTopRecEx e1) {
					// attilax 老哇的爪子 8:32:38 AM Aug 30, 2014
					e1.printStackTrace();
					core.warn(e1);
				}
			}

			

			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.concur.IconcurTest#maxID(java.lang.Class)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:43:22 AM$
	 */

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 30, 2014 8:33:37 AM$
	 */
	private void clrCurRecStat() {
		// attilax 老哇的爪子 8:33:37 AM Aug 30, 2014
		com.attilax.db.baseDAO c = new com.attilax.db.baseDAO();
		{
			curObj.setCurCursor(null);
			c.merge(curObj);
		}

	}

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 30, 2014 8:30:08 AM$
	 * @throws NoTopRecEx
	 */
	private void set_top_record_cursurStae() throws NoTopRecEx {
		// attilax 老哇的爪子 8:30:08 AM Aug 30, 2014
		com.attilax.db.baseDAO c = new com.attilax.db.baseDAO();
		String s = " from  GvDownloadTask   order by dsId";
		GvDownloadTask t = c.getTopOne(s);
		if (t == null)
			throw new NoTopRecEx();
		t.setCurCursor(1);
		c.merge(t);

	}

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 30, 2014 8:25:52 AM$
	 * @throws NoNextRecEx
	 */
	private void set_next_record_cursurStae() throws NoNextRecEx {
		// attilax 老哇的爪子 8:25:52 AM Aug 30, 2014
		com.attilax.db.baseDAO c = new com.attilax.db.baseDAO();
		String s = " from  GvDownloadTask where  dsId>"
				+ curObj.getDsId().toString() + " order by dsId";
		GvDownloadTask t = c.getTopOne(s);
		if (t == null)
			throw new NoNextRecEx();

		t.setCurCursor(1);
		c.merge(t);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.queue.CycleQueue#isLastPostion()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:43:22 AM$
	 */
	@Override
	public boolean isLastPostion() {
		// attilax 老哇的爪子 7:43:22 AM Aug 30, 2014

		{
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.attilax.queue.CycleQueue#gotoTop()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:43:22 AM$
	 */
	@Override
	public boolean gotoTop() {
		// attilax 老哇的爪子 7:43:22 AM Aug 30, 2014

		{
			return false;
		}

	}
}

// attilax 老哇的爪子