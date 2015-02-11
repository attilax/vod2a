
package com.attilax.api;

import java.text.ParseException;

public interface   Handler {
//	public String name;
//	public Handler(String name) {
//		this.name=name;
//	} 
 
	/**
	@author attilax 老哇的爪子
		@since  o7l h510$
	
	 * @param string
	 */ 
 
	public   Object handleReq(Object arg) throws Exception;
}
