package com.focusx.downRec;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.attilax.MDA.DateAdptr_rang;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
import com.attilax.anno.JoinConditional;
import com.attilax.anno.displayType;
import com.attilax.dsm.anno.NoFilt4condQuery;
import com.attilax.dsm.anno.distinct4query;
import com.attilax.dsm.anno.join;
import com.focusx.elmt.GvMaterial;
import com.focusx.pojo.Equipment;

/**    GvDownloadRecord entity. @author MyEclipse Persistence Tools */
@DynamicInsert @DynamicUpdate  @DataTransferObject  @distinct4query
@Entity @Table(name = "gv_download_record_v", schema = "", catalog = "") public class GvDownloadRecord4distinct implements java.io.Serializable {

	// Fields

	private Integer drId;
	private Integer downloadFlag;
	@Conditional(displayType=displayType.rang)
	@NoFilt4condQuery
	private Timestamp downloadOverTime;
	private Integer programmeId;
	@Conditional
	private Integer materialId;
	private Integer equipmentId;
	private String sjFileName;

	// Constructors

	/** default constructor */
	public GvDownloadRecord4distinct() {}

	/** full constructor */
	public GvDownloadRecord4distinct(Integer downloadFlag, Timestamp downloadOverTime, Integer programmeId, Integer materialId, Integer equipmentId, String sjFileName) {
		this.downloadFlag = downloadFlag;
		this.downloadOverTime = downloadOverTime;
		this.programmeId = programmeId;
		this.materialId = materialId;
		this.equipmentId = equipmentId;
		this.sjFileName = sjFileName;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment") @Id @GeneratedValue(generator = "generator") @Column(name = "dr_id", unique = true, nullable = false) public Integer getDrId() {
		return this.drId;
	}

	public void setDrId(Integer drId) {
		this.drId = drId;
	}
	
	 
	@JoinConditional 
	 GvMaterial mtrl;
	    
	    
	    /**
		//  attilax 老哇的爪子 5_l_39   o9n   
		 * @return the mtrl
		 */
	    @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "material_id", insertable = false,updatable = false)
	  //    @Column(name="material_id") 
	  
		public GvMaterial getMtrl() {
			return mtrl;
		}


		/**
		//  attilax 老哇的爪子 5_l_39   o9n   
		 * @param mtrl the mtrl to set
		 */
		public void setMtrl(GvMaterial mtrl) {
			this.mtrl = mtrl;
		}

		
	@CriteriaRelt(fld="departId",uiFld="groupid")
	Equipment eq;
	
    
    /**
	//  attilax 老哇的爪子 5_u_8   o9n   
	 * @return the eq
	 */
	  @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "equipment_id", insertable = false,updatable = false)
	   
	public Equipment getEq() {
		return eq;
	}


	/**
	//  attilax 老哇的爪子 5_u_8   o9n   
	 * @param eq the eq to set
	 */
	public void setEq(Equipment eq) {
		this.eq = eq;
	}
	Integer rmt_file_stat;

	@Column(name = "rmt_file_stat") 
	public Integer getRmt_file_stat() {
		return rmt_file_stat;
	}

	public void setRmt_file_stat(Integer rmt_file_stat) {
		this.rmt_file_stat = rmt_file_stat;
	}

	@Column(name = "download_flag") public Integer getDownloadFlag() {
		return this.downloadFlag;
	}

	public void setDownloadFlag(Integer downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	@Column(name = "download_over_time", length = 23) public Timestamp getDownloadOverTime() {
		return this.downloadOverTime;
	}

	public void setDownloadOverTime(Timestamp downloadOverTime) {
		this.downloadOverTime = downloadOverTime;
	}

	@Column(name = "programme_id") public Integer getProgrammeId() {
		return this.programmeId;
	}

	public void setProgrammeId(Integer programmeId) {
		this.programmeId = programmeId;
	}

	@Column(name = "material_id") public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	@Column(name = "equipment_id") public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Column(name = "sj_file_name", length = 256) public String getSjFileName() {
		return this.sjFileName;
	}

	public void setSjFileName(String sjFileName) {
		this.sjFileName = sjFileName;
	}

}