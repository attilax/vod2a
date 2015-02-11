package com.focusx.push;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.Closure;
import org.apache.log4j.Logger;
import org.apache.tools.ant.types.selectors.DateSelector;
import org.hibernate.Session;
import org.junit.experimental.categories.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.attilax.core;
import com.attilax.api.HandlerChain;
import com.attilax.biz.Timerang.TimeRangRec;
import com.attilax.biz.Timerang.TimerangX;
import com.attilax.coll.ListX;
import com.attilax.collection.CollX;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.GvCycleQueueSvs;
import com.attilax.collection.listUtil;
import com.attilax.db.DBX;
import com.attilax.db.IDBX;
import com.attilax.db.ix;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.lang.CaseX;
import com.attilax.lang.IcaseitemImp;
import com.attilax.lang.Icondit;
import com.attilax.lang.Ifltr;
import com.attilax.log.Level;
import com.attilax.log.logRec;
import com.attilax.noticer.jsonFldSerialIgone;
import com.attilax.persistence.PX;
import com.attilax.push.basePushX;
import com.attilax.secury.MD5;
import com.attilax.secury.SnX;
import com.attilax.time.jsonTimeFmtr;
import com.attilax.time.timeUtil;
import com.attilax.util.DateUtil;
import com.attilax.util.god;
import com.attilax.util.start;
import com.focusx.ServiceLoctor4vod;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.downtask.GvDownloadTaskSvs;
import com.focusx.elmt.GvMaterial;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.playRec.GvPlayRecordDAO;
import com.focusx.playRec.baseDAO;
import com.focusx.programme.dao.impl.ProgrammeDaoImpl;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.publish.dao.Impl.PublishDaoImpl;
import com.focusx.publish.entity.GvPublish;
import com.focusx.util.HbX4vod;
import com.focusx.util.Sys;
import com.google.inject.Inject;

/**
 * @Component  @Lazy(false)
 * @author  attilax 老哇的爪子
 *@since  o7t 1_p_e$
 */
public class TaskNoticer extends basePushX {
	 public static String lastUpQueueRecExecInfo="..";
	public DBX dbx=ServiceLoctor4vod.getDbx();
	public static Logger logger = Logger.getLogger("TaskNoticer");
	public static int inweb=0;
	public static int run=0;
	public static int inied=0;
	private static int run_incode=0;
	public static String heartbreakMsg;
	public static int invokeItfsTimes=0;
	public int fetchCount = 50;
	 
	/** @category 推送下载任务
	   @Scheduled(fixedDelay =12 * 1000)// per 5 sec
	 * @return */
	 @SuppressWarnings("all")
	private void pushTask() {

		if (inweb == 1 && inied == 0 && new File("c:\\NoticerStart").exists()) {
			// logger.info("------TaskNoticer cfg dir NoticerStop is exist...");
				run = 1;
				inied = 1;
		}

		if (run_incode == 1) run = 1;
		if (run == 0) {
			// logger.info("------TaskNoticer cfg run is 0...");
			return;
		}
		this.heartbreakMsg = DateUtil.getDatetime(new Date());
		logger.info("------TaskNoticer heartbreakruning...");
		int thisID = this.hashCode();
		;
		Sys.downTaskNoticerId = thisID;
		core.log("----downTaskNoticerId:" + thisID);

		final GvDownloadTaskSvs c = new GvDownloadTaskSvs();
		
		//o99
	 if( ! lisenceCheckOK())
	 {
		 
	 }
		 
		
		List li = c.peek(ServiceLoctor4vod.getMtrfetchCount());
		//o0n 
	//	listUtil.join(li2)
		try {
			String ids=	CollX.joinIds(li, new com.attilax.Closure<GvDownloadTask, Integer>() {

				@Override
				public Integer execute(GvDownloadTask arg0) throws Exception {
					arg0.getDsId();
					return null;
				}
			});
		lastPeekTaskids=ids;
		
		} catch (Exception e) {
			core.err(e);
		}
	////
		push_only(li);
		// return null;
	}
	 
	 public static String lastPeekTaskids="";
/**
	@author attilax 老哇的爪子
		@since  o9b 2_r_5   
	
	 * @return
	 */
	private boolean lisenceCheckOK() {
		try {
			// attilax 老哇的爪子  2_r_5   o9b 
			 String MotherboardSN = MD5.getMD5(  SnX.getMotherboardSN().trim()).trim();
			  if(!MotherboardSN.equals(ServiceLoctor4vod.getSn().trim()))
			  {
					core.log("----MotherboardSN :" );
				  return false;
			  }
			return true;
		} catch (Exception e) {
			core.warn(e);
			return true;
		}
		
		
	}
@Inject
	 PX px;
	@SuppressWarnings("all") private void push_only( List li) {
		final GvDownloadTaskSvs c=new GvDownloadTaskSvs();
		logger.info("--o8q1 "+core.toJsonStrO88(li));
		String ids=CollectionUtils.getIDs(li,"dsId");
		logger.info("--getList IDS 4 mtr_noticer:"+ids);
		 
		CollectionUtils.each_safe(li, new com.attilax.Closure() {
			
		 

			@Override public Object execute(final Object arg0) throws Exception {
			
			
			 try {
				 return push_single(arg0);
			} catch (Exception e) {
				filex.save_safe(core.getTrace(e), "c:\\pushMtrlErr\\pushMtrlErr"+filex.getUUidName()+".txt");
				filex.save_safe(core.getTrace(e), "d:\\pushMtrlErr\\pushMtrlErr"+filex.getUUidName()+".txt");
				throw new RuntimeException(e);
			}
					 
				
			}	

		   
		});
	}
	
	
	private  Ifltr getFltr(){
	    	return new Ifltr() {

				@Override public boolean exec(Object o) {
					// attilax 老哇的爪子  m_j_z   o8s 
					return false;
					
				}};
	    }
	//join point
	private void goNext2(  Object arg0) {
		final GvDownloadTaskSvs c=new GvDownloadTaskSvs();
		c.removeNappend(arg0);
	}
	/**
	 * with downtime_limit
	@author attilax 老哇的爪子
		@since  o0k l_j_5   
	
	 * @param tsk9
	 * @return
	 */
	public Object push_single(final Object tsk9) {
		// attilax 老哇的爪子  l_j_5   o0k 
		 
		// attilax 老哇的爪子  m_0_41   o7s 
		final GvDownloadTask tsk2=(GvDownloadTask) tsk9;
		if(tsk2.getEquipmentId()==1107)
			System.out.println("dbg");
		if(tsk2.getDsId()==798)
			System.out.println("aa");
		//o99   sometime notitce back not tak eff,so add retry find tsk.
		final GvDownloadTask tsk=findTsk(tsk2);
		//oa3
		if (isTestStore(tsk)) {
			System.out.println("--");
		} else {
			// o09 downtime limit
			if (!new File("C:\\pushByTimeStop").exists()) {
				if (is_downtime_limit(tsk)) {
					// o0N add limit ret
					add_limit_ret(tsk);

					logDdowntimeLmt(tsk);

					return null;
				}
			}
		}
		
		
		/////
		//oa
	
		if(tsk.getNotice_back()==null)
			tsk.setNotice_back(0);
		
		return CaseX.$().add(new IcaseitemImp(tsk.getNotice_back()==1) {
			/** @category  flt notice baced */
			@Override public Object exec() {
				// attilax 老哇的爪子 j_d_4 o8s
				System.out.println(" case111");
				//core.log("---tsk.getNotice_back()==1");
				goNext2(  tsk9);	
			 
				return true;

			}

		}).add(new IcaseitemImp(tsk.getNotice_back()!=1) {
		

			/** @category exec no notice_baed msg */
			@Override public Object exec() {
				// attilax 老哇的爪子 j_d_7 o8s
				if(tsk.getDsId()==791)
					System.out.println("dbg");
				pushX p = new pushX();
				//set file
				try {
					GvMaterialSvs gc = new GvMaterialSvs();
					GvMaterial mtr = gc.findById(tsk.getMaterialId());
					tsk.setFile(mtr.getFilePath());
					// BeanUtils.copyProperties(tsk, mtr);
					// tsk.setPlaytime(mtr.getPlaytime());
					core.copyPropertiesO8q(tsk, mtr);

					// core.obj2map(o)
				} catch (Exception e) {
					logger.info(e.getMessage(),e);
				}
				//oaa  set mtrl size
				try {
					setMtrlSize(tsk);
				} catch (FileNotExist e1) {
					//  Throwable e1;
					tsk.setSendRetMsg(e1.getMessage());
					 tsk.setDownloadStatus(1); //except from queue
					 tsk.setTrueDownFlag(FailState.FileNotExist);
					 
						setPushRzt(tsk9, tsk, "", "");			
						return "";
				} //checkMtrlFileExist(tsk);
				//obb 
				
				String msgAti = HandlerChain.addActNSecuryINfo(core.toJsonStrO7(tsk, jsonFldSerialIgone.jsonConfig_timefmtNinoneRetmsg()), "pushDownTask");
				
				try {
					invokeItfsTimes++;
					tsk.setSendRetMsg("..");
					if(tsk.getDsId()!=null && tsk.getDsId()!=0)
					p.push(msgAti, tsk.getEquipmentId());
				
			     	tsk.setSendMsgA(msgAti);
					tsk.setSendRetMsg(p.respMsg);
					tsk.setLastSuccRetMsg(p.respMsg);
					tsk.setLastSuccTime(timeUtil.getTimestamp());
				 
					
					 
					
					
				} catch (APIRequestException e) {
					logger.error(" --APIRequestException", e);
					logger.info("-------------------------------------split----------");
					String jsonStr_NF = core.toJsonStr_NF(e);
				//	core.log(jsonStr_NF);
					tsk.setSendRetMsg(jsonStr_NF);
					tsk.setSendMsgA(msgAti);
				//	new baseDAO().merge(tsk);
					System.out.println("");
				
				} catch (APIConnectionException e) {
					tsk.setSendRetMsg("APIConnectionException");
					tsk.setSendMsgA(msgAti);
				 
				//	new baseDAO().merge(tsk);
				}

				//saveSendMsg
				String sendMsg = p.sendMsg;
				setPushRzt(tsk9, tsk, sendMsg, msgAti);			
				return "";

			}

			

		

		}).start().toString();
		
	}
			/**
		@author attilax 老哇的爪子
		@since   obe 9_59_46
		 
		 */
	protected void checkMtrlFileExist(GvDownloadTask tsk) {
		// TODO Auto-generated method stub
		
	}
	public void setPushRzt(final Object tsk9,
			final GvDownloadTask tsk, String sendMsg, String msgAti) {
	
		tsk.setSendMsg(sendMsg);
		tsk.setSendMsgA(msgAti);
		 	log2db(tsk);
		try{
			dbx.merge_syn(tsk);
			lastUpQueueRecExecInfo="ok..";
		}catch(Exception e)
		{
			lastUpQueueRecExecInfo=god.getTrace(e);
		}
	
		//join point
		goNext2(  tsk9);
	}

		/**
		@author attilax 老哇的爪子
		 * @throws FileNotExist 
		@since   oaa c_53_w
		 
		 */
	public void setMtrlSize(GvDownloadTask tsk) throws FileNotExist {
		try {
			if (tsk.getDsId() == 2716)
				System.out.println("dbg");
			GvMaterial mtrl = tsk.getMtrl();
			String file = pathx.webAppPath() + "\\" + mtrl.getFilePath();
			filex.saveLog("file:"+file, "c:\\getfilesize" );
			if (!new File(file).exists())
				throw new FileNotExist("mtrl:" + mtrl.getFilePath());
			mtrl.setSize(String.valueOf(filex.getSize(file)));
			tsk.setSize(filex.getSize(file));
			tsk.setTimRang(String.valueOf(filex.getSize(file)));
		} catch (Exception e) {
			if (e instanceof FileNotExist) {
				FileNotExist e2 = (FileNotExist) e;
				throw e2;
			}
			core.err(e);
			filex.saveLog(e, "c:\\getfilesizeE", "gbk");
		}

	}


	@SuppressWarnings("rawtypes")
	/**
	 * 
	 * @param tsk
	 * @return
	 */
	public boolean isTestStore(GvDownloadTask tsk) {
		try {
			System.out.println("a");
			String file =pathx.webAppPath()+"\\0cfg\\testStoreEq.txt";
			String t = filex.read_SF(file, "");

			Set st = listUtil.toSet(t, ",");
			String eqid = tsk.getEquipmentId().toString();
			if (st.contains(eqid))
				return true;
			return false;
		} catch (Exception e) {
			core.err(e);
			return true;
		}

	}

	/**
	 * for test push  with log spt ... no time limit ..
	 * @param arg0
	 * @return
	 */
	public Object push_single_core(final Object arg0) {

		try {

			// attilax 老哇的爪子 l_j_5 o0k

			// attilax 老哇的爪子 m_0_41 o7s
			final GvDownloadTask tsk2 = (GvDownloadTask) arg0;
			if (tsk2.getDsId() == 798)
				System.out.println("aa");
			// o99 sometime notitce back not tak eff,so add retry find tsk.
			final GvDownloadTask tsk = findTsk(tsk2);

			// ///

			if (tsk.getNotice_back() == null)
				tsk.setNotice_back(0);

			/** @category exec no notice_baed msg */

			String rzt; // o0L
			// attilax 老哇的爪子 j_d_7 o8s
			if (tsk.getDsId() == 791)
				System.out.println("dbg");
			pushX p = new pushX();
			try {
				GvMaterialSvs gc = new GvMaterialSvs();
				GvMaterial mtr = gc.findById(tsk.getMaterialId());
				tsk.setFile(mtr.getFilePath());
				// BeanUtils.copyProperties(tsk, mtr);
				// tsk.setPlaytime(mtr.getPlaytime());
				core.copyPropertiesO8q(tsk, mtr);

				// core.obj2map(o)
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
			String msgAti = HandlerChain.addActNSecuryINfo(core.toJsonStrO7(
					tsk, jsonFldSerialIgone.jsonConfig_timefmtNinoneRetmsg()),
					"pushDownTask");

			try {
				invokeItfsTimes++;
				tsk.setSendRetMsg("..");

				p.push(msgAti, tsk.getEquipmentId());

				tsk.setSendMsgA(msgAti);
				tsk.setSendRetMsg(p.respMsg);
				tsk.setLastSuccRetMsg(p.respMsg);
				tsk.setLastSuccTime(timeUtil.getTimestamp());

				rzt = p.respMsg;

			} catch (APIRequestException e) {
				logger.error(" --APIRequestException", e);
				logger.info("-------------------------------------split----------");
				String jsonStr_NF = core.toJsonStr_NF(e);
				rzt = jsonStr_NF;
				// core.log(jsonStr_NF);
				tsk.setSendRetMsg(jsonStr_NF);
				tsk.setSendMsgA(msgAti);
				// new baseDAO().merge(tsk);
				System.out.println("");

			} catch (APIConnectionException e) {
				tsk.setSendRetMsg("APIConnectionException");
				tsk.setSendMsgA(msgAti);
				rzt = core.getTrace(e);
				// new baseDAO().merge(tsk);
			}

			// saveSendMsg
			tsk.setSendMsg(p.sendMsg);
			tsk.setSendMsgA(msgAti);
			log2db(tsk);
			try {
				dbx.merge_syn(tsk);
				lastUpQueueRecExecInfo = "ok..";
			} catch (Exception e) {
				lastUpQueueRecExecInfo = god.getTrace(e);
			}

			// join point
			return rzt;

		} catch (Exception e) {
			return core.getTrace(e);
		}

	}
	
	
	private void add_limit_ret(GvDownloadTask tsk) {
		try {
			tsk.setSendRetMsg("limit_time");
			dbx.merge_syn(tsk);
		
		} catch (Exception e) {
			lastUpQueueRecExecInfo="limit time...";
			core.err(e);
			}
	
	}
	/**
	@author attilax 老哇的爪子
		@since  o00 j_0_58   
	
	 * @param tsk
	 */
	protected void logDdowntimeLmt(GvDownloadTask tsk) {
		// attilax 老哇的爪子  j_0_58   o00 
		try {
			logRec o=new logRec();
			 o.setLevel(Level.info);
			 o.setIdx(tsk.getRltID());
			 o.setIdx2(tsk.getRltID2());
			 o.setIdx3(tsk.getRltID3());
			 o.setIdx3(tsk.getRltID3());
			 o.setQueId(tsk.getDsId());
			 o.setCreateTime(timeUtil.getTimestamp());
			 String cate = "downtimeLmt";
			o.setLogger(cate);
			 o.setMsg(tsk.getSendMsg());
			  o.setMsgA(tsk.getSendMsgA());
			  o.setSend("");
			  o.setRet(tsk.getSendRetMsg());
			  o.setThread(cate);
			  o.setCate(cate);
		 	//this.dbx.save(o);
				new baseDAO().save(o);
		} catch (Exception e) {
			//core.log(e);
		}
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o00 i_9_7   
	
	 * @param tsk
	 * @return
	 */
	@SuppressWarnings("all")
	public boolean is_downtime_limit(GvDownloadTask noUse) {
		// attilax 老哇的爪子 i_9_7 o00
		
	//	if (tsk.getPub().getPublishType() == 2) return false;
		// normal
		try {
			
			List li = this.px.findByHql(" from TimeRangRec  order by id desc ");
			TimeRangRec trr = (TimeRangRec) li.get(0);
			long Starttime;
			try {
				Starttime = timeUtil.str2secs(trr.getStartTime());
			} catch (ParseException e) {
				// attilax 老哇的爪子 j_8_40 o00
				e.printStackTrace();
				Starttime = 0;

			}
			long endTime;
			try {
				endTime = timeUtil.str2secs(trr.getEndTime());
			} catch (ParseException e) {
				// attilax 老哇的爪子 j_8_54 o00
				e.printStackTrace();
				endTime = timeUtil.str2secs_RE("23:59:59");
			}
			
			long nowTime_secOnlyFmt = timeUtil.getNow_timeonly_secFmt_CST();
			if(TimerangX.isInRang(Starttime,endTime,nowTime_secOnlyFmt ) )
				return false;
			filex.save_SF(String.valueOf(Starttime)+" endtime:"+String.valueOf(endTime)+" now:"+String.valueOf(nowTime_secOnlyFmt), "c:\\timelimitDbg\\oaa2.txt");
			// def
			return true;
		} catch (Exception e) {
			filex.save_SF(core.getTrace(e), "c:\\timelimitDbg\\oaa1.txt");
			logger.info(e.getMessage(),e);
			return false;
		}

	}
	/**
	@author attilax 老哇的爪子
		@since  o00 j_45_5   
	
	 * @param starttime
	 * @param endTime
	 * @return
	 */
	private boolean BigHeadStart(long starttime, long endTime) {
		// attilax 老哇的爪子  j_45_5   o00 
		return false;
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o00 j_44_z   
	
	 * @param starttime
	 * @param endTime
	 * @return
	 */
	private boolean LittleHeadStart(long starttime, long endTime) {
		// attilax 老哇的爪子  j_44_z   o00 
		return false;
		
	}
	/**
	 * 	//o99   sometime notitce back not tak eff,so add retry find tsk.
	 * and 4 test
	@author attilax 老哇的爪子
		@since  o90 i_42_44   
	
	 * @param tsk
	 * @return
	 */
	private GvDownloadTask findTsk(GvDownloadTask tsk) {
		// attilax 老哇的爪子  i_s_40   o90 
		try {
			if(this.px==null)
				px=IocX.getBean(PX.class);
			GvDownloadTask t=(GvDownloadTask) px.get(GvDownloadTask.class, tsk.getDsId());
			return t;
		} catch (Exception e) {
		 core.log(e);
		 return tsk;
		}
		
		
	}
	public static void main(String[] args) {
	//	tt();
		TaskNoticer.run_incode=1;
		 GvDownloadTask t=new GvDownloadTask();
		 t.setDsId(888);
		 t.setEquipmentId(1065);
		 t.setMaterialId(1);
 	new TaskNoticer().push_only(ListX.<GvDownloadTask>$().add(t).			toLi());
//		 GvDownloadTaskSvs c=new GvDownloadTaskSvs();
//		 List li=c.findByIds("10,0");
//		 new TaskNoticer().push_only(li);
		  
	
		//pushTask();
//		new ix(9999).times(new Closure() {
//			
//			@Override public void execute(Object arg0) {
//				// attilax 老哇的爪子  m_6_v   o7s 
//		
//			 core.sleep(5*3600*1000);
//			}
//		});
	}
		/**
	@author attilax 老哇的爪子
	\t@since  Aug 28, 2014 8:14:11 AM$
	
	 */
	private static void addTest9data() {
		// attilax 老哇的爪子  8:14:11 AM   Aug 28, 2014 
		
		 new ix(9).times(new Closure() {
			
			@Override
			public void execute(Object arg0) {
				// attilax 老哇的爪子  8:14:47 AM   Aug 28, 2014 
				int n=(Integer) arg0;
				GvDownloadTask t=new GvDownloadTask();
				t.setMaterialId(n);
				new baseDAO().save(t);;
				
			}
		});
		
		
	}
 

	private static void tt() {
		Session sess = HbX4vod.getSession();
		PublishDaoImpl c = new PublishDaoImpl();
		c.setSessionFactory(HbX4vod.getSessionFactory());
		sess = c.getSession();
		sess.beginTransaction();
		GvPublish o = c.getPublish(1090);
		o.setPrgrm(o.getPrgrm());
		// String describe = o.getPrgrm().getDescribe();
		// System.out.println(describe);
		// core.print(o);
		System.out.println(CycleDetectionStrategy.LENIENT);

		// 先过滤对set集合的拆解
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg1.equals("prgrm22")) {
					return true;
				} else {
					return false;
				}
			}

		});
		// 将数据转换成Json数据

		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件

		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略

		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); // 此处是亮点，不过经过测试

		// config.registerJsonValueProcessor(Date.class, new
		// JsonValueProcessor() {
		// // 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
		// public Object processObjectValue(String arg0, Object arg1, JsonConfig
		// arg2) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date d = (Date) arg1;
		// return sdf.format(d);
		// }
		// public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// return null;
		// }
		// });

		config.registerJsonValueProcessor(Timestamp.class, new JsonValueProcessor() {
			// 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
			public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Timestamp stp = (Timestamp) arg1;
					Date d = (Date) stp;
					return sdf.format(d);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return "..";
			}
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return null;
			}
		});

//		config.registerJsonValueProcessor(List.class, new JsonValueProcessor() {
//			// 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
//			public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
//
//				return "..list..";
//			}
//			public Object processArrayValue(Object arg0, JsonConfig arg1) {
//				return null;
//			}
//		});

		config.registerJsonValueProcessor(GvPlayRecordDAO.class, new JsonValueProcessor() {
			// 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
			public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					GvProgramme stp = (GvProgramme) arg1;
					GvProgramme o = new GvProgramme();
					BeanUtils.copyProperties(o, stp);
					o.setProgarmmeId(stp.getProgarmmeId());
					o.setDescribe(stp.getDescribe());

					JsonConfig config2 = new JsonConfig();
					config2.setJsonPropertyFilter(new PropertyFilter() {
						@Override public boolean apply(Object arg0, String arg1, Object arg2) {
							if (arg1.equals("list")) {
								return true;
							} else {
								return false;
							}
						}

					});
					// JSONObject jsonObject = JSONObject.fromObject(stp);
					// return jsonObject.toString();
					return core.toJsonStrO7(o);
				} catch (Exception e) {
					//core.log(e);
				}
				return null;
			}
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return null;
			}
		});

		JSONObject jsonObject = JSONObject.fromObject(o, config);
		System.out.println(jsonObject.toString(2));
		// genejson();
	}
	private static void genejson() {
		Session sess = HbX4vod.getSession();
		ProgrammeDaoImpl c = new ProgrammeDaoImpl();
		c.setSessionFactory(HbX4vod.getSessionFactory());
		sess = c.getSession();
		sess.beginTransaction();
		System.out.println(c.getProgrammeList(null, new HashMap()).size());
		;
	}

	/**
	@author attilax 老哇的爪子
	 * @param arg 
		@since  o8r i_0_9   
	
	 * @return
	 */
	public String postDownTaskBackfeed4noticeProcessor(Object arg) {
		// attilax 老哇的爪子  i_0_9   o8r 
		System.out.println("sss");
		JSONObject ja=JSONObject.fromObject(arg);
		int id=ja.getInt("dsId");
	 
	      GvDownloadTask t=	(GvDownloadTask) this.dbx.findById(GvDownloadTask.class, id);
	      if(t==null)
	    	  throw new RuntimeException("cant find rec by dsid:"+String.valueOf(id));
	      
	      t.setNotice_back(1);
	      t.setNoticeBackTim(timeUtil.getTimestamp());
	      dbx.merge_syn(t);
	      
	      log2db4DownTaskBackfeed4notice(t);
		return  "{\"errcode\":0,\"errmsg\":\"ok\" }";
		
	}
	
	
	private void log2db4DownTaskBackfeed4notice(GvDownloadTask tsk) {
		// attilax 老哇的爪子  上午01:49:53   2014-9-2 
		System.out.println("aaaaa");
		try {
			logRec o=new logRec();
			 o.setLevel(Level.info);
			 o.setIdx(tsk.getRltID());
			 o.setIdx2(tsk.getRltID2());
			 o.setIdx3(tsk.getRltID3());
			 o.setCreateTime(timeUtil.getTimestamp());
			 o.setLogger("mtrrecv");
			 o.setMsg(tsk.getSendMsg());
			  o.setMsgA(tsk.getSendMsgA());
			  o.setSend("recv");
			  o.setThread("mtrrecv");
			  o.setCate("mtrRecvBack");
		 	//this.dbx.save(o);
				new baseDAO().save(o);
		} catch (Exception e) {
			//core.log(e);
		}
		
		 
	}
	public void log2db(GvDownloadTask tsk) {
		// attilax 老哇的爪子  上午01:49:53   2014-9-2 
		System.out.println("aaaaa");
		try {
			logRec o=new logRec();
			 o.setLevel(Level.info);
			 o.setIdx(tsk.getRltID());
			 o.setIdx2(tsk.getRltID2());
			 o.setIdx3(tsk.getRltID3());
			 o.setIdx3(tsk.getRltID3());
			 o.setQueId(tsk.getDsId());
			 o.setCreateTime(timeUtil.getTimestamp());
			 o.setLogger("mtrpush");
			 o.setMsg(tsk.getSendMsg());
			  o.setMsgA(tsk.getSendMsgA());
			  o.setSend("send");
			  o.setRet(tsk.getSendRetMsg());
			  o.setThread("mtrpush");
		 	//this.dbx.save(o);
				new baseDAO().save(o);
		} catch (Exception e) {
			//core.log(e);
		}
		
		 
	}


		/**
		@author attilax 老哇的爪子
		@since   oa7 f_g_u
		 
		 */
	public Object startDownNoticeProcessor(Object arg) {
		System.out.println("sss");
		JSONObject ja=JSONObject.fromObject(arg);
		int id=ja.getInt("dsId");
	 
	      GvDownloadTask t=	(GvDownloadTask) this.dbx.findById(GvDownloadTask.class, id);
	      if(t==null)
	    	  throw new RuntimeException("cant find rec by dsid:"+String.valueOf(id));
	      t.setDownloadStatus(null);
	      t.setStartdownFlag(1);
	      t.setTrueDownFlag(null);
	  //    t.setNoticeBackTim(timeUtil.getTimestamp());
	      dbx.merge_syn(t);
	      
	  //    log2db4DownTaskBackfeed4notice(t);
		return  "{\"errcode\":0,\"errmsg\":\"ok\" }";
	}

}
