package com.attilax.cc;

public class datex {

	
	/**
	 * 
	 * @param s  xxNye2
	 * @return  2014-09
	 */
	public static String fix(String s) {
		String t=s.trim().replaceAll("年", "-").replaceAll(" ", "").replaceAll("\\(", "").replaceAll("月", "");
		t=t+"";
		return t;
	}
	
	
	public static String trim(String text) {
		return	 text.trim().replaceAll("--", "").replaceAll(",", "").replaceAll("[^\\d-]", "") .trim();
	//	 null;
	}
	
	public static void main(String[] args) {
		String s="2013-10-05?";
		System.out.println("re:"+trim(s));
	}
	

}
