package com.attilax.cc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import m.FileService;
import m.eml.emlC;
import m.numpkg.numUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.attilax.util.god;
import com.attilax.util.securyInt;

public class cmbBlancer extends ccParser implements Ibalance {

	public cmbBlancer() {
//		tableKeyword = "人民币/美元账户";
//		tableKeywordExclude = "本期应还款金额 ";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		cmbBlancer c = new cmbBlancer();
	// String eml = "C:\\ccx\\cc.ca\\招商银行信用卡电子账单-账单全新升级，更安全，更多专属优惠.eml";
		// 本期应还金额 ￥6,014.30 ＄0.00 最低还款额
		  String eml = "C:\\ccx\\cc c8\\招商银行信用卡电子账单.eml";
		item it = c.getBalanceItem(eml);
		item it2 = c.miniPayment();
		System.out.println(listUtil.toString_jsonFmt(it));
		System.out.println(listUtil.toString_jsonFmt(it2));

	}

	public String billTxt;

	public item getBalance_itemFmt(String path) {

		String left = "本期还款总额";
		String rit = "预借现金额度";

		String txt = emlC.eml2txt(path);
		System.out.println(txt);
		billTxt = txt;

		String add = com.attilax.text.strUtil.Mid(txt, left, rit);
		String bls = numUtil.trim(add);
		float bls_f = securyInt.getFloat(bls, 0);
		item it = new item();
		it.acc = "cmb";

		// it.cardno = cardno;
		it.accx = it.acc + it.cardno;

		it.m = bls_f;
		return it;

	}

	public Element getTable(Document doc, String keyword, String paichuWord) {

		Element tb_mid = super.getTable(doc, keyword, paichuWord);
		filex.write("c:\\tableFist.htm", tb_mid.toString());
		Element tb_mini = tb_mid.getElementsByTag("table").get(8);
		// String s=tb_mini.toString();
		// FileService.writeFile("c:\\table.txt",s);
		return tb_mini;

	}

	public float getBalance(Element tb_mid) {
		String bls_str = CellValue(tb_mid, 1, 3);

		float bls = trimBalanceMinipay(bls_str);
		return bls;
	}

	private float trimBalanceMinipay(String bls_str) {
		bls_str = bls_str.replaceAll("RMB", "");
		bls_str = numUtil.trim(bls_str);
		float bls = securyInt.getFloat(bls_str, 0);
		return bls;
	}

	@Override
	public item miniPayment() {

	 
	    item it;
	    
	    	it=ib1.miniPayment();
	    	if(it==null)
	    		return ib2.miniPayment();
	    	else
	    	return it;
	}
	 //String billTxt;
	Ibalance ib1;
	Ibalance ib2;
	
	@Override
	public item getBalanceItem(String filepath) {
		cmbBlancer_o3g cb2=new cmbBlancer_o3g();
	
	    Ibalance ib=new cmbBlancer_o3f();
	    
		ib1=ib;
		ib2=cb2;
	    item it = null;
	    try{
	    	it=ib.getBalanceItem(filepath);
	    }catch(Exception e)
	    {
	    	core.logger.debug(god.getTrace(e));
	    	if(it==null)
	    		return cb2.getBalanceItem(filepath);
	    	else
	    	return it;
	    }
		return it;
	    	
	   
	    
		 
	}
}
