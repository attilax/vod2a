//package com.attilax.seo;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//
//import com.attilax.Closure;
//import com.attilax.core;
//import com.attilax.biz.seo.getConnEx;
//import com.attilax.collection.CollX;
//import com.attilax.collection.listUtil;
//import com.attilax.io.filex;
//import com.attilax.io.pathx;
//import com.attilax.util.dirx;
//
//public class GeneLink {
//	// http://www.watchesnada.com/replica-watches/breitling-watches-bentley-asia-2813-auto-21j_661_p.html
//	// D:\home_src\watcheskof0\public_html\replica-watches\iwc-accessories-straps-boxsets-iwcacc004a-mark-xv-wall-clock-white-frame_3885_p.html
//	public String website = "http://www.watchesnada.com/replica-watches/";
//	private String outputSql;
//	private boolean test = false;
//
//	public static void main(String[] args) throws Exception {
//		GeneLink c = new GeneLink();
//		// c.brdtxt= pathx.classPath(GeneLink.class) + "brand.txt";
//		// c.gene("D:\\home_src\\watchesnada0\\public_html\\replica-watches");
//		c.outputSql = "c:\\kof.sql";
//		c.website = "http://www.watcheskof.com/replica-watches/";
//		c.brdtxt = pathx.classPath(GeneLink.class) + "Kofbrand.txt";
//		// c.test=true;
//		c.gene("D:\\home_src\\watcheskof0\\public_html\\replica-watches");
//	}
//
//	int n = 0;
//	private String brdtxt;
//
//	public void gene(String path) throws Exception {
//		final Set<String> set = new HashSet<String>();
//		Set<String> set2 = new HashSet<String>();
//		final List<String> brandLi = listUtil.file2List(brdtxt, "gbk");
//		if (test) {
//			System.out.println(core.toJsonStrO88(brandLi));
//			return;
//		}
//		// if("1"!="2")
//		// return;
//
//		// D:\workspace\AtiSeo\bin\linkgeneDB.properties
//		Properties prop = new Properties();
//		// InputStream in = this.getClass().getResourceAsStream(
//		// "/linkgeneDB.properties");
//		prop.load(new FileReader(new File(pathx.classPath()
//				+ "/linkgeneDB.properties")));
//		String envi = prop.getProperty("environment");
//		String driver = prop.getProperty(envi + ".driver");// mssql.driver
//		String url = prop.getProperty(envi + ".url");
//		String user = prop.getProperty(envi + ".username");
//		String pwd = prop.getProperty(envi + ".password");
//		// Dbx4Mysql c = new Dbx4Mysql(driver, url, user, pwd);
//		filex fc = new filex(outputSql, "utf-8");
//		dirx.trave(path, new Closure<String, Object>() {
//
//			@Override
//			public Object execute(String f) throws Exception {
//				// System.out.println(f);
//				// if (n > 100)
//				// return null;
//
//				String fname = filex.getFileName(f);
//				String etlAft = f.replaceAll("-", " ");
//				for (String brd : brandLi) {
//					// brd=brd.replace(" ","@");
//					if (brd.trim().length() == 0)
//						continue;
//
//					if (etlAft.toLowerCase().contains(brd.toLowerCase())) {
//						boolean sendFlag = new Closure<String, Boolean>() {
//
//							@Override
//							public Boolean execute(String arg0)
//									throws Exception {
//								if (!set.contains(brd)) {
//									set.add(brd);
//									return true;
//								}
//								if (!set2.contains(brd)) {
//									set2.add(brd);
//									return true;
//								}
//								return false;
//							}
//						}.execute(brd);
//						if (sendFlag) {
//							System.out.println(brd + "||" + etlAft);
//							String url = website + fname;
//							String sql = " insert link_big(name,url,type,status)values('@n','@u',1,1) ; "
//									.replaceAll("@n", brd)
//									.replaceAll("@u", url);
//							fc.append_HP(sql + "\r\n");
//						}
//
//						// c.executeUpdate(sql);
//					}
//				}
//
//				n++;
//				System.out.println(n);
//				return null;
//			}
//		});
//		//
//		fc.close();
//
//	}
//
//}
