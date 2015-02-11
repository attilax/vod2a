/**
 * 
 */
package com.attilax.lbs;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeoutException;

import m.eml.emlC;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.io.filex;
import com.attilax.net.websitex;

/**
 * @author ASIMO
 * 
 */
public class ZipX {

	public static void main(String[] args) throws NoRztEx {
		//
		String addr = "武汉市东西湖区东西湖大道5647号（43栋2层19室）";
		addr = "武汉市东湖开发区高新大道666号武汉国家生物产业基地项目B、C、D区研发楼B1栋";
		addr = "武汉市江岸区解放大道1338号汉飞青年城5层609室";
		addr = "武汉市东湖开发区高新大道666号";
		addr = "武汉市江岸区解放大道1338号汉飞青年城5层609室";// mulit rzt
		addr = "武汉市江岸区沿江大道160号武汉时代广场时代豪苑3、4栋3单元32层4室";

		// System.out.println(toZipFromAddr(addr));
	}

	/**
	 * @author attilax 老哇的爪子
	 * @throws NoRztEx
	 * @throws TimeoutException
	 * @since obi d_6_0
	 */
	@SuppressWarnings("all")
	public String toZipFromAddr(String addr) throws NoRztEx {
		// try {
		String api = null;
		try {
			api = "http://opendata.baidu.com/post/s?wd=@add&rn=20".replaceAll(
					"@add", URLEncoder.encode(addr, "gbk"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new NoRztEx("noRzt:UnsupportedEncodingException");
		}
		Document doc = null;
		// System.out.println ( new websitex(). WebpageContent(url, "gbk", 3));
		String html;
		try {
			html = new websitex().WebpageContent(api, "gbk", 5);
		} catch (TimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new NoRztEx("noRzt:TimeoutException");
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoRztEx(e.getMessage());
		}
		if (new File("C:\\traceOk").exists())
			filex.save_safe(html, "c:\\rztTrace.html");
		else
			filex.del("c:\\rztTrace.html");
		// filex.read("c:\\rzt.html", "gbk");
		// filex.write(path + ".htm", html);
		try {
			doc = Jsoup.parse(html);
			Elements tabs = doc.getElementsByTag("table");
			Element tab = tabs.get(0);
			Element tbody = tab.children().get(0);
			Element tr = tbody.children().get(1);
			Elements tds = tr.children();
			String zip = tds.get(0).text();
			String add = tds.get(1).text();
			return add + "," + zip + "";
		} catch (Exception e) {
			System.out.println("norzt:" + addr);
			throw new NoRztEx("noRzt");
		}

		// return tab.html();

	}
	// catch( java.net.SocketException e2)
	// {
	// throw new RuntimeException("e..");
	// }

	// catch ( Exception e) {
	// if(e.getMessage().equals("java.net.SocketException: Connection reset"))
	// throw new RuntimeException("e001");
	//
	// throw new RuntimeException("e..");
	// }
	// return addr;
	// }

}
