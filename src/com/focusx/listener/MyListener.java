package com.focusx.listener;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONObject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.attilax.Closure;
import com.attilax.ClosureNoExcpt;
import com.attilax.core;
import com.attilax.api.Handler;
import com.attilax.api.HandlerChain;
import com.attilax.coll.ListX;
import com.attilax.collection.CollX;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.GvCycleQueue;
import com.attilax.collection.GvCycleQueueSvs;
import com.attilax.collection.listUtil;
import com.attilax.db.BaseHibernateDAO;
import com.attilax.db.DBX;
import com.attilax.db.dbLockKiller;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.log.Level;
import com.attilax.log.logRec;
import com.attilax.secury.licenseWatchDog;
import com.attilax.time.timeUtil;
import com.attilax.timer.timerTask;
import com.attilax.util.god;
import com.attilax.web.UrlX;
import com.focusx.ServiceLoctor4vod;
import com.focusx.dao.ITaskDao;
import com.focusx.dao.impl.EquipmentDAOImpl;
import com.focusx.downRec.GvDownloadRecordSvs;
import com.focusx.downtask.GvDownloadTaskSvs;
import com.focusx.job.CheckTaskJober;
import com.focusx.playRec.GvPlayRecordSvs;
import com.focusx.playRec.baseDAO;
import com.focusx.push.PrgrmNoticer;
import com.focusx.push.TaskNoticer;
import com.focusx.util.HbX4vod;
import com.focusx.util.HibernateSessionFactory;
import com.focusx.util.watchdog4vod;
import com.google.inject.Inject;

/** 监听器，获取token，启用线程定时执行，初始化数据    监听Web应用的生命周期
 * com.focusx.listener.MyListener
 * */
public class MyListener implements ServletContextListener {
	public static int run=1;
	public static ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(2);

	public void contextDestroyed(ServletContextEvent arg0) {

	}
	//public static int run=1;
	
	public static void main(String[] args) {
		//new MyListener().injectApiHandler_sf();
		Map m=new HashMap();
		m.put("aa", "vv");m.put("bb", "bbv");
		Set st=m.keySet();
	String s=    CollX.joinOa9(st, ",");
	System.out.println(s);System.out.println("--");
	final GvCycleQueueSvs c =ServiceLoctor4vod.newGvCycleQueueSvs();
	
	//List li=c.findByHql("from GvCycleQueue as model where model.del=1 and model.rectype='prgrm' order by model.id desc " , 10);
	//core.print_wzFmt(li);
	MyListener ml=new MyListener();
	ml.setPrgrmResetDelFlag();
	
	
		
	}
	// 初始化程序，容器启动后自动执行
	@SuppressWarnings("unused") public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("---");
	 
			injectApiHandler_sf();
		 
		core.sleep(7000);  
		if(new File("C:\\listerStop").exists())
		{
			core.log("---o8j219: lister stop");
			run=0;
			return;
			}
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext-*.xml");

		try {
			injectApiHandler();
			core.log("--o789 injectApiHandler");
		} catch (Exception e) {
			core.log(e);
		}
		
		////   i_j_51 o7m  老哇的爪子  Attilax
 
			licenseWatchDog.start(new Closure() {
				
				public Object execute(Object arg0) {
					// attilax 老哇的爪子  i_k_51   o7m 
				//	int nowNum=new EquipmentDAOImpl(). getCountO7(HbX4vod.getSession();
					new watchdog4vod().start();
					return null;
					
					
				}
			});
			 
		

	try{
		ITaskDao taskDao = (ITaskDao) ac.getBean("taskDao");
		// 每5秒，检查一次任务表
		CheckTaskJober ctJober = new CheckTaskJober(taskDao);
		// schedulePool.scheduleWithFixedDelay(ctJober,0, 5, TimeUnit.SECONDS);
	//	schedulePool.scheduleWithFixedDelay(command, 0, delay, unit)
	//	schedulePool.s
		
		BaseHibernateDAO.hbntSessFktr=new HibernateSessionFactory();
	}catch(Exception e){core.log(e); }
		itfsHistoryX.start();
		
	
		
		setPUshxInwebState();
		
		startDblockkill();
		
	//	setPrgrmResetDelFlag();

	}
	@Inject
	
	public DBX dbx=new DBX();
	/**
	 * 
	 */
	public void setPrgrmResetDelFlag() {
		try{
			dbx=IocX.getBean(DBX.class);
			 Executors.newScheduledThreadPool(2).scheduleWithFixedDelay(new Runnable() {
				
				@Override public void run() {
					// attilax 老哇的爪子  l_y_z   o92 
					final GvCycleQueueSvs c =ServiceLoctor4vod.newGvCycleQueueSvs();
					List li=c.findByHql("from GvCycleQueue as model where model.del=1 and model.rectype='prgrm' order by model.id desc " , 10);
					CollX.each_safe(li, new Closure<GvCycleQueue, Object>() {

						@Override
						public Object execute(GvCycleQueue arg0)
								throws Exception {
							if(arg0.getSendRetMsg()==null)
							{
								arg0.setDel(0);
								dbx.merge_syn(arg0);
								filex.save_SF("setDel=0, queue idL:"+String.valueOf(arg0.getId()), "c:\\setDelFlag4prgrm\\"+filex.getUUidName()+".txt");
							}
							return null;
						}
						
					});
				}
			},0, 10, TimeUnit.SECONDS);
		} catch (Exception e) {
			core.log(e);
		}
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o09 g_52_r   
	
	 */
	private void injectApiHandler_sf() {
		// attilax 老哇的爪子  g_52_r   o09 
		try {
			injectApiHandler();
			core.log("--oa9 injectApiHandler");
		} catch (Exception e) {
			core.log(e);
		}
	}
	/**
	@author attilax 老哇的爪子
		@since  2014-9-2 下午06:01:42   
	
	 */
	private void setPUshxInwebState() {
		// attilax 老哇的爪子  下午06:01:42   2014-9-2 
		try {
			timerTask.startAtask(new Closure(){

				@Override
				public Object execute(Object arg0) throws Exception {
					// attilax 老哇的爪子  上午01:37:51   2014-9-2 
					PrgrmNoticer.inweb=1;
					TaskNoticer.inweb=1;
					return null;
					
				}},"--setPUshxInwebState thrd--",7000);
		} catch (Exception e) {
			core.log(e);
		}
		
		try{
		 Executors.newScheduledThreadPool(2).scheduleWithFixedDelay(new Runnable() {
			
			@Override public void run() {
				// attilax 老哇的爪子  l_y_z   o92 
				
				PrgrmNoticer.inweb=1;
				TaskNoticer.inweb=1;
			}
		},0, 10, TimeUnit.SECONDS);
	} catch (Exception e) {
		core.log(e);
	}
	}
	/**
	@author attilax 老哇的爪子
		@since  2014-9-2 上午01:25:34   
	
	 */
	private void startDblockkill() {
		// attilax 老哇的爪子  上午01:25:34   2014-9-2 
		
		timerTask.startAtask(new Closure(){

			@Override
			public Object execute(Object arg0) throws Exception {
				// attilax 老哇的爪子  上午01:37:51   2014-9-2 
				 dbLockKiller k=ServiceLoctor4vod.getDbLockKiller();
				 k.start();
				return null;
				
			}},"--startDblockkill thrd--");
		
	}
	/** @author attilax 老哇的爪子
	 * @since o7l i50g$ */
	@SuppressWarnings("all") public void injectApiHandler() {
		// attilax 老哇的爪子 i50g o7l
		HandlerChain.sess = HbX4vod.getSession();
		HandlerChain.reg("postPlayRec", new Handler() {

			@Override public Object handleReq(Object arg) throws Exception {
				// attilax 老哇的爪子 is6 o7l

				filex.save_SF(String.valueOf(arg), "c:\\postPlayRecDir\\postPlayRec"+filex.getUUidName()+".txt");
				try {
					logRec o=new logRec();
					 o.setLevel(Level.info);
				//	 o.setIdx( gvdr.getDrId());
//					 o.setIdx2(tsk.getRltID2());
//					 o.setIdx3(tsk.getRltID3());
					 o.setCreateTime(timeUtil.getTimestamp());
					 o.setLogger("postPlayRec");
					 o.setMsg(String.valueOf(arg) );
					  o.setMsgA(String.valueOf(arg));
					//  o.setMsg2(String.valueOf(arg));
					  o.setSend("");
					  o.setThread("postPlayRec");
					  o.setCate("postPlayRec");
				 	//this.dbx.save(o);
						new baseDAO().save(o);
				} catch (Exception e) {
					core.log(e);
				}
				return GvPlayRecordSvs.postPlayRec(arg);

			}
		});

		HandlerChain.reg(cmd_vod.postDownTaskBackfeed, new GvDownloadTaskSvs());
	//	HandlerChain.reg(cmd_vod.pushProgramBackfeed, new PrgrmNoticer());
		HandlerChain.reg(cmd_vod.postDownTaskBackfeed4notice, new Handler() {

			@Override public Object handleReq(Object arg) throws Exception {
				// attilax 老哇的爪子 is6 o7l
				return	new TaskNoticer().postDownTaskBackfeed4noticeProcessor(arg);
			//	return GvPlayRecordSvs.postPlayRec(arg);

			}
		});
		
		HandlerChain.reg(cmd_vod.startDownNotice, new Handler() {

			@Override public Object handleReq(Object arg) throws Exception {
				// attilax 老哇的爪子 is6 o7l
				return	new TaskNoticer().startDownNoticeProcessor(arg);
			//	return GvPlayRecordSvs.postPlayRec(arg);

			}
		});
		
		HandlerChain.reg(cmd_vod.delFileFeedback, new Handler() {

			@Override public Object handleReq(Object arg) throws Exception {
				// attilax 老哇的爪子 is6 o7l
			//	return	new TaskNoticer().startDownNoticeProcessor(arg);
				return new  GvDownloadRecordSvs().delFileFeedback(arg);
			//	return GvPlayRecordSvs.postPlayRec(arg);

			}
		});
		
		HandlerChain.reg(cmd_vod.getFileSize, new Handler() {

			@Override public Object handleReq(Object url) throws Exception {
				try {
					JSONObject jo=new JSONObject();
					jo.put("rzt", String.valueOf( UrlX.getFileSize( url.toString())));
					  return  jo.toString();
				} catch (Exception e) {
					JSONObject jo=new JSONObject();
					jo.put("rzt",core.toJsonStrO88(e));
					return  jo.toString();
				}
				// attilax 老哇的爪子 is6 o7l
				
			//	return GvPlayRecordSvs.postPlayRec(arg);

			}
		});
		
		

	}

}
