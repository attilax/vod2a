/**
 * @author attilax 老哇的爪子
	@since  o9p m_59_o$
 */
package com.attilax.count;
import com.attilax.core;
import com.attilax.ref.NoThisAnnoEx;
import com.attilax.ref.cantFindMatchFieldException;
import com.attilax.ref.refx;
import com.focusx.playRec.GvPlayRecord;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.persistence.Column;
/**
 * @author  attilax 老哇的爪子
 *@since  o9p m_59_o$
 */
public class CountX {

	/**
	@author attilax 老哇的爪子
		@since  o9p m_59_v   
	
	 * @param name
	 * @return
	 */
	public static String getColumnName(String fld_name,Class<?> cls) {
		// attilax 老哇的爪子  m_59_v   o9p 
	//	refx.getAnno(fldName, class1, annoCls)
		try {
			Column column=refx. getAnno(fld_name, cls,Column.class);
			return column.name();
		} catch (NoSuchMethodException e) {
			//  attilax 老哇的爪子 0_1_41   o9q   
			 
		} catch (SecurityException e) {
			//  attilax 老哇的爪子 0_1_41   o9q   
			 
		} catch (cantFindMatchFieldException e) {
			//  attilax 老哇的爪子 0_1_41   o9q   
			 
		} catch (NoThisAnnoEx e) {
			//  attilax 老哇的爪子 0_1_41   o9q   
			 
		}
		return fld_name;
		
	}
	//  attilax 老哇的爪子 m_59_p   o9p   
}

//  attilax 老哇的爪子