package com.attilax.zip;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;
/**
 * 程序实现了ZIP压缩。共分为2部分 ：
 * 压缩（compression）与解压（decompression）
 * <p>
 * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。
 * 需在代码中自定义源输入路径和目标输出路径。
 * <p>
 * 在本段代码中，实现的是解压部分；压缩部分见本包中compression部分。
 * @author HAN
 *
 */
public class zipX_jdk {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long startTime=System.currentTimeMillis();
		unZipFiles("C:\\00\\FlashFXP_4.3.1.1969_ati.zip","C:\\00");
//		try {
//			//Decompl("C:\\00\\FlashFXP_4.3.1.1969_ati.zip");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		long endTime=System.currentTimeMillis();
		System.out.println("耗费时间： "+(endTime-startTime)+" ms");
	}
@Deprecated
	private static void Decompl(String zipfile) throws FileNotFoundException {
		//String zipfile = "C:\\Users\\HAN\\Desktop\\stock\\SpectreCompressed.zip";
		ZipInputStream Zin=new ZipInputStream(new FileInputStream(
				zipfile));//输入源zip路径
		BufferedInputStream Bin=new BufferedInputStream(Zin);
		String Parent="C:\\00\\Desktop222"; //输出路径（文件夹目录）
		File Fout=null;
		ZipEntry entry;
		try {
			while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
				Fout=new File(Parent,entry.getName());
				if(!Fout.exists()){
					(new File(Fout.getParent())).mkdirs();
				}
				FileOutputStream out=new FileOutputStream(Fout);
				BufferedOutputStream Bout=new BufferedOutputStream(out);
				int b;
				while((b=Bin.read())!=-1){
					Bout.write(b);
				}
				Bout.close();
				out.close();
				System.out.println(Fout+"解压成功");	
			}
			Bin.close();
			Zin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	/**
	 * 解压到指定目录
	 * @param zipPath
	 * @param descDir
	 * @author isea533
	 */
	public static void unZipFiles(String zipPath,String descDir)throws IOException{
		unZipFiles(new File(zipPath), descDir);
	}
	/**
	 * 解压文件到指定目录
	 * 解压的时候，针对文件夹判断创建不存在的文件夹，对文件夹只创建，不进行解压..因为解压是针对文件的，不是文件夹，文件夹需要自己创建。 
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile,String descDir)throws IOException{
		File pathFile = new File(descDir);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		//zip.get
		for(Enumeration entries = zip.entries();entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry)entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
			//判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if(!file.exists()){
				file.mkdirs();
			}
			//判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if(new File(outPath).isDirectory()){
				continue;
			}
			//输出文件路径信息
			System.out.println(outPath);
			
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while((len=in.read(buf1))>0){
				out.write(buf1,0,len);
			}
			in.close();
			out.close();
			}
		System.out.println("******************解压完毕********************");
	}
}
