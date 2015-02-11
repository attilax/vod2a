package com.attilax.page;

import javax.servlet.http.HttpServletRequest;

import com.attilax.core;
import com.focusx.dictionary.entity.GvDictionary;
import com.focusx.util.Utils;

public class PageUtil {

	/**
	 * 
	  * @author：luojun
	  *
	  * @Description:获取页面传进来的参数
	  *  
	  * @CreateTime: 2014-7-14 下午04:51:07
	  *
	  * @param Pager
	  * @param request
	 */
	public static void init(Pager<?> pager, HttpServletRequest request) {
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize"); 
		if (Utils.isNotEmpty(currentPage)) {
			try {
				pager.setCurrentPage(Integer.parseInt(currentPage));
			} catch (Exception e) {
				pager.setCurrentPage(1);
			}
			
		}
		if (Utils.isNotEmpty(pageSize)) {
			try {
				pager.setPagesize(Integer.parseInt(pageSize));
			} catch (Exception e) {
				pager.setPagesize(10);
			}
			
		}
	}

		/**
		@author attilax 老哇的爪子
		@since   oaa g_9_44
		 
		 */
	public static int getPageSize(Object object) {
		try {
			Integer.parseInt(object.toString());
		} catch (Exception e) {
			core.err(e);
		}
		return 10;
	}

			/**
			@author attilax 老哇的爪子
			@since   oaa g_f_40
			 
			 */
		public static int getStartIndexFirstResult(Object curpage,int pagesize) {
			int page = 1;
			try {
				page=	Integer.parseInt(curpage.toString());
			} catch (Exception e) {
				core.err(e);
			}
			int startIndex=(page-1)*pagesize;
			return startIndex;
		}
}
