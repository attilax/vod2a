/**
 * 
 */
package com.focusx;

import java.util.List;
import java.util.Map;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.attilax.Closure2;
import com.attilax.designpatter.commandPkg.Command;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.time.timeUtil;
import com.focusx.auth.authx;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.downtask.GvDownloadTaskSvs;

/**
 * @author ASIMO
 *
 */
public class DwrCInier {
	
	public void execute(final Map<String, String> m)
	{
		
		
		Command.reg("stopMult", new Closure2() {

			@Override
			public Object execute(Object arg0) {
				 String ids=m.get("ids");
				 GvDownloadTaskSvs tc=IocX.getBean(GvDownloadTaskSvs.class);
				 String[] a=ids.split(",");
				 for (String id : a) {
					 try {
						 Integer id_i=Integer.parseInt(id);
						 tc.stop(id_i);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
				return "";
			  
			 
			}
		});
		Command.reg("repush", new Closure2() {

			@Override
			public Object execute(Object arg0) {
				 String ids=m.get("ids");
				 GvDownloadTaskSvs tc=IocX.getBean(GvDownloadTaskSvs.class);
			 	List<GvDownloadTask> li= tc.findByIds(ids);
				 for (GvDownloadTask gvDownloadTask : li) {
					 try {
						gvDownloadTask.setDownloadStatus(null);
						gvDownloadTask.setNotice_back(0);
					 	gvDownloadTask.setNoticeFlag(1);
					 	gvDownloadTask.setStartdownFlag(null);
					 	gvDownloadTask.setTrueDownFlag(null);
					 	gvDownloadTask.setSendMsg("");
					 	gvDownloadTask.setSendMsgA("");
					 	gvDownloadTask.setSendMsgContext("");
					 	gvDownloadTask.setSendRetMsg("");
					 	gvDownloadTask.setLastSuccRetMsg("");
					 	gvDownloadTask.setLastSuccTime(null);
					 	setOpInfo(gvDownloadTask);
	//				 	gvDownloadTask.setOpr(opr)
						 tc.dbx.merge_syn(gvDownloadTask);
					} catch (Exception e) {
						filex.saveLog(e,"c:\\repush");
					}
					
				}
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// GvDownloadTask tsk=tc.findById(id)
				return li.size();
			 
			}
		});
	
		Command.reg("stopAll", new Closure2() {

			@Override
			public Object execute(Object arg0) {
				
				GvDownloadTaskSvs c=IocX.getBean(GvDownloadTaskSvs.class);
			
				return 	c.stopAll(m);
			}
		});
		
//		Command.reg("repushAll", new Closure2() {
//
//			@Override
//			public Object execute(Object arg0) {
//				
//				
//				return arg0;
//			}
//		});
		
		Command.reg("repushAll", new Closure2() {

			@Override
			public Object execute(Object arg0) {
				
				GvDownloadTaskSvs c=IocX.getBean(GvDownloadTaskSvs.class);
				
				return 	c.repushAll(m);
				//return arg0;
			}
		});
		
	
	
	
	}

		/**
		@author attilax 老哇的爪子
		@since   obf d_39_a
		 
		 */
	protected void setOpInfo(GvDownloadTask t) {
		try {
			WebContext webContext = WebContextFactory.get();    

			t.setOpr( authx.getCurUsername(webContext.getHttpServletRequest()));
			t.setOptim(timeUtil.getTimestamp());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	//	return 	 authx.getCurUsername
		
	}

}
