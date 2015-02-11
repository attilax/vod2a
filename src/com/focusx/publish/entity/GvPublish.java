package com.focusx.publish.entity;

import java.sql.Timestamp;

import javax.persistence.Transient;

import org.directwebremoting.annotations.DataTransferObject;
import com.attilax.anno.Conditional;
import com.attilax.anno.op;
import com.focusx.pojo.Equipment;
import com.focusx.programme.entity.GvProgramme;

/**com.focusx.publish.entity.GvPublish,
 *  com.focusx.programme.entity.GvProgramme
 * @author luojun
 * 
 * Program Name:starx BS坐席
 *
 * Description: 发布管理实体类
 * 
 * CreateTime: 2014-7-16  下午2:08:40
 *  
 *
 */
@DataTransferObject 
public class GvPublish implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer publishId;
	@Conditional
	private Integer equipmentId;
	/**
	 * 	 * 设备名称
	 */
	private String equipmentName;
	/**
	 * 类型
	 */
	private Integer publishType;

	/**
	 * 插播时间类型
	 * 1:当前时间
	 * 2:预设时间
	 */
	private Integer interruptedTimeType;
	/**
	 * 插播开始时间
	 */
	private Timestamp interruptedStartTime;
	/**
	 * 起始时间
	 */
	@Conditional(op=op.gtet)
	private Timestamp startTime;
	/**
	 * 结束时间
	 */
	@Conditional(op=op.ltet)
	private Timestamp endTime;
	/**
	 * 执行状态
	 */
	private Integer status;
	/**
	 * 发布人ID
	 */
	private Integer publishManId;
	/**
	 * 发布人名字
	 */
	private String publishManName;
	/**
	 * 发布时间
	 */
	private Timestamp publishTime;
	/**
	 * 审核人ID
	 */
	private Integer reviewManId;
	/**
	 * 审核时间
	 */
	private Timestamp reviewTime;
	/**
	 * 节目单ID
	 */
	private Integer progarmmeId;
	/**
	 * 节目单名称
	 */
	private String progarmmeName;
	/**
	 * 描述
	 */
	private String mome;
	private GvProgramme prgrm;
	/**
	 * 
	 */
	Equipment eq;
	@Transient
	Equipment eqx;

	// Constructors
@Transient
	public Equipment getEqx() {
		return eqx;
	}


	public void setEqx(Equipment eqx) {
		this.eqx = eqx;
	}


	/**
	//  attilax 老哇的爪子 3_8_41   o01   
	 * @return the eq
	 */
	public Equipment getEq() {
		return eq;
	}


	/**
	//  attilax 老哇的爪子 3_8_41   o01   
	 * @param eq the eq to set
	 */
	public void setEq(Equipment eq) {
		this.eq = eq;
	}


	/** default constructor */
	public GvPublish() {
		
	}
 

	/** full constructor *//*
	public GvPublish(Integer publishId, GvEquipment gvEquipment,
			Integer publishType, Timestamp interruptedStartTime,
			Timestamp startTime, Timestamp endTime, Integer status,
			Integer publishManId, Timestamp publishTime, Integer reviewManId,
			Timestamp reviewTime, Integer progarmmeId, String mome) {
		this.publishId = publishId;
		this.gvEquipment = gvEquipment;
		this.publishType = publishType;
		this.interruptedStartTime = interruptedStartTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.publishManId = publishManId;
		this.publishTime = publishTime;
		this.reviewManId = reviewManId;
		this.reviewTime = reviewTime;
		this.progarmmeId = progarmmeId;
		this.mome = mome;
	}*/

	// Property accessors

	public Integer getPublishId() {
		return this.publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}

/*	public GvEquipment getGvEquipment() {
		return this.gvEquipment;
	}

	public void setGvEquipment(GvEquipment gvEquipment) {
		this.gvEquipment = gvEquipment;
	}*/

	public Integer getPublishType() {
		return this.publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

	public Timestamp getInterruptedStartTime() {
		return this.interruptedStartTime;
	}

	public void setInterruptedStartTime(Timestamp interruptedStartTime) {
		this.interruptedStartTime = interruptedStartTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPublishManId() {
		return this.publishManId;
	}

	public void setPublishManId(Integer publishManId) {
		this.publishManId = publishManId;
	}

	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getReviewManId() {
		return this.reviewManId;
	}

	public void setReviewManId(Integer reviewManId) {
		this.reviewManId = reviewManId;
	}

	public Timestamp getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Integer getProgarmmeId() {
		return this.progarmmeId;
	}

	public void setProgarmmeId(Integer progarmmeId) {
		this.progarmmeId = progarmmeId;
	}

	public String getMome() {
		return this.mome;
	}

	public void setMome(String mome) {
		this.mome = mome;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getPublishManName() {
		return publishManName;
	}

	public void setPublishManName(String publishManName) {
		this.publishManName = publishManName;
	}


	public String getEquipmentName() {
		return equipmentName;
	}


	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}


	public String getProgarmmeName() {
		return progarmmeName;
	}


	public void setProgarmmeName(String progarmmeName) {
		this.progarmmeName = progarmmeName;
	}
	
	/**
	//  attilax 老哇的爪子 j_o_f   o7r   
	 * @return the prgrm
	 */
	public GvProgramme getPrgrm() {
		return prgrm;
	}

	/**
	//  attilax 老哇的爪子 j_o_f   o7r   
	 * @param prgrm the prgrm to set
	 */
	public void setPrgrm(GvProgramme prgrm) {
		this.prgrm = prgrm;
	}

	/**
	@author attilax 老哇的爪子
		@since  o7r j_n_l$
	
	 * @param gvProgramme
	 */
	public void setProgrm(GvProgramme gvProgramme) {
		// attilax 老哇的爪子  j_n_l   o7r 
	this.prgrm=gvProgramme;
		
	}


	public Integer getInterruptedTimeType() {
		return interruptedTimeType;
	}


	public void setInterruptedTimeType(Integer interruptedTimeType) {
		this.interruptedTimeType = interruptedTimeType;
	}

}