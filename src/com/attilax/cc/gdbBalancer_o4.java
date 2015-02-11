package com.attilax.cc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import m.eml.emlC;
import m.numpkg.numUtil;

import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.util.securyInt;

public class gdbBalancer_o4 extends gdbBalancerBase implements Ibalance  {

	public  gdbBalancer_o4()
	{
		this.acc="gdb";
		tableKeyword="信用卡账户信息";
		tableKeywordExclude="本期账户明细";

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		 String path="C:\\ccx\\尊敬的艾龙先生，广发卡2014年02月电子对账单.eml";
	//	String path="C:\\ccx\\cc c8\\尊敬的艾龙先生，广发卡2013年08月电子对账单.eml";
		 path="C:\\ccx\\cc cb\\尊敬的艾龙先生，广发卡2013年12月电子对账单.eml";
		// path="c:\\ccx\\尊敬的艾龙先生，广发卡2014年02月电子对账单.eml";
		
		String txt = emlC.eml2txt(path);
		 
		filex.write(path + ".txt", txt);
		
		gdbBalancer_o4 c=new gdbBalancer_o4();
		 
		item it=	c.getBalance_itemFmt(path);
		item o2=c.miniPayment();
		
	System.out.println(listUtil.toString_jsonFmt(it));
	System.out.println(listUtil.toString_jsonFmt(o2));

	}




	
	
	 public item getBalance_itemFmt(String path) {
		 this.billFilePath=path;
//			tableKeyword="综合信用额度";
//			tableKeywordExclude="信用卡账户信息";

			File input = new File(path);
			String html = emlC.getBody(path);
			Document doc = null;
			filex.write(path + ".htm", html);
			doc = Jsoup.parse(html);
			
			hsbc hsbcc=new hsbc();
			String text = doc.text();
			this.emltxt=text;
			cardno =hsbcc. getCardNo(text);
			logger.info(cardno);
			List li = new ArrayList<item>();
			
			// if(1==1)return null;
			
			Element tb_mid = getTable(doc, tableKeyword, tableKeywordExclude);
			filex.write("c:\\table.htm", tb_mid.toString());
			//o39
			 
				float bals=getBalance(tb_mid);
				item it=new item();
				it.acc="gdb";
				
				it.cardno=cardno;
				it.accx=it.acc+it.cardno;
				it.m=bals;
				it.date=getAccDate(billFilePath);
			return it;

			

		
			
		 
			//return li;
		}
	 
		public Element getTable(Document doc, String keyword,String paichuWord) {
			
			Element tb_mid=super.getTable(doc, keyword, paichuWord);
			filex.write("c:\\tableFist.htm", tb_mid.toString());
			Element 	tb_mini=tb_mid.getElementsByTag("table").get(26);
			filex.write("c:\\tableFinal.htm", tb_mini.toString());
			this.table=tb_mini;
		//	String s=tb_mini.toString();
		//	FileService.writeFile("c:\\table.txt",s);
			return tb_mini;
			
		}
		
		public float getBalance(Element tb_mid) {
			String bls_str = CellValue(tb_mid, 1, 2);

			bls_str = bls_str.replaceAll("RMB", "");
			bls_str = bls_str.replaceAll(",", "");
			bls_str=numUtil.trim(bls_str);
			float bls = securyInt.getFloat(bls_str, 0);
			return bls;
		}
		
		@Override
		public item miniPayment() {
			
			
			String bls_str=CellValue(table,1,3);
			 
			
	 
	float bls=ccParser.trimBalanceMinipay_o3(bls_str);
	 
			
			item it=new item();
			it.acc=this.acc;
			it.m=bls;
			it.accx=it.acc;
			it.balanceOrMinipay=item.minipay;
			it.balanceOrMinipay=item.minipay;
			it.date=getAccDate(billFilePath);
			return it;
		}



		@Override
		public item getBalanceItem(String filepath) {
		 
			return (item) getBalance_itemFmt(filepath) ;
		}

}
