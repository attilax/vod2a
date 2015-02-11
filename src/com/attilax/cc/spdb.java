package com.attilax.cc;

import java.util.ArrayList;
import java.util.List;

import m.dataPkg.dateUtil;
import m.datepkg.dateUtil_o16;
import m.numpkg.numUtil;
import  com.attilax.office.WordToHtml;
import net.sf.json.JSONArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.io.filex;
import com.attilax.util.god;

public class spdb extends ccParser {

	protected String findTablePaichuStr = "账单明细";
	private String findTableKeyword = "交易摘要";
	private String paichuLIne = "扣款帐户余额不足,银行代发,跨行转入,还款";
	private String cardNo = "";
	private int skipLine = 1;
	public String acc = "spdb";

	public static void main(String[] args) {
		spdb c=new spdb();
	 
		String path="C:\\ccx\\spdb ca.doc";
		List li =c. parse(path);
		String s = JSONArray.fromObject(li).toString(2);
		System.out.println(s);
		System.out.println("f");

	}

	/**
	 * path is doc
	 */
	List parse(String path) {
		frompath = path;
		WordToHtml wordtohtml = new WordToHtml();
		String htmlfile = "c://spdb."+god.getUUid()+".html";
		wordtohtml.wordToHtml(path, htmlfile);

		List li = new ArrayList<item>();
		String html = filex.read(htmlfile,"gbk");
		Document doc = null;
		  filex.write(htmlfile+".debug.htm", html);
		doc = Jsoup.parse(html);
		cardno = getCardNo(doc.text());
		logger.info(cardno);
		// if(1==1)return null;
		List<Element> tables = getTables(doc, findTableKeyword,
				findTablePaichuStr);
		for (Element table : tables) {
			Element tb_mid = table;
			String yearMonth = "";
			;// get_yearMonth(doc);

			filex.write("c:\\table.htm", tb_mid.toString());

			Element tbdy = tb_mid.children().get(0);
			Elements trs = tbdy.children();
			int n = 0;
			for (Element e : trs) {
				n++;
				if (n <= skipLine)
					continue;
				if (paichuLine(e.toString(), paichuLIne))
					continue;
				Elements tds = e.children();

				item it = getItem(tds, yearMonth);
				li.add(it);

			}
		}

		int el_size = li.size();
		System.out.println(el_size);
		return li;
	}

	private List<Element> getTables(Document doc, String keyword,
			String paichuWord) {
		List<Element> li = new ArrayList<Element>();
		Element tb_mid = null;
		Elements el = doc.getElementsByTag("table");
		int n = 0;
		for (Element e : el) {
			n++;
			System.out.println(" now table inedex:" + n);
			String tabletxt = e.ownText();

			tabletxt = e.text();
			if (isPaichuTable(tabletxt.trim(), paichuWord))
				continue;
			if (tabletxt.trim().contains(paichuWord)) {
				// System.out.println(tabletxt);
				continue;
			}

			if (tabletxt.trim().contains(keyword)) {
				System.out.println("-----------");
				System.out.println(tabletxt);
				tb_mid = e;
				li.add(e);
				// break;
			}

		}
		// tb_mid=tb_mid.getElementsByTag("div").get(0).getElementsByTag("table").get(0);
		return li;
	}
	
	public item getItem(Elements tds, String yearMonth) {
		item it = new item();
		int m = 0;
		for (Element td : tds) {
			m++;
			String text = td.text();
			if (m == 1)
			{
				it.date =dateUtil_o16.convertDate_YYYY_MM_DD(    dateUtil.trim(text) );
			}
			else if (m == 3)
				it.demo = text;
			else if (m == 4)
			{
				String t2=numUtil.trim(text);  
				it.m =(float) numUtil.toPrice2(  Float.parseFloat(t2) );
			}
			
//			else if (m == 4)
//				it.cardno=numUtil.trim(  text);
//			else if (m == 6)
//				it.shmacyar = text;
//			else if (m == 7)
//			{
//				String t2=  text.trim().replaceAll("-", "").replaceAll(",", "");
//				it.usd =securyInt.getFloat(t2, 0) ;
//			}
		
		 

		}
		it.fromFile=frompath;
	
		it.acc=acc;
		if(it.demo.contains("利息"))
			it.cls="itrst";
		return it;
	}

}
