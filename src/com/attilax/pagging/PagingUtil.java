package com.attilax.pagging;

//package mole.common;
/**
 * 
 */
import java.util.ArrayList;
import java.util.List;
import com.focusx.elmt.GvMaterial;
/**
 * last fix 2009-4-13 05:05:01
 * @author  attilax 老哇的爪子
 *@since  o7g Vq56$
 */
public class PagingUtil {
	public int PageSize = 0;

	public int Page = 0;

	public int PageCount = 0;

	public List list = null;

	public int listCount = 0;

	public List listCurPage = new ArrayList();

	public void setList(List list, int pagesize, int page) {
		this.list = list;
		this.listCount=list.size();
		this.PageSize = pagesize;
		this.Page = page;
		this.PageCount = this.getPagecount(list.size() , pagesize);
		int passcount = this.getPassCount();
		for (int i = 0; i < this.list.size(); i++) {
			if (i+1 > passcount) {
				this.listCurPage.add(list.get(i));
				if (this.listCurPage.size() == this.PageSize) {
					break;
				}
			}

		}

	}

	int getPassCount() {

		return (this.Page - 1) * this.PageSize;

	}

	int getPagecount(int listlen, int pagesize) {
		int n = 0;
		if (listlen % pagesize == 0)
			n = listlen / pagesize;
		else
			n = listlen / pagesize + 1;
		return n;

	}

	public static void main(String[] args) {

		List list = new ArrayList();
		for (int i = 1; i <= 21; i++)
			list.add(i);
		PagingUtil p = new PagingUtil();
		p.setList(list, 5, 2);
		System.out.print(p.PageCount);

	}

	/**
	@author attilax 老哇的爪子
		@since  o7g Vt52$
	
	 * @param list2
	 * @param object
	 * @param object2
	 * @return
	 */
	public static List getList(List list, Object pagesize, Object page) {
		// attilax 老哇的爪子  Vt52   o7g 
		PagingUtil p = new PagingUtil();
		if(pagesize==null)pagesize=10;
		if(page==null)page=1;
		int pagesize2=Integer.parseInt(pagesize.toString());
		int page2=Integer.parseInt(page.toString());
		p.setList(list, pagesize2, page2);
		return p.listCurPage;
		 
		
	}

}
