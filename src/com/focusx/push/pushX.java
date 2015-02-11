/**
 * @author attilax 老哇的爪子
	@since  o7m X390$
 */
package com.focusx.push;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.examples.PushExample;
import cn.jpush.api.examples.connOrReqErr;
import com.attilax.core;
import com.attilax.api.Handler;
import com.attilax.api.HandlerChain;
import com.attilax.corePkg.JSONObject;
import com.attilax.io.filex;
import com.attilax.lang.callbackItfs;
import com.attilax.spri.SpringUtil;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.publish.entity.GvPublish;
import com.focusx.publish.service.IPublishService;
import static com.attilax.core.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.net.*;
import java.io.*;
import m.secury.securyWhile;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
/** @author attilax 老哇的爪子
 * @since o7m X390$ */
public class pushX {
	public static int invokeTimes=0;
	public static Logger logger = Logger.getLogger("pushXLoger");
	  protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PushExample.class);
	/** @author attilax 老哇的爪子
	 * @since o7m X390$
	 * 
	 * @param args
	 * @throws APIRequestException
	 * @throws APIConnectionException */
	public static void main(String[] args) throws  Exception {
tPushPblsh_singleFile();
		// attilax 老哇的爪子 X390 o7m
//		GvProgramme o = new GvProgramme();
//		// BeanUtils.copyProperties(o, stp);
//		o.setProgarmmeId(15);
//		// o.setDescribe(stp.getDescribe());
//		core.print(o);
//		LOG.info("------------o8a");
//		while(true)
//		{
//		  tPushPblsh();
//		  core.sleep(10000);
//		}
		//tSyncGetDateFrmClient();
	 
		
	}
	/** @author attilax 老哇的爪子
	 * @throws APIRequestException
	 * @throws APIConnectionException
	 * @since o88 1_44_3$ */   //for asyn2syn use 
	public static  Map rztMap = new ConcurrentHashMap();
	/**
	 * 
	@author attilax 老哇的爪子
		@since  o80 l_54_q   
	
	 * @throws Exception
	 */
	private static void tSyncGetDateFrmClient() throws Exception  {
		// attilax 老哇的爪子 1_44_3 o88
		final String callMeth = "call123";
		HandlerChain.reg(callMeth, new Handler() {

			@Override public Object handleReq(Object arg) throws Exception {
				// attilax 老哇的爪子 2_9_44 o88
				rztMap.put(callMeth, arg);
				core.log("----callMeth exe");
				return "{\"errcode\":0,\"errmsg\":\"ok\" }";

			}
		});
		String js = new JSONObject().put("sql", "select * form x").put("callback", callMeth).toString(2);
		String s = HandlerChain.addActNSecuryINfo(js, submeth.sql_syn);
		String rzt = null;
		try {
			rzt = new pushX().push_sync(callMeth, s, 1035);
		} catch (Exception e) {
			//  attilax 老哇的爪子 3_1_1   o88   
			HandlerChain.unreg(callMeth);
	 throw e;
		 
		}
		System.out.println(rzt);
	}
	// attilax 老哇的爪子 X390 o7m

	/** @author attilax 老哇的爪子
	 * @since o88 2_0_7$
	 * 
	 * @param s
	 * @param i
	 * @return
	 * @throws APIRequestException
	 * @throws APIConnectionException */
	 public String rztO88;  //for asy2syn
	static	ExecutorService threadPool = Executors.newSingleThreadExecutor();
	private String push_sync(final String callMeth, final String txt, final Object... target) throws APIConnectionException, APIRequestException, InterruptedException, ExecutionException, TimeoutException {
		// attilax 老哇的爪子 2_0_7 o88

		push(txt, target);
	
		Future<String> future = threadPool.submit(new Callable<String>() {
			public String call() throws Exception {
				// String rzt =null;
				new com.attilax.lang.securyWhile(new callbackItfs() {

					@Override public Object callMethod(Object obj) {
						// attilax 老哇的爪子 2_w_40 o88
						String v = (String) rztMap.get(callMeth);
						if (v != null) {
							rztO88 = v;
							return true;
						}
						return false;

					}
				}, logger, 1000, 600, 9999);
				return rztO88;

			}
		});
		HandlerChain.unreg(callMeth);
		return future.get(20000, TimeUnit.MILLISECONDS);

	}
	/** @author attilax 老哇的爪子
	 * @throws APIRequestException
	 * @throws APIConnectionException
	 * @since o86 m_39_s$ */
	private static void tPushPblsh() throws APIConnectionException, APIRequestException {
		
		jpushCompressor.kmprsLenMin=10;
		// attilax 老哇的爪子 m_39_s o86
//		IPublishService pc = (IPublishService) SpringUtil.getBean("publishService");
//		PrgrmNoticer c = new PrgrmNoticer();
//		GvPublish pb = pc.getPublish(1090);
//		// PrgrmNoticer prgrmNoticer = new PrgrmNoticer();
//		String s = HandlerChain.addActNSecuryINfo(core.toJsonStrO7(pb), submeth.pushProgram);
		String s=filex.read("c:\\publsh.json");
 	final pushX p = new pushX();
		p.push(s, 1035);
	}
	
	
	private static void tPushPblsh_singleFile() throws APIConnectionException, APIRequestException {
		
		//jpushCompressor.kmprsLenMin=10;
		// attilax 老哇的爪子 m_39_s o86
//		IPublishService pc = (IPublishService) SpringUtil.getBean("publishService");
//		PrgrmNoticer c = new PrgrmNoticer();
//		GvPublish pb = pc.getPublish(1090);
//		// PrgrmNoticer prgrmNoticer = new PrgrmNoticer();
//		String s = HandlerChain.addActNSecuryINfo(core.toJsonStrO7(pb), submeth.pushProgram);
		String s=filex.read("c:\\long.txt");
 	final pushX p = new pushX();
		p.push(s, 1099);
	}
	 public String respMsg="...";
	//public static ThreadLocal<String> respMsg=new ThreadLocal<String>();
	public String sendMsg="...";
	/** @author attilax 老哇的爪子
	 * @since o7m X5042$
	 * 
	 * @param string
	 * @param integer
	 * @return
	 * @throws connOrReqErr
	 * @throws APIRequestException
	 * @throws APIConnectionException */
	public String push(final String txt, final Object... target) throws APIConnectionException, APIRequestException {
		// attilax 老哇的爪子 X5042 o7m  //   k_56_44 o80  老哇的爪子  Attilax
		core.log("---- o8q14 add act n seruinfo before  txt:" + txt);
	 
		core.log("----add act n seruinfo before len:" + String.valueOf(txt.length()));
		final String s = jpushCompressor.kmprs(txt);
		core.log("----add act n seruinfo aft len:" + String.valueOf(s.length()));

		logger.info(txt + "\r\ntarget:" + StringUtils.join(target, ",") + " ");

		//new com.attilax.tryX<String>() {
		

		//	@Override public String $$(Object t) throws Exception {
				// attilax 老哇的爪子 2_q_9 o86
		setInvokeTimes();
		 	try {
		 		
					PushExample.SendPush_msgFmt(s, core.toStrArr(target));
					
 			}finally{
 				this.respMsg=PushExample.rzt.get();
				this.sendMsg=PushExample.sendMsg_thrdloc.get();
 			}
					
	//				catch (APIConnectionException e) {
//					  respMsg="APIConnectionException";
//				}
//				catch (APIRequestException e) {
//					respMsg=   
//				}
				
	//			return null;
//			}
//		}.$("");

		new com.attilax.tryX<String>() {
			@Override public String $$(Object t) throws Exception {
				// attilax 老哇的爪子 2_q_9 o86
			//	PushExample.SendPush(s, core.toStrArr(target));
				return null;
			}
		}.$("");

		return "";
	}
	/**
	@author attilax 老哇的爪子
		@since  o8r l_j_43   
	
	 */
synchronized	private void setInvokeTimes() {
		// attilax 老哇的爪子  l_j_43   o8r 
		invokeTimes++;
	}
}

// attilax 老哇的爪子