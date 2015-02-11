/**
 * @author attilax 老哇的爪子
	@since  o02 3_d_50$
 */
package com.attilax.dsm;
import com.attilax.core;
import com.attilax.ioc.IocX;
import com.attilax.ref.NoThisAnnoEx;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
 import static  com.attilax.core.*;
import java.sql.Timestamp;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author  attilax 老哇的爪子
 *@since  o02 3_d_50$
 */
public class baseSvs4editBean  extends  BaseSvs{

	/**
	@author attilax 老哇的爪子
		@since  o02 3_d_50   
	
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws NoThisAnnoEx 
	 * @throws cantFindMatchFieldException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static void main(String[] args) throws Exception  {
		// attilax 老哇的爪子  3_d_50   o02 
		baseSvs4editBean c=IocX.getBean(baseSvs4editBean.class);
		IocX.getBean("xx");
		String cls="com.attilax.biz.Timerang.TimeRangRec";
		Class<?> class1 = null;
		try {
			class1 = Class.forName(cls);
		} catch (ClassNotFoundException e) {
			//  attilax 老哇的爪子 j_38_56   o03   
			e.printStackTrace();
		}
		
		String tit=c.getTitle(class1);
		Field[] flds = class1.getDeclaredFields();
		for (Field field : flds) {
			if(field.getType()==Timestamp.class)
			{
				
			}
		}

	}
	//  attilax 老哇的爪子 3_d_50   o02   

	/**
	@author attilax 老哇的爪子
		@since  o02 3_z_0   
	
	 * @param class1
	 * @return
	 */
	private String getTitle(Class<?> class1) {
		// attilax 老哇的爪子  3_z_0   o02 
		Title titAnno=class1.getAnnotation(Title.class);
		if(titAnno!=null)
		{
			return titAnno.value();
		}
//		try {
//			titAnno = refx.getAnno("Title", class1, Title.class);
//			return titAnno.value();
//		} catch (NoSuchMethodException e) {
//			//  attilax 老哇的爪子 3_z_41   o02   
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			//  attilax 老哇的爪子 3_z_41   o02   
//			e.printStackTrace();
//		} catch (cantFindMatchFieldException e) {
//			//  attilax 老哇的爪子 3_z_41   o02   
//			e.printStackTrace();
//		} catch (NoThisAnnoEx e) {
//			//  attilax 老哇的爪子 3_z_41   o02   
//			e.printStackTrace();
//		}
		return "";
		
		
	}
	
	public String exec(HttpServletRequest request,HttpServletResponse response)
	{
		
		//String cls="com.attilax.biz.Timerang.TimeRangRec";
		String cls=request.getParameter("cls");
		Class<?> class1=null;
		try {
			class1 = Class.forName(cls);
		} catch (ClassNotFoundException e1) {
			//  attilax 老哇的爪子 4_o_58   o02   
			e1.printStackTrace();throw new RuntimeException(e1);
			
		}
		
		String tit=getTitle(class1);
		request.setAttribute("tit", tit);
		Field[] flds = class1.getDeclaredFields();
		request.setAttribute("flds", flds);
//		for (Field field : flds) {
//			if(field.getType()==Timestamp.class)
//			{
//				
//			}
//		}
		
		String tmpltPage="/com.attilax/dsm/tmplt_edit.jsp";
		try {
			request.getRequestDispatcher(tmpltPage).forward(request, response);
		} catch (ServletException e) {
			//  attilax 老哇的爪子 4_m_y   o02   
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			//  attilax 老哇的爪子 4_m_y   o02   
			e.printStackTrace();throw new RuntimeException(e);
		}
		return null;
		
	}
}

//  attilax 老哇的爪子