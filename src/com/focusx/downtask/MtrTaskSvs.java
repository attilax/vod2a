/**
 * @author attilax 老哇的爪子
	@since  o8t 2_w_r$
 */
package com.focusx.downtask;
import com.attilax.core;
import com.attilax.lang.CaseX;
import com.attilax.lang.Icaseitem;
import com.attilax.lang.IcaseitemImp;
import com.attilax.queue.DatasCursor;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.hibernate.Query;
/**
 * @author  attilax 老哇的爪子
 *@since  o8t 2_w_r$
 */
public class MtrTaskSvs extends GvDownloadTaskSvs {

	
	DatasCursor<Integer > dCursor=new DatasCursor<Integer>();
	/**
	@author attilax 老哇的爪子
		@since  o8t 2_w_r   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  2_w_r   o8t 
	final MtrTaskSvs c = new MtrTaskSvs();
		
		List li = c.peek(3);
		
		

	}
	
	
	public List peek(int fetchCount) {
		// attilax 老哇的爪子  m_9_r   o7s 
		final List li=new ArrayList();
 
		//  noticeFlag is null
		String hql=" from GvDownloadTask   where 1=1 and incursor=1 and downloadStatus=null   order by   dsId ";
		Query q = getSession().createQuery(hql); 
		q.setFirstResult(0); 
		q.setMaxResults(fetchCount); 
		List l = q.list(); 
		CaseX.$().add(new IcaseitemImp(l.size() == 0) {

			@Override public Object exec() {
				// attilax 老哇的爪子 2_59_56 o8t
				return null;

			}
		}).add(new IcaseitemImp(l.size() < fetchCount) {

			@Override public Object exec() {
				// attilax 老哇的爪子 3_0_j o8t
				return null;

			}
		}).start();

		if(l.size()<fetchCount)
		{
			
		}
		return l;
		 
		
	}
	//  attilax 老哇的爪子 2_w_r   o8t   
}

//  attilax 老哇的爪子