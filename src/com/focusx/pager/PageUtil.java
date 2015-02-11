package com.focusx.pager;

import javax.servlet.http.HttpServletRequest;

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
}
