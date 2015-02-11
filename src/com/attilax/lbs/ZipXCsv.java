/**
 * 
 */
package com.attilax.lbs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.attilax.BaseTaskX;
import com.attilax.ITaskX;
import com.attilax.MultilineTaskX;
import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.ioc.IocX2;
import com.attilax.task.RunStat;
import com.attilax.time.timeUtil;

/**
 * @author ASIMO
 * 
 */
public class ZipXCsv extends MultilineTaskX implements ITaskX  {
	
	ZipX zipx1=new ZipX();
	ZipX zipx2;//= new ZipX138();
	ZipX zipx3= new ZipX51youbian();



	public static void main(String[] args) throws IOException, isIngEx {
	//	Timer tm=new Timer();
	//	tm.p
		String path=args[0];
		String rzt=args[1];
		Integer idx=Integer.parseInt( args[2].trim());
		ZipXCsv c = IocX2.getInstanceAti("iocx4zip").getBean(ZipXCsv.class);

		c.gene(path, idx, rzt,1);
		//tt();
	}

	private static void tt() throws isIngEx {
		String path = "D:\\My Documents\\Tencent Files\\1466519819\\FileRecv\\新建 Microsoft Office Excel Workbook1.csv";
		path = "c:\\a.csv";
		String rzt = "c:\\rzt.csv";
		ZipXCsv c = new ZipXCsv();

		c.gene(path, 3, rzt,1);
	}
	filex fc4norzt ;
	filex outfile;

	/**
	 * encode gbk
	 * 
	 * @author attilax 老哇的爪子
	 * @param addIndex
	 * @param startline 
	 * @throws isIngEx 
	 * @throws IOException
	 * @since obi d_56_f
	 */
	void gene(String path, final int addIndex, String rzt, int startline) throws isIngEx {
		 List<String> li = checkBefRun(path, rzt);
		int n = 0;
		linecount = li.size();
		this.runState=RunStat.run;
		for (final String line : li) {
			if(this.runState.equals(RunStat.pause))
				waiting();
			if(this.runState.equals(RunStat.stop))
				break;
			n++;
			nowLine = n;			
			System.out.println("now line:" + String.valueOf(n));
			if(nowLine<startline)
				continue;
			FutureTask<String> fut=new FutureTask(new Callable<String>() {

				@Override
				public String call() throws Exception {
					 geneSingle(addIndex, outfile, fc4norzt, nowLine, line);
					return "ok";
				}
			}) ;
			core.submit(fut, "fut");
			try {
				System.out.println(fut.get(20, TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				//  fut.cancel(true);
				e.printStackTrace();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			 

		}
		//end for
		closeFileAll();
		this.runState=RunStat.stop;

	}

	private List<String> checkBefRun(String path, String rzt) throws isIngEx {
		if(this.runState.equals(RunStat.run))
			 throw new isIngEx();
			if(timeUtil. isOvertime("2014-12-29"))
				throw new RuntimeException(" overtime  ..");
		if (!filex.getExtName(path.trim()).toLowerCase().equals("csv") )
			if(!filex.getExtName(path.trim()).toLowerCase().equals("txt"))
			throw new RuntimeException("must be csv or txt file  ..");
		if (path.trim().length() == 0)
			throw new RuntimeException("path is empty..");
		// String message = " path is empty";
		// // if(!new File(path.trim()).exists())
		// // String message = " path is empty";
		// try {
		// rztQueue.put("lineL:-1 msg:" + message);
		// } catch (InterruptedException e) {
		// throw new RuntimeException(e.getMessage());
		// }
		//
		// throw new RuntimeException(message);
		// }
		List<String> li = filex.read2list(path, "gbk");
		if (li.size() == 0)
			throw new RuntimeException("file rzt is empty..");

		
		try {
			outfile = new filex(rzt, "gbk");
		} catch (IOException e1) {

			throw new RuntimeException("create out file fail..");
		}
	
		try {
			fc4norzt = new filex("c:\\ex\\norzt"+filex.getUUidName()+".txt", "gbk");
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		return li;
	}



	private void closeFileAll() {
		try {
			outfile.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			fc4norzt.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void geneSingle(int addIndex, filex outfile, filex fc4norzt, int n,
			  String line) {
		boolean bdok;
		try {
			if (line.trim().length() == 0)
				return ;
			String[] a = line.split(",");
			String add = a[addIndex - 1].trim();
			String addNpost = "";
			try {
				if(zipx1==null)
					throw new NoRztEx("");
				addNpost = zipx1.toZipFromAddr(add) + ",frmit1";
				bdok=true;
			} catch (NoRztEx e2) {
				System.out.println("bdrzt:no");
				bdok=false;
				try {
					if(zipx2==null)
						throw new NoRztEx("");
					addNpost =zipx2.toZipFromAddr(add) + ",frmit2";
				} catch (NoRztEx e) {
					System.out.println("it2rzt:no");
					bdok=false;
					try {
						addNpost =zipx3.toZipFromAddr(add)
								+ ",frmit3";
					} catch (NoRztEx e3) {
						System.out.println("it3rzt:no");
						if (fc4norzt != null)
							fc4norzt.appendLine_flush_safe( add  );
						return ;
					}

				}

				// throw new RuntimeException("norzt jump");
			}

			String line_new = line + "," + addNpost;
			outfile.appendLine_flush_safe(line_new  );
			String string = "line:" + String.valueOf(n) + " rzt:"
					+ line_new;
			rztQueue.put(string);
			System.out.println(string);
			if(bdok)
				core.sleep(300);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(n);
			filex.saveLog(e, "c:\\ex");

		}
		return ;
	}



	/**
	 * @author attilax 老哇的爪子
	 * @since obi f_h_48
	 */
	public void geneTest(int max) {
		for (int i = 0; i < max; i++) {
			try {
				rztQueue.put("lineL:" + String.valueOf(i) + " msg:xxx");
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			// Thread.sleep(500);
		}
	}

		/**
		@author attilax 老哇的爪子
		@since   obn d_9_v
		 
		 */
 

		

}
