package com.focusx.exp.entity;

/**
 * 
 * @Title:
 * @Description:播放流水
 * @Author:luojungong
 * @Since:2014年11月18日
 * @Version:1.1.0
 */
public class ViewPlayWater implements java.io.Serializable {

	/**
	 * 素材id 素材描述 总播放时长 播放次数 分公司 省份 门店 门店id 设备id 设备描述 统计周期范围
	 */
	private static final long serialVersionUID = 1L;

	private Integer prId;// 播放流水

	private Integer materialId;

	private String materialDescription;// 素材描述

	private String branchName;// 分公司名称

	private String provinceName;// 省份名称

	private String storesName;// 门店名称

	private Integer storesId;// 门店ID

	private Integer equipmentId;// 设备ID

	private String equipmentName;// 设备名称

	private String playTime;// 播放时间

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getStoresName() {
		return storesName;
	}

	public void setStoresName(String storesName) {
		this.storesName = storesName;
	}

	public Integer getStoresId() {
		return storesId;
	}

	public void setStoresId(Integer storesId) {
		this.storesId = storesId;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

	public Integer getPrId() {
		return prId;
	}

	public void setPrId(Integer prId) {
		this.prId = prId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPlayTime() {
		return playTime;
	}
}
