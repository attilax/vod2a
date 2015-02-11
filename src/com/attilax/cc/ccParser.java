package com.attilax.cc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import m.dataPkg.dateUtil;
import m.datepkg.dateUtil_o16;
import m.eml.emlC;
import com.attilax.html.htmlx;
import m.numpkg.numUtil;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.attilax.util.securyInt;

public abstract  class ccParser {
	 Logger logger = Logger.getLogger(mainx.class.getName());
	public String cardno="";
	public String tableKeyword;
	public String 	tableKeywordExclude;
	protected   String findTablePaichuStr = "交易明细表";
	private   String findTableKeyword = "交易描述";
	private   String paichuLIne="扣款帐户余额不足,银行代发,跨行转入";
	private   String cardNo="";
	private   int skipLine=1;
	private   String accDay = "10";
	private String htmlEncode = "utf-8";
	protected String frompath;
	 public static String balance="balance";
	 public String acc;
	 public String emltxt;
	 public Ibalance balancerInstanceO3;
	 public String billFilePath;
	 public String billContent;
	 /**
	  * o39
	  * @param text
	  * @return
	  */
		protected float getBalance(String text) {
			// TODO Auto-generated method stub
			return 0;
		}
	protected String getCardNo(String string) {
		String html2txt = htmlx.html2txt(string.toString());
		 
		String s=	strUtil.getMidtrings( html2txt  ,"\\*\\*","本期账务说明");
		s=numUtil.trim(s);
		
	return s;
}
	
	public Elements getItemTds(Element e) {
		Element tb=	e.getElementsByTag("table").get(2);
		Element tbdy_2= tb.children().get(0);
		Element tr=tbdy_2.children().get(0);
	return tr.children();
}
	protected boolean isPaichuTable(String string, String paichuWord) {
		String[] a=paichuWord.split(",");
		for(String s:a)
		{	if(string.contains(s))
		     return true;
			String[] a2=s.split("and");
			if(a2.length>1)
			{
				if(string.contains(a2[0])&& string.contains(a2[1]))
					return true;
			}
			
		}
		 
	return false;
	}
	/**
	 * o39
	 * @param tb_mid
	 * @param row
	 * @param column
	 * @return
	 */
	protected String CellValue(Element tb_mid, int row, int column) {
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
	public item getItem(Elements tds, String yearMonth) {
		item it = new item();
		int m = 0;
		for (Element td : tds) {
			m++;
			String text = td.text();
			logger.debug( " td index:"+m+" value:"+text);
			if (m == 1)
			{
				if(text.trim().length()==0)
					return new item();
//				if(text.contains("主卡")||text.contains("卡号末四位") )
//					return new item();
				it.date =dateUtil_o16.convertDate_YYYY_MM_DD_safe(   dateUtil.trim(text) );
			}
			else if (m == 3)
				it.demo = text;
			else if (m == 5)
			{
				String t2=numUtil.trim(text);  
				it.m = securyInt.getFloat(t2, 0);
			}
		
//			else if (m == 3)
//				it.cardno=numUtil.trim(  text);
//			else if (m == 6)
//				it.shmacyar = text;
//			else if (m == 7)
//			{
//				String t2=  text.trim().replaceAll("-", "").replaceAll(",", "");
//				it.usd =securyInt.getFloat(t2, 0) ;
//			}
		
		 

		}
		it.acc="hsbc";
		if(it.demo.contains("利息"))
			it.cls="itrst";
		if(it.demo.contains("手续费"))
			it.cls="itrst";
//		if(it.demo.contains("手续费"))
//			it.cls="fcssf";
		it.cardno=this.cardno;
		
		
		
it.fromFile=frompath;
		return it;
	}
	public Element table;
	public Element getTable(Document doc, String keyword,String paichuWord) {
		Element tb_mid=null;
		Elements el = doc.getElementsByTag("table");
		int n=0;
		for (Element e : el) {
			n++;
			System.out.println(" now table inedex:"+n);
			String tabletxt = e.ownText();
			 
			tabletxt = e.text();
			if(isPaichuTable(tabletxt.trim(),paichuWord))
				continue;
			if(tabletxt.trim().contains(paichuWord))
			{
			//	System.out.println(tabletxt);
				continue;
			}
			 
			
			if (tabletxt.trim().contains(keyword)) {
				System.out.println("-----------");
				System.out.println(tabletxt);
				tb_mid = e;
				break;
			}
			// tabletxt=e.toString();

			// tabletxt=e.

			// System.out.println(tabletxt);
			
		}
		//tb_mid=tb_mid.getElementsByTag("div").get(0).getElementsByTag("table").get(0);
		this.table=tb_mid;
		return tb_mid;
	}
	public boolean paichuLine(String string,String paichuLIneKeywords_str) {
		String[] a=paichuLIneKeywords_str.split(",");
		for(String s:a)
		{	if(string.contains(s))
		     return true;
			String[] a2=s.split("and");
			if(a2.length>1)
			{
				if(string.contains(a2[0])&& string.contains(a2[1]))
					return true;
			}
			
		}
		 
	return false;
}
	
	public item getBalance_itemFmt(String path) {

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
		it.acc = "msb";

		it.cardno = cardno;
		it.accx = it.acc + it.cardno;
		it.m = bals;
		return it;
	}
	public float getBalance(Element tb_mid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static float trimBalanceMinipay_o3(String bls_str) {
		bls_str = bls_str.replaceAll("RMB", "");
		bls_str = numUtil.trim(bls_str);
		float bls = securyInt.getFloat(bls_str, 0);
		return bls;
	}
}
