package com.focusx.entity.vod;

import java.sql.Timestamp;

/**
 * GvDownloadRecord entity. @author MyEclipse Persistence Tools
 */

public class DownloadRecord implements java.io.Serializable {

	// Fields

	private Integer drId;
	private Integer downloadFlag;
	private Timestamp downloadOverTime;
	private Integer programmeId;
	private Integer materialId;
	private Integer equipmentId;
	private String sjFileName;

	// Constructors

	/** default constructor */
	public DownloadRecord() {
	}

	/** minimal constructor */
	public DownloadRecord(Integer drId) {
		this.drId = drId;
	}

	/** full constructor */
	public DownloadRecord(Integer drId, Integer downloadFlag,
			Timestamp downloadOverTime, Integer programmeId,
			Integer materialId, Integer equipmentId, String sjFileName) {
		this.drId = drId;
		this.downloadFlag = downloadFlag;
		this.downloadOverTime = downloadOverTime;
		this.programmeId = programmeId;
		this.materialId = materialId;
		this.equipmentId = equipmentId;
		this.sjFileName = sjFileName;
	}

	// Property accessors

	public Integer getDrId() {
		return this.drId;
	}

	public void setDrId(Integer drId) {
		this.drId = drId;
	}

	public Integer getDownloadFlag() {
		return this.downloadFlag;
	}

	public void setDownloadFlag(Integer downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	public Timestamp getDownloadOverTime() {
		return this.downloadOverTime;
	}

	public void setDownloadOverTime(Timestamp downloadOverTime) {
		this.downloadOverTime = downloadOverTime;
	}

	public Integer getProgrammeId() {
		return this.programmeId;
	}

	public void setProgrammeId(Integer programmeId) {
		this.programmeId = programmeId;
	}

	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getSjFileName() {
		return this.sjFileName;
	}

	public void setSjFileName(String sjFileName) {
		this.sjFileName = sjFileName;
	}

}