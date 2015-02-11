/**
 * @author attilax 老哇的爪子
	@since  o9p l_o_54$
 */
package com.focusx.eq;

import com.attilax.core;
import com.attilax.net.websitex;
import com.focusx.job.UpdateMonitorJober;
import com.focusx.listener.MonitorListener;
import static com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
/** @author attilax 老哇的爪子
 * @since o9p l_o_54$ */
public class EqMntX {

	/** @author attilax 老哇的爪子
	 * @since o9p l_o_54
	 * 
	 * @param args */
	public static void main(String[] args) {
		// attilax 老哇的爪子 l_o_54 o9p
		String u = "http://localhost/vod/eqMntRun.jsp?run=0";
		System.out.println(websitex.WebpageContent(u));
		System.out.println("--ok");
		// run(1);
	}

	/** @author attilax 老哇的爪子
	 * @since o9p l_p_s
	 * 
	 * @param i */
	public static void run(HttpServletRequest request) {
		// attilax 老哇的爪子 l_p_s o9p

		try {
core.log("---o9o1");
			if (request.getParameter("run") != null) {
				int stat = Integer.parseInt(request.getParameter("run"));
				if (stat == 1) // ini task
				{

					if (MonitorListener.start == 0) {
						MonitorListener.inied = 1;
						MonitorListener ml = new MonitorListener();
						ml.contextInitialized(null);
					}

				}
				UpdateMonitorJober.run = stat;

			} else // def run
			{

				if (MonitorListener.start == 0) {
					MonitorListener.inied = 1;
					MonitorListener ml = new MonitorListener();
					ml.contextInitialized(null);
				}
				UpdateMonitorJober.run = 1;

			}

		} catch (Exception e) {
			core.log(e);
		}

	}

	// attilax 老哇的爪子 l_o_54 o9p
}

// attilax 老哇的爪子