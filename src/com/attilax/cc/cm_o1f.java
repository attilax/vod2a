package com.attilax.cc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m.FileService;
import m.utf编码;
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
import com.attilax.util.js;
import com.attilax.util.securyInt;
import com.attilax.util.tryX;

@SuppressWarnings("all")
public class cm_o1f extends ccParser{

	private String ver;
	@utf编码
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static  void main(String[] args) throws IOException {
		// Object[] obj = null;
		// System.out.println(obj[2]);

		String path = "c:\\cm2.eml";
		path="c:\\ccx\\cc.c0\\招商银行信用卡电子账单.eml";
		cm_o1f c=new cm_o1f();
		List li = c.parse(path);
		String s = JSONArray.fromObject(li).toString(2);
		System.out.println(s);
		System.out.println("f");

	}

	public  List parse(String path) throws IOException {

		String pathname = path;
		String body = emlC.getBody(pathname);
		String html_file_name = path + ".htm";
		// html_file_name="c:\\hsbc.htm";
		FileService.writeFile(html_file_name, body);
		// System.out.println("f");

		path = html_file_name;

		File input = new File(path);

		final Document doc = Jsoup.parse(body);
		String html2txt = htmlx.html2txt(doc.toString());
		FileService.writeFile("c:\\tmp.txt", html2txt);
		System.out.println(html2txt);

		Element tb_mid = (Element) (new tryX() {

			@Override
			public Object item(Object t) throws Exception {
				Element e = getTable(doc, "零售利息");
				;
				return e;
			}
		}).$(null);
		//o1f
		if(tb_mid==null)
		{
			this.ver="o1f";
			tb_mid = (Element) (new tryX() {

				@Override
				public Object item(Object t) throws Exception {
					Element e = getTable_o1f(doc, "零售利息");
					;
					return e;
				}

			 
			}).$(null);
		}
		
		if(tb_mid!=null)
			FileService.writeFile("c:\\table.txt", tb_mid.toString());

		String yearMonth ="";// get_yearMonth(doc);
		String year=get_year(doc);
		List li = new ArrayList<item>();
		Element tbdy = tb_mid.children().get(0);
		Elements trs = tbdy.children();
		int n = 0;
		for (Element e : trs) {
			n++;
			 if (n <=2)
			 continue;
			 
			if(paichuLine(e.toString(),"自动还款,支付宝网络还款"))
						continue;
			Elements tds ;
			if(this.ver.equals("o1f"))
			{
				tds=getItemTds(e);
			}else
			{
				 tds = e.children();
			}
			logger.debug("------------ get tds: now ");
			logger.debug(tds.toString());

			item it = getItem(tds, year);
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
	
	private String get_year(Document doc) {
	//	
//		账单周期
//		Statement Cycle
//		2013/07/21-2013/08/20
//		到期还款日
		String s2 = "   账单周期 Statement cycle 2013/11/15 - 2013/12/14 综合信用额度";
		s2 = doc.text();

		String abcdef = "abcdef";
		String fd = "ab(.*?)ef";
		fd = "账单周期(.*?)到期还款日";
		String s = strUtil.getMidtrings(s2, fd);
		String[] arr = s.split("-");
		String month = arr[1].trim();
		String monthx = getYearStep2(month);
		return monthx;
	}

	private String getYearStep2(String month) {
		String[] a = month.split("/");
		String y = a[0].trim();
		y = numUtil.trim(y);
		return y ;
	}

	public Elements getItemTds(Element e) {
		Element tb=	e.getElementsByTag("table").get(2);
		Element tbdy_2= tb.children().get(0);
		Element tr=tbdy_2.children().get(0);
	return tr.children();
}
/**
 * 
 * @param doc
 * @param string
 * @return
 */
	protected  Element getTable_o1f(Document doc,@js("keyword") String keyword) {
		keyword="循环利息";
	String	paichuWord="Adjustment";
		
		Element tb_mid = null;
		Elements el = doc.getElementsByTag("table");
		int n = 0;
		for (Element e : el) {
			n++;
			System.out.println(" now table inedex:" + n);
			String tabletxt = e.ownText();

			tabletxt = e.text();
			if (tabletxt.trim().contains(paichuWord)) {
				// System.out.println(tabletxt);
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
		tb_mid = tb_mid	.getElementsByTag("table").get(1);
		return tb_mid;
	}

	private  String get_yearMonth(Document doc) {
		//	
//		账单周期
//		Statement Cycle
//		2013/07/21-2013/08/20
//		到期还款日
		String s2 = "   账单周期 Statement cycle 2013/11/15 - 2013/12/14 综合信用额度";
		s2 = doc.text();

		String abcdef = "abcdef";
		String fd = "ab(.*?)ef";
		fd = "账单周期(.*?)到期还款日";
		String s = strUtil.getMidtrings(s2, fd);
		String[] arr = s.split("-");
		String month = arr[1].trim();
		String monthx = getmonth(month);
		return monthx;
	}

	private  Element getTable(Document doc, String keyword) {
		Element tb_mid = null;
		Elements el = doc.getElementsByTag("table");
		int n = 0;
		for (Element e : el) {
			n++;
			System.out.println(" now table inedex:" + n);
			String tabletxt = e.ownText();

			tabletxt = e.text();
			if (tabletxt.trim().contains("卡号Card")) {
				// System.out.println(tabletxt);
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
		tb_mid = tb_mid.getElementsByTag("div").get(0)
				.getElementsByTag("table").get(0);
		return tb_mid;
	}

	public  item getItem(Elements tds, String yearMonth) {
		item it = new item();
		int m = 0;
		for (Element td : tds) {
			m++;
			
			String text = td.text();
			logger.debug(" index:"+m +"   val:"+text);
			if (m == 2) {
				it.date = dateUtil_o16.convertDate_YYYY_MM_DD(
						dateUtil.trim(yearMonth+text));
			} else if (m == 4)
				it.demo = text;
			else if (m == 5) {
				String t2 = numUtil.trim(text);
				it.m = Float.parseFloat(t2);
			}

			 else if (m == 6)
			 it.cardno=numUtil.trim( text);
			else if (m == 7)
				it.shmacyar = text;
			else if (m == 8) {
				String t2 = text.trim().replaceAll("-", "").replaceAll(",", "");
				it.usd = securyInt.getFloat(t2, 0);
			}

		}
		if(it.demo.contains("利息"))
			it.cls="itrst";
		it.acc="cmb";
		
		return it;
	}

	public  void mainx(String[] string) {
		String s2 = "   账单周期 Statement cycle 2013/11/15 - 2013/12/14 综合信用额度";

		String abcdef = "abcdef";
		String fd = "ab(.*?)ef";
		fd = "账单周期(.*?)综合信用额度";
		String s = strUtil.getMidtrings(s2, fd);
		String[] arr = s.split("-");
		String month = arr[1].trim();
		String monthx = getmonth(month);

		System.out.println(monthx);

	}

	private  String getmonth(String month) {
		String[] a = month.split("/");
		String y = a[0].trim();
		y = numUtil.trim(y);
		return y + "-" + a[1].trim();
	}

	private  final String findTablePaichuStr = "DESCRIPTION";
	private  final String findTableKeyword = "利息交易";
	private  String htmlEncode = "utf-8";
}
