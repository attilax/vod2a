package com.attilax.cc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m.eml.emlC;
import m.numpkg.numUtil;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.text.strUtil;
import com.attilax.util.god;
import com.attilax.util.securyInt;

public class gdb {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public void main(String[] args) throws IOException {
		// TODO Auto-generated method stub  ������Ϣ
		String path = "c:\\html.txt.htm";
		path = "c:\\gdb.htm";
		List li = parse(path);
		String s = JSONArray.fromObject(li).toString(2);
		System.out.println(s);
		System.out.println("f");

	}
	 Logger logger = Logger.getLogger(gdb.class.getName());
	private String frompath;
	public  List parse(String path)   {
		frompath=path;
		File input = new File(path);
		String html=emlC.getBody(path);
		Document doc = null;
		try {
			doc = Jsoup.parse(html);
		} catch (Exception e1) {			 
		 
			logger.error("--------- this file:"+path);
			logger.error(god.getTrace(e1));
		 
		}
		Element tb_mid = getTable(doc,"零售利息");
		String   yearMonth=get_yearMonth(doc);
		
		List li = new ArrayList<item>();
		Element tbdy= tb_mid.children().get(0);
		Elements trs = tbdy.children();
		int n = 0;
		for (Element e : trs) {
			n++;
//			if (n == 1)
//				continue;
			if(paichuLine(e.toString(),"自动还款,支付宝网络还款,转账还款"))
				continue;
			Elements tds = e.children();
			
			item it = getItem(tds,yearMonth);
			li.add(it);

		}

		int el_size = li.size();
		System.out.println(el_size);
		return li;
	}
	
	/**
	 * aa  line ,key s
	 */
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
	
	private String get_yearMonth(Document doc) {
String s2="   账单周期 Statement cycle 2013/11/15 - 2013/12/14 综合信用额度";
s2=doc.text();
		
		String abcdef="abcdef";
		String fd="ab(.*?)ef";
		fd="账单周期(.*?)综合信用额度";
	String s=	strUtil.getMidtrings(s2, fd);
	String[] arr=s.split("-");
	String month=arr[1].trim();
	String monthx=getmonth(month);
		return monthx;
	}

	private Element getTable(Document doc, String keyword) {
		Element tb_mid=null;
		Elements el = doc.getElementsByTag("table");
		int n=0;
		for (Element e : el) {
			n++;
			System.out.println(" now table inedex:"+n);
			String tabletxt = e.ownText();
			 
			tabletxt = e.text();
			if(tabletxt.trim().contains("卡号Card"))
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
		tb_mid=tb_mid.getElementsByTag("div").get(0).getElementsByTag("table").get(0);
		return tb_mid;
	}

	private item getItem(Elements tds, String yearMonth) {
		item it = new item();
		int m = 0;
		for (Element td : tds) {
			m++;
			String text = td.text();
			if (m == 1)
			{
				it.date =yearMonth+"-05";
			}
			else if (m == 3)
				it.demo = text;
			else if (m == 4)
			{
				String t2=numUtil.trim(text);  
				it.m =Float.parseFloat(t2) ;
			}
			
//			else if (m == 3)
//				it.cardno=numUtil.trim(  text);
			else if (m == 6)
				it.shmacyar = text;
			else if (m == 7)
			{
				String t2=  text.trim().replaceAll("-", "").replaceAll(",", "");
				it.usd =securyInt.getFloat(t2, 0) ;
			}
		
		 

		}
		it.acc="gdb";
		it.fromFile=frompath;
		return it;
	}

	public void mainx(String[] string) {
		String s2="   账单周期 Statement cycle 2013/11/15 - 2013/12/14 综合信用额度";
		
		String abcdef="abcdef";
		String fd="ab(.*?)ef";
		fd="账单周期(.*?)综合信用额度";
	String s=	strUtil.getMidtrings(s2, fd);
	String[] arr=s.split("-");
	String month=arr[1].trim();
	String monthx=getmonth(month);
     
	System.out.println(monthx);
		
	}
	private String getmonth(String month) {
		 String[] a=month.split("/");
		 String y=a[0].trim();
		 y=numUtil.trim(y);
		return y+"-"+a[1].trim();
	}
}
