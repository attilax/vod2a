package com.focusx.action.equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.attilax.Closure;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.rails.foreachProcessor;
import com.attilax.spri.SpringUtil;
import com.focusx.entity.TMbGroup;
import com.focusx.pager.PageUtil;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.pojo.EquipmentMonitor;
import com.focusx.pojo.EquipmentVeriyf;
import com.focusx.service.BranchManagerService;
import com.focusx.service.IEquipmentService;
import com.focusx.service.IEquipmentVeriyfService;
import com.focusx.service.impl.EquipmentServiceImpl;
import com.focusx.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class VeriyfAction extends ActionSupport {
	private IEquipmentVeriyfService equipmentVeriyfService;
	private IEquipmentService equipmentService;
	private String SerialCode;		//机身串码
	private String ip;				//ip
	private String groupid;			//所属组织ID
	private String groupname;		//所属组织名称
	private String veriyfStartTime;	//查询开始时间
	private String veriyfEndTime;	//查询结束时间
	private Pager<EquipmentVeriyf> pager; //分页对象
	private List<EquipmentVeriyf> veriyfs;
	private EquipmentVeriyf veriyf;
	private Map<String, Object> data;
	
	
	public String list(){
		HttpServletRequest request = ServletActionContext.getRequest();
		pager = new Pager<EquipmentVeriyf>();
		veriyfs = new ArrayList<EquipmentVeriyf>();
		data = new HashMap<String, Object>();
		PageUtil.init(pager, request);
		if(Utils.isNotEmpty(groupid)){
			data.put("groupid", groupid);
		}
		if(Utils.isNotEmpty(veriyfStartTime)){
			data.put("veriyfStartTime", veriyfStartTime);
		}
		if(Utils.isNotEmpty(veriyfEndTime)){
			data.put("veriyfEndTime", veriyfEndTime);
		}
		if(veriyf != null && veriyf.getSystemFlag() != null){
			data.put("systemFlag", veriyf.getSystemFlag());
		}
		veriyfs = equipmentVeriyfService.getListPage(data, pager);
		data.put("total", pager.getTotal());
		if(veriyfs.size() > 0){
			StringBuffer sb = new StringBuffer("");
			for (EquipmentVeriyf ev : veriyfs) {
				if(ev.getEquipmentId() != null){
					sb.append(ev.getEquipmentId() + ",");
				}
			}
			if(sb.length() > 0){
				List<Equipment> equipments = equipmentService.getListByIds(sb.substring(0, sb.length() - 1));
				if(equipments.size() > 0){
					StringBuffer sbEids = new StringBuffer("");
					for (Equipment equipment : equipments) {
						for (EquipmentVeriyf ev : veriyfs) {
							if(ev.getEquipmentId() != null){
								if(ev.getEquipmentId().equals(equipment.getEquipmentId())){
									sbEids.append(equipment.getDepartId() + ",");
									ev.setEquipment(equipment);
								}
							}
						}
					}
					if(sbEids.length() > 0){
						List<TMbGroup> groups = equipmentService.getGroupList(sbEids.substring(0,sbEids.length() - 1));
						if(groups.size() > 0 && equipments.size() > 0){
							for (TMbGroup tMbGroup : groups) {
								for (Equipment equipment : equipments) {
									if(tMbGroup.getGroupid() == equipment.getDepartId()){
										equipment.setDepartName(tMbGroup.getGroupname());
									}
								}
							}
						}
					}
				}
			}
			
		}
		
		grpX.setProvNsubBrach(veriyfs, new Closure<Object, TMbGroup>() {

			@Override
			public TMbGroup execute(Object arg0) throws Exception {
				EquipmentVeriyf eqVlog=(EquipmentVeriyf) arg0;
				IEquipmentService eqx=	   SpringUtil.getBean(IEquipmentService.class)	;
				Equipment eq=eqx.getEquipment(eqVlog.getEquipmentId());
				BranchManagerService grpx=	 (BranchManagerService) SpringUtil.getBean(BranchManagerService.class)	;
			//	equipmentId
				TMbGroup grp=  eq.getDpt();
				eqVlog.setGrp(grp);
				return  grp;
			}
		});
		pager.setData(veriyfs);
		return "list";
	}
	
	
	
	
	
	
	
	
	
	public IEquipmentVeriyfService getEquipmentVeriyfService() {
		return equipmentVeriyfService;
	}
	public void setEquipmentVeriyfService(
			IEquipmentVeriyfService equipmentVeriyfService) {
		this.equipmentVeriyfService = equipmentVeriyfService;
	}
	public String getSerialCode() {
		return SerialCode;
	}
	public void setSerialCode(String serialCode) {
		SerialCode = serialCode;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public EquipmentVeriyf getVeriyf() {
		return veriyf;
	}
	public void setVeriyf(EquipmentVeriyf veriyf) {
		this.veriyf = veriyf;
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
	
	public List<EquipmentVeriyf> getVeriyfs() {
		return veriyfs;
	}

	public void setVeriyfs(List<EquipmentVeriyf> veriyfs) {
		this.veriyfs = veriyfs;
	}

	public Pager<EquipmentVeriyf> getPager() {
		return pager;
	}

	public void setPager(Pager<EquipmentVeriyf> pager) {
		this.pager = pager;
	}









	public IEquipmentService getEquipmentService() {
		return equipmentService;
	}









	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}









	public String getVeriyfStartTime() {
		return veriyfStartTime;
	}









	public void setVeriyfStartTime(String veriyfStartTime) {
		this.veriyfStartTime = veriyfStartTime;
	}









	public String getVeriyfEndTime() {
		return veriyfEndTime;
	}









	public void setVeriyfEndTime(String veriyfEndTime) {
		this.veriyfEndTime = veriyfEndTime;
	}
	
	
	
}
