/**
 * 
 */
package com.attilax.biz.oplog;

import com.focusx.elmt.GvMaterial;
import com.focusx.util.OperLogUtil4vod;

/**
 * @author ASIMO
 *
 */
public abstract class IOpLogger {

		/**
		@author attilax 老哇的爪子
		@since   ob0 g_0_58
		 
		 */
	public void log(String string) {
		OperLogUtil4vod.log( string);
		
	}

}
