package com.focusx.action.equipment;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.io.filex;
import com.attilax.ioc.IocX;
import com.attilax.lang.BeanX;
import com.attilax.persistence.Hbx;
import com.attilax.persistence.HbxX;
import com.attilax.spri.SpringUtil;
import com.attilax.util.DateUtil;
import com.focusx.entity.TMbGroup;
import com.focusx.pager.PageUtil;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentMonitor;
import com.focusx.service.BranchManagerService;
import com.focusx.service.IEquipmentMonitorfService;
import com.focusx.service.IEquipmentService;
import com.focusx.util.MyCacher;
import com.focusx.util.Utils;
import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 设备监控
 * @author zhoujianbin
 *
 */
public class MonitorAction extends ActionSupport {
	protected static Logger logger = Logger.getLogger(MonitorAction.class);
	//private MyCacher mc = MyCacher.getInstance();
	private IEquipmentService equipmentService;
	private IEquipmentMonitorfService emService;
	private String monitorJson;
	private List<EquipmentMonitor> monitors;
	private EquipmentMonitor monitor;
	private Map<String, Object> data;
	private Pager<EquipmentMonitor> pager; //分页对象
	private String groupid;			//所属组织ID
	private String groupname;		//所属组织名称
	private String status;			//设备状态 是否启用
	private String lineFlag;		//是否在线
	private String equipmentStatus;	//终端状态
	private static ExecutorService es = Executors.newFixedThreadPool(10);
	
	public String list(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data = new HashMap<String, Object>();
		pager = new Pager<EquipmentMonitor>();
		monitors = new ArrayList<EquipmentMonitor>();
		PageUtil.init(pager, request);
		
		Pager<Equipment> pagerEquipment = new Pager<Equipment>();
		PageUtil.init(pagerEquipment, request);
		if(Utils.isNotEmpty(groupid)){
			data.put("groupid", groupid);
		}
		
		if(Utils.isNotEmpty(status)){
			data.put("status", status);
		}
		
		if(Utils.isNotEmpty(lineFlag)){
			data.put("lineFlag", lineFlag);
		}
		
		if(Utils.isNotEmpty(equipmentStatus)){
			data.put("equipmentStatus", equipmentStatus);
		}
		
		
		monitors = emService.getListPage(data, pager);
		
		try {
		  for(EquipmentMonitor em : monitors)
		  {
			  try {
					IEquipmentService ec=SpringUtil.getBean(IEquipmentService.class);
					em.setEquipmentMome(ec.getEquipment(em.getEquipmentId()).getMome());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		  }
		} catch (Exception e) {
			// TODO: handle exception
		}
	 
		grpX.setProvNsubBrach(monitors, new Closure<Object, TMbGroup>() {

			@Override
			public TMbGroup execute(Object arg0) throws Exception {
				EquipmentMonitor eq=(EquipmentMonitor) arg0;
				BranchManagerService grpx=	 (BranchManagerService) SpringUtil.getBean(BranchManagerService.class)	;
				
				TMbGroup parent= (grpx.getGroup(eq.getDepartId()));
				eq.setGroup(parent);
				return eq.getGroup();
			}
		});
		return "list";
	}
	
	
	/**
	 * 异步刷新设备监控数据
	 * @return
	 */
	public String ajaxList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data = new HashMap<String, Object>();
		pager = new Pager<EquipmentMonitor>();
		monitors = new ArrayList<EquipmentMonitor>();
		PageUtil.init(pager, request);
		
		Pager<Equipment> pagerEquipment = new Pager<Equipment>();
		PageUtil.init(pagerEquipment, request);
		if(Utils.isNotEmpty(groupid)){
			data.put("groupid", groupid);
		}
		
		if(Utils.isNotEmpty(status)){
			data.put("status", status);
		}
		
		if(Utils.isNotEmpty(lineFlag)){
			data.put("lineFlag", lineFlag);
		}
		
		if(Utils.isNotEmpty(equipmentStatus)){
			data.put("equipmentStatus", equipmentStatus);
		}
		monitors = emService.getListPage(data, pager);
		if(monitors.size() > 0){//取出对应几个数据字典的数据 EQUIPMENT_STATUS：设备状态、WHETHER_ONLINE：是否在线、TERMINAL_STATE：播放状态
			MyCacher mc = MyCacher.getInstance();
			data.put("EQUIPMENT_STATUS", mc.getCache("EQUIPMENT_STATUS"));
			data.put("WHETHER_ONLINE", mc.getCache("WHETHER_ONLINE"));
			data.put("TERMINAL_STATE", mc.getCache("TERMINAL_STATE"));
		}
		data.put("monitors", monitors);
		data.put("pager", pager);
		return Action.SUCCESS;
	}

	/** 
	 * 对于Struts2中Action中返回字符串的处理，比如做级联时，就可能用到返回json(字符串)的数据，些时Action该返回字符串
	 * ,其实：让Action中的返回字符串的方法，直接return
	 * null;并在return之前用输出流输出字符串就可以了，跟servlet输入HTMl方法一样。 更新或增加监控数据
	 * @author attilax 老哇的爪子
	 * @since o9n i_a_38
	 * 
	 * @return */
	public String putCacher() {

		// CountDownLatch countDown = new CountDownLatch(1);

		es.execute(new Runnable() {

			@Override public void run() {
			
					// data = new HashMap<String, Object>();
				filex.save_SF(monitorJson, "c:\\monitorUpLogDir\\mntrUplog"+filex.getUUidName()+".txt");
				
					JSONArray json = JSONArray.fromObject(monitorJson);
					if (json.size() > 0) {
						for (int i = 0; i < json.size(); i++) {
							if(i>0)return;
							try {
								putCacherSingle(json, i);
							} catch (Exception e) {
								e.printStackTrace();
								filex.save_SF(monitorJson+core.getTrace(e), "c:\\monitorUpLogDir_err\\mntrUplog"+filex.getUUidName()+".txt");
							}
						}
					}
					filex.save_SF("--ok", "c:\\monitorUpLogDir_ok\\mntrUplog"+filex.getUUidName()+".txt");
					
				
				// finally{
				// countDown.countDown();
				// }
			}

			private void putCacherSingle(JSONArray json, int i) {
				EquipmentMonitor monitorTmp = new EquipmentMonitor();
				synchronized(this){
				 	JSONObject jsonObject = (JSONObject) json.get(i);
					monitorTmp = (EquipmentMonitor) JSONObject.toBean(jsonObject, EquipmentMonitor.class);
				 	monitorTmp.setMonitorTime( DateUtil.str2date( jsonObject.get("monitorTime").toString(),true)  );
//					try {
//						//PropertyUtils.copyProperties(monitorTmp, (JSONObject) json.get(i));
//						BeanX.copyProperties(monitorTmp,  (JSONObject) json.get(i));
//						 BeanUtils.copyProperties(monitorTmp,  (JSONObject) json.get(i));
//					} catch ( Exception e1) {
//						 
//						e1.printStackTrace();
//						core.err(e1);
//						throw new RuntimeException(e1);
//					}
					delExtraDvs(monitorTmp);
					if(Utils.isNotEmpty(monitorTmp.getSerialCode())){
						EquipmentMonitor checkEm = emService.getEmBySerialCode(monitorTmp.getSerialCode());
						if(checkEm == null){
							logger.info("未检索到设备编码[" + monitorTmp.getSerialCode() + "]增加新设备监控数据。");
//										data.put("result", "add");
//										data.put("message", "未检索到设备编码[" + monitor.getSerialCode() + "]增加新设备监控数据。");
							emService.save(monitorTmp);
						}else{
							logger.info("检索到设备编码[" + monitorTmp.getSerialCode() + "]更新设备监控数据。");
							//set_depart_info o9f
							Equipment equipment = emService.getEquipmentById(String.valueOf(monitorTmp.getEquipmentId()));
							if(equipment != null){
								if(equipment.getDepartId() != null){
									TMbGroup group = emService.getTMbGroup(String.valueOf(equipment.getDepartId()));
									if(group != null){
										monitorTmp.setDepartId(equipment.getDepartId());
										monitorTmp.setDepartName(group.getGroupname());
									}
								}
							}
							int emId = checkEm.getEmId();
							//ati ooo
							Integer aAreaMtrId = null;
							String aAreaMtrTxt = null;
							if (monitorTmp.getAScreenMaterialId() == null
									|| monitorTmp.getAsmContent() == null
									|| "".equals(monitorTmp.getAsmContent())) {
								aAreaMtrId = checkEm.getAScreenMaterialId();
								aAreaMtrTxt = checkEm.getAsmContent();

							}
								try {
								BeanUtils.copyProperties(checkEm, monitorTmp);
							} catch (Exception e2) {
								//throw new RuntimeException(e2);
								filex.save_SF(monitorJson+core.getTrace(e2), "c:\\monitorUpLogDir_err\\mntrUplog"+filex.getUUidName()+".txt");
								
							}
								try {
//									if(aAreaMtrId!=null  && )
//										checkEm.setAScreenMaterialId(aAreaMtrId);
//									if(aAreaMtrTxt!=null)
//										checkEm.setAsmContent(aAreaMtrTxt);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
							try {
								
								
								checkEm.setEmId(emId);
//											data.put("result", "update");
//											data.put("message", "检索到设备编码[" + monitor.getSerialCode() + "]更新设备监控数据。");
								checkEm.setMonitorTime(new Date());
								emService.update(checkEm);
							}catch (Exception e) {
								
								core.err(e);
								throw new RuntimeException("line226:"+e.getMessage(),e);
							}
						}
					}else{
						logger.info("设备编码为空或丢失!"); 
						throw new RuntimeException("eq getSerialCode is empty ");
					}
				}
			}

		
		});
		
		//countDown.await(60, TimeUnit.SECONDS);
		   retInfo();
	   
	        

		
		return null;
	}


	private void retInfo() {
		ActionContext context = ActionContext.getContext();  
		//    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
		    HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  

	    response.setCharacterEncoding("utf-8");  
	      PrintWriter pw=null;  
	         try {  
	            pw = response.getWriter();  
	           pw.write(defOkRet);  
	           pw.flush();  
		       pw.close();  
	       } catch (IOException e) {  
	           core.err(e);  
	        }  
	      //response.setCharacterEncoding("utf-8");  
	}
	public static	String defOkRet= "{\"errcode\":0,\"errmsg\":\"ok\" }";
	//@ImplementedBy(HbxX.class)
	@Inject
	//@ImplementedBy(HbxX.class)
	Hbx hbx=new HbxX();
	private void delExtraDvs(EquipmentMonitor monitorTmp) {
		// attilax 老哇的爪子  4_46_l   o9l 
		try {
			 String dvsID=String.valueOf(monitorTmp.getEquipmentId());
	         String hq=" from EquipmentMonitor where serialCode=null and  equipmentId= "+dvsID;
	         hbx.deleteByHql(hq);
	         core.log("--del extra eq monitor data , eq id:"+dvsID );
		} catch (Exception e) {
			core.err(e);
		}
	        
		
	}
	public static void main(String[] args) {
//		MyCacher mc = MyCacher.getInstance();
//		for (int i = 0; i < 1000; i++) {
//			EquipmentMonitor em = new EquipmentMonitor();
//			mc.getCache("em_" + (i + 1));
//			System.out.println("ID:" + em.getEmId() + " IP:" + em.getIp() + "组织架构：" + em.getEquipmentMome());
//		}
		MonitorAction ma=IocX.getBean(MonitorAction.class);
	//	ma.delExtraDvs(new EquipmentMonitor(){  {this.setEquipmentId(1096);}});
		   String hq=" from EquipmentMonitor where   equipmentId= 1096";
		List li=   new MonitorAction().hbx.findByHql(hq);
		core.print_wzFmt(li);
		
	}

	
	
	public List<EquipmentMonitor> getMonitors() {
		return monitors;
	}

	public void setMonitors(List<EquipmentMonitor> monitors) {
		this.monitors = monitors;
	}

	public EquipmentMonitor getMonitor() {
		return monitor;
	}

	public void setMonitor(EquipmentMonitor monitor) {
		this.monitor = monitor;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getMonitorJson() {
		return monitorJson;
	}

	public void setMonitorJson(String monitorJson) {
		this.monitorJson = monitorJson;
	}

	public Pager<EquipmentMonitor> getPager() {
		return pager;
	}

	public void setPager(Pager<EquipmentMonitor> pager) {
		this.pager = pager;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public IEquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IEquipmentMonitorfService getEmService() {
		return emService;
	}

	public void setEmService(IEquipmentMonitorfService emService) {
		this.emService = emService;
	}

	public String getLineFlag() {
		return lineFlag;
	}

	public void setLineFlag(String lineFlag) {
		this.lineFlag = lineFlag;
	}

	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
}
