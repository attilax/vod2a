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

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.ref.refx;
import com.attilax.util.god;
import com.attilax.util.tryX;

//import fentsi.fileC0;
//import com.attilax.util.tryX;
import m.dirPkg.travDir;

@SuppressWarnings("all")
public class balanceMainCls {
	static String op;
	static Logger logger = Logger.getLogger(balanceMainCls.class.getName());

	public static void main(String[] args) {

		List<Object> li_all = new ArrayList<Object>();
		String strPath = "c:\\cc o7";
		strPath = args[0];
		op = args[1];
		// if(op.equals("balance"))
		String saveREsultPath = args[2];

		travDir.refreshFileList(strPath);
		List<String> li = travDir.filelist;
		for (String filepath : li) {

			try {
//				if(!filepath.contains("平安"))
//					continue;
				// logger
				itemProcess(li_all, filepath);
			} catch (Exception e) {
				logger.error("--------- this file:" + filepath);
				logger.error(god.getTrace(e));
			}

		}

		System.out.println(li_all.size());
		logger.info(" get all size:" + li_all.size());
		String s = JSONArray.fromObject(li_all).toString(2);
		logger.fatal(s);
		String saveFilePath = saveREsultPath + "\\" + god.getUUid() + ".csv";
		logger.info(" save in file:"+saveFilePath);
		geneCSV(li_all, saveFilePath);

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
						+ i.cls + "," + i.balanceOrMinipay+","+i.fromFile;
				li.add(s);
			}
		}
		filex.saveList2file(li, string, "gbk");

	}

	private static void itemProcess(List<Object> li_all, final String filepath) {

		String ext = com.attilax.io.filex.getExtName(filepath);
		if (ext.toLowerCase().equals("htm"))
			return;
		if (ext.toLowerCase().equals("txt"))
			return;

		Ibalance blsCls = getBblsCls(filepath);
		if(blsCls==null)
			return;
		li_all.add(filepath);
		item obj = blsCls.getBalanceItem(filepath);
		item obj2 = blsCls.miniPayment();
		if (obj == null) {
			logger.error("--------- this file:" + filepath);
			logger.error("    cant get getBalanceItem ");
		}
		if (obj2 == null) {
			logger.error("--------- this file:" + filepath);
			logger.error("    cant get miniPayment ");
		}
		if (obj != null)
		{
			obj.fromFile=filepath;
			li_all.add(obj);
		}
		if (obj2 != null)
		{	obj2.fromFile=filepath;
			li_all.add(obj2);}

	}

	private static Ibalance getBblsCls(String filepath) {
		try {
			String s = "光大,gda_balancer,交通银行  ,hsbcBlancer,	民生信用卡,msBlancer, "
					+ "招商银行 ,cmbBlancer,中信银行 ,jsbBalancerProxy,广发卡,gdbBalancerProxy, 平安信用卡,pnbBalancer,中国银行, bocBalancer,spdb,spdbBlancerProxy";
			String pkg = "m.cc.";
			String classname = "";
			List<String> li = listUtil.toList(s);
			int n = 0;
			for (String item : li) {
				if (filepath.contains(item)) {
					String clsname = li.get(n + 1);
					clsname=clsname.trim();
					classname = pkg + clsname;
					break;
				}
				n++;
			}
			core.logger.info(" start new filepath:"+filepath);
			core.logger.info(" start new clsname:"+classname);
			return (Ibalance) refx.newInstance(classname);
		} catch (Exception e) {
			core.logger.error(god.getTrace(e));
			return null;
		}
	}

}
