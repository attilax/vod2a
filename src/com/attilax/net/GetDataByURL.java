package com.attilax.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.attilax.core;
import com.attilax.io.filex;

public class GetDataByURL {
	public	String refer="";
 public  BufferedReader  reader_ThreadLocal;
	/** default 20 sec release conn
	 *ua:: Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0
	@author attilax 老哇的爪子
	\t@since  Aug 23, 2014 4:40:54 PM$
	
	 * @param Url
	 * @param encode
	 * @return
	 */
	public String downloadPage(String Url, String encode) {
		try {
			URL pageUrl = new URL(Url);
			 
			URLConnection con = pageUrl.openConnection();
			//Referer: http://www.czvv.com/
			// User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0
			con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
	    if(refer!=null && refer.length()>0)
			  con.setRequestProperty("Referer",refer);
			InputStream in = con.getInputStream();
			// Open connection to URL for reading. pageUrl.openStream()
			final BufferedReader reader = new BufferedReader(new InputStreamReader(in
					, encode));
			reader_ThreadLocal=(reader);
			core.execMeth_Ays(new Runnable() {
				
				@Override
				public void run() {
					core.sleep_safe(20000);
				//	reader.
					closeStream();
					//reader.close();
					
				}
			}, "resRealeasor4GetDataByURL"+filex.getUUidName());
		
			// BufferedReader br = new BufferedReader(new InputStreamReader(in,
			// "gbk"));
			// Read page into buffer.
		//	reader.cl
			String line;
			StringBuffer pageBuffer = new StringBuffer();
			//synchronized (this)     nerver sync jeig beri easy lock
			//.BufferedReader.readLine()
			{
				while ((line = reader.readLine()) != null) {
					pageBuffer.append(line);
				}
			}
			
			String s = pageBuffer.toString();
			return s;
			// System.out.println(s);
			// return pageBuffer.toString();
		} catch (Exception e) {
			// return null;
		
			throw new RuntimeException(e);
		}

	}

	// public String getUrlContent(String path){
	// String rtn = "";
	// int c;
	// try{
	// URL l_url = new URL(path);
	// HttpURLConnection l_connection = ( HttpURLConnection)
	// l_url.openConnection();
	// l_connection.setRequestProperty("User-agent","Mozilla/4.0");
	// nnect();
	// InputStream l_urlStream = l_connection.getInputStream();
	// while (( ( c= l_urlStream.read() )!=-1)){
	// int all=l_urlStream.available();
	// byte[] b =new byte[all];
	// l_urlStream.read(b);
	// rtn+= new String(b, "UTF-8");
	// }
	// //Thread.sleep(2000);
	// l_urlStream.close();
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// return rtn;
	// }
	public static String cc(String leibie, String num) {
		StringBuffer temp = new StringBuffer();
		try {
			System.out.println(leibie);
			System.out.println(num);
			String url = "http://www.baidu.com/jiaojing/ser.php";
			HttpURLConnection uc = (HttpURLConnection) new URL(url)
					.openConnection();
			uc.setConnectTimeout(10000);
			uc.setDoOutput(true);
			uc.setRequestMethod("GET");
			uc.setUseCaches(false);
			DataOutputStream out = new DataOutputStream(uc.getOutputStream());

			// 要传的参数
			String s = URLEncoder.encode("ra", "GB2312") + "="
					+ URLEncoder.encode(leibie, "GB2312");
			s += "&" + URLEncoder.encode("keyword", "GB2312") + "="
					+ URLEncoder.encode(num, "GB2312");
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			out.writeBytes(s);
			out.flush();
			out.close();
			InputStream in = new BufferedInputStream(uc.getInputStream());
			Reader rd = new InputStreamReader(in, "Gb2312");
			int c = 0;
			while ((c = rd.read()) != -1) {
				temp.append((char) c);
			}
			System.out.println(temp.toString());
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp.toString();
	}

	public static void main(String[] a) {
		GetDataByURL.cc("1", "吉H");
	}

		/**
		@author attilax 老哇的爪子
		@since   obm b_58_z
		 
		 */
	synchronized public void closeStream() {
	//	
		try {
			if(reader_ThreadLocal!=null)
			reader_ThreadLocal.close();
			else
				System.out.println("warning... reader is null");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}