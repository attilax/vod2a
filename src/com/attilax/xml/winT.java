/**
 * 
 */
package com.attilax.xml;

import java.io.File;

/**
 * @author ASIMO
 *
 */
public class winT {

	/**
	@author attilax 老哇的爪子
	@since   obf 9_52_39
	 
	 */
	public static void main(String[] args) {
		String s="c:\\workspace\\vodx\\WebRoot\\uploadf/1205.rmvb";
		System.out.println(new File(s).exists());
		String s2="c:/workspace/vodx/WebRoot/uploadf/1205.rmvb";
		System.out.println(new File(s2).exists());

	}

}
