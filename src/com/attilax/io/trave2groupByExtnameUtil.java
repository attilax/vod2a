/**
 * @author attilax 老哇的爪子
\t@since  Aug 2, 2014 3:18:22 AM$
 */
package com.attilax.io;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollectionUtils;
import com.attilax.io.filex;
import com.attilax.util.dirx;
import com.attilax.util.fileC0;

import static com.attilax.core.*;

import java.util.*;
import java.net.*;
import java.io.*;

import net.sf.jsqlparser.statement.replace.Replace;

import org.apache.log4j.Logger;

/**
 * @author attilax 老哇的爪子
 * @since Aug 2, 2014 3:18:22 AM$
 */
public class trave2groupByExtnameUtil {

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 2, 2014 3:18:22 AM$
	 * 
	 * @param args
	 */
	public static Logger logger = Logger.getLogger("traver");

	public static void main(String[] args) throws IOException {
		
		String strPath = "D:\\workSpace\\vod";
	 List li=	trav2li (strPath, "c:\\vodfiles.txt");
	 final List<String> li3=new ArrayList<String>();
	 List li2=CollectionUtils.each(li, new Closure<String, Map>() {

		@Override
		public Map execute(final String arg0) throws Exception {
			// attilax 老哇的爪子  8:47:22 AM   Aug 19, 2014 
			String ext=filex.getExtName(arg0);
			if(!li3.contains(ext))
			li3.add(ext);
			{ 
//			return new HashMap(){{
//				String ext=filex.getExtName(arg0);
//				put("ext",ext);
//			}};
			 }
			return null; 
			
			
		}
	});
	 core.print_wzFmt( li3);
		// attilax 老哇的爪子 3:18:22 AM Aug 2, 2014
//		System.out.println("@f".replace("@f", "c:\\aa.aa"));
//		long start = new Date().getTime();
//		trav();
//		long end = new Date().getTime();
//		System.out.println("--pass::" + String.valueOf(end - start));
		// MySqlClass mc=new MySqlClass("dbc8","root","");
		//
		// mc.executeUpdate(sql);
		//
		// {
		// System.out.println("--");
		// }
		System.out.println("--f");
	}

	// attilax 老哇的爪子 3:18:22 AM Aug 2, 2014

	private static void trav(String strPath,String SavefileName  ) throws IOException {
		//final String fileName = "c:\\clraft.sql";
		final CharSequence tab="folderCountClraftr";
		final filex fc = new filex(SavefileName);
	//	String strPath = "D:\\workSpace\\vod";
		dirx.trave(strPath, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 3:46:39 AM Aug 2, 2014

				{
					String fname = arg0.toString();
//					String oriname=fname;
//					fname=	fname.replace("'", "\\'");
//					File f = new File(fname);
//
//				
//					String sql = "insert @t(file,size,extname)values('@f',@size,'@ext') "
//							.replace("@f", fname)
//							.replace("@size", String.valueOf(f.length()))
//							.replace("@ext", filex.getExtName(oriname))
//					.replace("@t",tab);
//					logger.info(sql);
//					sql = sql.replace("\\", "\\\\");
//					sql = sql.replace("\\\\'", "\\'");// Coldwine\\'s Blog - 博客园.htm   restore to \'
					fc.append_HP(fname + ";\r\n");

					return null;
				}

			}
		});
		fc.close();
	}
	
	

	private static List trav2li(String strPath,String SavefileName  )   {
		//final String fileName = "c:\\clraft.sql";
		final CharSequence tab="folderCountClraftr";
		//final filex fc = new filex(SavefileName);
	//	String strPath = "D:\\workSpace\\vod";
		final List<String> li=new ArrayList<String>();
		dirx.trave(strPath, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 3:46:39 AM Aug 2, 2014

				{
					String fname = arg0.toString();
//					String oriname=fname;
//					fname=	fname.replace("'", "\\'");
//					File f = new File(fname);
//
//				
//					String sql = "insert @t(file,size,extname)values('@f',@size,'@ext') "
//							.replace("@f", fname)
//							.replace("@size", String.valueOf(f.length()))
//							.replace("@ext", filex.getExtName(oriname))
//					.replace("@t",tab);
//					logger.info(sql);
//					sql = sql.replace("\\", "\\\\");
//					sql = sql.replace("\\\\'", "\\'");// Coldwine\\'s Blog - 博客园.htm   restore to \'
					//fc.append_HP(fname + ";\r\n");
					li.add(fname);

					return null;
				}

			}
		});
		return li;
		//fc.close();
	}
	
	/**
	 * for compare tree /a ....perf test
	@author attilax 老哇的爪子
	\t@since  Aug 2, 2014 8:16:57 AM$
	
	 * @throws IOException
	 */@Deprecated
	private static void trav2() throws IOException {
		final String fileName = "c:\\sql.txt.sql";
		final filex fc = new filex(fileName);
		dirx.trave("G:\\ati\\doc", new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 3:46:39 AM Aug 2, 2014

				{
				 
				 
					fc.append_HP(arg0.toString() + ";\r\n");

					return null;
				}

			}
		});
		fc.close();
	}
}

// attilax 老哇的爪子