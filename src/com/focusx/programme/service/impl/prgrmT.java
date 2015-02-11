/**
 * @author attilax 老哇的爪子
	@since  o93 2_3_53$
 */
package com.focusx.programme.service.impl;
import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.spri.SpringUtil;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.entity.GvProgrammeDetail;
import com.focusx.programme.service.IProgrammeService;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author  attilax 老哇的爪子
 *@since  o93 2_3_53$
 */
public class prgrmT {

	/**
	@author attilax 老哇的爪子
		@since  o93 2_3_53   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  2_3_53   o93 
		IProgrammeService pc=(IProgrammeService) SpringUtil.getBean(IProgrammeService.class);
		//GvProgramme p=new GvProgramme();
		int id=62;
		 GvProgramme p=	pc.getProgramme(id);
		 GvProgrammeDetail pd=(GvProgrammeDetail) p.list.get(0);
		 pd.setMaterialId(3);
	//	p.setCreateMan(4);
	//	pc.insert(p, Mapx.<String,Object>$().add("materialIds", "1,2").toMap());
		 pc.update(p,  Mapx.<String,Object>$().add("materialIds", "1,2").toMap());
		System.out.println("--");
	}
	
	@Test
	public void  findT()
	{
		int id=139;
		IProgrammeService pc=(IProgrammeService) SpringUtil.getBean(IProgrammeService.class);
	 GvProgramme p=	pc.getProgramme(id);
	
	 core.print_wzFmt(p.list);
	 Assert.assertTrue(p.list.size()>0);
	}
	//  attilax 老哇的爪子 2_3_53   o93   
}

//  attilax 老哇的爪子