package com.attilax.collection;

import java.util.Iterator;
import java.util.List;

import com.attilax.util.Func_4SingleObj;
import com.attilax.util.tryX;

public class Any {
	
	/**
	 * ret a any elmt
	 * @param arr
	 * @param function
	 * @return
	 * @throws NoBinElmtExcpt 
	 */@SuppressWarnings("all")
	public static <atiType, iptType, retType,retTypeFinnaly>  Object  any(
			atiType arr, Func_4SingleObj function) throws NoBinElmtExcpt {
	
		
//			retTypeFinnaly ja = new tryX<retTypeFinnaly>() {
//
//				@Override
//				public retTypeFinnaly item(Object t) throws Exception {
//					// attilax 老哇的爪子 下午01:34:58 2014-5-26
//					return retTypeFinnalyClass.newInstance();
//				}
//			}.$();
			
			List ia = (List) arr;
			// JSONArray arr1=(JSONArray)arr;
			List li = (List) arr;
			Iterator<iptType> it = li.iterator();
	 
			while (it.hasNext()) {
				iptType next = it.next();
				Boolean invokeRzt = (Boolean) function.invoke(next);
				if(invokeRzt)
				  return next;
			}
			throw new NoBinElmtExcpt();
		
	
	}

}
