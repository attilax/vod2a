/**
 * 
 */
package com.attilax.lbs;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.attilax.Base64;
import com.attilax.html.HtmlX2;
import com.attilax.io.filex;
import com.attilax.net.websitex;
import com.attilax.util.mainThreadEnd;

/**
 * @author ASIMO
 *
 */
public class ZipX138 extends ZipX {
	private boolean test=false;


	public static void main(String[] args) throws NoRztEx {
		String addr="武汉市江汉区新华下路410号5层37室";
		//
		ZipX138 zipX138 = new ZipX138();
		zipX138.test=true;
		System.out.println(zipX138. toZipFromAddr(addr));
	}
	
	
	public     String toZipFromAddr(String addr) throws NoRztEx {
		try {
		  	String api="http://alexa.ip138.com/post/search.asp?address=@add".replaceAll("@add", URLEncoder.encode(addr, "gbk"));
			Document doc = null;
			String html ;//=websitex.WebpageContent(api, "gbk");
			html = new websitex().WebpageContent(api, "gbk",5);
					//filex.read("c:\\ip138rzt.html", "gbk");
					//
			if(new File("C:\\traceOk").exists())
				filex.save_safe(html, "c:\\rztTrace.html");
			else if(this.test)
				filex.save_safe(html, "c:\\rztTrace_yseba.html");
			else
				filex.del("c:\\rztTrace.html");
					//filex.read("c:\\rzt.html", "gbk");
			//filex.write(path + ".htm", html);
			doc = Jsoup.parse(html);
			HtmlX2 hx=new HtmlX2() {
			};
			//yKu5+tPKseDH+LrFy9HL9w==
			//%C8%AB%B9%FA%D3%CA%B1%E0%C7%F8%BA%C5%CB%D1%CB%F7
		//	%E5%85%A8%E5%9B%BD%E9%82%AE%E7%BC%96%E5%8C%BA%E5%8F%B7%E6%90%9C%E7%B4%A2
			Element tab = hx.getTable(doc, "市、县、区名",Base64.  decode("yKu5+tPKseDH+LrFy9HL9w==") );		 
			Element tr=hx.getTr(tab,1,true);
			Elements tds=tr.children();
			String add=tds.get(0).text();
			String zip=tds.get(1).text();
			return  add + ","+zip+"";
	//	return	tab.html();
			
		} catch ( Exception e) {
			e.printStackTrace();
			throw new NoRztEx(e.getMessage());
			//throw new RuntimeException(e.getMessage());
		}
	//	return addr;
	}


}
