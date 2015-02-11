package com.attilax.ref;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.beanutils.BeanUtils;
 

import com.attilax.Closure;
import com.attilax.ClosureNoExcpt;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.anno.Conditional;
//import com.attilax.anno.DataConverte;
 
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.cantFindOneMatchElmtException;
import com.attilax.count.CountRelt;
import com.attilax.lang.ref.None;
import com.attilax.text.strUtil;
import com.focusx.elmt.GvMaterial;
import com.focusx.playRec.GvPlayRecord;

public class refx {

	/** refre cn.freeteam.base包下共同方法
	 * @author freeteam 2011-6-14
	 * 
	 * public class Base {
	 * @param args 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException */
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		System.out.println(curMethod());
		// String s="com.attilax.ref.refx";
		// refx c=(refx) newInstance(s);
		// c.t();
		// refx.t2();
		// refx.t3();
		// refx.t5();
		// refx.t6();
		
		Class<?>  cls1 = None.class;
		Class<?>  cls2=None.class;
		System.out.println(cls1==cls2);   //true
		cls2=String.class;
		System.out.println(cls1==cls2);  //false
		
		GvMaterial o=new GvMaterial();
		o.setMaterialId(0);
	//	BeanUtils.copyProperty(o, "materialId", 7);
		core.setProperty(o, "materialId", null);
		System.out.println(BeanUtils.getProperty(o, "materialId"));
	}

	/** @author attilax 老哇的爪子
	 * @since 2014-4-18 下午04:07:08$ */
	private static void t6() {
		// 下午04:07:08 2014-4-18

	}

	/** @author attilax 老哇的爪子
	 * @since 2014-4-18 上午11:19:03$ */
	private static void t5() {
		// 上午11:19:03 2014-4-18

	}

	/** @author attilax 老哇的爪子
	 * @since 2014-4-18 上午11:14:43$ */
	private static void t4() {
		// 上午11:14:43 2014-4-18

	}

	/** @author attilax 老哇的爪子
	 * @since 2014-2014-4-18 上午11:10:04 */
	private static void t3() {}

	/**
	 * 
	 */
	private static void t2() {
		// TODO Auto-generated method stub

	}

	public static ThreadLocal<Class> class_4getAnno_inThrdLoc=new ThreadLocal<Class>();
	public static String curMethod() {
		StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}
	public static StackTraceElement preMethod() {
		StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		StackTraceElement ste = stes[3];
		return ste;
	}
	public static StackTraceElement preMethod(int level) {
		StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		StackTraceElement ste = stes[level];
		return ste;
	}

	/** \
	 * 
	 * ，然后调用其newInstance()方法，相当于实例化（调用无参的构造函数,有参数的invoke method another ,bsi
	 * jeig..
	 * @param className
	 * @return */
	public static Object newInstance(String className) {

		Object accpTeacher;
		try {
			accpTeacher = Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
		return accpTeacher;
	}

	public void t() {
		System.out.println("ttt");
	}

	/** @author attilax 老哇的爪子
	 * @since o7g Xw1$
	 * 
	 * @param fld
	 * @return */
	public static boolean isIntType(Field fld) {
		// attilax 老哇的爪子 Xw1 o7g

		return fld.getType().getName().equals(Integer.class.getName());

	}
	
	public static boolean isTimeType(Field fld) {
		// attilax 老哇的爪子 Xw1 o7g

		return fld.getType().getName().equals(Timestamp.class.getName());

	}

	/** @author attilax 老哇的爪子
	 * @since o7g Z0l$
	 * 
	 * @param string
	 * @return */
//	public static Field getField(String string) {
//		// attilax 老哇的爪子 Z0l o7g
//		return null;
//
//	}

	/** @author attilax 老哇的爪子
	 * @since o7g Z18$
	 * 
	 * @param fldName
	 * @param class1
	 * @return
	 * @throws cantFindMatchFieldException */
	public static Field getField(final String fldName, Class<?> class1) throws cantFindMatchFieldException {
		// attilax 老哇的爪子 Z18 o7g
		Field[] flds = class1.getDeclaredFields();
		try {
			return (Field) CollectionUtils.find(flds, new ClosureNoExcpt() {

				@Override public Object execute(Object arg0) {
					// attilax 老哇的爪子 Z254 o7g
					Field fd = (Field) arg0;
					if (fd.getName().equals(fldName)) return true;
					return false;

				}
			});
		} catch (cantFindOneMatchElmtException e) {
			// attilax 老哇的爪子 Zf0 o7g
			e.printStackTrace();
			throw new cantFindMatchFieldException(fldName);
		}

	}

	/**
	@author attilax 老哇的爪子
		@since  o7g g5857$
	
	 * @param key
	 * @param class1
	 * @return
	 * @throws cantFindMatchFieldException 
	 */
//	public static Object getFieldDefVal(String key, Class<?> class1) throws cantFindMatchFieldException {
//		// attilax 老哇的爪子  g5857   o7g 
//		Field fld=refx.getField(key, class1);
//		DataConverte dct = fld.getAnnotation(DataConverte.class);
//		if(isIntType(fld))
//		return dct.defValInt();
//		if(isStrType(fld))
//			return dct.defValStr();
//		return null;
//		 
//		
//	}

	/**
	@author attilax 老哇的爪子
		@since  o7g ha50$
	
	 * @param fld
	 * @return
	 */
	public static boolean isStrType(Field fld) {
		// attilax 老哇的爪子  ha50   o7g 
	  	return fld.getType().getName().equals(String.class.getName()); 
		 
		
	}
	
	/**
	 * 
	@author attilax 老哇的爪子
		@since  o08 2_50_40   
	
	 * @param fld
	 * @return
	 */
	public static boolean isTimestampType(Field fld) {
		// attilax 老哇的爪子  ha50   o7g 
	  	return fld.getType().getName().equals(Timestamp.class.getName()); 
		 
		
	}
	
	public static  boolean isType(Field fld,Class<?> cls) {
		// attilax 老哇的爪子  ha50   o7g 
	  	return fld.getType().getName().equals(cls.getName()); 
		 
		
	}
	public static boolean isTimestampType(Object obj) {
		// attilax 老哇的爪子  ha50   o7g 
	     if(obj instanceof Timestamp)
	    	 return true;
	     else
	    	 return false;
		 
		
	}

	/**
	@author attilax 老哇的爪子
	\t@since  Jul 19, 2014 8:41:41 PM$
	
	 * @param obj
	 * @param closure
	 */
	public static void eachFld(Object obj, Closure closure) {
		// attilax 老哇的爪子 8:41:41 PM Jul 19, 2014

		Field[] flds = obj.getClass().getDeclaredFields();
		for (Field fld : flds) {
			try {
				closure.execute(fld);

			} catch (Exception e) {
				// attilax 老哇的爪子 8:59:01 PM Jul 19, 2014
				core.log(e);
			}
			// Id id=fld.getAnnotation(Id.class);

		}

	}

	/**
	@author attilax 老哇的爪子
	\t@since  Jul 19, 2014 8:43:56 PM$
	
	 * @param obj
	 * @param closure
	 * @return 
	 * @throws cantFindMatchFieldException 
	 * @throws Exception 
	 */
	public static Field anyFld(Object obj, Closure closure) throws cantFindMatchFieldException {
		// attilax 老哇的爪子  8:43:56 PM   Jul 19, 2014 
		Field[] flds =obj.getClass().getDeclaredFields();
		for (Field fld : flds) {
		try {
			if(	(Boolean) closure.execute(fld))
				return fld;
		} catch (Exception e) {
			//  attilax 老哇的爪子 8:59:01 PM   Jul 19, 2014   
			core.log(e);
		}
			//Id id=fld.getAnnotation(Id.class);
			
		}
		throw new cantFindMatchFieldException("");
		
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o8o j_52_q   
	
	 * @param o
	 * @return
	 * @throws cantFindMatchFieldException 
	 */
	@Deprecated
	public static Field getIdFld_EX(Class<?> o) throws cantFindMatchFieldException {
		// attilax 老哇的爪子  j_52_q   o8o 
		return refx.anyFld(o, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 8:40:55 PM Jul 19, 2014
				Field fld = (Field) arg0;
				Id id = fld.getAnnotation(Id.class);
				if (id != null)
					return true;
				return false;

			}
		});		
	}
	
	
	
	
	public static Field getIdFld_EXO9o(final Class<?> o) throws cantFindMatchFieldException {
		// attilax 老哇的爪子  j_52_q   o8o 
		
		
		Field[] flds =o.getDeclaredFields();
		for (Field fld : flds)
		{
			   if(isIdFld(fld,o))
					return fld;
				
		}
	    throw new cantFindMatchFieldException("");
	}
	
	/**
	 * 
	@author attilax 老哇的爪子
		@since  o9r j_0_a   
	
	 * @param o
	 * @return
	 */
	public static Field getIdFld_RE(final Class<?> o)   {
		// attilax 老哇的爪子  j_52_q   o8o 
		
		try {
			return getIdFld_EXO9o(o);
		} catch (cantFindMatchFieldException e) {
			//  attilax 老哇的爪子 j_9_q   o9r   
			throw new RuntimeException(e);
		}
	 
	}
	
	/**
	@author attilax 老哇的爪子
		@since  o9q a_0_47   
	
	 * @param fld
	 * @param o
	 * @return
	 */
	protected static boolean isIdFld(Field fld, Class<?> o) {
		// attilax 老哇的爪子  a_0_47   o9q 
		
		Id  c=fld.getAnnotation(Id.class);
		if(c==null)
		{
			 String methName=getMethName(fld.getName());
			 if(methName.equals("getSerialVersionUID"))
				 return false;
			Method m = null;
			try {
				m = refx.getMeth(methName,o);
			} catch (NoSuchMethodException e) {
				//  attilax 老哇的爪子 a_d_o   o9q   
				//e.printStackTrace();
			//	throw new RuntimeException("NoSuchMethodExceptionAti:"+methName, e);
				core.warn(e);
				return false;
			} catch (SecurityException e) {
				//  attilax 老哇的爪子 a_d_o   o9q   
				//e.printStackTrace();
			//	throw new RuntimeException("SecurityExceptionAti,getmeth:"+methName, e);	core.warn(e);
				return false;
			}
			Id c2=m.getAnnotation(Id.class);
			if(c2==null)
				 return false;
			else
				return true;
		}
		return true;
		
	}

	/**
	 * return Val =null is not cant
	@author attilax 老哇的爪子
		@since  o09 j_2_n   
	
	 * @param o
	 * @return
	 */
	public static Field getIdFld_RV(Class<?> o)   {
		// attilax 老哇的爪子  j_52_q   o8o 
		try {
			return refx.anyFld(o, new Closure() {

				@Override
				public Object execute(Object arg0) throws Exception {
					// attilax 老哇的爪子 8:40:55 PM Jul 19, 2014
					Field fld = (Field) arg0;
					Id id = fld.getAnnotation(Id.class);
					if (id != null)
						return true;
					return false;

				}
			});
		} catch (cantFindMatchFieldException e) {
			//  attilax 老哇的爪子 j_55_51   o8o   
			core.warn(e);
			return null;
		}		
	}

	/**
	@author attilax 老哇的爪子
		@since  o9p i_0_8   
	
	 * @param rit2
	 * @return
	 */
	public static boolean isStrType(Object obj) {
		// attilax 老哇的爪子  i_0_8   o9p 
	    if(obj instanceof String)
	    	 return true;
	     else
	    	 return false;
	//	return false;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o9p m_r_8   
	
	 * @param string
	 * @param class1
	 * @param class2
	 * @return
	 * @throws cantFindMatchFieldException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws NoThisAnno 
	 */
	public static <t extends Annotation> t getAnno(String fldName, Class<?> class1, Class<t> annoCls) throws cantFindMatchFieldException, NoThisAnnoEx, NoSuchMethodException, SecurityException {
		// attilax 老哇的爪子  m_r_8   o9p 
		 		// attilax 老哇的爪子  7:31:58 AM   Jul 20, 2014 
			 
				Field fld=refx.getField(fldName, class1);
						//this.saveObjClass.getDeclaredField(fldName);
				 
				t c=fld.getAnnotation(annoCls);
				if(c==null)
				{
					 String methName=getMethName(fld.getName());
					Method m=refx.getMeth(methName,class1);
					t c2=m.getAnnotation(annoCls);
					if(c2==null)
						throw new NoThisAnnoEx();
					else
						return c2;
				}
			return	 c;
	 
			 
			
			
	 
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o9p m_38_59   
	
	 * @param methName
	 * @param class1
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static Method getMeth(String methName, Class<?> class1) throws NoSuchMethodException, SecurityException {
		// attilax 老哇的爪子  m_38_59   o9p 
		return  class1.getDeclaredMethod(methName);
	 
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o9p m_38_55   
	
	 * @param name
	 * @return
	 */
	private static String getMethName(String name) {
		// attilax 老哇的爪子  m_38_55   o9p 
		
		return "get"+strUtil.UpHeadStr(name);
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o9q 10_v_y   
	
	 * @param saveObjClass
	 * @param class1
	 * @return
	 */
	public static List getFlds(Class<?> class1, Class<? extends Annotation> annoCls) {
		// attilax 老哇的爪子  10_v_y   o9q 
		List li=new ArrayList();
		Field[] flds = class1.getDeclaredFields();
		for (Field field : flds) { 
		 
			Annotation a=	field.getAnnotation(annoCls);
			if(a!=null)
				li.add(field.getName());
				
		}
		return li;
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o9r m_l_37   
	
	 * @param subobjFld
	 * @param class1
	 * @return
	 */
	@Deprecated
	public static Annotation getAnno(Field subobjFld, Class<? extends Annotation> class1) {
		// attilax 老哇的爪子  m_l_37   o9r 
		return subobjFld.getAnnotation (class1);
		
	}

		/**
		@author attilax 老哇的爪子
		@since   oaj c_w_50
		 
		 */
	public static boolean isIdsType(Field fld, Map reqMap) {
		return	 refx.isIntType(fld) && SafeVal.val(reqMap.get(fld.getName()), "")  .contains(",");
		 
	}

	/**
	@author attilax 老哇的爪子
		@since  o7g j839$
	
	 * @param key
	 * @param class1
	 * @return
	 * @throws cantFindMatchFieldException 
	 */
//	public static ValidateSvrside getFieldValidateSvrside(String key, Class<GvMaterial> class1) throws cantFindMatchFieldException {
//		// attilax 老哇的爪子  j839   o7g 
//		Field fld=getField(key, class1);
//		ValidateSvrside cdt = fld.getAnnotation(ValidateSvrside.class);
//		return cdt;
//		 
//		
//	}

}
