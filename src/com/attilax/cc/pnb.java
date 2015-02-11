package com.attilax.cc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m.dataPkg.dateUtil;
import m.eml.emlC;
import m.numpkg.numUtil;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.io.filex;
import com.attilax.util.securyInt;

public class pnb {
	//private static final String findTableKeyword = "交易描述";  
	
	private static final String findTableKeyword = "卡号末四位";
	private static final String findTablePaichuStr = "交易明细,账单也能分期还";
	private static final String paichuLIne="支付宝还款";
//	交易明细


	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String path = "c:\\pnb.eml";
		path="c:\\ccx\\cc.cb\\平安信用卡电子账单(5).eml";
		path="c:\\ccx\\cc.cb\\平安信用卡电子账单.eml";
		List li = parse(path);
		String s = JSONArray.fromObject(li).toString(2);
		System.out.println(s);
		System.out.println("f");

	}

	/**
	 * ��������R
	 * <tr>
	 * <td style="border-top:1px #919191 solid; border-bottom:1px #919191 solid; background-color:#f3ab71; font-family:'����'; font-size:12px; font-weight:bold; color:#5c2e1e; line-height:30px; padding-left:7px;">
	 * ������ϸ��������˻���</td>
	 * </tr>
	 * 
	 * @param string
	 * @return
	 * @throws IOException
	 */
	 static Logger logger = Logger.getLogger(pnb.class.getName());
	private static String frompath;
	static List parse(String path) throws IOException {
		frompath=path;
 		File input = new File(path);
		String body = emlC.getBody(path);
		filex.write(path+".htm",body);
		Document doc = Jsoup.parse(body);
		Element tb_mid = null;
		Elements el = doc.getElementsByTag("table");
		for (Element e : el) {
			String tabletxt = e.ownText();
			tabletxt = e.text();
			logger.debug(tabletxt);
			if(tabletxt.trim().contains(findTablePaichuStr))
			{
			//	System.out.println(tabletxt);
				continue;
			}
			if(isPaichuTable(tabletxt))
			{
				continue;
			}
			if (tabletxt.trim().contains(findTableKeyword)) {
				tb_mid = e;
				break;
			}
			// tabletxt=e.toString();

			// tabletxt=e.

		 // System.out.println(tabletxt);
			
		}
		filex.write("c:\\table.htm", tb_mid.toString());
		List li = new ArrayList<item>();
		Element tbdy= tb_mid.children().get(0);
		Elements trs = tbdy.children();
		int n = 0;
		for (Element e : trs) {
			n++;
			if (n == 1)
				continue;
			if(e.toString().contains(paichuLIne))continue;
			Elements tds = e.children();
			item it = getItem(tds);
			li.add(it);

		}

		int el_size = li.size();
		System.out.println(el_size);
		return li;
	}
	private static boolean isPaichuTable(String string) {
		String[] a=findTablePaichuStr.split(",");
		for(String s:a)
		{	if(string.contains(s))
		     return true;
			
		}
		 
	return false;
		
}
	private static item getItem(Elements tds) {
		item it = new item();
		int m = 0;
		for (Element td : tds) {
			m++;
			String text = td.text();
			logger.debug( " td index:"+m+" value:"+text);
			if(tds.size()==7)
				 getItemFormTD7(it,text,m);
			else{
				if (m == 1)
					it.date =dateUtil.trim(text);
				
				else if (m == 3)
					it.cardno=numUtil.trim(  text);
				else if (m == 4)
					it.demo = text;
				else if (m == 6)
					it.shmacyar = text;
				else if (m == 7)
				{
					String t2=  text.trim().replaceAll("-", "").replaceAll(",", "");
					it.usd =securyInt.getFloat(t2, 0) ;
				}
				else if (m == 8)
				{
					String t2=numUtil.trim(text);  
					it.m =Float.parseFloat(t2) ;
				}
			}
		 

		}
		 
		if(it.demo.contains("利息"))
			it.cls="itrst";
		it.acc="pnb";
		it.fromFile=frompath;
		return it;
	}

	private static void getItemFormTD7(item it, String text, int m) {
		if (m == 1)
			it.date =dateUtil.trim(text);
		
		else if (m == 3)
			it.cardno=numUtil.trim(  text);
		else if (m == 4)
			it.demo = text;
		else if (m == 6)
			it.shmacyar = text;
//		else if (m == 7)
//		{
//			String t2=  text.trim().replaceAll("-", "").replaceAll(",", "");
//			it.usd =securyInt.getFloat(t2, 0) ;
//		}
		else if (m == 7)
		{
			String t2=numUtil.trim(text);  
			it.m =Float.parseFloat(t2) ;
		}
	}
	private static item getItemx(Elements tds) {
		item it = new item();
		int m = 0;
		for (Element td : tds) {
			m++;
			String text = td.text();
			if (m == 1)
				it.date =dateUtil.trim(text);
			
			else if (m == 3)
				it.cardno=numUtil.trim(  text);
			else if (m == 4)
				it.demo = text;
			else if (m == 6)
				it.shmacyar = text;
			else if (m == 7)
			{
				String t2=  text.trim().replaceAll("-", "").replaceAll(",", "");
				it.usd =securyInt.getFloat(t2, 0) ;
			}
			else if (m == 8)
			{
				String t2=numUtil.trim(text);  
				it.m =Float.parseFloat(t2) ;
			}
		 

		}
		if(it.demo.contains("利息"))
			it.cls="itrst";
		return it;
	}

}
