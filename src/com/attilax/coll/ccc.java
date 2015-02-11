/**
 * 
 */
package com.attilax.coll;

import java.util.HashMap;
import java.util.Map;

import com.attilax.core;
import com.attilax.text.strUtil;
import com.focusx.elmt.GvMaterialSvs;

/**
 * @author ASIMO
 *
 */
public class ccc {

	/**
	@author attilax 老哇的爪子
	@since   ob1 c_1_47
	 
	 */
	public static void main(String[] args) {
		String s="logicDel=8, playtime_start=00:00:01, element4=, materialType=, element3=, playtime_end=00:00:01, idsCheckVals=";
	Map m=strUtil.toMap(s);
	GvMaterialSvs c=new GvMaterialSvs();
	  Map  r=c.findByPropertyss_page(m);
	  System.out.println(core.toJsonStrO88(r));
		
	}

}
