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

import com.attilax.io.filex;

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

 

public class JxlUtils {

	public static void export(String[] tableHeader, String xlsName, Integer[] footer, List data,HttpServletResponse response) {
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
			try {
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
			} catch (Exception e) {
				filex.saveLog(e, "c:\\");
			}
			
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
