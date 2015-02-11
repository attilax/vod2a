/**
 * @author attilax 老哇的爪子
	@since  o7l h41t$
 */
package com.attilax.util;

import com.attilax.core;
 
import com.attilax.collection.Any;
import com.attilax.collection.NoBinElmtExcpt;
import com.attilax.collection.NoHandlerCanProcessReqExcpt;
import com.attilax.net.requestImp;
import com.attilax.secury.propertyReader;
import com.attilax.util.god;
import static com.attilax.core.*;
import java.text.ParseException;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.poi.hpsf.SummaryInformation;
import net.sf.json.JSONObject;
/** @author attilax 老哇的爪子
 * @since o7l h41t$ */
public class HandlerChain {
	public static Logger logger = Logger.getLogger(HandlerChain.class);
	// public static Map mp=new HashMap<String,Handler>();
	public List mp = new ArrayList();
	
	public HandlerChain(com.attilax.util.Handler... handler)
	{
		for (Handler h : handler) {
			mp.add(h);
		}
		
	}
//	public void reg(Handler handler) {
//		mp.add(handler);
//
//	}
public	Handler bingoPrcsr;
public	Object rst ;
	/** \
	 * @author attilax 老哇的爪子
	 * @since o85 1_y_58$
	 * 
	 * @param proccsor
	 * @return
	 * @throws Exception */
	@SuppressWarnings("all") public Object handleReq(Object data) throws Exception {

		List li = mp;
	 
	 
	 
		try {
			bingoPrcsr = (Handler) Any.any(li, new Func_4SingleObj() {

				@Override public Object invoke(Object o) {
					Handler p = (Handler) o;
					try {
						Object rst = p.handleReq("");
						rst=rst;
					//	li_r.add(rst);
						return true;
					} catch (ParseException e) {
						return false;
					} catch (Exception e) {
						// attilax 老哇的爪子 h47i o7l
						return false;
					}

				}
			});
		} catch (NoBinElmtExcpt e) {
			throw new NoHandlerCanProcessReqExcpt();
		}

		return rst;

	}

	/** @author attilax 老哇的爪子
	 * @since o7l h41t$
	 * 
	 * @param args
	 * @throws Exception */
	public static void main(String[] args) throws Exception {
		// attilax 老哇的爪子 h41t o7l
		requestImp ri = new requestImp();
		ri.setParam("submethod", "aa");

		System.out.println(new HandlerChain().handleReq(ri));
	}

}

// attilax 老哇的爪子