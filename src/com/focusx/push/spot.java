/**
 * @author attilax 老哇的爪子
	@since  o7m Xw0$
 */
package com.focusx.push;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.api.HandlerChain;
import com.attilax.collection.GvCycleQueue;
import com.attilax.collection.GvCycleQueueDAO;
import com.attilax.collection.GvCycleQueueSvs;
import com.attilax.corePkg.JSONObject;
import com.attilax.io.filex;
import com.attilax.io.pathx;
import com.attilax.ioc.IocX;
import com.attilax.log.Level;
import com.attilax.log.logRec;
import com.attilax.noticer.jsonFldSerialIgone;
import com.attilax.persistence.PX;
import com.attilax.spri.SpringUtil;
import com.attilax.time.timeUtil;
 
import com.focusx.downtask.GvDownloadTask;
import com.focusx.downtask.GvDownloadTaskSvs;
import com.focusx.elmt.GvMaterial;
import com.focusx.elmt.GvMaterialSvs;
import com.focusx.playRec.baseDAO;
import com.focusx.pojo.Equipment;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.programme.entity.GvProgrammeDetail;
import com.focusx.publish.entity.GvPublish;
import com.focusx.publish.service.IPublishService;
import com.focusx.publish.service.Impl.PublishServiceImpl;
import com.google.inject.Inject;
 import static  com.attilax.core.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.*;
import java.io.*;

import org.hibernate.Session;
/**
 * @author  attilax 老哇的爪子
 *@since  o7m Xw0$
 */
public class spot {

	/**
	@author attilax 老哇的爪子
		@since  o7m Xw0$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  Xw0   o7m 
		GvPublish o=	new GvPublish();
		o.setEquipmentId(12);
		o.setProgrm(new GvProgramme(){	

			{
			  add(new GvProgrammeDetail());
			}

			private void add(GvProgrammeDetail gvProgrammeDetail) {
				// attilax 老哇的爪子  j_o_45   o7r 
			 this.list.add(gvProgrammeDetail);
				
			}
		});
		
//		GvProgramme p=new GvProgramme();
//		GvProgrammeDetail pd=;
		System.out.println(core.toJsonStrO7(o));
	//	new spot().pushNappMtrPush(new GvPublish(),null);
	//	tDisableEq();
		IPublishService pbc=(IPublishService) SpringUtil.getBean(IPublishService.class);
		GvPublish pub=pbc.getPublish(2142);
		new spot().push2rmt(pub);
		System.out.println("--");

	}
	/**
	@author attilax 老哇的爪子
		@since  o99 i_9_41   
	
	 */
	private static void tDisableEq() {
		// attilax 老哇的爪子  i_9_41   o99 
		GvPublish o=	new GvPublish();
		o.setPublishId(1);
		o.setEquipmentId(1080);
		o.setProgarmmeId(2);
		spot c=IocX.getBean(spot.class);
	c.	pushNappMtrPush(o,null);
		
	}
	/**
	@author attilax 老哇的爪子
		@since  o7r j_o_x$
	
	 * @param gvProgrammeDetail
	 */
 @Inject
	PX px;	
 
	/**sendMtrl_on_pub_async(t);
	 * 
	@author attilax 老哇的爪子
		@since  o7m X52n$
	
	 * @param o
	 * @param session 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object pushNappMtrPush(GvPublish o1, Session session) {
		System.out.println("--");
		// if("1".equals("1"))
		// throw new RuntimeException("xxx");
		GvPublish pb = (GvPublish) o1;
		// if eq disable not add queue O99
		if (EqDisable(o1))
			return o1;

		// ////end o99

		push2rmt(pb);

		// // i_47_x o85 老哇的爪子 Attilax
		try {
			new GvDownloadTaskSvs().append2taskQueue(o1, session,
					new Closure() {

						@Override
						public Object execute(Object arg0) throws Exception {
							TaskNoticer taskNoticer = IocX.getBean(TaskNoticer.class);
							boolean is_downtime_limit_rzt = taskNoticer.is_downtime_limit(null);
						    filex.save_SF("is_downtime_limit_rzt:"+String.valueOf(is_downtime_limit_rzt), "C:\\oaa.txt");
							if (!is_downtime_limit_rzt)
								 sendMtrl_on_pub_async((GvDownloadTask) arg0);
							return null;
						}
					});
			
		} catch (Exception e) {
			core.log(e);
			throw new RuntimeException(e);
		}
		
		try {
			new PrgrmNoticer().app(o1); // app to queue
		} catch (Exception e) {
			 
		}

		return o1;

	}
	public static ExecutorService es=	Executors.newFixedThreadPool(100);

	public void sendMtrl_on_pub_async(final GvDownloadTask tsk) {
		
		try {
			if(tsk.getDsId()!=null && tsk.getDsId()!=0)
				es.execute(new Runnable() {
					
					@Override public void run() {
						// attilax 老哇的爪子  2_q_l   o0n 
						if (!new TaskNoticer().is_downtime_limit(null))
						sendMtrl(tsk);
					}
				});
		} catch (Exception e) {
			core.err(e);
		}
		
		

	}
	
	

    /**
@author attilax 老哇的爪子
	@since  o0n 2_w_52   

 * @param t
 */


	public void sendMtrl(GvDownloadTask tsk) {
		
		
		//===================set file
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
		//oaa  ===============set mtrl size
		try {
			setMtrlSize(tsk);
		} catch (FileNotExist e1) {
			//  Throwable e1;
			tsk.setSendRetMsg(e1.getMessage());
			 tsk.setDownloadStatus(1); //except from queue
			 tsk.setTrueDownFlag(FailState.FileNotExist);
			 TaskNoticer tn=IocX.getBean(TaskNoticer.class);
				tn.setPushRzt(tsk, tsk, "", "");			
			//	return "";
		} //checkMtrlFileExist(tsk);
		 
		// attilax 老哇的爪子 2_w_52 o0n
		String msgAti = HandlerChain.addActNSecuryINfo(core.toJsonStrO7(tsk, jsonFldSerialIgone.jsonConfig_timefmtNinoneRetmsg()), "pushDownTask");

		TaskNoticer.invokeItfsTimes++;
		tsk.setSendRetMsg("..");
		GvDownloadTask t = tsk;
		try {
			this.pushx.push(msgAti, tsk.getEquipmentId());
			log2db4pushmtrl(t, null, msgAti);
			
		} catch (APIConnectionException e) {
			// attilax 老哇的爪子 k_7_o o00
			core.err(e);
			log2db4pushmtrl(t, e, msgAti);
			throw new RuntimeException("APIConnectionException",e);

		} catch (APIRequestException e) {
			// attilax 老哇的爪子 k_7_o o00
			core.err(e);
			log2db4pushmtrl(t, e, msgAti);
			throw new RuntimeException("APIRequestException",e);
		}
	}
	private pushX pushx=new pushX();
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
			filex.saveLog(e, "c:\\getfilesize", "gbk");
		}

	}

public void log2db4pushmtrl(GvDownloadTask tsk,Exception e,String pushMsg) {
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
		  o.setCate("mtrpushOnpub");
		  if(e!=null)
				 o.setMsg(core.getTrace(e));
			  o.setMsgA(pushMsg);
	 	//this.dbx.save(o);
			new baseDAO().save(o);
	} catch (Exception e2) {
		//core.log(e);
	}
	
}
	
	
	//  attilax 老哇的爪子 Xw0   o7m   
	/**
	 * only prgrm
	@author attilax 老哇的爪子
		@since  o0n 2_t_47   
	
	 * @param pb
	 */
	public void push2rmt(final GvPublish pb) {
		
		core.execMeth_Ays(new Runnable() {
			
			@Override public void run() {
				// attilax 老哇的爪子  i_z_45   o08 
				pushX p=new pushX();
				
				try {
					if(pb.getPrgrm()!=null )
					{
						if(pb.getPrgrm().getList()!=null && pb.getPrgrm().getList().size()>0)
						{
					//	JSONObject jo=new JSONObject().put("submethod", "pushProgram").put("param",pb).put("eqid", pb.getEquipmentId());	
						String jsonStrO7 = core.toJsonStrO7(pb,jsonFldSerialIgone.jsonConfig_simp4prgrm());
						String msgAti = HandlerChain.addActNSecuryINfo(jsonStrO7, submeth.pushProgram);
						filex.save_SF(jsonStrO7,"c:\\oa5_3.txt");
					 	p.push(msgAti,pb.getEquipmentId());
						}
					}
				} catch (APIConnectionException e) {
					//  attilax 老哇的爪子 1_u_w   o7t   
					e.printStackTrace();
					core.warn(e);
				} catch (APIRequestException e) {
					//  attilax 老哇的爪子 1_u_w   o7t   
					e.printStackTrace();
					core.warn(e);
				}catch (Exception e) {
				 core.warn(e);
				}
				
			}
		}, "push2rmt"+filex.getUUidName());
		
	}

	/**
	@author attilax 老哇的爪子
	 * @param pb 
		@since  o99 h_57_a   
	
	 * @return
	 */
	private boolean EqDisable(GvPublish pb) {
		// attilax 老哇的爪子 h_57_a o99
		try {
			Integer eqID = pb.getEquipmentId();
			Equipment eq = (Equipment) px.get(Equipment.class, eqID);
			if (eq.getStatus() == 0) {
				try {
					logRec o = new logRec();

					o.setLevel(Level.info);
					o.setIdx(pb.getPublishId());
					o.setIdx2(pb.getProgarmmeId());
				 o.setIdx3(pb.getEquipmentId());
					o.setCreateTime(timeUtil.getTimestamp());
					o.setLogger("spot");
					o.setMsg("eq stat is 0");
					o.setMsgA("eq stat is 0");
					o.setSend("none");
					o.setThread("spotThd");
					o.setCate("igoPub");
					px.save(o);

				} catch (Exception e) {
					core.log(e);
				}
				return true;
			}
		} catch (Exception e) {
			core.log(e);

		}
		return false;

	}
}

//  attilax 老哇的爪子