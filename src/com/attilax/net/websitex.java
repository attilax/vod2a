package com.attilax.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.attilax.core;
import com.attilax.io.filex;

//import org.jsoup.Jsoup;

/**
 * 
 * @author attilax 老哇的爪子
 * @since Aug 23, 2014 4:59:05 PM$
 */
public class websitex {
	public String refer;
//	public static ThreadLocal<BufferedReader> reader_ThreadLocal = new ThreadLocal<BufferedReader>();
//	public	static  ThreadLocal<GetDataByURL> getDataByURL_thrdloc =new ThreadLocal<GetDataByURL>();//= new GetDataByURL();
	public	    GetDataByURL  getDataByURL_thrdloc;
	public   String WebpageContent(final String url, final String encode,
			int timeoutSec) throws TimeoutException {
		// final BufferedReader reader;
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future<String> fut = es.submit(new Callable<String>() {

			

			@Override
			public String call() throws Exception {
				// core.sleep(5000);
			//	System.out.println("--start down page");
				GetDataByURL gbc		 = new GetDataByURL();
				gbc.refer=refer;
				 getDataByURL_thrdloc=(gbc);
				// getDataByURL_thrdloc.get();
				//reader_ThreadLocal.set(getDataByURL.reader_ThreadLocal.get());
				// reader=;
				//	System.out.println("--start down pageww");
				return gbc.downloadPage(url, encode);
			}
		});
		try {
			return (fut.get(timeoutSec, TimeUnit.SECONDS));
		} catch (InterruptedException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (TimeoutException e) {
		//	System.out.println("TimeoutException");
		//	fut.stop
		//	fut.
		//	fut.
			fut.cancel(true);
			core.execMeth_Ays(new Runnable() {
				
				@Override
				public void run() {
					if(getDataByURL_thrdloc!=null)
						getDataByURL_thrdloc.closeStream();
					
				}
			}, "websitex close conn thread"+filex.getUUidName());
		
			try {
				fut.cancel(true);
				// fut.
				es.shutdown();
				es.shutdownNow();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			

//			while (!es.isTerminated()) {
//				System.out.println("start termina es pool ");
				try {
					// 等待关闭线程池，每次等待的超时时间为30秒

					es.awaitTermination(20, TimeUnit.SECONDS);
					// es.awaitTermination(3, TimeUnit.SECONDS);

				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//			}
			//System.out.println(es.isShutdown());// true
		//	System.out.println(es.isTerminated());
			e.printStackTrace();
			System.out.println("==webconn thread finish ");
			throw e;
		} finally {
			try {
				// fut.cancel(true);
				es.shutdownNow();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		// return

	}

	@Deprecated
	public static String WebpageContent(String url, String encode) {
		return new GetDataByURL().downloadPage(url, encode);

	}

	//

	public static void main(String[] a) throws IOException, TimeoutException {
//		String url = "http://200.140.235.199:8888/vod/uploadf/huangweicheng/music2.mp4";
//		url="http://www.baidu.com";
//		System.out.println ( new websitex(). WebpageContent(url, "gbk", 330));
		// obb();
		// String webpageContent = WebpageContent(url);
		// System.out.println( webpageContent);
		// String ip="183.2.49.184";
		// String add = addr(ip);
		
		
		//
	 	websitex wc = new websitex();
	 	wc.refer="http://www.czvv.com/";//http://www.czvv.com/
		String html = wc.WebpageContent("http://www.czvv.com/k5bu6562Rp0c0cc0s0m0e0f0d0.html", "utf-8", 5);
		System.out.println(html);
		System.out.println("---");
		// System.out.println(add);

	}

	private static void obb() {
		String url = "http://www.sharimage.com/images/2014/05/k6EvPvY8/1223/1.jpg";
		byte[] ba = WebpageContent_Bin(url);
		filex.save(ba, "c:\\0.jpg");
	}

	private static String addr(String ip) {
		String urlContent = WebpageContent("http://www.123cha.com/ip/?q=" + ip,
				"utf-8");
		String txt = html2txt(urlContent);
		String left = "参考数据一:";
		String add = com.attilax.text.strUtil.Mid(txt, left, "参考数据二:");
		return add;
	}

	public static String html2txt(String urlContent) {
		// org.jsoup.nodes.Document doc = null;
		// //filex.write(path + ".htm", html);
		// doc = Jsoup.parse(urlContent);
		//
		// return doc.text() ;
		return null;
	}

	/**
	 * @author attilax 老哇的爪子
	 * @since 2014-5-12 下午01:24:44$
	 * 
	 * @param url
	 * @return
	 */
	public static String WebpageContent(String url) {
		// attilax 老哇的爪子 下午01:24:44 2014-5-12
		return WebpageContent(url, "utf-8");
	}

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 23, 2014 9:17:50 AM$
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static void WebpageContent_Bin(String urlx, OutputStream os)
			throws IOException {
		// attilax 老哇的爪子 9:17:50 AM Aug 23, 2014

		// 构造URL
		URL url = new URL(urlx);
		// 打开连接
		URLConnection con = url.openConnection();
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		// OutputStream os = new FileOutputStream(filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
		// byte[] ba2=bos.toByteArray();
		// return ba2;

	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param encode
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String WebpageContentO8f(String url, String encode,
			Map<String, String> headProps) {
		return WebpageContent(url, "", encode, headProps);
	}

	/**
	 * 向指定URL发送GET方法的请求 conn.setConnectTimeout(3000); // 设置连接超时时间
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param encode
	 * @param headProps
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String WebpageContent(String url, String param,
			String encode, Map<String, String> headProps) {
		String result = "";
		BufferedReader reader = null;
		try {
			String urlNameString = url
					+ ((param.length() > 0) ? "?" + param : "");
			// "?" + param;
			core.log("--o722a" + urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			// SocketAddress addr = new
			// InetSocketAddress("127.0.0.1",8888);//是代理地址:192.9.208.16:3128
			// Proxy typeProxy = new Proxy(Proxy.Type.HTTP, addr);
			// URLConnection connection = realUrl.openConnection(typeProxy);

			URLConnection connection = realUrl.openConnection();

			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// o8f
			for (Map.Entry<String, String> e : headProps.entrySet()) {
				connection.setRequestProperty(e.getKey(), e.getValue());
			}
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}

			// BufferedReader reader = new BufferedReader(new InputStreamReader(
			// pageUrl.openStream(), encode));
			// 定义 BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encode));
			String line;
			while ((line = reader.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			core.log(e);
			throw new RuntimeException(e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				core.log(e2);
			}
		}
		return result;
	}

	/**
	 * output byteArr
	 * 
	 * @author attilax 老哇的爪子 \t@since Aug 23, 2014 3:44:59 PM$
	 * 
	 * @param urlx
	 * @param os
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	public static byte[] WebpageContent_Bin(String urlx) {
		// attilax 老哇的爪子 9:17:50 AM Aug 23, 2014

		// 构造URL
		URL url;
		try {
			url = new URL(urlx);

			// 打开连接
			URLConnection con = url.openConnection();
			// 输入流
			InputStream is = con.getInputStream();
			// 1K的数据缓冲
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			// OutputStream os = new FileOutputStream(filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				bos.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			bos.close();
			is.close();
			byte[] ba2 = bos.toByteArray();
			return ba2;
		} catch (Exception e) {
			// attilax 老哇的爪子 4:03:28 PM Aug 23, 2014
			core.log(e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * @author attilax 老哇的爪子
	 * @since o9t 1_42_g
	 * 
	 * @param cabzip
	 * @param string
	 * @throws IOException
	 */
	public static void down(String cabzipUrl, String savepath)
			throws IOException {
		// attilax 老哇的爪子 1_42_g o9t
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;

		URL url = new URL(cabzipUrl);

		URLConnection conn = url.openConnection();
		InputStream inStream = conn.getInputStream();
		FileOutputStream fs = new FileOutputStream(savepath);

		byte[] buffer = new byte[1204];
		int length;
		while ((byteread = inStream.read(buffer)) != -1) {
			bytesum += byteread;
			// System.out.println(bytesum);
			fs.write(buffer, 0, byteread);
		}

	}

	public void downloadNet(String cabzip, String savepath) {

	}

}
