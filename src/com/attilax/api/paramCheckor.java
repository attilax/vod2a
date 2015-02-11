/**
 * @author attilax 老哇的爪子
	@since  o83 j_58_d$
 */
package com.attilax.api;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
/**
 * @author  attilax 老哇的爪子
 *@since  o83 j_58_d$
 */
public class paramCheckor {

	/**
	@author attilax 老哇的爪子
		@since  o83 j_58_z$
	
	 * @param req
	 * @throws paramCheckorFailEx 
	 */
	public paramCheckor(HttpServletRequest req) throws paramCheckorFailEx {
		//  attilax 老哇的爪子 j_58_z   o83   
		
		String subMeth = req.getParameter("submethod");
		if(subMeth==null || subMeth.equals(""))
			 throw new paramCheckorFailEx( errcode. errcode(errcode.miss_meth_param));
		subMeth = req.getParameter("param");
		if(subMeth==null || subMeth.equals(""))
			 throw new paramCheckorFailEx( errcode. errcode(errcode.miss_param_param));
	}
	//  attilax 老哇的爪子 j_58_d   o83   
}

//  attilax 老哇的爪子