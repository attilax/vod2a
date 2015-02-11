package com.focusx.pojo;

import java.util.Date;

import com.focusx.entity.TMbGroup;

/**
 * 设备监控信息
 * @author zhoujianbin
 *
 */
public class EquipmentMonitor implements java.io.Serializable {

	private static final long serialVersionUID = 6756379105883548750L;

	private Integer emId;
	private Integer equipmentLineFlag;	//终端是否在线   0：离线 1：在线
	private Integer equipmentStatus;	//终端状态 0：开机 1：播放 2：开机未播放 3:未开机或中断
	private String ip;					//ip
	private Date monitorTime;			//监控时间
	private Integer equipmentId;		//设备ID
	private String serialCode;			//设备串码
	private String equipmentMome;		//设备描述
	private Integer status;				//设备状态
	private Integer departId;			//所属组织ID
	private String departName;			//所属组织名称
	private TMbGroup group;				//门店
	private Integer AScreenMaterialId;	//A屏播放内容
	private String asmContent;			//A屏播放内容名称
	private Integer CScreenMaterialId;	//B屏播放内容
	private String bsmContent;			//B屏播放内容名称
	private Integer BScreenMaterialId;	//C屏播放内容
	private String csmContent;			//C屏播放内容名称
	//private Integer departId;			//所属组织ID


	public Integer getEmId() {
		return this.emId;
	}

	public void setEmId(Integer emId) {
		this.emId = emId;
	}

	public Integer getEquipmentLineFlag() {
		return this.equipmentLineFlag;
	}

	public void setEquipmentLineFlag(Integer equipmentLineFlag) {
		this.equipmentLineFlag = equipmentLineFlag;
	}

	public Integer getEquipmentStatus() {
		return this.equipmentStatus;
	}

	public void setEquipmentStatus(Integer equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getMonitorTime() {
		return this.monitorTime;
	}

	public void setMonitorTime(Date monitorTime) {
		this.monitorTime = monitorTime;
	}

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getAScreenMaterialId() {
		return this.AScreenMaterialId;
	}

	public void setAScreenMaterialId(Integer AScreenMaterialId) {
		this.AScreenMaterialId = AScreenMaterialId;
	}

	public Integer getCScreenMaterialId() {
		return this.CScreenMaterialId;
	}

	public void setCScreenMaterialId(Integer CScreenMaterialId) {
		this.CScreenMaterialId = CScreenMaterialId;
	}

	public Integer getBScreenMaterialId() {
		return this.BScreenMaterialId;
	}

	public void setBScreenMaterialId(Integer BScreenMaterialId) {
		this.BScreenMaterialId = BScreenMaterialId;
	}

	public String getEquipmentMome() {
		return equipmentMome;
	}

	public void setEquipmentMome(String equipmentMome) {
		this.equipmentMome = equipmentMome;
	}

	public TMbGroup getGroup() {
		return group;
	}

	public void setGroup(TMbGroup group) {
		this.group = group;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getAsmContent() {
		return asmContent;
	}

	public void setAsmContent(String asmContent) {
		this.asmContent = asmContent;
	}

	public String getBsmContent() {
		return bsmContent;
	}

	public void setBsmContent(String bsmContent) {
		this.bsmContent = bsmContent;
	}

	public String getCsmContent() {
		return csmContent;
	}

	public void setCsmContent(String csmContent) {
		this.csmContent = csmContent;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}
	
}