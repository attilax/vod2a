package com.focusx.action.equipment;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.Stream.Mapx;
import com.attilax.anno.Inj;
import com.attilax.api.HandlerChain;
import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.io.filex;
import com.attilax.log.Level;
import com.attilax.log.logRec;
import com.attilax.noticer.jsonFldSerialIgone;
import com.attilax.spri.SpringUtil;
import com.attilax.time.timeUtil;
import com.attilax.util.request;
import com.focusx.dao.impl.baseDao;
import com.focusx.downtask.GvDownloadTask;
import com.focusx.entity.TMbGroup;
import com.focusx.eq.CantFindAnyDeviceEx;
import com.focusx.eq.EqX;
import com.focusx.pager.PageUtil;
import com.focusx.pager.Pager;
import com.focusx.playRec.baseDAO;
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentVeriyf;
 
import com.focusx.publish.dao.Impl.PublishDaoImpl;
import com.focusx.push.pushX;
import com.focusx.push.submeth;
import com.focusx.service.BranchManagerService;
import com.focusx.service.IEquipmentService;
import com.focusx.service.IEquipmentVeriyfService;
import com.focusx.util.MD5;
import com.focusx.util.Utils;
import com.focusx.util.watchdog4vod;
import com.google.inject.Inject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 设备管理action
 * @author zhoujianbin
 *
 */
@RemoteProxy(name = "eqX")
/**
 * com.focusx.action.equipment.EquipmentAction
 * @author ASIMO
 *
 */
public class EquipmentAction extends ActionSupport {
	protected static Logger logger = Logger.getLogger(EquipmentAction.class);
	private IEquipmentService equipmentService;
	private IEquipmentVeriyfService equipmentVeriyfService;
	private String opType;			//操作类型
	private Equipment equipment;	//设备类对象
	private String groupid;			//所属组织ID
	private String groupname;		//所属组织名称
	private String SerialCode;		//机身串码
	private String ip;				//ip
	private String equipmentIds;	//设备ID集合
	//分页对象
	private Pager<Equipment> pager; //分页对象
	private Map<String, Object> data;	
	private List<Equipment> equipments;
	@Inject
	pushX pushx=new pushX();
	
	@RemoteMethod
	public Object setWorktimeRmt(Map map)
	{
	//	core.toMap(obj)
		JSONObject jo=new JSONObject();
		jo.put("worktime_start", map.get("worktime_start"));
		jo.put("worktime_end", map.get("worktime_end"));
		int eqid =  Integer.parseInt(  map.get("equipment.equipmentId").toString());
		jo.put("equipmentId", eqid);
		String msgAti = HandlerChain.addActNSecuryINfo(core.toJsonStrO7(jo), submeth.setWorktime);
		
		 
		try {
			pushx.push(msgAti, eqid);
		} catch (APIConnectionException e) {
			 
			e.printStackTrace();
			return pushx.respMsg;
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return pushx.respMsg;
		}
		return    JSONObject.fromObject(  pushx.respMsg);
		
		
	}
	
	public String list() {
	//	if(true)return null;
		try {
			return list_ori();
		} catch (Exception e) {
			filex.save_SF(core.getTrace(e), "c:\\eqMntrListWarn\\"+filex.getUUidName()+".txt");
			try {
				return list_ori();
			} catch (Exception e2) {
				filex.save_SF(core.getTrace(e), "c:\\eqMntrListWarn2\\"+filex.getUUidName()+".txt");
				try {
					return list_ori();
				} catch (Exception e3) {
					//return list_ori();
					 return null;
				}
				
			}
			
		}
		
	}
	private final class OrgFilter extends PropFilter {
		@Override
		public Object getExp() {
			try {
				HttpServletRequest request = ServletActionContext
						.getRequest();
			  String eqIds=EqX.FindByLoginUid(request);			
			
				 return " and equipmentId in (@ids) ".replaceAll("@ids", eqIds);
				
			}   catch (isSuperAdminEx e) {
				return "  ";
			} catch (CantFindAnyDeviceEx e) {
				 
				return "   and equipmentId=8888  ";
			}
			
		}
	}
	private String list_ori() {
		HttpServletRequest request = ServletActionContext.getRequest();
		pager = new Pager<Equipment>();
		data = new HashMap<String, Object>();
		if(equipment != null){
			if(Utils.isNotEmpty(equipment.getMome())){
				data.put("mome", equipment.getMome());
			}
			
			if(equipment.getStatus() != null){
				data.put("status", equipment.getStatus());
			}
		}
		
		if(groupid != null && Utils.isNotEmpty(groupid)){
			data.put("groupid", groupid);
		}
		if(Utils.isNotEmpty(SerialCode)){
			data.put("SerialCode", SerialCode);
		}
		
		PageUtil.init(pager, request);
		
		//oaf org filter
 	baseDao.proFltr.set(new OrgFilter());
		equipments = equipmentService.getListPage(data, pager);
		
		if(equipments.size() > 0){
			StringBuffer sb = new StringBuffer();
			for (Equipment e : equipments) {
				sb.append(e.getDepartId() + ",");
			}
			if(sb.length() > 0){
				List<TMbGroup> groups = equipmentService.getGroupList(sb.substring(0, sb.length() - 1));
				for (Equipment e : equipments) {
					Integer departId = e.getDepartId();
					for (TMbGroup tMbGroup : groups) { 
						if( departId.equals(tMbGroup.getGroupid())){
							e.setDepartName(tMbGroup.getGroupname());
						}
					} 
				} 
			}
		}
		
		//set Prov n subBrach oab
		setProvNsubBrach(equipments,new Closure<Object, TMbGroup>() {

			@Override
			public TMbGroup execute(Object arg0) throws Exception {
				Equipment eq=(Equipment) arg0;
				return eq.getDpt();
			}
		});
		
		pager.setData(equipments);
		return "list";
	}
	
		/**
		@author attilax 老哇的爪子
		@since   oab 10_w_x
		 
		 */
	private void setProvNsubBrach(List  li,Closure<Object,TMbGroup> getGrpAdapter) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (Object eq : li) {
			try {
				TMbGroup grp=getGrpAdapter.execute(eq);
				BranchManagerService grpx=	 (BranchManagerService) SpringUtil.getBean(BranchManagerService.class)	;
				TMbGroup parent= (grpx.getGroup(grp.getParentId()));
				Integer gradPrtId=parent.getParentId();
				TMbGroup grdPrt=grpx.getGroup(gradPrtId);
				grp.setParentGrp(parent);
				grp.setGradParentGrp(grdPrt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public String add(){
		opType = "add";
		return "add";
	}
	
	public String update(){
		opType = "update";
		equipment = equipmentService.getEquipment(equipment.getEquipmentId());
		List<TMbGroup> groups = equipmentService.getGroupList(equipment.getDepartId().toString());
		if(groups.size() > 0){
			equipment.setDepartName(groups.get(0).getGroupname());
		}
		return "update";
	}
	
	public String delete(){
		if(Utils.isNotEmpty(equipmentIds)){
			String[] item = equipmentIds.split(",");
			data = new HashMap<String, Object>();
			data.put("result", equipmentService.deleteEquipments(item) == true ? 1 : 0);
		}
		return Action.SUCCESS;
	}
	/**
	 * new save and edit save
	@author attilax 老哇的爪子
		@since  o94 2_40_i   
	
	 * @return
	 */
	public String save(){
		try {
			equipment.setDepartId(Integer.parseInt(groupid));
			//equipment.setDepartName(groupname);
			
			//o92 ori 
			//equipment.setSerialCode(MD5.getMD5(equipment.getSerialCode()));
			if(isNormalSn(equipment.getSerialCode()))
		//	if(equipment.getEquipmentId()==null)
				equipment.setSerialCode(MD5.getMD5(equipment.getSerialCode()));
			else
				equipment.setSerialCode( (equipment.getSerialCode()));
			//end o92
			
			//oa5
			HttpServletRequest request = ServletActionContext.getRequest();
			equipment.setWorktime_start(request.getParameter("worktime_start"));
			equipment.setWorktime_end(request.getParameter("worktime_end"));
			
			equipmentService.saveUpdate(equipment);
//			try {
//				resp.sendRedirect("equipment/equipment!list");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return "save_return";
		} catch (Exception e) {
			filex.save_SF(core.getTrace(e), "c:\\eqSaveErr"+filex.getUUidName()+".txt");
			return null;
		}
	
	}
	
		/**
		@author attilax 老哇的爪子
		@since   oa6 g_53_h
		 
		 */
	private boolean isNormalSn(String serialCode2) {
		if(serialCode2.length()!=32)
			return true;
		else{
			if(serialCode2.contains("-"))
				return true;
		}
		return false;
	}

	/**
	 * 检查机身串码是否重复
	 * @return
	 */
	public String ajaxCheckSerialCode(){ 
		logger.info("检查机身串码是否重复:" + SerialCode);
		if(Utils.isNotEmpty(SerialCode)){ 
			data = new HashMap<String, Object>();
			data.put("result", equipmentService.checkSerialCode(SerialCode).size() >= 1 ? 1 : 0);
		}
		return Action.SUCCESS;
	}
	
	/** check sn
	 * 验证机身串码
	 * @return
	 */
	public String ajaxValidateSerialCode(){ 
		
		logger.info("加密狗处理设备数量 开始");
		new watchdog4vod().start();
		logger.info("加密狗处理设备数量 结束");
		
		logger.info("验证机身串码是否存在:" + SerialCode);
		data = new HashMap<String, Object>();
		if(Utils.isNotEmpty(SerialCode)){
			EquipmentVeriyf ev = new EquipmentVeriyf();
			ev.setIp(this.ip);
			ev.setSerialCode(this.SerialCode);
			ev.setVeriyfTime(new Timestamp(new Date().getTime()));
		//	String sn_md5=MD5.getMD5(SerialCode);
			List<Equipment> equipments = equipmentService.checkSerialCode(SerialCode);
			if(equipments != null && equipments.size() >= 1 ){
				List<TMbGroup> groups = equipmentService.getGroupList(equipments.get(0).getDepartId().toString());
				if(groups != null && groups.size() > 0) equipments.get(0).setDepartName(groups.get(0).getGroupname());
				String msg = SerialCode + "机身串码存在，所属单位为：" +  equipments.get(0).getDepartName();
				logger.info(msg);
				ev.setSystemFlag(1);
				ev.setEquipmentId(equipments.get(0).getEquipmentId());
				data.put("result", 1);
				data.put("obj", equipments.get(0));
				log2db(Mapx.<String, Object>$().add("sn", SerialCode).add("msg", msg));
			}else{
				
				String msg = SerialCode + "机身串码不存在，转码MD5后值为：" + MD5.getMD5(SerialCode);
				logger.info(msg);
				log2db(Mapx.<String, Object>$().add("sn", SerialCode).add("msg", msg));
				ev.setSystemFlag(0);
				ev.setEquipmentId(null);
				data.put("result", 0);
				data.put("obj", null);
				
			}
			String s=core.toJsonStrO88(data);
			equipmentVeriyfService.save(ev);//添加验证流水
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			logger.info("添加验证流水,机身串码[" + SerialCode + "],设备IP[" + ip + "]" + "验证时间[" + sdf.format(ev.getVeriyfTime()) + "]");
		}else{
			data.put("result", 0);
			data.put("obj", "机身串码为空");
		}
		return Action.SUCCESS;
	}
	
	private void log2db(Mapx<String, Object> mapx) {
		// attilax 老哇的爪子  上午01:49:53   2014-9-2 
		System.out.println("aaaaa");
//		try {
//			logRec o=new logRec();
//			 o.setLevel(Level.info);
//			 o.setIdx(mapx.getRltID());
//			 o.setIdx2(mapx.getRltID2());
//			 o.setIdx3(mapx.getRltID3());
//			 o.setIdx3(mapx.getRltID3());
//			 o.setQueId(mapx.getDsId());
//			 o.setCreateTime(timeUtil.getTimestamp());
//			 o.setLogger("mtrpush");
//			 o.setMsg(mapx.getSendMsg());
//			  o.setMsgA(mapx.getSendMsgA());
//			  o.setSend("send");
//			  o.setRet(mapx.getSendRetMsg());
//			  o.setThread("mtrpush");
//		 	//this.dbx.save(o);
//				new baseDAO().save(o);
//		} catch (Exception e) {
//			core.log(e);
//		}
		
		 
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}



	public IEquipmentService getEquipmentService() {
		return equipmentService;
	}



	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
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

	public String getSerialCode() {
		return SerialCode;
	}

	public void setSerialCode(String serialCode) {
		SerialCode = serialCode;
	}

	public String getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(String equipmentIds) {
		this.equipmentIds = equipmentIds;
	}

	public Pager<Equipment> getPager() {
		return pager;
	}

	public void setPager(Pager<Equipment> pager) {
		this.pager = pager;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public IEquipmentVeriyfService getEquipmentVeriyfService() {
		return equipmentVeriyfService;
	}

	public void setEquipmentVeriyfService(
			IEquipmentVeriyfService equipmentVeriyfService) {
		this.equipmentVeriyfService = equipmentVeriyfService;
	}
	
}
