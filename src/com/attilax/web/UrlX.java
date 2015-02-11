/**
 * 
 */
package com.attilax.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import com.attilax.net.urlEncode;
import com.attilax.net.websitex;

/**
 * @author ASIMO
 *
 */
public class UrlX {

	/**
	@author attilax 老哇的爪子
	 * @throws IOException 
	 * @since   obb h_46_s
	 
	 */
	public static void main(String[] args) throws IOException {
//		 String downloadUrl="http://200.140.235.199:8888/vod/uploadf/huangweicheng/music2.mp4";
//downloadUrl="http://192.168.1.33/vod/uploadf/01.mp4";
//		 int fileSize = getFileSize(downloadUrl); 
//		   System.out.println(fileSize);
		   System.out.println(URLEncoder.encode("全国邮编区号搜索", "gbk"));
		   
		   String file = "http://192.168.1.33/vod/uploadf/1205.rmvb";  
			String t_urlencode=URLEncoder.encode(file, "utf-8");
			String url="http://localhost/vod/api.jsp?submethod=getFileSize&appid=1234507y767676&sign=xxxxxx&param="+t_urlencode;
			System.out.println(websitex.WebpageContent(url));;

	}

	public static long getFileSize(String downloadUrl)
			throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL(downloadUrl);
		
		   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		   conn.setConnectTimeout(5 * 1000);
		//   conn.setRequestProperty("Accept-Language", Params.ACCEPT_LANGUAGE);
		//   conn.setRequestProperty("Charset", Params.ChARSET);
		   conn.setRequestMethod("GET");
		   int statusCode = conn.getResponseCode();
		   if(statusCode!=200)
			   throw new RuntimeException("httpstate:"+String.valueOf(statusCode)+ " url:"+downloadUrl);
		//   if(conn)
		//   conn.setRequestProperty("Connetion", "Keep-Alive");
		   // 文件大小

		   long fileSize = conn.getContentLengthLong( );
		return fileSize;
	}

}
