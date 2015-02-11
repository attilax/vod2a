package com.attilax.collection;

import java.util.Iterator;
import java.util.List;

import com.attilax.util.Func_4SingleObj;
import com.attilax.util.tryX;

public class map {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static <atiType, iptType, retType,retTypeFinnaly>  retTypeFinnaly  map_genericO5(
			atiType arr, Func_4SingleObj<iptType, retType> function,final Class<retTypeFinnaly> retTypeFinnalyClass) {
	
		
			retTypeFinnaly ja = new tryX<retTypeFinnaly>() {

				@Override
				public retTypeFinnaly item(Object t) throws Exception {
					// attilax 老哇的爪子 下午01:34:58 2014-5-26
					return retTypeFinnalyClass.newInstance();
				}
			}.$();
			List ia = (List) ja;
			// JSONArray arr1=(JSONArray)arr;
			List li = (List) arr;
			Iterator<iptType> it = li.iterator();
	 
			while (it.hasNext()) {
				iptType next = it.next();
				retType o = (retType) function.invoke(next);
				ia.add(o);
			}
			return   ja;
		
	
	}

}
