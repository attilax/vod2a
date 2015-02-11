package com.focusx.exp.entity;

/**
 * 
 *@Title:
 *@Description:下载管理
 *@Author:luojungong
 *@Since:2014年11月18日
 *@Version:1.1.0
 */
public class ViewDownMana  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer materialId;
 
	private String materialDescription;//素材描述
	
	private String storesName;//门店名称
	
	private Integer storesId;//门店ID
	
	private String downloadStatus;//下载状态
	
	private Integer equipmentId;//设备ID
	
	private String equipmentName;//设备名称
	
	private Integer progarmmeId;//节目单id
	
	private String progarmmeName;//发布描述
	
	private Integer rltID;//发布id
	
	private String opr;//操作者
	
	private String optim;//	操作时间	
	
	private String downloadCreateTime;//创建时间
	
	  

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(String downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

	public Integer getProgarmmeId() {
		return progarmmeId;
	}

	public void setProgarmmeId(Integer progarmmeId) {
		this.progarmmeId = progarmmeId;
	}

	public String getProgarmmeName() {
		return progarmmeName;
	}

	public void setProgarmmeName(String progarmmeName) {
		this.progarmmeName = progarmmeName;
	}

	public Integer getRltID() {
		return rltID;
	}

	public void setRltID(Integer rltID) {
		this.rltID = rltID;
	}

	public String getOpr() {
		return opr;
	}

	public void setOpr(String opr) {
		this.opr = opr;
	}

	public String getOptim() {
		return optim;
	}

	public void setOptim(String optim) {
		this.optim = optim;
	}

	public String getDownloadCreateTime() {
		return downloadCreateTime;
	}

	public void setDownloadCreateTime(String downloadCreateTime) {
		this.downloadCreateTime = downloadCreateTime;
	}
}
