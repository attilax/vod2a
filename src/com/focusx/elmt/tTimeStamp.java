/**
 * 
 */
package com.focusx.elmt;

import java.sql.Timestamp;
import java.text.ParseException;

import com.attilax.time.timeUtil;

/**
 * @author ASIMO
 *
 */
public class tTimeStamp {

	/**
	@author attilax 老哇的爪子
	 * @throws ParseException 
	@since   ob9 i_j_37
	 
	 */
	public static void main(String[] args) throws ParseException {
		Timestamp	ts= timeUtil.getTimestamp("2014-10-27 00:00:01");
		//times 1414393201000   1414339201000
		System.out.println(ts.getTime());
		

	}

}
