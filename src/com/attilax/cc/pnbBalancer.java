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

public class pnbBalancer extends ccParser  implements Ibalance{

	public pnbBalancer()
	{
		this.acc="pnb";
		 tableKeyword = "人民币/美元账户";
		 tableKeywordExclude = "本期应还款金额 ";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 平安信用卡电子账单.eml
		pnbBalancer c = new pnbBalancer();
		
		String filepath = "C:\\ccx\\平安信用卡电子账单.eml";
 	filepath="C:\\ccx\\cc ca\\平安信用卡电子账单(10).eml";
 	
 	filepath="c:\\ccx\\cc ca\\平安信用卡电子账单(10).eml";
		
		item it = c.getBalanceItem(filepath);
		System.out.println(c.emltxt);
item it2=c.miniPayment();
		System.out.println(listUtil.toString_jsonFmt(it));
		core.print(it2);

	}

	public item getBalance_itemFmt(String path) {

		String txt = emlC.eml2txt(path);
		this.emltxt=txt;
		String left = "本期应还金额";
		String right = "本期还款日";
		String add = com.attilax.text.strUtil.Mid(txt, left, right);
		add=add.replaceAll("RMB", "");
		add=add.replaceAll(",", "");
		item it = new item();
		it.acc = this.acc;

		// it.cardno = cardno;
		it.accx = it.acc + it.cardno;
		String bls = numUtil.trim(add);
		it.m = securyInt.getFloat(bls, 0);
		it.date=getAccDate();
		return it;
		// return add;

	}
	
	private String getAccDate() {
		//本期账单日	2014-02-05 	本期应还金额
		String s=strUtil.Mid(emltxt, "本期账单日", "本期应还金额").trim();
		s=datex.trim(s);
		if(s.length()==0)
		{
			//2011年02月的信用卡对账单
			//账单日 2011-02-05 到期还款日
			s=strUtil.Mid(emltxt, "账单日", "到期还款日").trim();
			s=datex.trim(s);
		}
		
		return s;
	}
	@Deprecated
	private item getBalance_itemFmt_bek(String path) {

		File input = new File(path);
		String html = emlC.getBody(path);
		Document doc = null;
		filex.write(path + ".htm", html);
		doc = Jsoup.parse(html);

		hsbc hsbcc = new hsbc();
		cardno = hsbcc.getCardNo(doc.text());
		logger.info(cardno);
		List li = new ArrayList<item>();

		// if(1==1)return null;

		Element tb_mid = getTable(doc, tableKeyword, tableKeywordExclude);
		// Element tb_mini=getTable

		filex.write("c:\\table.htm", tb_mid.toString());
		// o39

		float bals = getBalance(tb_mid);
		item it = new item();
		it.acc = "pnb";

		it.cardno = cardno;
		it.accx = it.acc + it.cardno;
		it.m = bals;
		return it;
	}

	public float getBalance(Element tb_mid) {
		String bls_str = CellValue(tb_mid, 2, 1);

		bls_str = bls_str.replaceAll("人民币RMB", "");

		float bls = securyInt.getFloat(bls_str, 0);
		return bls;
	}
	
	
	@Override
	public item miniPayment() {
 
		
		item it=	  new tryX<item>()
		{

			@Override
			public  item  item(Object t) throws Exception {
				String txt=strUtil.Mid(emltxt,"本期最低还款额", "万里通"  );
				float bls = trimBalanceMinipay_o3(txt);
				item it=new item();
				it.acc=acc;
				it.m=bls;
				it.accx=it.acc+cardno;
				it.balanceOrMinipay=item.minipay;
				it.date=getAccDate();
				return it;
				 
			}
			
		}.$(null);
		if(it==null)
		{
			return new tryX<item>()
			{

				@Override
				public  item  item(Object t) throws Exception {
					return (item) miniPayment_o3(emltxt);
					 
				}
				
			}.$(null,1); 
		}
		return it;
		
		
		 
	}



	protected item miniPayment_o3(String emltxt) {

	//	最低还款金额 RMB      897.26 本期应还款金额
		String txt=strUtil.Mid(emltxt,"最低还款金额", "本期应还款金额"  );
		float bls = trimBalanceMinipay_o3(txt);
		item it=new item();
		it.acc=acc;
		it.m=bls;
		it.accx=it.acc+cardno;
		it.balanceOrMinipay=item.minipay;
		it.date=getAccDate();
		return it;
		//return null;
	}
	@Override
	public item  getBalanceItem(final String filepath) {
	 
		item bls=	  new tryX<item>()
		{

			@Override
			public  item  item(Object t) throws Exception {
				return (item) getBalance_itemFmt(filepath);
				 
			}
			
		}.$(null);
		if(bls==null)
		{
			return new tryX<item>()
			{

				@Override
				public  item  item(Object t) throws Exception {
					return (item) getBalance_itemFmt_o3(emltxt);
					 
				}
				
			}.$(null,1); 
		}
		return bls;
			
		
	}
	protected item getBalance_itemFmt_o3(String emltxt) {
	//	本期应还款金额 RMB   17,945.17 最低还款金额
	//	String txt = emlC.eml2txt(path);
	//	this.emltxt=txt;
		String left = "本期应还款金额";
		String right = "最低还款金额";
		String add = com.attilax.text.strUtil.Mid(emltxt, left, right);
		add=add.replaceAll("RMB", "");
		add=add.replaceAll(",", "");
		item it = new item();
		it.acc = this.acc;

		// it.cardno = cardno;
		it.accx = it.acc + it.cardno;
		String bls = numUtil.trim(add);
		it.m = securyInt.getFloat(bls, 0);
		it.date=getAccDate();
		return it;
	}

}
