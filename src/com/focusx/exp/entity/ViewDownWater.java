package com.focusx.exp.entity;

/**
 * 
 *@Title:
 *@Description:下载流水
 *@Author:luojungong
 *@Since:2014年11月18日
 *@Version:1.1.0
 */
public class ViewDownWater  implements java.io.Serializable {

	/**
	 *   
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String downloadStatus;//下载状态
	
	private String downloadOverTime;// 下载完成时间
	
	private Integer materialId;
 
	private String materialDescription;//素材描述
	
	private String branchName;//分公司名称
	
	private String provinceName;//省份名称
	
	private String storesName;//门店名称
	
	private Integer storesId;//门店ID
	
	private Integer equipmentId;//设备ID
	
	private String equipmentName;//设备名称
	
	private String sjFileName;//素材文件名称
	
	private String rmt_file_stat;//远程文件状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(String downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

	public String getDownloadOverTime() {
		return downloadOverTime;
	}

	public void setDownloadOverTime(String downloadOverTime) {
		this.downloadOverTime = downloadOverTime;
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

	public String getSjFileName() {
		return sjFileName;
	}

	public void setSjFileName(String sjFileName) {
		this.sjFileName = sjFileName;
	}

	public String getRmt_file_stat() {
		return rmt_file_stat;
	}

	public void setRmt_file_stat(String rmt_file_stat) {
		this.rmt_file_stat = rmt_file_stat;
	}
	  
}
