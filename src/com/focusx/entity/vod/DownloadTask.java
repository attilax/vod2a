package com.focusx.entity.vod;

import java.sql.Timestamp;

/**
 * GvDownloadTask entity. @author MyEclipse Persistence Tools
 */

public class DownloadTask implements java.io.Serializable {

	// Fields

	private Integer dsId;
	private Integer noticeFlag;
	private Integer downloadStatus;
	private Integer materialId;
	private Integer equipmentId;
	private Timestamp downloadCreateTime;
	private Timestamp downloadEndTime;

	// Constructors

	/** default constructor */
	public DownloadTask() {
	}

	/** minimal constructor */
	public DownloadTask(Integer dsId) {
		this.dsId = dsId;
	}

	/** full constructor */
	public DownloadTask(Integer dsId, Integer noticeFlag,
			Integer downloadStatus, Integer materialId, Integer equipmentId,
			Timestamp downloadCreateTime, Timestamp downloadEndTime) {
		this.dsId = dsId;
		this.noticeFlag = noticeFlag;
		this.downloadStatus = downloadStatus;
		this.materialId = materialId;
		this.equipmentId = equipmentId;
		this.downloadCreateTime = downloadCreateTime;
		this.downloadEndTime = downloadEndTime;
	}

	// Property accessors

	public Integer getDsId() {
		return this.dsId;
	}

	public void setDsId(Integer dsId) {
		this.dsId = dsId;
	}

	public Integer getNoticeFlag() {
		return this.noticeFlag;
	}

	public void setNoticeFlag(Integer noticeFlag) {
		this.noticeFlag = noticeFlag;
	}

	public Integer getDownloadStatus() {
		return this.downloadStatus;
	}

	public void setDownloadStatus(Integer downloadStatus) {
		this.downloadStatus = downloadStatus;
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

	public Timestamp getDownloadCreateTime() {
		return this.downloadCreateTime;
	}

	public void setDownloadCreateTime(Timestamp downloadCreateTime) {
		this.downloadCreateTime = downloadCreateTime;
	}

	public Timestamp getDownloadEndTime() {
		return this.downloadEndTime;
	}

	public void setDownloadEndTime(Timestamp downloadEndTime) {
		this.downloadEndTime = downloadEndTime;
	}

}