package com.attilax.secury;

 

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

 

public class aesC47 {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
	 

		String aesKey="aeskey";
	 
		String json="http://www.p6like.com";
		String aesEncode=aes.encrypt(json, aesKey);
		System.out.println(aesEncode);
	//	tt();
		System.out.println(aes.decrypt(aesEncode, "aeskey"));
			
		
		
	}

	private static void tt() throws UnsupportedEncodingException {
		String aesKey="aeskey";
		String md5Key="md5key";
		String json="[{id:1},{id:2}]";
		String aesEncode=aes.encrypt(json, aesKey);
		aesEncode=	aesEncode.replaceAll("\r\n", "");
		String md5Encode="";//md5.MD5(aesEncode+md5Key);
		
		System.out.println(aesEncode);
		System.out.println(md5Encode);
		//md5Encode
		//bZ5o1uhLJPnxwnDEK4266Q==
//		2292a175def267c69f87952111e0324f
		
		
		String urlEncode=URLEncoder.encode(aesEncode,"utf-8");
		System.out.println(urlEncode);
		//bZ5o1uhLJPnxwnDEK4266Q%3D%3D
	}

}
