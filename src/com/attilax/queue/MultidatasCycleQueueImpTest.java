/**
 * @author attilax 老哇的爪子
\t@since  Aug 30, 2014 7:30:12 AM$
 */
package com.attilax.queue;

import com.attilax.core;
import com.attilax.collection.CollectionUtils;
import com.attilax.concur.dataISEmptyEx;
import com.attilax.db.EmptyEx;
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
public class MultidatasCycleQueueImpTest<EE> extends GvDownloadTaskSvs<EE> implements
		CycleQueue< EE> {
	
	public com.attilax.db.baseDAO dbx=new com.attilax.db.baseDAO();
	public int rangSize=20;

	public static void main(String[] args) {

		final Session sess = HbX4vod.getSession();
		baseDAO.sessThrdloc.set(sess);
	//	dbx.session=sess;
		nx.$(1).times(new Closure() {

			@Override
			public void execute(Object arg0) {
				// attilax 老哇的爪子 7:50:27 AM Aug 30, 2014
				core.log("===cur times:" + arg0.toString());
				MultidatasCycleQueueImpTest<GvDownloadTask> q = new MultidatasCycleQueueImpTest<GvDownloadTask>();
				q.dbx.session = sess;
				// q.iniCursor();
				List<GvDownloadTask> t = (List<GvDownloadTask>) q.peek();
				if (t.size() != 0) {					 
						System.out.println(CollectionUtils.getIDs(t, "dsId"));
						q.next();
				}
				// core.sleep(10000);

			}
		});

	}

	List<GvDownloadTask> curObjs;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#peek()
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EE peek() {
		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014

		{
			try {
				curObjs = (List<GvDownloadTask>) iniCursor();
			} catch (NoRecEx e) {
				// attilax 老哇的爪子 8:39:28 AM Aug 30, 2014
				return (EE) new ArrayList<GvDownloadTask>();
			}

			return (EE) curObjs;
		}

	}
	
//	
//	public EE peek() {
//		// attilax 老哇的爪子 7:30:19 AM Aug 30, 2014
//
//		{
//			try {
//				curObjs = (List<GvDownloadTask>) iniCursor();
//			} catch (NoRecEx e) {
//				// attilax 老哇的爪子 8:39:28 AM Aug 30, 2014
//				return (EE) new ArrayList<>();
//			}
//
//			return (EE) curObjs;
//		}
//
//	}

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
 

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 * 
	 * @author attilax 老哇的爪子
	 * 
	 * @since Aug 30, 2014 7:30:19 AM$
	 */
 

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
		List<GvDownloadTask> li = new ArrayList<GvDownloadTask>();
		// has cusr
		li = (List) baseDAO.findByHql(" from  GvDownloadTask where downloadStatus=null  and curCursor=1 ");
				 
		if (li.size() == 0) {
			// reset cuser
			String hql = " from GvDownloadTask  where downloadStatus=null order by dsId ";

			 
				try {
					li = baseDAO.getTop(hql, this.rangSize);
				} catch (EmptyEx e) {
					//  attilax 老哇的爪子 10:14:20 PM   Aug 30, 2014   
					throw new NoRecEx();
				}
			 
			if (li.size() == 0)
				throw new NoRecEx();
			setCurCursor(li);
			setCurCursorMaxIdx(this.lastTask);
			 
		}else
		{// ini cusr max idx
			setCurCursorMaxIdx(li);
		}
		
		
		
		
		

		return li;

	}

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 30, 2014 10:26:18 PM$
	
	 * @param li
	 */
	private void setCurCursorMaxIdx(List<GvDownloadTask> li) {
		// attilax 老哇的爪子  10:26:18 PM   Aug 30, 2014 
		
		
		{ 
			GvDownloadTask maxCurIndex=null;
			for (GvDownloadTask t : li) {
				maxCurIndex=t;
			 
			}
			
			setCurCursorMaxIdx(maxCurIndex);
			
		 } 
		
		
	}
	GvDownloadTask lastTask;
	/**
	@author attilax 老哇的爪子
	\t@since  Aug 30, 2014 7:54:01 PM$
	
	 * @param li
	 */
	private void setCurCursor(List<GvDownloadTask> li) {
		// attilax 老哇的爪子  7:54:01 PM   Aug 30, 2014 
		
		{ 
			GvDownloadTask maxCurIndex=null;
			for (GvDownloadTask t : li) {
				lastTask=t;
				t.setCurCursor(1);
				dbx.merge(t);
			}
			
			//set maxcurindex
			
			
		 } 
		 
	//	return lastTask;
		
		
	}

	private void setCurCursorMaxIdx(GvDownloadTask t) {
		if(t!=null)
		{
		t.setCurCursorMaxIdx(1);
		dbx.merge(t);
		}
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
	public boolean next()  {
		// attilax 老哇的爪子 7:40:19 AM Aug 30, 2014

		{
			com.attilax.db.baseDAO c = new com.attilax.db.baseDAO();
System.out.println("");
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
			} catch (NoRecEx e) {
				//  attilax 老哇的爪子 10:19:06 PM   Aug 30, 2014   
				e.printStackTrace();
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
		
		GvDownloadTask maxCurIndex=null;
		//for (GvDownloadTask t : li) {
		
		for (GvDownloadTask t : curObjs) {
			maxCurIndex=t;
			t.setCurCursor(null);
			t.setCurCursorMaxIdx(null);
			dbx.merge(t);
			
		}
		

	}

 

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 30, 2014 8:30:08 AM$
	 * @throws NoTopRecEx
	 */
	private void set_top_record_cursurStae() throws NoTopRecEx {
		// attilax 老哇的爪子 8:30:08 AM Aug 30, 2014
		com.attilax.db.baseDAO c = new com.attilax.db.baseDAO();
		String s = " from  GvDownloadTask  where  downloadStatus=null   order by dsId";
		List<GvDownloadTask> t;
		try {
			t = c.getTop(s,this.rangSize);
		} catch (EmptyEx e) {
			//  attilax 老哇的爪子 10:56:53 PM   Aug 30, 2014   
			throw new NoTopRecEx();
		}
		if (t.size()==0)
			throw new NoTopRecEx();
		 setCurCursor(t);
	 

	}

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 30, 2014 8:25:52 AM$
	 * @throws NoNextRecEx
	 * @throws NoRecEx 
	 */
	private void set_next_record_cursurStae() throws NoNextRecEx, NoRecEx {
		// attilax 老哇的爪子 8:25:52 AM Aug 30, 2014
		com.attilax.db.baseDAO c = new com.attilax.db.baseDAO();
		Integer maxCursorIdx=getMmaxCursorIdx();
		String s = " from  GvDownloadTask where   downloadStatus=null and dsId>"
				+ maxCursorIdx.toString() + " order by dsId";
		List<GvDownloadTask> li;
		try {
			li = c.getTop(s,this.rangSize);
		} catch (EmptyEx e) {
			//  attilax 老哇的爪子 10:14:56 PM   Aug 30, 2014   
		 
			throw new NoNextRecEx();
		}
		if (li.size()==0)
			throw new NoNextRecEx();
		 setCurCursor(li);
		 
		 //top pad to fix 
		if(li.size()<this.rangSize)
		{
			int padFromtopInt=this.rangSize-li.size();
			try {
				List<GvDownloadTask> liFrmTop=c.getTop(" from  GvDownloadTask where   downloadStatus=null   order by dsId",padFromtopInt);
				 setCurCursor(liFrmTop);
				setCurCursorMaxIdx(this.lastTask);
			} catch (EmptyEx e) {
				//  attilax 老哇的爪子 12:07:14 AM   Aug 31, 2014   
				e.printStackTrace();
			}
			
		}

		
	 

	}

	/** where set_next_record_cursurStae use
	@author attilax 老哇的爪子
	\t@since  Aug 30, 2014 8:10:41 PM$
	
	 * @param curObjs2
	 * @return
	 * @throws NoRecEx 
	 * @throws EmptyEx 
	 */
	private Integer getMmaxCursorIdx( ) throws NoRecEx  {
		// attilax 老哇的爪子  8:10:41 PM   Aug 30, 2014 
		
		{ 
	//	return CollectionUtils.Max(this.curObjs,"dsId");
		 } 
		GvDownloadTask top = null;
		try {
			top = dbx.getTop("from GvDownloadTask where  downloadStatus=null  and curCursorMaxIdx=1 order by dsId ", 1);
		} catch (EmptyEx e) {
			//  attilax 老哇的爪子 10:05:38 PM   Aug 30, 2014   
			core.log(e);
			Integer r=CollectionUtils.Max(this.curObjs,"dsId");
			if(r!=null)return r;
			 
			try {
				top = dbx.getTop("from GvDownloadTask where  downloadStatus=null   order by dsId ", 1);
			} catch (EmptyEx e1) {
				//  attilax 老哇的爪子 10:16:17 PM   Aug 30, 2014   
				throw new  NoRecEx();
			}

		}
		return  top.getDsId();
		
		
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