package com.focusx.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;

public class TxtUtil {

	public static void export(String tableHeader, String txtName, List data,
			HttpServletResponse response) {
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("GB2312");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String((txtName).getBytes(), "iso-8859-1"));
			wirteData2(response.getOutputStream(), tableHeader, data);
		} catch (WriteException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void wirteData2(OutputStream os, String headers, List data)
			throws IOException, WriteException {
		// 第一步，定义对应的输出流
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String n = "\r\n";
		String r = "\t"; 
		try {
			buff = new BufferedOutputStream(os);
			// 写入标题
			write.append(headers);
			write.append(n);
			Iterator it = data.iterator();
			while (it.hasNext()) {
				Object[] value = (Object[]) it.next();
				for (int j = 0; j < value.length; j++) {
					write.append(value[j]);
					write.append(r);  
				}
				write.append(n);
			}
			buff.write(write.toString().getBytes("UTF-8"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}