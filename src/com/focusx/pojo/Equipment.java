package com.focusx.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.focusx.entity.TMbGroup;

/** 
 * GvEquipment entity. @author MyEclipse Persistence Tools
 */
@DataTransferObject
public class Equipment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374929407009696306L;
	// Fields
@Id @Column(name="equipment_Id")
	private Integer equipmentId;
	private String serialCode;
	private Integer status = 1;
	private Integer departId;
	private String departName;
	private String mome;
	String worktime_start;
	String worktime_end;
	
	public String getWorktime_start() {
		return worktime_start;
	}

	public void setWorktime_start(String worktime_start) {
		this.worktime_start = worktime_start;
	}

	public String getWorktime_end() {
		return worktime_end;
	}

	public void setWorktime_end(String worktime_end) {
		this.worktime_end = worktime_end;
	}

	TMbGroup dpt;

	// Constructors

	/**
	//  attilax 老哇的爪子 5_w_39   o9n   
	 * @return the dpt
	 */
 
	 @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "departId", insertable = false,updatable = false)
	public TMbGroup getDpt() {
		return dpt;
	}

	/**
	//  attilax 老哇的爪子 5_w_39   o9n   
	 * @param dpt the dpt to set
	 */
	public void setDpt(TMbGroup dpt) {
		this.dpt = dpt;
	}

	/** default constructor */
	public Equipment() {
	}

	public Equipment(Integer equipmentId) {
		super();
		this.equipmentId = equipmentId;
	}


	/** minimal constructor */
	public Equipment(Integer equipmentId, Integer departId) {
		this.equipmentId = equipmentId;
		this.departId = departId;
	}

	/** full constructor */
	public Equipment(Integer equipmentId, String serialCode, Integer status,
			Integer departId, String mome) {
		this.equipmentId = equipmentId;
		this.serialCode = serialCode;
		this.status = status;
		this.departId = departId;
		this.mome = mome;
	}

	// Property accessors

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getSerialCode() {
		return this.serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDepartId() {
		return this.departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public String getMome() {
		return this.mome;
	}

	public void setMome(String mome) {
		this.mome = mome;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

}