package com.attilax.cc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m.FileService;
import m.dataPkg.dateUtil;
import m.datepkg.dateUtil_o16;
import m.eml.emlC;
import com.attilax.html.htmlx;
import m.numpkg.numUtil;
import net.sf.json.JSONArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.text.strUtil;

public class ms {

	private   final String findTablePaichuStr = "DESCRIPTION";
	private   final String findTableKeyword = "利息交易";
	private   String htmlEncode = "utf-8";
	private String frompath;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static  void main(String[] args) throws IOException {
		 
		String path = "c:\\html.txt.htm";
		path = "c:\\msb.eml";
		ms c=new ms();
		List li = c.parse(path);
		String s = JSONArray.fromObject(li).toString(2);
		System.out.println(s);
		System.out.println("f");

	}
/**
 * 
 * @param path emlpath
 * @return
 * @throws IOException
 */
	  List parse(String path) throws IOException {
		
		  frompath=path;
		
		String pathname = "C:\\gdb.eml";
		pathname = path;
	    String body=emlC.getBody(pathname);
		String html_file_name = path+".htm";
	//	html_file_name="c:\\hsbc.htm";
		FileService.writeFile(html_file_name, body);
	//	System.out.println("f");
		
		
		path=html_file_name;
		File input = new File(path);
	
		Document doc = Jsoup.parse(input, htmlEncode, "http://example.com/");
		Element tb_mid = getTable(doc,findTableKeyword);
		String   yearMonth=get_year(doc);
		
		List li = new ArrayList<item>();
		Element tbdy= tb_mid.children().get(0);
		Elements trs = tbdy.children();
		int n = 0;
		for (Element e : trs) {
			
			n++;
//			if (n == 1)
//				continue;
			Element tb=	e.getElementsByTag("table").get(2);
			Element tbdy_2= tb.children().get(0);
			Element tr=tbdy_2.children().get(0);
			
			Elements tds = tr.children();
			
			item it = getItem(tds,yearMonth);
			li.add(it);

		}

		int el_size = li.size();
		System.out.println(el_size);
		return li;
	}

	/**
	 *  Statement Date 2013/12/13    
	 * @param doc
	 * @return
	 */
	private   String get_year(Document doc) {
		
		
		String s=	strUtil.getMidtrings( htmlx.html2txt(doc.toString())  ,"本期账单日","本期最后还款日");
		String[] a=s.split("/");
		for(String si:a)
		{
			String six=numUtil.trim(si);
			if(six.length()==4)return six;
		}
 
//		Elements el = doc.getElementsByTag("td");
//		int n=0;
//		for (Element e : el) {
//			n++;
//			System.out.println(" now table inedex:"+n);
//			String tabletxt = e.ownText();
//			 
//			tabletxt = e.text();
//			System.out.println(tabletxt);
//			if(tabletxt.trim().contains("Statement") && tabletxt.trim().contains("Date"))
//			{
//				Element yearTD=e.nextElementSibling();
//				String ys=yearTD.text().trim();
//				String year=ys.split("/")[0];
//				return year;
//			}
//		}
	//return null;
		return "2099";
}
	private   String get_yearMonth(Document doc) {
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

	private   Element getTable(Document doc, String keyword) {
		Element tb_mid=null;
		Elements el = doc.getElementsByTag("table");
		int n=0;
		for (Element e : el) {
			n++;
			System.out.println(" now table inedex:"+n);
			String tabletxt = e.ownText();
			 
			tabletxt = e.text();
			if(tabletxt.trim().contains(findTablePaichuStr))
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
		
		tb_mid=tb_mid.getElementsByTag("table").get(1);
		String s=tb_mid.toString();
		FileService.writeFile("c:\\table.txt",s);
		return tb_mid;
	}

	private   item getItem(Elements tds, String yearMonth) {
		item it = new item();
		int m = 0;
		for (Element td : tds) {
			m++;
			String text = td.text();
			if (m == 2)
			{
				it.date =dateUtil_o16.convertDate_YYYY_MM_DD( yearMonth+""+  dateUtil.trim(text) );
			}
			else if (m == 4)
				it.demo = text;
			else if (m == 5)
			{
				String t2=numUtil.trim(text);  
				it.m =Float.parseFloat(t2) ;
			}
			
			else if (m == 6)
				it.cardno=numUtil.trim(  text);
//			else if (m == 6)
//				it.shmacyar = text;
//			else if (m == 7)
//			{
//				String t2=  text.trim().replaceAll("-", "").replaceAll(",", "");
//				it.usd =securyInt.getFloat(t2, 0) ;
//			}
		
		 

		}it.acc="msb";
		it.fromFile=frompath;
		if(it.demo.contains("利息"))
			it.cls="itrst";
		return it;
	}

	public   void mainx(String[] string) {
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
	private   String getmonth(String month) {
		 String[] a=month.split("/");
		 String y=a[0].trim();
		 y=numUtil.trim(y);
		return y+"-"+a[1].trim();
	}
}
