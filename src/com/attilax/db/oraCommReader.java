/**
 * @author attilax 老哇的爪子
	@since  o7e jv2$
 */
package com.attilax.db;
import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.text.strUtil;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o7e jv2$
 */
public class oraCommReader {
	//  attilax 老哇的爪子 jv2   o7e   
	public static void main(String[] args) {
		String f="D:\\workspace\\vodx\\sqlx\\dic.sql";
		List r=new ArrayList();
		List<String> li=filex.read2list(f);
		for (String string : li) {
			if(string.trim().length()==0)continue;
			if(string.startsWith("--"))
				continue;
			Col o=new Col();
			if(string.startsWith("comment on column"))
			{
				getColTAbleAndCol(string,o);
			}
			if(string.trim().startsWith("is"))
			{
				getComm(string,o);
			
				r.add(o);
				geneMssqlComm(o);
			}
		}
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o7e j3855$
	
	 * @param o
	 */
	private static void geneMssqlComm(Col o) {
		// attilax 老哇的爪子  j3855   o7e 
		{String s="EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'#comm' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'#tab', @level2type=N'COLUMN',@level2name=N'#col'";
		s=s.replaceAll("#comm", o.comm);
		s=s.replaceAll("#tab", o.tab);
		s=s.replaceAll("#col", o.col);
		System.out.println(s);  } 
		{}
		{}
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o7e j3852$
	
	 * @param string
	 * @param o
	 */
	private static void getComm(String string, Col o) {
		// attilax 老哇的爪子  j3852   o7e 
		  String s=strUtil.replaceBatchO7(string, "is,',;","") ; 
		o.comm=s;
		{}
		{}
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o7e j378$
	
	 * @param string
	 * @param o
	 */
	private static void getColTAbleAndCol(String string, Col o) {
		// attilax 老哇的爪子  j378   o7e 
		String[] sa=string.split(" ");
		String[] sa2= strUtil.SplitByDot(sa[sa.length-1]) ;
		o.tab=sa2[0];
		o.col=sa2[1];
	
	}
}

//  attilax 老哇的爪子