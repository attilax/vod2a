/**
 * @author attilax 老哇的爪子
	@since  o85 l_41_59$
 */
package com.attilax.collection;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.api.Handler;
import com.attilax.util.Func_4SingleObj;
import com.attilax.util.HandlerChain;
 import static  com.attilax.core.*;
import java.text.ParseException;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o85 l_41_59$
 */
public class handlerPiper extends HandlerChain {
	//  attilax 老哇的爪子 l_41_59   o85   
	
	/** \
	 * @author attilax 老哇的爪子
	 * @since o85 1_y_58$
	 * 
	 * @param proccsor
	 * @return
	 * @throws Exception */
	@SuppressWarnings("all") public Object handleReq(final Object data) throws Exception {

		List li = mp;

		return CollectionUtils.reduce(li, data,new Ireduce() {

			@Override public Object $(Object curHd, Object lastRetOBj) {
				// attilax 老哇的爪子 m_e_59 o85
				Handler p = (Handler) curHd;
				try {
					rst = p.handleReq(lastRetOBj);
					return rst;
				} catch (Exception e) {
					// attilax 老哇的爪子 m_g_59 o85
					core.log(e);
					return lastRetOBj;
				}

			}
		});

	}
}

//  attilax 老哇的爪子