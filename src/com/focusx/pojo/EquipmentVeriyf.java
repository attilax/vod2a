package com.focusx.pojo;

import java.sql.Timestamp;

import javax.persistence.Transient;

import com.focusx.entity.TMbGroup;

/**
 * GvEquipmentVeriyf entity. @author MyEclipse Persistence Tools
 */

public class EquipmentVeriyf implements java.io.Serializable {

	// Fields

	private Integer mvId;
	private String serialCode;
	private String ip;
	private Timestamp veriyfTime;
	private Integer systemFlag;
	private Integer equipmentId;
	@Transient
	TMbGroup grp;
	@Transient
	public TMbGroup getGrp() {
		return grp;
	}

	public void setGrp(TMbGroup grp) {
		this.grp = grp;
	}

	private Equipment equipment;

	// Constructors

	/** default constructor */
	public EquipmentVeriyf() {
	}

	/** minimal constructor */
	public EquipmentVeriyf(Integer mvId) {
		this.mvId = mvId;
	}

	/** full constructor */
	public EquipmentVeriyf(Integer mvId, String serialCode, String ip,
			Timestamp veriyfTime, Integer systemFlag, Integer equipmentId) {
		this.mvId = mvId;
		this.serialCode = serialCode;
		this.ip = ip;
		this.veriyfTime = veriyfTime;
		this.systemFlag = systemFlag;
		this.equipmentId = equipmentId;
	}

	// Property accessors

	public Integer getMvId() {
		return this.mvId;
	}

	public void setMvId(Integer mvId) {
		this.mvId = mvId;
	}

	public String getSerialCode() {
		return this.serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getVeriyfTime() {
		return this.veriyfTime;
	}

	public void setVeriyfTime(Timestamp veriyfTime) {
		this.veriyfTime = veriyfTime;
	}

	public Integer getSystemFlag() {
		return this.systemFlag;
	}

	public void setSystemFlag(Integer systemFlag) {
		this.systemFlag = systemFlag;
	}

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}