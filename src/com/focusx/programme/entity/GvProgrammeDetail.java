package com.focusx.programme.entity;

import javax.persistence.Transient;

import org.directwebremoting.annotations.DataTransferObject;

import com.focusx.elmt.GvMaterial;

 
/**
 *  com.focusx.programme.entity.GvProgrammeDetail
 * @author luojun
 *   
 * Program Name:starx BS坐席
 *
 * Description: 节目单详情实体类
 * 
 * CreateTime: 2014-7-16  下午2:08:07
 *  
 *
 */
@DataTransferObject 
public class GvProgrammeDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pdId;
	/**
	 * 播放顺序
	 */
	private Integer playOrder;
	/**
	 * 节目表Id
	 */
	private Integer programmeId;
	/**
	 * 素材ID
	 */
	
	/**
	 * oa7
	 */
	GvMaterial mtrl;
	@Transient
	public GvMaterial getMtrl() {
		return mtrl;
	}


	public void setMtrl(GvMaterial mtrl) {
		this.mtrl = mtrl;
	}

	private Integer materialId;
	
	  private Integer materialType;
	     /**
		//  attilax 老哇的爪子 1_d_h   o8j   
		 * @return the materialType
		 */  @Transient
		public Integer getMaterialType() {
			return materialType;
		}


		/**
		//  attilax 老哇的爪子 1_d_h   o8j   
		 * @param materialType the materialType to set
		 */  @Transient
		public void setMaterialType(Integer materialType) {
			this.materialType = materialType;
		}

	// Constructors

	/** default constructor */
	public GvProgrammeDetail() {
	}

	/** minimal constructor */
	public GvProgrammeDetail(Integer pdId) {
		this.pdId = pdId;
	}

	/** full constructor */
	public GvProgrammeDetail(Integer pdId, Integer playOrder,
			Integer programmeId, Integer materialId) {
		this.pdId = pdId;
		this.playOrder = playOrder;
		this.programmeId = programmeId;
		this.materialId = materialId;
		System.out.println("--");
	}

	// Property accessors

	public Integer getPdId() {
		return this.pdId;
	}

	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}

	public Integer getPlayOrder() {
		return this.playOrder;
	}

	public void setPlayOrder(Integer playOrder) {
		this.playOrder = playOrder;
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

}