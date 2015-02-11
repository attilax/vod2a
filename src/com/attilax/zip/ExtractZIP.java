/*
  * All rights reserved.
 * @author: JODY
 * @Date: 2008-05-27
 * @Time: 0:15:04
 */
package com.attilax.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.tools.zip.ZipEntry;

/*
 * <p>
 * 功能描述 标准ZIP文件解压缩<br>
 * 支持中文目录、文件名<br>
 * 无限级目录结构
 * </p>
 * 文件名称：ExtractZIP.java<br>
 * 类型名称：ExtractZIP<br>
 * @author: JODY
 */
public class ExtractZIP {
	
	public ExtractZIP(){
		
	}
	/**
	 * 解压静态方法
	 * @param zipFileName
	 * @param outputDirectory
	 * @throws Exception
	 */
	public static void extract(String zipFileName,String outputDirectory) throws Exception{
		try {
			org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(zipFileName);
			java.util.Enumeration e = zipFile.getEntries();

			org.apache.tools.zip.ZipEntry zipEntry = null;

			while (e.hasMoreElements()){
				zipEntry = (ZipEntry)e.nextElement();
				 System.out.println("unziping "+zipEntry.getName());
				if (zipEntry.isDirectory()){
					String name=zipEntry.getName();
					name=name.substring(0,name.length()-1);		
					mkDirs(outputDirectory+File.separator+name);					
					//System.out.println("创建目录："+outputDirectory+File.separator+name);

				}else{
					String name=zipEntry.getName();
					String dir = name.substring(0,name.lastIndexOf("/"));
					mkDirs(outputDirectory+File.separator+dir);					
					//System.out.println("创建文件："+outputDirectory+File.separator+name);					
					File f=new File(outputDirectory+File.separator+zipEntry.getName());
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out=new FileOutputStream(f);					
					int c;
					byte[] by=new byte[1024];
					while((c=in.read(by)) != -1){
						out.write(by,0,c);
					}
					out.close();
					in.close();
				}
			}
		}
		catch (Exception ex){
			System.out.println("解压文件异常"+ex.getMessage());
			ex.printStackTrace();
		}
	}
	/**
	 * 创建目录，包括子目录
	 * @param dir
	 * @throws Exception
	 */
	private static void mkDirs(String dir) throws Exception{
		if(dir == null || dir.equals("")) return;
		File f1 = new File(dir);
		if(!f1.exists())
			f1.mkdirs();
	}	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String f="C:\\00\\FlashFXP_4.3.1.1969_ati.zip";
			String f2="C:\\00";
			extract(f, f2);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}
