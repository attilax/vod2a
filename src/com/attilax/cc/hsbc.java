package com.attilax.cc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m.dataPkg.dateUtil;
import m.datepkg.dateUtil_o16;
import m.eml.emlC;
import com.attilax.html.htmlx;
import m.numpkg.numUtil;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.io.filex;
import com.attilax.text.strUtil;
import com.attilax.util.securyInt;

public class hsbc extends boc{

	private String frompath;
	private   String paichuLIne="扣款帐户余额不足,银行代发,跨行转入,取现－银联,分期当月扣款,主卡and卡号末四位";
	private   String findTablePaichuStr = "取现及费用扣收明细,取现及其他费用明细";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public  static void main(String[] args) throws IOException {
		String path = "c:\\html.txt.htm";
	
		path = "c:\\hsbc.htm";
		path="c:\\ccx\\cc cb\\交通银行信用卡电子账单(7).eml";
		path="c:\\ccx\\cc cb\\交通银行信用卡电子账单(1).eml";
		
		hsbc c=new hsbc();
		List li =c. parse(path);
		String s = JSONArray.fromObject(li).toString(2);
		System.out.println(s);
		System.out.println("f");

	}
	public String getCardNo(String string) {
		String html2txt = htmlx.html2txt(string.toString());
		 
		String s=	strUtil.getMidtrings( html2txt  ,"\\*\\*","本期账务说明");
		s=numUtil.trim(s);
		
	return s;
}
	
	List parse(String path)   {
		frompath=path;
		
		
		File input = new File(path);
		String html=emlC.getBody(path);
		Document doc = null;
		 filex.write(path+".htm", html);
			doc = Jsoup.parse(html);
		 cardno=getCardNo(doc.text());
		 logger.info(cardno);
		//	if(1==1)return null;
		Element tb_mid = getTable(doc,"本期利息",findTablePaichuStr);
		String   yearMonth="";;//get_yearMonth(doc);
		
		 filex.write("c:\\table.htm", tb_mid.toString());
		
		List li = new ArrayList<item>();
		Element tbdy= tb_mid.children().get(0);
		Elements trs = tbdy.children();
		int n = 0;
		for (Element e : trs) {
			n++;
			if (n <=2)
				continue;
			if(paichuLine(e.toString(), paichuLIne))
				continue;
			Elements tds = e.children();
			
			item it = getItem(tds,yearMonth);
			li.add(it);

		}

		int el_size = li.size();
		System.out.println(el_size);
		return li;
	}
	
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
	public Logger logger = Logger.getLogger(hsbc.class.getName());
	
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

}
