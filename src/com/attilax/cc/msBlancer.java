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
import com.attilax.util.securyInt;

public class msBlancer extends ccParser  implements Ibalance {
	
	//特别呈上您	2014年02月	对账单
	public msBlancer()
	{
		 tableKeyword = "人民币/美元账户";
		 tableKeywordExclude = "本期应还款金额 ";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		msBlancer c = new msBlancer();		
		String path = "C:\\ccx\\民生信用卡2014年02月电子对账单.eml";
		path = "C:\\ccx\\cc ca\\民生信用卡2013年11月电子对账单.eml";
		item it = c.getBalance_itemFmt(path);
		System.out.println(c.emltxt);
		

		System.out.println(listUtil.toString_jsonFmt(it));
		item it2=c.miniPayment();
		core.print(it2);

	}

	public	item getBalance_itemFmt(String path) {

		File input = new File(path);
		String html = emlC.getBody(path);
		Document doc = null;
		filex.write(path + ".htm", html);
		doc = Jsoup.parse(html);

		hsbc hsbcc = new hsbc();
		String text = doc.text();
		this.emltxt=text;
		cardno = hsbcc.getCardNo(text);
		logger.info(cardno);
		List li = new ArrayList<item>();

		// if(1==1)return null;

		Element tb_mid = getTable(doc, tableKeyword, tableKeywordExclude);
	//	Element tb_mini=getTable
		this.table=tb_mid;
		
		filex.write("c:\\table.htm", tb_mid.toString());
		// o39

		float bals = getBalance(tb_mid);
		item it = new item();
		it.acc = "msb";

		it.cardno = cardno;
		it.accx = it.acc + it.cardno;
		it.m = bals;
		it.date=getAccDate();
		return it;

	}

	private String getAccDate() {
		//	2014年02月	对账单
		String s=strUtil.Mid(emltxt, "特别呈上您", "月.*对账单");
		s=datex.fix(s)+"-11";
		return s;
	}

	public Element getTable(Document doc, String keyword,String paichuWord) {
		
		Element tb_mid=super.getTable(doc, keyword, paichuWord);
		filex.write("c:\\tableFist.htm", tb_mid.toString());
		Element 	tb_mini=tb_mid.getElementsByTag("table").get(8);
	//	String s=tb_mini.toString();
	//	FileService.writeFile("c:\\table.txt",s);
		return tb_mini;
		
	}
	public float getBalance(Element tb_mid) {
		String bls_str = CellValue(tb_mid, 1, 3);

	float bls=	trimBalanceMinipay(bls_str);
		return bls;
	}
	
	
	private float trimBalanceMinipay(String bls_str) {
		bls_str = bls_str.replaceAll("RMB", "");
		bls_str=numUtil.trim(bls_str);
		float bls = securyInt.getFloat(bls_str, 0);
		return bls;
	}
	
	
	@Override
	public item miniPayment() {
		
		
		String bls_str=CellValue(table,1,4);
		float bls = trimBalanceMinipay(bls_str);
 
		
		item it=new item();
		it.acc="msb";
		it.m=bls;
		it.accx=it.acc+cardno;
		it.balanceOrMinipay=item.minipay;
		it.date=getAccDate();
		return it;
	}



	@Override
	public item getBalanceItem(String filepath) {
	 
		return (item) getBalance_itemFmt(filepath);
	}
}
