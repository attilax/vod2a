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

public class cmbBlancer_o3f extends ccParser implements Ibalance {

	public cmbBlancer_o3f() {
		tableKeyword = "人民币/美元账户";
		tableKeywordExclude = "本期应还款金额 ";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		cmbBlancer_o3f c = new cmbBlancer_o3f();
		// String eml="C:\\ccx\\cc.ca\\招商银行信用卡电子账单-账单全新升级，更安全，更多专属优惠.eml";
		// 本期应还金额 ￥6,014.30 ＄0.00 最低还款额
		String eml = "C:\\ccx\\cc c8\\招商银行信用卡电子账单.eml";
		item it = c.getBalance_itemFmt(eml);
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
		emltxt=txt;

		String add = com.attilax.text.strUtil.Mid(txt, left, rit);
		String bls = numUtil.trim(add);
		float bls_f = securyInt.getFloat(bls, 0);
		item it = new item();
		it.acc = "cmb";

		// it.cardno = cardno;
		it.accx = it.acc + it.cardno;

		it.m = bls_f;
		it.date = getAccDate();
		return it;

	}

	private String getAccDate() {
		// 账单周期 Statement Cycle 2013/07/21-2013/08/20 到期还款日
		String s = strUtil.Mid(emltxt, "账单周期", "到期还款日");
		String[] a = s.split("-");
		s = a[1].trim().replaceAll("/", "-");
		return s;
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
		try {

			String left = "本期最低还款额 ";
			String rit = "人民币账户";

			String add = com.attilax.text.strUtil.Mid(billTxt, left, rit);
			add = add.replaceAll("Min.Payment", "");
			String bls = numUtil.trim(add);
			float bls_f = securyInt.getFloat(bls, 0);
			item it = new item();
			it.acc = "cmb";

			// it.cardno = cardno;
			it.accx = it.acc + it.cardno;
			it.balanceOrMinipay = item.minipay;
			it.m = bls_f;
			it.date=getAccDate();
			return it;
		} catch (Exception e) {
			core.logger.debug(god.getTrace(e));
			return null;
		}
	}

	@Override
	public item getBalanceItem(String filepath) {

		return (item) getBalance_itemFmt(filepath);
	}
}
