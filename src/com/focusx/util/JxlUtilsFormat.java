package com.focusx.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import jxl.CellReferenceHelper;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
  

public class JxlUtilsFormat {
	public  WritableCellFormat wcf_value;// 表格数据样式
	public  WritableCellFormat wcf_value_left;
	public  WritableCellFormat wcf_key;// 表头样式
	public  WritableCellFormat wcf_name_left;// 表名样式
	public  WritableCellFormat wcf_name_right;// 表名样式
	public  WritableCellFormat wcf_name_center;// 表名样式
	public  WritableCellFormat wcf_title;// 页名称样式
	public  WritableCellFormat wcf_percent_float;
	
	public  final int MAXCOLS = 7;// 表名称样式
	
	public JxlUtilsFormat() throws WriteException{ 
		/******   定义表格格式start    *****/
		WritableFont wf_key = new jxl.write.WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.BOLD);
		WritableFont wf_value = new jxl.write.WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.NO_BOLD);
		wcf_value = new WritableCellFormat(wf_value);
		wcf_value.setAlignment(jxl.format.Alignment.CENTRE);
		wcf_value.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcf_value.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		wcf_value_left = new WritableCellFormat(wf_value);
		wcf_value_left.setAlignment(jxl.format.Alignment.LEFT);
		wcf_value_left.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcf_value_left.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		wcf_value_left.setWrap(true);
		wcf_key = new WritableCellFormat(wf_key);
		wcf_key.setAlignment(jxl.format.Alignment.CENTRE);
		wcf_key.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		wcf_name_left = new WritableCellFormat(wf_key);
		wcf_name_left.setAlignment(Alignment.LEFT);
		wcf_name_left.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcf_name_right = new WritableCellFormat(wf_key);
		wcf_name_right.setAlignment(Alignment.LEFT);
		wcf_name_center = new WritableCellFormat(wf_key);
		wcf_name_center.setAlignment(Alignment.CENTRE);
		jxl.write.NumberFormat wf_percent_float = new jxl.write.NumberFormat("0.00");
		wcf_percent_float = new jxl.write.WritableCellFormat(wf_value,wf_percent_float);
		wcf_percent_float.setAlignment(jxl.format.Alignment.CENTRE);
		wcf_percent_float.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcf_percent_float.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		WritableFont wf_title = new jxl.write.WritableFont(WritableFont.createFont("微软雅黑"), 12, WritableFont.BOLD);
		wcf_title = new WritableCellFormat(wf_title);
		wcf_title.setAlignment(Alignment.LEFT);
	}
	/******   定义表格格式end    *****/

	// 生成空单元格
	public  void generateCells(WritableSheet ws, int startRows,
			int startColNums, int rows, int cols) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				try {
					ws.addCell(new Label(startColNums + c, startRows + r, ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
 

 

	public static void export(String[] tableHeader, String xlsName, List data,HttpServletResponse response) {
		Integer footer[] = { 1, 1 };
		try { 
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("GB2312");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((xlsName).getBytes(), "iso-8859-1"));
			wirteData2(response.getOutputStream(), tableHeader, footer, 1, data, 0);
		} catch (WriteException e) {
			e.printStackTrace();
			 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
 

	public static void wirteData2(OutputStream os, String[] headers, Integer[] footer, int startrow, List data, int hfreeze)
			throws IOException, WriteException {
		// Sender sender = new Sender();
		WritableWorkbook workbook = createWorkbook(os);
		WritableFont songti12 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD);
		WritableFont arial12 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
		WritableFont arialRedBold12 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
		arialRedBold12.setBoldStyle(WritableFont.BOLD);
		arialRedBold12.setColour(Colour.RED);

		WritableSheet s = workbook.createSheet("统计", 0);
		s.getSettings().setVerticalFreeze(1);
		if (hfreeze > 0) {
			s.getSettings().setHorizontalFreeze(hfreeze);
		}
		WritableCellFormat cwt = new WritableCellFormat(songti12);
		WritableCellFormat wrappedText = new WritableCellFormat(songti12);
		WritableCellFormat cfipc = new WritableCellFormat(arial12, NumberFormats.PERCENT_INTEGER);
		WritableCellFormat cfiry = new WritableCellFormat(NumberFormats.ACCOUNTING_RED_FLOAT);
		wrappedText.setAlignment(Alignment.CENTRE);
		wrappedText.setWrap(false);
		int i = 0;
		ArrayList<Integer> percents = new ArrayList<Integer>();
		ArrayList<Integer> rys = new ArrayList<Integer>();
		for (i = 0; i < headers.length; i++) {
			String header = headers[i];
			boolean is = false;
			int m = header.indexOf("%");
			if (m > -1) {
				percents.add(i);
				header = header.substring(0, m);
				is = true;
			}
			if (is == false) {
				m = header.indexOf("￥");
				if (m > -1) {
					rys.add(i);
					header = header.substring(0, m);
				}
			}
			Label l = new Label(i, 0, header, wrappedText);
			s.addCell(l);
		}

		i = 1;
		Iterator it = data.iterator();
		while (it.hasNext()) {
			Object[] value = (Object[]) it.next();
			for (int j = 0; j < value.length; j++) {
				if (value[j] instanceof Number) {
					if (((Number) value[j]).doubleValue() != 0) {
						WritableCellFormat cwt1 = cwt;
						boolean is = false;
						if (percents.contains(j)) {
							cwt1 = cfipc;
							is = true;
						}
						if (is == false) {
							if (rys.contains(j)) {
								cwt1 = cfiry;
								is = true;
							}
						}
						jxl.write.Number numCell = new jxl.write.Number(j, i, ((Number) value[j]).doubleValue(), cwt1);
						s.addCell(numCell);
					} else {
						Label lblCell = new Label(j, i, "", cwt);
						s.addCell(lblCell);
					}
				} else if (value[j] instanceof String) {
					Label l = new Label(j, i, (String) value[j], cwt);
					s.addCell(l);
				} else if (value[j] instanceof Date) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Label l = new Label(j, i, sdf.format(value[j]), cwt);
					s.addCell(l);
				}
			}
			i++;
		}
		if (footer != null) {
			int dataLen = data.size();
			int first = footer[0] - 1;
			Label lblCell = new Label(first, startrow + dataLen, "合计", cwt);
			s.addCell(lblCell);
			for (i = 0; i < footer.length; i++) {
				String Cr1 = CellReferenceHelper.getCellReference(footer[i], startrow);
				String Cr2 = CellReferenceHelper.getCellReference(footer[i], startrow + dataLen - 1);
				Formula f = new Formula(footer[i], startrow + dataLen, "SUM(" + Cr1 + ":" + Cr2 + ")", cwt);
				s.addCell(f);
			}
		}
		closeWorkbook(workbook);
	}

	 
 
 

	public static WritableWorkbook createWorkbook(OutputStream os) throws IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(Locale.SIMPLIFIED_CHINESE);
		WritableWorkbook workbook = Workbook.createWorkbook(os, ws);
		return workbook;
	}

 

	public static void closeWorkbook(WritableWorkbook workbook) throws IOException, WriteException {
		workbook.write();
		workbook.close();
	}
}
