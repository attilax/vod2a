package com.attilax.cc;

//生成Excel的类
import java.io.File;

//import jxl.Workbook;
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.attilax.io.filex;
import com.attilax.util.god;
import com.attilax.util.tryX;

//import fentsi.fileC0;
//import com.attilax.util.tryX;
import m.dirPkg.travDir;

@SuppressWarnings("all")
public class mainx {
	static String op="";
	static Logger logger = Logger.getLogger(mainx.class.getName());

	public static void main(final String[] args) {

		List<Object> li_all = new ArrayList<Object>();
		final String strPath1 = "C:\\cc o7";
		String strPath = new tryX<String>() {

			@Override
			public String item(Object t) throws Exception {
				// strPath1=;
				op = args[1];
				return args[0];
			}

		}.$(strPath1);

		String saveREsultPath = new tryX<String>() {

			@Override
			public String item(Object t) throws Exception {
				// strPath1=;
				// op=args[1];
				return args[2];
			}

		}.$("c:\\cco4Result");

		travDir.refreshFileList(strPath);
		List<String> li = travDir.filelist;
		for (String filepath : li) {

			try {
				// logger
			mainx c = new mainx();
			c.filt();
	//		c.filtReverse(bk.pn);
			c.	itemProcess(li_all, filepath);
			} catch (Exception e) {
				logger.error("--------- this file:" + filepath);
				logger.error(god.getTrace(e));
			}

		}

		System.out.println(li_all.size());
		logger.info(" get all size:" + li_all.size());
		String s = JSONArray.fromObject(li_all).toString(2);
		logger.fatal(s);
		geneCSV(li_all, saveREsultPath + "\\" + god.getUUid() + ".csv");

	}

	private String filtReverse="";

	/**
	@author attilax 老哇的爪子
		@since  o85 1_d_n$
	
	 * @param gda
	 */
	private void filtReverse(String gda) {
		// attilax 老哇的爪子  1_d_n   o85 
	this.filtReverse=gda;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o85 0_48_r$
	
	 */
	private void filt() {
		// attilax 老哇的爪子  0_48_r   o85 
		{  } 
		{}
		{}
		
	}

	private static void geneCSV(List<Object> li_all, String string) {
		if (op.equals("balance")) {
			geneCsv4Balance(li_all, string);
			return;
		}
		List<String> li = new ArrayList<String>();
		for (Object obj : li_all) {
			if (obj instanceof item) {
				item i = (item) obj;
				String s = i.acc + i.cardno + "," + i.date + "," + i.m + ","
						+ i.cls + "," + i.demo;
				li.add(s);
			}
		}
		filex.saveList2file(li, string, "gbk");

	}

	private static void geneCsv4Balance(List<Object> li_all, String string) {
		List<String> li = new ArrayList<String>();
		for (Object obj : li_all) {
			if (obj instanceof item) {
				item i = (item) obj;
				String s = i.acc + i.cardno + "," + i.date + "," + i.m + ","
						+ i.cls + "," + i.demo;
				li.add(s);
			}
		}
		filex.saveList2file(li, string, "gbk");

	}

	private   void itemProcess(List<Object> li_all, final String filepath) {
		String ext = com.attilax.io.filex.getExtName(filepath);
		//o85 flt geneed htm 
		if (ext.toLowerCase().equals("htm"))
			return;
		if(this.filtReverse.length()>0)
		if(!filepath.contains(this.filtReverse))
			return;
		// com.sun.mail
		if (filepath.contains("光大")) {
			gda c = new gda();
			li_all.add(filepath);
			c.op = op;
			List lix = c.parse(filepath);
			li_all.addAll(lix);
		} else if (filepath.contains("交通银行")) {
			hsbc c = new hsbc();
			li_all.add(filepath);
			List lix = c.parse(filepath);
			li_all.addAll(lix);
		} else if (filepath.contains("民生信用卡")) {
			try {
				li_all.add(filepath);
				ms msc = new ms();
				List lix = msc.parse(filepath);
				li_all.addAll(lix);
			} catch (IOException e) {
				logger.error("--------- this file:" + filepath);
				logger.error(god.getTrace(e));
			}

		} else if (filepath.contains("招商银行")) {
			List lix = (List) new tryX() {

				@Override
				public Object item(Object t) throws Exception {
					cm cmx = new cm();

					return cmx.parse(filepath);
				}

				public Object log(Exception e) {
					logger.info(" paser cm catn ,try next ");
					logger.info("--------- this file:" + filepath);
					logger.warn(god.getTrace(e));
					logger.info("  warning end ");
					return e;
				}

			}.$(null);
			if (lix == null)
				lix = (List) new tryX() {

					@Override
					public Object item(Object t) throws Exception {
						cm_o1f cmx = new cm_o1f();

						return cmx.parse(filepath);

					}

					public Object log(Exception e) {

						logger.error("--------- this file:" + filepath);
						logger.error("");
						logger.error(god.getTrace(e));
						logger.info("  error end ");
						return e;
					}
				}.$(null);

			if (lix == null) {
				logger.error("--------- this file:" + filepath);
				logger.error("  cmb cant get any rs ");
			} else if (lix.size() == 0) {
				logger.warn("--------- this file:" + filepath);
				logger.warn("  cmb   get rs is empty");
			} else
				li_all.addAll(lix);

		} else if (filepath.contains("中信银行")) {
			try {
				li_all.add(filepath);
				js jsc = new js();
				List lix = jsc.parse(filepath);
				li_all.addAll(lix);
			} catch (IOException e) {
				logger.error("--------- this file:" + filepath);
				logger.error(god.getTrace(e));
			}

		} else if (filepath.contains("广发卡")) {
			li_all.add(filepath);
			gdb c = new gdb();
			List lix = c.parse(filepath);
			li_all.addAll(lix);

		} else if (filepath.contains(bk.pn)) {
			try {
				li_all.add(filepath);
				List lix = pnb.parse(filepath);
				li_all.addAll(lix);
			} catch (IOException e) {
				logger.error("--------- this file:" + filepath);
				logger.error(god.getTrace(e));
			}

		} else if (filepath.contains("中国银行")) {
			try {
				li_all.add(filepath);
				boc c = new boc();
				List lix = c.parse(filepath);
				li_all.addAll(lix);
			} catch (IOException e) {
				logger.error("--------- this file:" + filepath);
				logger.error(god.getTrace(e));
			}

		}

		else if (filepath.contains("spdb")) {
			try {
				li_all.add(filepath);
				spdb c = new spdb();
				List lix = c.parse(filepath);
				li_all.addAll(lix);
			} catch (Exception e) {
				logger.error("--------- this file:" + filepath);
				logger.error(god.getTrace(e));
			}

		}
	}

}
