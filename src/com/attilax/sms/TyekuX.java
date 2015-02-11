/**
 * 
 */
package com.attilax.sms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.time.timeUtil;

import System.Web.Request;

/**
 * @author ASIMO
 * 
 */
public class TyekuX {

	/**
	 * @author attilax 老哇的爪子
	 * @since obs e_57_o
	 */
	public static void main(String[] args) {
		System.out.println("");
		// http://localhost/vod/sm.jsp?message=aaa
		new TyekuX().exec();
	}

	static Vector<Map<String, String>> li = new Vector<Map<String, String>>();
	@SuppressWarnings("unused")
	public String exec() {
		try {
			
			String Key = "", Phone = "", Message = "", SMS_KEY_frmLoc = "";
			Boolean SetPass = false;
			if (Request.Form("phone") != null) {
				Phone = Request.Form("phone").Trim();
			}
			if (Request.Form("message") != null) {
				Message = Request.Form("message").Trim();
			}

			if (Request.Form("smskey") != null) {
				Key = Request.Form("smskey").Replace("'", "''").Trim();
			}
			Map m = new HashMap();
			m.put("ph", Phone);
			m.put("msg", Message);
			m.put("key", Key);
			m.put("time", timeUtil.Now_CST());
			li.add(0, m);
			

			filex.save_safe(Phone + "," + Message +","+ Key,
					"c:\\sms\\" + filex.getUUidName() + ".txt");
			return core.toJsonStrO88(li);
		} catch (Exception e) {
			filex.saveLog(e, "c:\\smse");
			return core.toJsonStrO88(e);
		}

	}

}
