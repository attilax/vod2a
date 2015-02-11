package com.attilax.html;

import org.jsoup.Jsoup;

@Deprecated
public class htmlx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
String s=html2txt("aa");
	}

	public static String html2txt(String html) {
	//	String html = "��ã���������<a href='http://www.oschina.net/' target='_blank'>��Դ�й�����</a>�ĺ��?";
	//	System.out.println(Jsoup.parse(html).text());
		return Jsoup.parse(html).text();
	}

}
