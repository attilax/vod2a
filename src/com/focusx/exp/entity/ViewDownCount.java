package com.focusx.exp.entity;

/**
 * 
 *@Title:
 *@Description:
 *@Author:luojungong
 *@Since:2014年11月18日
 *@Version:1.1.0
 */
public class ViewDownCount  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String timRang;//时间
	
	private Integer shouldDown;//应该下载数量
	
	private Integer actDown;//实际下载数量
	
	private String branchName;//分公司名称
	
	private String provinceName;//省份名称
	
	private String storesName;//门店名称
	
	private Integer storesId;//门店ID
	
	private Integer equipmentId;//设备ID
	
	private String equipmentName;//设备名称

	public String getTimRang() {
		return timRang;
	}

	public void setTimRang(String timRang) {
		this.timRang = timRang;
	}

	public Integer getShouldDown() {
		return shouldDown;
	}

	public void setShouldDown(Integer shouldDown) {
		this.shouldDown = shouldDown;
	}

	public Integer getActDown() {
		return actDown;
	}

	public void setActDown(Integer actDown) {
		this.actDown = actDown;
	}

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
}
