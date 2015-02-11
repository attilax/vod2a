/**
 * 
 */
package com.attilax.pagging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.attilax.SafeVal;

/**
 * @author ASIMO
 *
 */
public class PageX {

		/**
		@author attilax 老哇的爪子
		@since   oaj 9_56_u
		 
		 */
	public static Map getEmptyPageData_EasyuiFmt() {
		Map mp=new HashMap();
		mp.put("total" ,0);
		mp.put("rows",new ArrayList());
		return mp;
	}

}
