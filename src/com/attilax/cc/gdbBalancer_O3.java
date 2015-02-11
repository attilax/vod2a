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
import com.attilax.text.strUtil;
import com.attilax.util.securyInt;

public class gdbBalancer_O3 extends gdbBalancerBase implements Ibalance  {

	



	public  gdbBalancer_O3()
	{
		this.acc="gdb";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		//String path="C:\\ccx\\尊敬的艾龙先生，广发卡2014年02月电子对账单.eml";
		String path="C:\\ccx\\cc c8\\尊敬的艾龙先生，广发卡2013年08月电子对账单.eml";
		
		String txt = emlC.eml2txt(path);
		System.out.println(txt);
		 
		filex.write(path + ".txt", txt);
		
		gdbBalancer_O3 c=new gdbBalancer_O3();
		 
		item it=	c.getBalance_itemFmt(path);
		item o2=c.miniPayment();
		
	System.out.println(listUtil.toString_jsonFmt(it));
	System.out.println(listUtil.toString_jsonFmt(o2));

	}




	
	/**
	 * o3i
	 */
	 public item getBalance_itemFmt(String path) {
		 this.billFilePath=path;
		 //Balance 25,327.69 本期最低还款额
		this.emltxt=emlC.eml2txt(path);
		 String left="Balance";
		 String right="本期最低还款额";
		 String s=strUtil.Mid(this.emltxt, left, right);
		 float bls=trimBalanceMinipay_o3(s);
			 
			//o39
			 
			 
				item it=new item();
				it.acc="gdb";
				
				it.cardno=cardno;
				it.accx=it.acc+it.cardno;
				it.m=bls;
				it.date=getAccDate(path);
			return it;

			

		
			
		 
			//return li;
		}
	 
		public Element getTable(Document doc, String keyword,String paichuWord) {
			
			Element tb_mid=super.getTable(doc, keyword, paichuWord);
			filex.write("c:\\tableFist.htm", tb_mid.toString());
			Element 	tb_mini=tb_mid.getElementsByTag("table").get(21);
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
			
			//本期最低还款额 Min Payment 1,267.00 还款到期日
			
			 String left="本期最低还款额";
			 String right="还款到期日";
			 String s=strUtil.Mid(this.emltxt, left, right);
			 float bls=trimBalanceMinipay_o3(s);
		 
			
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
