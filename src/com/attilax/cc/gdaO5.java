package com.attilax.cc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m.dataPkg.dateUtil;
import m.datepkg.dateUtil_o16;
import m.eml.emlC;
import m.numpkg.numUtil;
import net.sf.json.JSONArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.io.filex;
import com.attilax.util.securyInt;
 
/**
 * invoke by gda.java
 * @author  attilax 老哇的爪子
 *@since  o84 k_40_h$
 */
public class gdaO5 extends ccParser {

	private String frompath;
	private String paichuLIne = "已收妥您的款项,存入";
	private String findTablePaichuStr = "人民币账户交易明细";
	public String findTableStr = "账单账单分期";
	int skiplineNum = 2;
	 String op="";
	
 
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub  ������Ϣ
		String path = "E:\\kp\\o4\\cc o4\\光大银行信用卡电子对账单20140405.eml";
	//	path = "c:\\gdb.htm";
		gdaO5 c=new gdaO5();
		List li =c. parse(path);
		String s = JSONArray.fromObject(li).toString(2);
		System.out.println(s);
		System.out.println(" result size:$$$$$$$$"+li.size());
		System.out.println("f");

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
		this.table=tb_mid;
		return tb_mid;
	}
	
	public List parse(String path) {
		frompath = path;

		File input = new File(path);
		String html = emlC.getBody(path);
		Document doc = null;
		filex.write(path + ".htm", html);
		doc = Jsoup.parse(html);
		cardno = getCardNo(doc.text());
		logger.info(cardno);
		List li = new ArrayList<item>();
		//o39
		if(this.op.equals(this.balance))
		{
			float bals=getBalance(doc.text());
			item it=new item();
			it.acc="gda";
			it.m=bals;
			li.add(it);
			return li;
		}
		// if(1==1)return null;
		Element tb_mid = getTable(doc, findTableStr, findTablePaichuStr);
		String yearMonth = "";
		;// get_yearMonth(doc);
//
		filex.write("c:\\table.htm", tb_mid.toString());

	
		Element tbdy = tb_mid.children().get(0);
		Elements trs = tbdy.children();
		int n = 0;
		for (Element e : trs) {
			n++;
			
			if (n <= skiplineNum)
				continue;
			if (paichuLine(e.toString(), paichuLIne))
				continue;
			Elements tds = e.children();
			if(tds.size()<3)
				continue;

			item it = getItem(tds, yearMonth);
			li.add(it);

		}

		int el_size = li.size();
		System.out.println(el_size);
		return li;
	}

	protected float getBalance(String text) {
		// TODO Auto-generated method stub
		return 0;
	}
	public item getItem(Elements tds, String yearMonth) {
		item it = new item();
		int m = 0;
		for (Element td : tds) {
			m++;
			String text = td.text();
			logger.debug(" td index:" + m + " value:" + text);
			if (m == 1) {
				if (text.trim().length() == 0)
					return new item();
				// if(text.contains("主卡")||text.contains("卡号末四位") )
				// return new item();
				it.date = dateUtil_o16.convertDate_YYYY_MM_DD_safe(dateUtil
						.trim(text));
			} else if (m == 4)
				it.demo = text;
			else if (m == 5) {
				String t2 = numUtil.trim(text);
				it.m = securyInt.getFloat(t2, 0);
			}

			 else if (m == 3)
			 it.cardno=numUtil.trim( text);
			// else if (m == 6)
			// it.shmacyar = text;
			// else if (m == 7)
			// {
			// String t2= text.trim().replaceAll("-", "").replaceAll(",", "");
			// it.usd =securyInt.getFloat(t2, 0) ;
			// }

		}
		it.acc = "gda";
		if (it.demo.contains("利息"))
			it.cls = "int";
		else 	if (it.demo.contains("手续费"))
			it.cls = "int";
		else if(it.demo.contains("取现费"))
		  it.cls="int";
		else
			it.cls="mkt";
	//	it.cardno = this.cardno;

		it.fromFile = frompath;
		return it;
	}

}
