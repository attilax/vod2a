/**  com.focusx.elmt.dwrReX.pushJs(null)
 * @author attilax 老哇的爪子
	@since  o0b m_50_r$
 */
package com.focusx.elmt;

import com.attilax.core;
import com.attilax.util.PropXwebrootBase;

import static com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.directwebremoting.Container;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.ui.dwr.Util;
/** @author attilax 老哇的爪子
 * @since o0b m_50_r$ */
public class dwrReX {
	// attilax 老哇的爪子 m_50_r o0b

	public static void main(String[] args) {
		 Container container = ServerContextFactory.get().getContainer();
		 ScriptSessionManager manager =
		 container.getBean(ScriptSessionManager.class);
		// manager.gets
		// ((ServerContext) manager).getScriptSessionsByPage("/page?foo=bar")
		// manager.getAllScriptSessions();
		Collection<ScriptSession> sesses = ServerContextFactory.get().getScriptSessionsByPage("/vod/elemt/elemt_edit.jsp");
		// Util util=new Util(sesses);
		for (ScriptSession scriptSession : sesses) {
			String script = "recvFilepath('aaaa');";
			scriptSession.addScript(new ScriptBuffer(script));
		}

		// pushJs("");
	}

	/** @author attilax 老哇的爪子
	 * @since o0c 1_u_8
	 * 
	 * @param js_str */
	public static void pushJs(String js_str) {
		js_str=js_str.replace("/elfinder-2.0-rc1/php/../../", "");
		// attilax 老哇的爪子 1_u_8 o0c
		String config_o99 = new PropXwebrootBase().getConfig_o99("cfg.txt", "scriptSessionPage");
		//com.attilax.util
		@SuppressWarnings("deprecation")
		Collection<ScriptSession> sesses = ServerContextFactory.get().getScriptSessionsByPage(config_o99);
		// Util util=new Util(sesses);
		for (ScriptSession scriptSession : sesses) {
			String script = js_str;// "recvFilepath('aaaa');";
			scriptSession.addScript(new ScriptBuffer(script));
		}
	}
	//end push js
}

// attilax 老哇的爪子