package com.attilax.cc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import m.eml.emlC;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.attilax.util.securyInt;

public class gda_balancer  extends gda implements Ibalance {

public gda_balancer()
{
 	 op=ccParser.balance;
}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		gda_balancer c=new gda_balancer();
	
	String path = "C:\\ccx\\光大银行信用卡电子对账单20140205.eml";
 	//path="C:\\ccx\\cc ca\\光大银行信用卡电子对账单20131105.eml";
 
	item li=	c.getBalanceItem(path);
	item mini=c.miniPayment();
System.out.println(listUtil.toString_jsonFmt(li));
System.out.println(listUtil.toString_jsonFmt(mini));
	}

	
	public String billtxt;
	public List parse(String path) {
		tableKeyword="人民币本期应还款额";
		tableKeywordExclude="信用卡账户信息 ";

		File input = new File(path);
		String html = emlC.getBody(path);
		Document doc = null;
		filex.write(path + ".htm", html);
		doc = Jsoup.parse(html);
		String text = doc.text();
	billtxt=text;
		cardno = getCardNo(text);
		logger.info(cardno);
		List li = new ArrayList<item>();
		
		// if(1==1)return null;
		
		Element tb_mid = getTable(doc, tableKeyword, tableKeywordExclude);
		filex.write("c:\\table.htm", tb_mid.toString());
		//o39
		if(this.op.equals(this.balance))
		{
			float bals=getBalance(tb_mid);
			item it=new item();
			it.acc="gda";
			it.m=bals;
			it.accx=it.acc+it.cardno;
			it.date=getAccDate();
			li.add(it);
			return li;
		}
		
		String yearMonth = "";
		;// get_yearMonth(doc);

		

	
		
		int el_size = li.size();
		System.out.println(el_size);
		return li;
	}


	private String getAccDate() {
		//
		String s = strUtil.Mid(billtxt, "电子账单(", "月)");
		s =datex.fix(s);
		s=s+"-05";
		
		return s;
	}


	Element table;
	public float getBalance(Element tb_mid) {
		table=tb_mid;
		String bls_str=CellValue(tb_mid,2,4);
		bls_str=bls_str.replaceAll("￥", "");
		bls_str=bls_str.replaceAll(",", "");
		
 
float bls=securyInt.getFloat(bls_str, 0);
		return bls;
	}



	public String CellValue(Element tb_mid, int row, int column) {
		Element tbdy = tb_mid.children().get(0);
		Elements trs = tbdy.children();
		int n = 1;
		for (Element e : trs) {
			
			
			if (n < row)
			{
				n++;
				continue;
			}
		 
			Elements tds = e.children();
			Element td=tds.get(column-1);
			String text = td.text();
			return text;

		}
		return null;
	}



	@Override
	public item miniPayment() {
		
		
		String bls_str=CellValue(table,2,5);
		bls_str=bls_str.replaceAll("￥", "");
		bls_str=bls_str.replaceAll(",", "");
		
 
float bls=securyInt.getFloat(bls_str, 0);
 
		
		item it=new item();
		it.acc="gda";
		it.m=bls;
		it.accx=it.acc;
		it.balanceOrMinipay=item.minipay;
		it.date=getAccDate();
		return it;
	}



	@Override
	public item getBalanceItem(String filepath) {
	 
		return (item) parse(filepath).get(0);
	}

}
