/**
 * @author attilax 老哇的爪子
	@since  o9f 7_5_i$
 */
package com.test;
import com.attilax.core;
import com.attilax.coll.ListX;
import com.attilax.collection.GvCycleQueue;
import com.attilax.collection.GvCycleQueueSvs;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.spri.SpringUtil;
import com.attilax.util.DateUtil;
import com.focusx.pojo.Equipment;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.service.IProgrammeService;
import com.focusx.publish.entity.GvPublish;
import com.focusx.publish.service.IPublishService;
import com.focusx.push.PrgrmNoticer;
import com.focusx.push.spot;
import com.focusx.service.IEquipmentService;
import com.focusx.util.HbX4vod;
import com.focusx.util.Sys;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o9f 7_5_i$
 */
public class publishT {

	/**
	@author attilax 老哇的爪子
		@since  o9f 7_5_i   
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  7_5_i   o9f 
	//	tpushProgrm(233, 1075);  local
	//	HbX4vod.update=false;
	// 	tpushProgrm(283,1100);  //vod server
		
		IPublishService pbc = (IPublishService) SpringUtil.getBean("publishService");
GvPublish pubobj=		pbc.getPublish(1);
System.out.println(pubobj);

	// tp();
	}
	//  attilax 老哇的爪子 7_5_i   o9f
	
	/**
	@author attilax 老哇的爪子
		@since  o9f l_c_m   
	
	 */
	private static void tp() {
		// attilax 老哇的爪子  l_c_m   o9f 
		try {
			GvPublish publish=new GvPublish();
			spot sp = IocX.getBean(spot.class);
			sp.pushNappMtrPush(publish, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	msg = false;
		//	e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}

	private static void tpushProgrm(int prgrmID,int equipmentId ) {
		// attilax 老哇的爪子  1_v_j   o8s 
		
	//	IProgrammeService pc = (IProgrammeService) SpringUtil.getBean(IProgrammeService.class);
		IPublishService pbc = (IPublishService) SpringUtil.getBean("publishService");
		IEquipmentService eqc= (IEquipmentService) SpringUtil.getBean(IEquipmentService.class);
		Equipment e=	eqc.getEquipment(equipmentId);
	//	GvProgramme p=pc.getProgramme(prgrmID);
		
		
		GvPublish pb=new GvPublish();
		pb.setEquipmentId(equipmentId);
	//	pb.setPrgrm(p);
		pb.setProgarmmeId(prgrmID);
		pb.setPublishType(1);
		pb.setStatus(1);
		pb.setMome("ati"+ DateUtil.getDatetime());
		pbc.insert(pb,  e.getDepartId().toString( ));
//		GvCycleQueue	pbCyc  =new GvCycleQueue();
//		pbCyc.setRltRecId(Sys.pubVar.get().getPublishId().toString());
//		pbCyc.setRectype(new PrgrmNoticer().type);
//		List<GvCycleQueue> li=ListX.<GvCycleQueue>$().add(pbCyc).toLi();
//		  
//	 	new PrgrmNoticer().push_only(new GvCycleQueueSvs(), li);
//	 // filex.save(str_thdloc.get(), "c:\\t1.txt");
	  
	  System.out.println("--f");
		
	}

}

//  attilax 老哇的爪子