package com.attilax.cc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import m.eml.emlC;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.attilax.util.securyInt;

public class hsbcBlancer extends ccParser  implements Ibalance{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		hsbcBlancer c=new hsbcBlancer();
		 
	String path = "C:\\ccx\\交通银行信用卡电子账单.eml";
	path="c:\\ccx\\cc cb\\交通银行信用卡电子账单(8).eml";
	item it=	c.getBalance_itemFmt(path);
	item it2=c.miniPayment();
	

System.out.println(listUtil.toString_jsonFmt(it));
core.print(it2);

	}

	 public item getBalance_itemFmt(String path) {
		tableKeyword="本期账单应还款额";
		tableKeywordExclude="本期账务说明 ";

		File input = new File(path);
		String html = emlC.getBody(path);
		Document doc = null;
		filex.write(path + ".htm", html);
		doc = Jsoup.parse(html);
		
		hsbc hsbcc=new hsbc();
		  billContent = doc.text();
		cardno =hsbcc. getCardNo(billContent);
		logger.info(cardno);
		List li = new ArrayList<item>();
		
		// if(1==1)return null;
		
		Element tb_mid = getTable(doc, tableKeyword, tableKeywordExclude);
		filex.write("c:\\table.htm", tb_mid.toString());
		//o39
		 
			float bals=getBalance(tb_mid);
			item it=new item();
			it.acc="hsbc";
			
			it.cardno=cardno;
			it.accx=it.acc+it.cardno;
			it.m=bals;
			it.date=getAccDate();
		return it;

		

	
		
	 
		//return li;
	}
	private String getAccDate() {
		//是您2014年02月份的信用卡电子账单。
		String s=strUtil.Mid(billContent, "是您", "月份");
		s=datex.fix(s)+"-11";
		return s;
	}

	public float getBalance(Element tb_mid) {
		String bls_str=CellValue(tb_mid,2,1);
		 
		float bls = trimBalanceMinipay(bls_str);
		return bls;
	}

	private float trimBalanceMinipay(String bls_str) {
		bls_str=bls_str.replaceAll("人民币RMB", "");
		
 
float bls=securyInt.getFloat(bls_str, 0);
		return bls;
	}


	@Override
	public item miniPayment() {
		
		
		String bls_str=CellValue(table,2,3);
		float bls = trimBalanceMinipay(bls_str);
 
		
		item it=new item();
		it.acc="hsbc";
		it.m=bls;
		it.accx=it.acc+cardno;
		it.balanceOrMinipay=item.minipay;
		it.date=getAccDate();
		it.cardno=this.cardno;
		return it;
	}



	@Override
	public item getBalanceItem(String filepath) {
	 
		return (item) getBalance_itemFmt(filepath);
	}

 

}
