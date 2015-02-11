/**
 * @author attilax 老哇的爪子
\t@since  Aug 3, 2014 4:00:19 AM$
 */
package com.attilax.io;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.text.regExpress;
import com.attilax.text.strUtil;
import com.attilax.util.dirx;

import static  com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  Aug 3, 2014 4:00:19 AM$
 */
public class geneDelBatShell {

	/**
	@author attilax 老哇的爪子
	\t@since  Aug 3, 2014 4:00:19 AM$
	
	 * @param args
	 * @throws IOException 
	 */	@SuppressWarnings("all")
	public static void main(String[] args) throws IOException {
		// attilax 老哇的爪子 4:00:19 AM Aug 3, 2014
		final filex fc = new filex("c:\\infoDelBat.bat");

		String s = "c:\\info2.txt";
		filex.read_HP(s, "gbk", new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 4:11:57 AM Aug 3, 2014

				{
					String line = arg0.toString();
				
					List<String> li = strUtil
							.find(regExpress.pathExpress, line);
					for (String f : li) {
						if(f.length()<9)continue;
						String fname=filex.getExtName(f);
						if(fname.length()==0)continue;
						System.out.println(f);
						
						if(pathx.isFile(f))
							fc.append_HP(f + "\r\n");
					}
					return null;
				}

			}
		});
		fc.close();
		System.out.println("--f");

	}
	 
	
	//  attilax 老哇的爪子 4:00:19 AM   Aug 3, 2014   
}

//  attilax 老哇的爪子