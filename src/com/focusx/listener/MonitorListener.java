package com.focusx.listener;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.api.Handler;
import com.attilax.api.HandlerChain;
import com.attilax.io.filex;
import com.attilax.secury.licenseWatchDog;
import com.focusx.dao.IEquipmentMonitorfDAO;
import com.focusx.dao.ITaskDao;
import com.focusx.job.CheckTaskJober;
import com.focusx.job.UpdateMonitorJober;
import com.focusx.playRec.GvPlayRecordSvs;
import com.focusx.service.IEquipmentMonitorfService;
import com.focusx.util.watchdog4vod;

/** 监听器，获取token，启用线程定时执行，初始化数据 */
public class MonitorListener implements ServletContextListener {

	public static ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(2);
	public static int inied=0;
	public static int run=1;
	public static int start=0;
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override public void contextInitialized(ServletContextEvent sce) {
		System.out.println("---MonitorListener ini ");
		core.log("---MonitorListener ini o9k");
		core.sleep(10000); //   4 dbg o91
		if (new File("C:\\listerStop").exists() && inied == 0) {
			core.log("---o8j220: MonitorListener stop");
			inied = 1;
			return;

		}
		
//
//		if (inweb == 1 && inied == 0 && new File("c:\\NoticerStart").exists()) {
//		// logger.info("------TaskNoticer cfg dir NoticerStop is exist...");
//			run = 1;
//			inied = 1;
//	}
		try {
			start=1;
			core.log("---MonitorListener ini o9k1");
			String files = "classpath:applicationContext-*.xml";
			String[] locations = filex.getFiles_Spring(files);
			ApplicationContext ac = new FileSystemXmlApplicationContext(locations);
			// IEquipmentMonitorfDAO monitorfDAO = (IEquipmentMonitorfDAO)
			// ac.getBean("emService");
			IEquipmentMonitorfService monitorfService = (IEquipmentMonitorfService) ac.getBean("emService");
			UpdateMonitorJober monitorJober = new UpdateMonitorJober(monitorfService);
			schedulePool.scheduleWithFixedDelay(monitorJober, 0, 120, TimeUnit.SECONDS);
			core.log("---MonitorListener ini o9k2");
		} catch (Exception e) {
			core.log(e);
		}

	}

	
}
