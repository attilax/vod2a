package com.attilax.util;

import java.util.Random;

import com.attilax.text.strUtil;
@utf8编码
public class randomx {

	public randomx() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
	    Random random = new Random();
        for(int i = 0; i < 3;i++) {
        	//1820344736
        	System.out.println(random.nextInt());
            System.out.println(Math.abs(random.nextInt())%100);
        }
 
//»ñµÃµÄËæ»úÊýÓÐÕýÓÐ¸ºµÄ£¬ÓÃMath.absÊ¹»ñÈ¡Êý¾Ý·¶Î§Îª·Ç¸ºÊý
	}

	public static int random(int min, int max) {
		 Random random = new Random();
		return Math.abs(random.nextInt())%100;
	}
	
	public static int random( int max) {
		 Random random = new Random();
		return Math.abs(random.nextInt())%(max+1);
	}
	
	public static String randomMinOrSec( ) {
		int max=59;
		 Random random = new Random();
		int i = Math.abs(random.nextInt())%(max+1);
	String s=	strUtil.pad(String.valueOf(i), 2, "0");
		return s;
	}

}
