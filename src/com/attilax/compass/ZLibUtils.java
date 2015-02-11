/**
 * 2009-9-9
 */
package com.attilax.compass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import com.attilax.Base256;
import com.attilax.io.filex;

/**
 * ZLib压缩工具
 * 
 * @author <a href="mailto:zlex.dongliang@gmail.com">梁栋</a>
 * @version 1.0
 * @since 1.0
 */
public abstract class ZLibUtils {

	/**
	 * 压缩
	 * 
	 * @param data
	 *            待压缩数据
	 * @return byte[] 压缩后的数据
	 */
	public static byte[] compress(byte[] data) {
		byte[] output = new byte[0];

		Deflater compresser = new Deflater();

		compresser.reset();
		compresser.setInput(data);
		compresser.finish();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[1024];
			while (!compresser.finished()) {
				int i = compresser.deflate(buf);
				bos.write(buf, 0, i);
			}
			output = bos.toByteArray();
		} catch (Exception e) {
			output = data;
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		compresser.end();
		return output;
	}

	/**
	 * 压缩
	 * 
	 * @param data
	 *            待压缩数据
	 * 
	 * @param os
	 *            输出流
	 */
	public static void compress(byte[] data, OutputStream os) {
		DeflaterOutputStream dos = new DeflaterOutputStream(os);

		try {
			dos.write(data, 0, data.length);

			dos.finish();

			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压缩
	 * 
	 * @param data
	 *            待压缩的数据
	 * @return byte[] 解压缩后的数据
	 */
	public static byte[] decompress(byte[] data) {
		byte[] output = new byte[0];

		Inflater decompresser = new Inflater();
		decompresser.reset();
		decompresser.setInput(data);

		ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[1024];
			while (!decompresser.finished()) {
				int i = decompresser.inflate(buf);
				o.write(buf, 0, i);
			}
			output = o.toByteArray();
		} catch (Exception e) {
			output = data;
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		decompresser.end();
		return output;
	}

	/**
	 * 解压缩
	 * 
	 * @param is
	 *            输入流
	 * @return byte[] 解压缩后的数据
	 */
	public static byte[] decompress(InputStream is) {
		InflaterInputStream iis = new InflaterInputStream(is);
		ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
		try {
			int i = 1024;
			byte[] buf = new byte[i];

			while ((i = iis.read(buf, 0, i)) > 0) {
				o.write(buf, 0, i);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return o.toByteArray();
	}

	public static void main(String[] args) throws  Exception  {
		String s=filex.read("c:\\publsh.json");
		s="我ab哈";
		String kmprsed = compress_iso8859(s);
		System.out.println(kmprsed);
		System.out.println("==="+decompress_iso8859(kmprsed));
	}
	/**
	@author attilax 老哇的爪子
		@since  o8a 1_47_b   
	
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException 
	 */@Deprecated
	public static String compress_base256(String s) throws UnsupportedEncodingException {
		// attilax 老哇的爪子  1_47_b   o8a 
		 
				byte[] bytes = s.getBytes("gbk");
				byte[] a=compress(bytes);
		
			//	String charsetName = "iso-8859-1";
				return   Base256.encode(a);
	}
	/**
	 * 
	@author attilax 老哇的爪子
		@since  o8a 1_48_57   
	
	 * @param s
	 * @return
	 * @throws IOException 
	 */@Deprecated
	public static String decompress_base256(String s) throws IOException {
		String charsetName = "gbk";
		byte[] byteBefBase256= Base256.decode(s);
		byte[] ori=decompress(byteBefBase256);
		  return new String(ori,charsetName);
 	 
		
	}
	
	
	public static String compress_iso8859(String s) throws UnsupportedEncodingException {
		// attilax 老哇的爪子  1_47_b   o8a 
		 
				byte[] bytes = s.getBytes("gbk");
				byte[] a=compress(bytes);
		
			//	String charsetName = "iso-8859-1";   Base256.encode(a);
				return  new String(a,"iso-8859-1");
	}
	/**
	 * 
	@author attilax 老哇的爪子
	@since  o8a 1_48_57  	
	 * @param s
	 * @return
	 * @throws IOException 
	 */
	public static String decompress_iso8859(String s) throws IOException {
		String charsetName = "gbk";
	//	byte[] byteBefBase256= Base256.decode(s);
		byte[] b=s.getBytes("iso-8859-1");
		byte[] ori=decompress(b);
		  return new String(ori,charsetName);
 	 
		
	}
}

