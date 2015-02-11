package com.focusx.util;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.attilax.core;
import com.attilax.dotnet.System.Web.HttpContext;

/**
 * todox ob5
 * 
 * @author ASIMO
 * 
 */
public class UEditorFilter extends StrutsPrepareAndExecuteFilter {

	public static void main(String[] args) {
		// System.out.println(Platform.is64Bit());
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) {
		HttpContext.Current.set(new HttpContext());
		HttpContext.Request.set(req);
		ServletRequest req2 = (ServletRequest) HttpContext.Current.get().Request
				.get();
		core.log("--loadorderO9::UEditorFilter");
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();
		try {
			if ("/gial/ueditor/jsp/imageUp.jsp".equals(url)) {
				chain.doFilter(req, res);
			} else { // to struts process
				super.doFilter(req, res, chain);
			}
		} catch (Exception e) {
			e.printStackTrace();
			core.err(e);
			// throw new RuntimeException(e.getMessage(), e);
			// ooo bg throw recommen le .beir shwa submit forward cant befeor
			// output cache
		} catch (Throwable ee) {

		}
	}
}
