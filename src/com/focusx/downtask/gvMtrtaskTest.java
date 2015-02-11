/**
 * @author attilax 老哇的爪子
	@since  o8r 1_50_0$
 */
package com.focusx.downtask;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollX;
import com.attilax.collection.CollectionUtils;
import com.attilax.db.DBX;
import com.attilax.ioc.IocX;
import com.attilax.time.timeUtil;
import com.focusx.playRec.baseDAO;
import com.focusx.programme.entity.GvProgrammeDetail;

import static com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
import net.sf.json.JsonConfig;
import org.junit.Test;
/**
 * @author attilax 老哇的爪子
 *@since o8r 1_50_0$
 */
// @test
@SuppressWarnings("all")
public class gvMtrtaskTest {

	/**
	 * @author attilax 老哇的爪子
	 * @since o8r 1_50_0
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		List li = new ArrayList() {
			{
				this.add(new GvProgrammeDetail() {
					{
						this.setMaterialId(12);
					}
				});
				this.add(new GvProgrammeDetail() {
					{
						this.setMaterialId(12);
					}
				});
			}

		};
		// attilax 老哇的爪子 1_50_0 o8r
		// testFilter();

		// o0k();
		final Set set_pdIDs = CollX.deDuli(li, new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				GvProgrammeDetail d = (GvProgrammeDetail) arg0;
				return d.getMaterialId();
			}
		});
		//
		GvProgrammeDetail d = new GvProgrammeDetail();
		d.setMaterialId(12);
		System.out.println((set_pdIDs.contains(d.getMaterialId())));
		// for deduli o0k

	}

	private static void o0k() {
		DBX dbx=IocX.getBean(DBX.class);
		   GvDownloadTask t=	(GvDownloadTask) dbx.findById(GvDownloadTask.class, 691);
		     
		      
		      t.setNotice_back(1);
		      t.setNoticeBackTim(timeUtil.getTimestamp());
		      dbx.merge_syn(t);
		      System.out.println("--f");
	}

	private static void testFilter() {
		GvDownloadTask t = new GvDownloadTaskSvs().findById(59);

		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		jsonConfig.setExcludes(new String[] { "lastSuccRetMsg", "sendMsg", "sendRetMsg" });
		// jsonConfig.setIgnoreDefaultExcludes(true); // 设置默认忽略
		System.out.println(core.toJsonStrO7(t, jsonConfig));
		;
		GvDownloadTaskSvs c = new GvDownloadTaskSvs();
		System.out.println(c.peek(3));
		String ids = CollectionUtils.getIDs(c.peek(3), "dsId");
		System.out.println(ids);
		// core.print_wzFmt( c.peek(3) );
	}

	@SuppressWarnings("all")
	@Test
	public void add() {
		GvDownloadTask t = new GvDownloadTask();
		t.setSendMsg("msggg");
		t.setSendMsgContext("txt");
		t.setSendMsgA("aaa");
		t.setDownloadStatus(1);
		baseDAO c = new baseDAO();
		c.merge(t);
	//	c.delete(t);

	}

	// attilax 老哇的爪子 1_50_0 o8r
}

// attilax 老哇的爪子