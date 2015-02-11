/**
 * @author attilax 老哇的爪子
	@since  o02 g_47_x$
 */
package com.attilax.biz.Timerang;
import com.attilax.Closure;
import com.attilax.ClosureNoExcpt;
import com.attilax.core;
import com.attilax.MDA.IDefVal;
import com.attilax.anno.DataTypeConstants;
import com.attilax.anno.DefVal;
import com.attilax.collection.CollectionUtils;
import com.attilax.dsm.BaseSvs;
import com.attilax.dsm.Dsmx;
import com.attilax.dsm.SingleRecEditor;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.util.DwrX;
import com.focusx.elmt.GvMaterial;
import com.google.inject.Inject;
 import static  com.attilax.core.*;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.impl.DwrXmlConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**  com.attilax.biz.Timerang.TimerangX, com.attilax.biz.Timerang.TimeRangRec
 * @author  attilax 老哇的爪子
 *@since  o02 g_47_x$
 */
@RemoteProxy(name = "TimerangX")
public class TimerangX extends SingleRecEditor {
	
	@Inject
	String cfgfile="cfg.txt";

	/**
	@author attilax 老哇的爪子
		@since  o02 g_47_x   
	
	 * @param args
	 */
	public static void main(String[] args) {
		String s2 = "a\r\nbc";
	 	String s=StringEscapeUtils.escapeHtml4(s2);
 	s=core.txt2html(s2);
		//"b&lt;p&gt;c"
		System.out.println(s);
		// attilax 老哇的爪子  g_47_x   o02 
//		Field fld=null;
//		String datatype=Dsmx.getDatatype(fld);
//		if(datatype.equals(DataTypeConstants.time))
//		{
//			
//		}
		IocX.update=true;
		TimeRangRec o=new TimeRangRec();
		o.setStartTime("aa");
		TimerangX c=IocX.getBean(TimerangX.class);
		c.merge(o);

	}
	
//	@RemoteMethod public List findByIds(java.lang.Integer[] ids) {
//		log.debug("finding GvMaterial findByIds instance with property: ");// + propertyName + ", value: " + value);
// 
//			return (List) CollectionUtils.each_safe(ids, new Closure(){
//
//				@Override public Object execute(Object arg0) throws Exception {
//					// attilax 老哇的爪子  h572   o7g 
//					Object o=findById((Integer) arg0);
//					return o;
//					 
//					
//				}});
// 
//	}
	
	@RemoteMethod public Object findById(java.lang.Integer id) {
		//log.debug("getting GvMaterial instance with id: " + id);
	//	try {
			Object instance =  this.px.get(TimeRangRec.class, id);
			return instance;
//		} catch (RuntimeException re) {
//		//	log.error("get failed", re);
//			throw re;
//		}
	}
	
	
	/**
	@author attilax 老哇的爪子
		@since  o03 j_y_3   
	
	 * @param o
	 */
 
	HttpServletRequest request;
	@SuppressWarnings("unused") public String exec(HttpServletRequest request,HttpServletResponse response)
	{
		
		//String cls="com.attilax.biz.Timerang.TimeRangRec";
		String cls=request.getParameter("cls");
		if(request.getAttribute("cls")!=null)
			cls=(String) request.getAttribute("cls");
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
		String dwrx=Dsmx.getDwrx(TimerangX.class);
		request.setAttribute("dwrx", dwrx);
		request.setAttribute("defv",new ClosureNoExcpt() {
			
			 

			@Override public String execute(Object arg0)   {
				// attilax 老哇的爪子  m_5_h   o02 
				TimeRangRec o=new TimeRangRec();
				o.setStartTime("09:00:00");
				o.setEndTime("21:00:00");
				return core.toJsonStr_NF(o);
				
			}} .execute(null));
		
		
//		if(tmpltPage.length()==0)
//			tmpltPage = tmpltPageDefV;
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
	
	@Inject 
	BaseSvs basesvs=IocX.getBean(BaseSvs.class);
	@SuppressWarnings("all")
	@RemoteMethod public void save_map( Map mp) {
		try {
			 this.basesvs.saveObjClass=TimeRangRec.class;
			 this.basesvs.save_map(mp);
		} catch (Exception e) {
			 DwrX.getSession().setAttribute("errO9", core.getTrace(e));
			 throw new RuntimeException("--oa1c:"+e.getMessage(),e);
		}
		
	}
	@RemoteMethod public String getErr() {
		return this.basesvs. getErr();
	}

	/**
	@author attilax 老哇的爪子
		@since  o00 j_45_49   
	
	 * @param starttime
	 * @param endTime
	 * @return
	 */
	public static boolean LittleHeadStart(long starttime, long endTime) {
		// attilax 老哇的爪子  j_45_49   o00 
		return starttime<=endTime;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o00 j_46_j   
	
	 * @param starttime
	 * @param endTime
	 * @return
	 */
	public static boolean BigHeadStart(long starttime, long endTime) {
		// attilax 老哇的爪子  j_46_j   o00 
		return starttime>endTime;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o00 j_49_0   
	
	 * @param starttime
	 * @param endTime
	 * @param nowTime_secOnlyFmt
	 * @return
	 */
	public static boolean isInRang(long Starttime, long endTime, long nowTime_secOnlyFmt) {
		// attilax 老哇的爪子  j_49_0   o00 
		if(TimerangX. LittleHeadStart(Starttime,endTime))
			if (nowTime_secOnlyFmt > Starttime && nowTime_secOnlyFmt <= endTime) return true;
		if(TimerangX.BigHeadStart(Starttime,endTime))
			{
				if (nowTime_secOnlyFmt >= Starttime  || nowTime_secOnlyFmt <= endTime)
					return true;
			}
		return false;
		
	}
	
	
	//  attilax 老哇的爪子 g_47_x   o02   
}

//  attilax 老哇的爪子