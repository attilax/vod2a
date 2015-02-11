/**
 * @author attilax 老哇的爪子
\t@since  Aug 24, 2014 2:53:07 AM$
 */
package com.focusx.elmt;

import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.ioc.IocX;
import com.attilax.ioc.guiceT;
import com.attilax.persistence.PX;
import com.attilax.spri.SpringUtil;
import com.attilax.time.timeUtil;
import com.attilax.util.DateUtil;
import com.focusx.playRec.baseDAO;
import com.focusx.programme.entity.GvProgrammeDetail;
import com.focusx.programme.service.IProgrammeService;
import com.google.inject.Inject;

import static com.attilax.core.*;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.*;
import java.net.*;
import java.io.*;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author attilax 老哇的爪子
 * @since Aug 24, 2014 2:53:07 AM$
 */
public class tMtrSvs {

	
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * {
		 
			{
				this.setPlaytime(888);
			}
		};
	@author attilax 老哇的爪子
	\t@since  Aug 24, 2014 8:03:40 AM$
	 */
	public void playTimeTest() {
		//delete test dt
		List<GvMaterial> li2 = getLi4tPlaytime();
		for (GvMaterial mtr : li2) {
			new baseDAO().delete(mtr);
		}

		//add a test dt
		GvMaterial o = new GvMaterial() ; o.setPlaytime(888);
		new baseDAO().save(o);
		
		//query and assert
		 
		List li = getLi4tPlaytime();
		// int expResult = 0;
		// int result = instance.add(x, y);
		core.log("---o8fa1 get size:"+li.size());
		assertTrue(li.size() == 1);
		// assertEquals(expResult, result);
	}

	private List<GvMaterial> getLi4tPlaytime() {
		Map m2 = Mapx.<String, Object> $().add("playtime_start",timeUtil.secs2str_SF(887) )
				.add("playtime_end", timeUtil.secs2str_SF(889)).toMap();
		List<GvMaterial> li2 = new GvMaterialSvs().findByPropertyss(m2);
		return li2;
	}
	@SuppressWarnings("all") @Test
	
	public void delTest()
	{
		GvMaterialSvs c =  IocX.getBean(GvMaterialSvs.class);
		GvMaterial m=new GvMaterial();
		m.setApplicationType(1);
		c.save(m);
		
		List<GvProgrammeDetail> listDetail = new ArrayList<GvProgrammeDetail>();
	 
		 
			GvProgrammeDetail gvProgrammeDetail = new GvProgrammeDetail();
			gvProgrammeDetail.setPlayOrder(1);
			gvProgrammeDetail.setMaterialId(m.getMaterialId());
			gvProgrammeDetail.setProgrammeId(1);
			listDetail.add(gvProgrammeDetail);
			c.px.save(gvProgrammeDetail);
	 
//		IProgrammeService ps=	(IProgrammeService) SpringUtil.getBean(IProgrammeService.class);
//		ps.i
	//	c.delete_ByID(m.getMaterialId());
		core.print_wzFmt(m);
		
	}

	/**
	 * @author attilax 老哇的爪子 \t@since Aug 24, 2014 2:53:07 AM$
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// attilax 老哇的爪子 2:53:07 AM Aug 24, 2014

//		{ new tMtrSvs(). playTimeTest();
//		}
	//	 new tMtrSvs().	delTest();
	//	tob9();
		new tMtrSvs().	queryTest();
		

	}
	@Inject
	PX px=IocX.getBean(PX.class);
		/**
		@author attilax 老哇的爪子
		 * @throws ParseException 
		@since   ob9 h_f_49
		 
		 */
	private   void queryTest() throws ParseException {
	//	CriteriaImpl(com.focusx.elmt.GvMaterial:this[][createTime>2014-10-08 00:00:01.0, createTime<2014-10-08 23:59:59.0, logicDel is null])

		Criteria c =px. getSession().createCriteria(GvMaterial.class);	
		
	
		c.add( Restrictions.gt("createTime",  timeUtil.getTimestamp("2014-12-07 00:00:01")));
		c.add( Restrictions.lt("createTime", timeUtil.getTimestamp("2014-12-07 23:59:59")));
		List li=c.list();
		System.out.println(c.toString());
		System.out.println(li.size());
		
	}

	// attilax 老哇的爪子 2:53:07 AM Aug 24, 2014

	private static void tob9() {
		for(int i=0;i<10000;i++)
		{
		Map mp=new HashMap();
//		mp.put("materialDescription","你");
//		mp.put("materialType",0);
//		mp.put("idsCheckVals", "68,67,");
 		mp.put("failureTime_start", "2014-11-25");
 		mp.put("failureTime_end", "2015-12-12");
 		mp.put("logicDel", 8);
//		mp.put("effectieTime", "1990-01-02 03:04:05");
// 		mp.put("playtime", "00:02:05");
// 		mp.put("materialId", 1);
		
		GvMaterialSvs c=new GvMaterialSvs();
		System.out.println(c.findByPropertys4expireMtrlMana(mp).size());
		System.out.println("--");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}

// attilax 老哇的爪子