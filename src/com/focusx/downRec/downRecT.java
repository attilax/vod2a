/**
 * 
 */
package com.focusx.downRec;

import com.attilax.ioc.IocX;
import com.focusx.downtask.GvDownloadTask;

/**
 * @author ASIMO
 *
 */
public class downRecT {

	/**
	@author attilax 老哇的爪子
	@since   oaj 9_c_5
	 
	 */
	public static void main(String[] args) {
		GvDownloadTask t=new GvDownloadTask();
		t.setMaterialId(1);
		t.setEquipmentId(12);
		 GvDownloadRecordSvs c=IocX.getBean(GvDownloadRecordSvs.class);
		
//	System.out.println(c.isExistWaterRec(t));	;

	}

}
