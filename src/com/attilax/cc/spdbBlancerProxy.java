package com.attilax.cc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import m.FileService;
import m.eml.emlC;
import m.numpkg.numUtil;
import  com.attilax.office.WordToHtml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.attilax.util.securyInt;
import com.attilax.util.tryX;

public class spdbBlancerProxy  extends ccParser  implements Ibalance {

	
	private String acc;
	public spdbBlancerProxy ()
	{
		this.acc="spdb";
		 tableKeyword = "人民币/美元账户";
		 tableKeywordExclude = "本期应还款金额 ";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	// D:\workspace\imServer\jacob-1.17-M2\jacob-1.17-M2-x86.dll
	 	System.setProperty("java.library.path", "D:/workspace/imServer/jacob-1.17-M2;./;");
		System.out.println(System.getProperty("java.library.path"));
		spdbBlancerProxy c = new spdbBlancerProxy();
		String docx="C:\\ccx\\spdb c8.docx";
		
		//本期欠款余额 2,919.60 本期最低还款额 186.90 到期还款日
		//docx="c:\\ccx\\spdb.doc";
 
		item it = c.getBalanceItem(docx);
		item it2=c.miniPayment();
		System.out.println(listUtil.toString_jsonFmt(it));
		System.out.println(listUtil.toString_jsonFmt(it2));

	}
public String billTxt;
	public	item getBalance_itemFmt(String path) {
		// ￥6,014.30 ＄0.00 最低还款额
		//当前欠款 2,869.65 上次还款
		String left="当前欠款";  
		String rit="上次还款";
		
		String txt = emlC.eml2txt(path);
		System.out.println(txt);
		billTxt=txt;
		 
		String add = com.attilax.text.strUtil.Mid(txt, left, rit);
		String bls = numUtil.trim(add);
	float bls_f=	securyInt.getFloat(bls, 0);
		item it = new item();
		it.acc = "cmb";

		// it.cardno = cardno;
		it.accx = it.acc + it.cardno;
	
		it.m = bls_f;
		return it;

	 

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
		
		 
		return balancerInstanceO3.miniPayment();
	}



	@Override
	public item getBalanceItem(final String filepath) {
	 
		 item o=new tryX<item>(){

			@Override
			public  item  item(Object t) throws Exception {
			    spdbBlancer c=new spdbBlancer();
			    balancerInstanceO3=c;
				return c.getBalanceItem(filepath);
			}}.$(null);
			
			if(o==null)
			{
				 o=new tryX<item>(){

						@Override
						public  item  item(Object t) throws Exception {
						    spdbBlancerO3 c=new spdbBlancerO3();
						    balancerInstanceO3=c;
							return c.getBalanceItem(filepath);
						}}.$(null);
			}
			return o;
	}
}
