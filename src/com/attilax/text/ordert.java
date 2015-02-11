package com.attilax.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.attilax.core;
import com.attilax.collection.listUtil;
import com.attilax.io.filex;
import com.attilax.util.Func_4SingleObj;

public class ordert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> li2=filex.read2list("c:\\ord.txt");
		List<String> li=listUtil.map_genericO5(li2, new Func_4SingleObj<String, String>() {

			@Override
			public String invoke(String o) {
			 
				return o.toLowerCase();
			}
		}, ArrayList.class);
		Collections.sort(li ); 
		core.print(li);
		
	}

}
