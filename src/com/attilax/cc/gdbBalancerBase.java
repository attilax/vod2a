package com.attilax.cc;

import com.attilax.text.strUtil;

public class gdbBalancerBase extends ccParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//c:\ccx\尊敬的艾龙先生，广发卡2014年02月电子对账单.eml
	public String getAccDate(String filepath) {
		// 账单周期 Statement Cycle 2013/07/21-2013/08/20 到期还款日
		String s = strUtil.Mid(filepath, "广发卡", "月");
	s=datex.fix(s)+"-14";
		return s;
	}

}
