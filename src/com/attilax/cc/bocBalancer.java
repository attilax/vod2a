package com.attilax.cc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import m.eml.emlC;
import m.numpkg.numUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.attilax.util.securyInt;
import com.attilax.util.tryX;

public class bocBalancer extends ccParser implements Ibalance {

	public bocBalancer() {
		this.acc = "boc";
		tableKeyword = "人民币/美元账户";
		tableKeywordExclude = "本期应还款金额 ";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 平安信用卡电子账单.eml
		bocBalancer c = new bocBalancer();

		item it = c.getBalanceItem("C:\\ccx\\中国银行银行卡电子账单.eml");
		System.out.println(c.emltxt);
		item it2 = c.miniPayment();
		System.out.println(listUtil.toString_jsonFmt(it));
		core.print(it2);

	}

	float minipay;

	/**
	 * o3h 最低还款额  RMB-61.29\-10.00  * 本期余额为负
	 */
	public item getBalance_itemFmt(String path) {

		final String txt = emlC.eml2txt(path);
		this.emltxt = txt;

		return new tryX<item>() {

			@Override
			public item item(Object t) throws Exception {
				String left = "最低还款额";
				String right = "本期余额为负";
				String add = com.attilax.text.strUtil.Mid(txt, left, right);
				add = add.replaceAll("RMB", "");
				add = strUtil.replaceByStar(add);
				String[] arr = strUtil.SplitByBackslash(add);
				String bls_s = arr[0];
				String mini_s = arr[1];
				String bls_trim = bls_s.trim().replaceAll("-", "");
				bls_trim = numUtil.trim(bls_trim);
				float bls = securyInt.getFloat(bls_trim, 0);

				String mini_trim = mini_s.trim().replaceAll("-", "");
				mini_trim = numUtil.trim(mini_trim);
				float mini = securyInt.getFloat(mini_trim, 0);
				minipay = mini;
				add = add.replaceAll(",", "");
				item it = new item();
				it.acc ="boc";

				// it.cardno = cardno;
				it.accx = it.acc + it.cardno;

				it.m = bls;
				it.date=getAccDate();
				return it;
				// return add;
			}

			

		}.$(null);

	}
	/**
	 * 账单日期 2014-02-07 到期还款日 
	 * @return
	 */
	private String getAccDate() {
		String date=strUtil.Mid(this.emltxt, "账单日期", "到期还款日").trim();
		return date;
	}
	public float getBalance(Element tb_mid) {
		String bls_str = CellValue(tb_mid, 2, 1);

		bls_str = bls_str.replaceAll("人民币RMB", "");

		float bls = securyInt.getFloat(bls_str, 0);
		return bls;
	}

	@Override
	public item miniPayment() {
		// return null;

		// String txt=strUtil.Mid(emltxt,"本期最低还款额", "万里通" );
		// String bls_str=CellValue(table,2,3);
		// float bls = trimBalanceMinipay_o3(txt);
		// 
		//		
		item it = new item();
		it.acc = this.acc;
		it.m = minipay;
		it.accx = it.acc + cardno;
		it.balanceOrMinipay = item.minipay;
		it.date=getAccDate();;
		return it;
	}

	@Override
	public item getBalanceItem(String filepath) {

		return (item) getBalance_itemFmt(filepath);
	}

}
