package com.focusx.push;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.api.Handler;
import com.attilax.api.HandlerChain;
import com.attilax.coll.ListX;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.GvCycleQueue;
import com.attilax.collection.GvCycleQueueSvs;
import com.attilax.collection.listUtil;
import com.attilax.db.ix;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.log.Level;
import com.attilax.log.logRec;
import com.attilax.noticer.jsonFldSerialIgone;
import com.attilax.spri.SpringUtil;
import com.attilax.time.jsonTimeFmtr;
import com.attilax.time.timeUtil;
import com.attilax.util.DateUtil;
import com.attilax.util.ZipStrUtil;
import com.focusx.ServiceLoctor4vod;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.downtask.GvDownloadTaskSvs;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.playRec.GvPlayRecordDAO;
import com.focusx.playRec.baseDAO;
import com.focusx.pojo.Equipment;
import com.focusx.programme.dao.impl.ProgrammeDaoImpl;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.service.IProgrammeService;
 
import com.focusx.publish.dao.Impl.PublishDaoImpl;
import com.focusx.publish.entity.GvPublish;
import com.focusx.publish.service.IPublishService;
import com.focusx.service.IEquipmentService;
import com.focusx.util.HbX4vod;
import com.focusx.util.Sys;

/**
 * @Component @Lazy(false)
 *  @author attilax 老哇的爪子
 * @since o7t 1_p_e$ */

 public class PrgrmNoticer implements Handler {
	public static Logger logger = Logger.getLogger("PrgrmNoticer");
	public  String type = "prgrm";
	@Autowired IPublishService PublishService;
	public static String heartbreakMsg;
	public static int run_incode=0;
	public static int tt;
	public static int inweb=0;
	public static int run=0;
	public static int inied=0;
	public static int secsSpan;
	public static int invokeItfsTimes=0;
	public static ThreadLocal<String> str_thdloc=new ThreadLocal<String>();
	public static ThreadLocal<String> sendmsg_thdloc=new ThreadLocal<String>();
	int fetchCount = 10;
	/** @category 推送prgrm
	 *  @Scheduled(fixedDelay = 15 * 1000)// per 5 sec
	 * @return */
	@SuppressWarnings("all")
	private void pushTask() {
		if(!"1".equals("2"))
			return;
		System.out.println("..22");

	int ldInt=	PrgrmNoticer.class.getClassLoader().hashCode();
		//boot cfg swtich
		 
		if (inweb == 1 && inied == 0 && new File("c:\\NoticerStart").exists()) {
			// logger.info("------TaskNoticer cfg dir NoticerStop is exist...");
			run = 1;
			inied = 1;
		}
		//test code switch,, def sure is 0
		if (run_incode == 1) run =1;
		//	else run = 0;
		// runtime switch 
		if (run == 0) {
			// logger.info("------TaskNoticer cfg run is 0...");
			return;
		}
		this.heartbreakMsg = DateUtil.getDatetime(new Date());
		logger.info("------PrgrmNoticer runing...");
		logger.info("------PrgrmNoticer runing...");
//		final GvCycleQueueSvs c = new GvCycleQueueSvs();
//	
//		List li = c.peek(ServiceLoctor4vod.getPrgrmfetchCount(), type);
//
//		push_only(c, li);
		// return null;
	}

	@SuppressWarnings("all") public void push_only(final GvCycleQueueSvs c, List li) {
		String ids=CollectionUtils.getIDs(li,"id");
		logger.info("--getList IDS:"+ids);
		CollectionUtils.each_safe(li, new com.attilax.Closure() {

			@Override public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子 m_0_41 o7s
				final GvCycleQueue tsk = (GvCycleQueue) arg0;
				try {
					if(tsk.getId()==16)
					{
						System.out.println();
					}
				} catch (Exception e) {
				 
				}
				
				GvPublish pb = null;
				try {					
					int pblshID = core.toInt(tsk.getRltRecId());
					IPublishService pc = (IPublishService) SpringUtil.getBean("publishService");
					pb = pc.getPublish(pblshID);
					c.moveToTail(tsk);
				} catch (Exception e) {
					logger.info(e);
				}

				new com.attilax.tryX<String>() {
					@Override public String $$(Object t) throws Exception {
						// attilax 老哇的爪子 k_39_c o85

						return null;
					}
				}.$("");
				final pushX p = new pushX();
				String msgAti = HandlerChain.addActNSecuryINfo(core.toJsonStrO7(pb,jsonFldSerialIgone.jsonConfig_simp4prgrm()), submeth.pushProgram);
				
				try {
					if(pb==null)throw new pubIsNullEx();
					invokeItfsTimes++;
					p.push(msgAti, pb.getEquipmentId());
					tsk.setSendRetMsg(p.respMsg);
str_thdloc.set(p.respMsg);
sendmsg_thdloc.set(p.sendMsg);
					tsk.setLastSuccRetMsg(p.respMsg);
					tsk.setLastSuccTime(timeUtil.getTimestamp());
					tsk.setSendMsg(p.sendMsg);
					new baseDAO().merge_syn(tsk);

				} catch (APIRequestException e) {
					logger.error(" --APIRequestException", e);
					logger.info("-------------------------------------split----------");
					String jsonStr_NF = core.toJsonStr_NF(e);
					logger.info(jsonStr_NF);
					tsk.setSendRetMsg(jsonStr_NF);
					tsk.setSendMsg(p.sendMsg);
					new baseDAO().merge_syn(tsk);

				} catch (APIConnectionException e) {
					tsk.setSendRetMsg("APIConnectionException");
					tsk.setSendMsg(p.sendMsg);
					new baseDAO().merge_syn(tsk);
				}catch(pubIsNullEx e)
				{
					tsk.setSendMsg("pubIsNullEx");
					new baseDAO().merge_syn(tsk);
				}
				tsk.setSendMsgA(msgAti);
				log2db(tsk);
				return null;

			}
		});
	}

	public static void main(String[] args) throws APIConnectionException, APIRequestException {
		
		tpush_pub(1917);
	//	tpushProgrm(77,1065);
		// tt();

	//	tPushSmpMsg();

		// GvCycleQueue o=new GvCycleQueue();
		// o.setRectype("aa");
		// new baseDAO().save(o);
	
		
		// prgrmNoticer.app(pb);
		// prgrmNoticer. pushTask();
		// new ix(9999).times(new Closure() {
		//
		// @Override public void execute(Object arg0) {
		// // attilax 老哇的爪子 m_6_v o7s
		//
		// core.sleep(5*3600*1000);
		// }
		// });
	}

	private void log2db(GvCycleQueue tsk) {
		// attilax 老哇的爪子 上午01:49:53 2014-9-2
		System.out.println("aaaaa");
		try {
			logRec o = new logRec();
			o.setLevel(Level.info);
			o.setIdx(Integer.parseInt(tsk.getRltRecId()));  //pubid
			//  o.setIdx2(tsk.getRltID2());
			// o.setIdx3(tsk.getRltID3());
			//o.setId(id)
			o.setCreateTime(timeUtil.getTimestamp());
			o.setLogger("prgrmPush");
			o.setMsg(tsk.getSendMsg());
			o.setMsgA(tsk.getSendMsgA());
			o.setSend("send");
			o.setThread("prgrmPushThd");
			o.setCate("prgrmPush");
			// this.dbx.save(o);
			new baseDAO().save(o);
		} catch (Exception e) {
			logger.info(e);
		}

	}
	
	
	private void log2db4recv(Object tsk) {
		// attilax 老哇的爪子  上午01:49:53   2014-9-2 
		System.out.println("aaaaa");
		
	
		try {
			logRec o=new logRec();
			 o.setLevel(Level.info);
			 
			 try{
				JSONObject ja=JSONObject.fromObject(tsk);
				int id=ja.getInt("dsId");  //publish id 
				o.setIdx(id);
			 }catch(Exception e){logger.info(e);}
		//	 o.setIdx2(tsk.getRltID2());
			// o.setIdx3(tsk.getRltID3());
			 o.setCreateTime(timeUtil.getTimestamp());
			 o.setLogger("prgrmRecv");
			 o.setMsg(tsk.toString());
			  o.setMsgA(tsk.toString());
			  o.setSend("recv");
			  o.setThread("prgrmRecv");
		 	//this.dbx.save(o);
				new baseDAO().save(o);
		} catch (Exception e) {
			logger.info(e);
		}
		
		 
	}
	
	/**
	@author attilax 老哇的爪子
		@since  o8s 1_v_j   
	
	 * @param prgrmID
	 */
	private static void tpushProgrm(int prgrmID,int equipmentId ) {
		// attilax 老哇的爪子  1_v_j   o8s 
		
		IProgrammeService pc = (IProgrammeService) SpringUtil.getBean(IProgrammeService.class);
		IPublishService pbc = (IPublishService) SpringUtil.getBean("publishService");
		IEquipmentService eqc= (IEquipmentService) SpringUtil.getBean(IEquipmentService.class);
		Equipment e=	eqc.getEquipment(equipmentId);
		GvProgramme p=pc.getProgramme(prgrmID);
		GvPublish pb=new GvPublish();
		pb.setEquipmentId(equipmentId);
		pb.setPrgrm(p);
		pb.setProgarmmeId(prgrmID);
		pb.setPublishType(1);
		pb.setStatus(1);
		pb.setMome("ati"+ DateUtil.getDatetime());
		pbc.insert(pb,  e.getDepartId().toString( ));
		GvCycleQueue	pbCyc  =new GvCycleQueue();
		pbCyc.setRltRecId(Sys.pubVar.get().getPublishId().toString());
		pbCyc.setRectype(new PrgrmNoticer().type);
		List<GvCycleQueue> li=ListX.<GvCycleQueue>$().add(pbCyc).toLi();
		  
	 	new PrgrmNoticer().push_only(new GvCycleQueueSvs(), li);
	  filex.save(str_thdloc.get(), "c:\\t1.txt");
	  try {
		filex.append("c:\\t1.txt", sendmsg_thdloc.get());
	} catch (IOException e1) {
		//  attilax 老哇的爪子 4_2_s   o8s   
		e1.printStackTrace();
	}
	  System.out.println("--f");
		
	}

	/**
	@author attilax 老哇的爪子
		@since  o8q 2_1_x   
	
	 * @param i
	 */
	private static void tpush_pub(int pubid) {
		// attilax 老哇的爪子  2_1_x   o8q 
		IPublishService pc = (IPublishService) SpringUtil.getBean("publishService");
		GvCycleQueue	pbCyc = (GvCycleQueue) new baseDAO().get(GvCycleQueue.class, 700);
 	List li=ListX.<GvCycleQueue>$().add(pbCyc).toLi();
  
 	new PrgrmNoticer().push_only(new GvCycleQueueSvs(), li);
		
	}

	private static void tPushSmpMsg() throws APIConnectionException, APIRequestException {
		final pushX p = new pushX();
		Object pb = new Object();
		String jsonStrO7 = core.toJsonStrO7(pb);
		jsonStrO7=filex.read("c:\\json2.txt");
		
	//
			 
			
		 
	//	logger.info("----zip aft len:"+String.valueOf( jsonStrO7.length()));
		String s = HandlerChain.addActNSecuryINfo(jsonStrO7, "pushPrgrm");		
		p.push(s, 1035);
	}

	public void t()
	{
		logger.info("---say halo");
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

		// config.registerJsonValueProcessor(List.class, new
		// JsonValueProcessor() {
		// // 参数1 ：属性名 参数2：json对象的值 参数3：jsonConfig对象
		// public Object processObjectValue(String arg0, Object arg1, JsonConfig
		// arg2) {
		//
		// return "..list..";
		// }
		// public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// return null;
		// }
		// });

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
					logger.info(e);
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

	/** @author attilax 老哇的爪子
	 * @since o85 j_d_y$
	 * 
	 * @param o */
	public void app(GvPublish o) {
		// attilax 老哇的爪子 j_d_y o85
		// new GvCycleQueueSvs().add(o);
		GvCycleQueue rec = new GvCycleQueue();
		rec.setRltRecId(o.getPublishId().toString());
		rec.setRectype(type);
		try {
			ServiceLoctor4vod.getDbx().save(rec);
		} catch (Exception e) {
			logger.info(e);
			new baseDAO().save(rec);
		}

	}

	/* (non-Javadoc)  for feed back
	 * @see com.attilax.api.Handler#handleReq(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o87 0_38_6$
	 */
	@Override public Object handleReq(Object arg) throws Exception {
		// attilax 老哇的爪子  0_38_6   o87 
		JSONObject ja=JSONObject.fromObject(arg);
		int id=ja.getInt("dsId");  //publish id 
		final GvCycleQueueSvs c =ServiceLoctor4vod.newGvCycleQueueSvs();
		c.remove(id);
	
		log2db4recv(arg);
	 
		return  "{\"errcode\":0,\"errmsg\":\"ok\" }";
		
	}

	/**
	@author attilax 老哇的爪子
	 * @param arg 
		@since  o8r i_0_9   
	
	 * @return
	 */
	public String postDownTaskBackfeed4noticeProcessor(Object arg) {
		// attilax 老哇的爪子  i_0_9   o8r 
		JSONObject ja=JSONObject.fromObject(arg);
		int id=ja.getInt("dsId");
		final GvCycleQueueSvs c = new GvCycleQueueSvs();
		c.moveToTail(id);
		return  "{\"errcode\":0,\"errmsg\":\"ok\" }";
		
	}

}
