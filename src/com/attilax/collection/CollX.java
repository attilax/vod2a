/**
 * @author attilax 老哇的爪子
	@since  o9p 7_w_b$
 */
package com.attilax.collection;
import com.attilax.Closure;
import com.attilax.core;
import com.attilax.text.strUtil;
import com.focusx.downtask.GvDownloadTask;

 import static  com.attilax.core.*;
import java.util.*;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import net.sf.json.JSONArray;
/**
 * @author  attilax 老哇的爪子
 *@since  o9p 7_w_b$
 */
public class CollX extends listUtil {
	//  attilax 老哇的爪子 7_w_b   o9p   
	
	public static void main(String[] args) {
		List SelectItemList=new ArrayList();
		SelectItemList.add("aa");
		SelectItemList.add("bb");
	System.out.println( CollX.join(SelectItemList));	
	}
	
	/**
	 * 
	 * @param li
	 * @param closure
	 * @return
	 */
	@SuppressWarnings("all")
	public static List each_safe( List li, final Closure closure) {
		final List li2 = new ArrayList<Object>();

		for (final Object string : li) {
			try {
				Object o = closure.execute(string);
				if(o!=null)
					li2.add(o);
				//return null;
			} catch (Exception e) {
				core.log(e);
			}

		}
		// attilax 老哇的爪子 i056 o7f
	 
		return li2;

	}
	
	/**
	 * def char is comma
	@author attilax 老哇的爪子
		@since  o9p 7_x_o   
	
	 * @param li2
	 * @return
	 */
	public static String join(Collection  li2) {
		// attilax 老哇的爪子 下午03:17:16 2014-5-14
		String s = "";
		for (Object s2 : li2) {
			s += s2+",";
		}
		return strUtil.trimx(",", s) ;
	}
	
//	public static String join(List<Integer> li2) {
//		// attilax 老哇的爪子 下午03:17:16 2014-5-14
//		String s = "";
////		for (String s2 : li2) {
////			s += s2+",";
////		}
//		return strUtil.trimx(",", s) ;
//	}

	/**
	@author attilax 老哇的爪子
		@since  o9p 7_37_54   
	
	 * @param whereExpList
	 * @param string
	 * @return
	 */
	public static String join(  List<String> li2, String joinChar) {
		// attilax 老哇的爪子  7_37_54   o9p 
		
		String s = "";
		for (String s2 : li2) {
			if(s.length()==0)
				s += s2;
			else
				s += joinChar+ s2;
		}
		return s;
		
	}
	public static String joinOa9(  Collection<String> li2, String joinChar) {
		// attilax 老哇的爪子  7_37_54   o9p 
		
		String s = "";
		for (String s2 : li2) {
			if(s.length()==0)
				s += s2;
			else
				s += joinChar+ s2;
		}
		return s;
		
	}
	
	public static List  covertToIntList(Object object) {
		List li=new ArrayList ();
		 String[] a=object.toString().split(",");
		 for (String id_s : a) {
			 try {
				 Integer id= Integer.parseInt(id_s);
				 li.add(id);
			} catch (Exception e) {
				core.err(e);
			}
			
		}
		 
		return li;
		//return null;
	}
	 
	/**
	@author attilax 老哇的爪子
		@since  o01 3_44_b   
	
	 * @param li
	 * @return
	 */
	public static JSONArray List2jsonArr(List li) {
		if(li==null)
			return new JSONArray();
		// attilax 老哇的爪子  3_44_b   o01 
		String li2json=core.toJsonStrO88(li);
	;

		return 	net.sf.json.JSONArray.fromObject(li2json);
		
	}

	public static List mapToInt(List li, final Closure closure) {
		final List li2=new ArrayList ();
		CollectionUtils.each_safe(li, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				li2.add(closure.execute(null));
				return  null;
			}});
		return li2;
	 
		
	}
	 @SuppressWarnings("all") 
	public static Set deDuli(List li,final Closure closure) {
		final Set st=new HashSet ();
		CollectionUtils.each_safe(li, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				st.add(closure.execute(arg0));
				return  null;
			}});
		return st;
	}

	public static String joinIds(Collection li, com.attilax.Closure closure) {
		List li2 = new ArrayList();
		for (Object object : li) {
			try {
				Object id = closure.execute(object);
				li2.add(String.valueOf(id));
			} catch (Exception e) {
				core.err(e);
			}
		}
		
		return join(li2, ",");
	}

		/**
		@author attilax 老哇的爪子
		@since   oab i_42_43
		 
		 */
	public static List toList(Field[] flds) {
		 
		List  li = new ArrayList ();
			for (Field fld : flds) {
				li.add(fld.getName());
			}
			
		 
				
			 
			return li;
		 
	 
	}

			/**
			@author attilax 老哇的爪子
			@since   ob0 i_7_53
			 
			 */
		public static String toString(List<Integer> li) {
			String s = "";
			for (Integer str : li) {
				s = s + "," + str.toString();
			}
			s = strUtil.trimx(",", s);
			return s;
		}
		
		public static String toString2(List<String> li) {
			String s = "";
			for (String str : li) {
				s = s + "," + str;
			}
			s = strUtil.trimx(",", s);
			return s;
		}

			/**
			@author attilax 老哇的爪子
			@since   obe j_i_o
			 
			 */
		public static String toString(List<Map> li, String key) {
			String s = "";
			 for (Map map : li) {
				 try {
					 s = s + "," + map.get(key);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			 s=strUtil.trimx(",", s);
			return s;
		}
}

//  attilax 老哇的爪子