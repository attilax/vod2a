package com.focusx.playRec;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
import com.attilax.anno.displayType;
import com.attilax.anno.ignore4query;
import com.attilax.anno.op;
import com.attilax.count.CountRelt;
import com.attilax.count.GroupBy;
import com.attilax.count.GroupByDate;
import com.attilax.count.GroupByFld;
import com.attilax.count.NoFilt;
import com.attilax.count.OrderByTimeType;
import com.attilax.count.Table4count;
import com.attilax.count.TimeType;
import com.attilax.count.reduce;
import com.attilax.dsm.BizTypeEnum;
import com.attilax.dsm.TypeEnum;
import com.attilax.dsm.anno.BizType;
import com.attilax.dsm.anno.DisplayAdptr;
import com.attilax.dsm.anno.QueryAdptr;
import com.attilax.meta.DateAdptr_rangN4count4sql;
import com.focusx.elmt.GvMaterial;
import com.focusx.pojo.Equipment;
import com.focusx.programme.entity.GvProgramme;


/**
 * GvPlayRecord entity. @author MyEclipse Persistence Tools
 */
@DataTransferObject
@Entity
@Table(name="gv_play_record"
    ,schema="dbo"
    ,catalog=""
)
@Table4count(name="playcount_day" )
//@GroupByFldx ("material_description")
public class GvPlayRecord  implements java.io.Serializable {


    // Fields    
@Conditional
     private Integer prId;
     private Integer screen;
     @Conditional(displayType=displayType.rang,adptr=DateAdptr_rangN4count4sql.class)  
     @NoFilt
     @com.attilax.dsm.anno.Type(TypeEnum.Timestamp)
     @BizType(BizTypeEnum.daterang)
     @DisplayAdptr(displayType=displayType.rang)
     @QueryAdptr(sqlwhere=" rec.play_time>'@p' ")
     private Timestamp playTime;
     private Integer publishType;
@Conditional   @GroupBy
     private Integer materialId;
@Conditional
     private Integer equipmentId;

     @GroupBy
  String   materialDescription;
     
     
     Integer prgrmId;
     @Column(name="programme_id", length=23 )
     public Integer getPrgrmId() {
		return prgrmId;
	}


	public void setPrgrmId(Integer prgrmId) {
		this.prgrmId = prgrmId;
	}


	/**
//  attilax 老哇的爪子 9_54_0   o9q   
 * @return the materialDescription
 */
  @Transient
public String getMaterialDescription() {
	return materialDescription;
}


/**
//  attilax 老哇的爪子 9_54_0   o9q   
 * @param materialDescription the materialDescription to set
 */
public void setMaterialDescription(String materialDescription) {
	this.materialDescription = materialDescription;
}
//jeig condit only 4 count query
	@GroupBy @Conditional @ignore4query
     private Integer  groupid;
     
     /**
	//  attilax 老哇的爪子 2_49_6   o9p   
	 * @return the groupid
	 */
     @Transient   @GroupBy
	public Integer getGroupid() {
		return groupid;
	}


	/**
	//  attilax 老哇的爪子 2_49_6   o9p   
	 * @param groupid the groupid to set
	 */
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	 @reduce(reduceExp=" sum(timLen),count(*) as playCount")
	private Integer timLen;
     
     /**
	//  attilax 老哇的爪子 1_0_0   o9p   
	 * @return the timLen
	 */
     @Transient @reduce
	public Integer getTimLen() {
		return timLen;
	}


	/**
	//  attilax 老哇的爪子 1_0_0   o9p   
	 * @param timLen the timLen to set
	 */
	public void setTimLen(Integer timLen) {
		this.timLen = timLen;
	}
	
	@TimeType @OrderByTimeType
	String timeType;

	/**
	//  attilax 老哇的爪子 6_39_y   o9p   
	 * @return the timeType
	 */
	@Transient @TimeType @OrderByTimeType
	public String getTimeType() {
		return timeType;
	}


	/**
	//  attilax 老哇的爪子 6_39_y   o9p   
	 * @param timeType the timeType to set
	 */
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}


	String timeIdxType;
     /**
	//  attilax 老哇的爪子 m_58_49   o9o   
	 * @return the timeIdxType
	 */
     @Transient
	public String getTimeIdxType() {
		return timeIdxType;
	}


	/**
	//  attilax 老哇的爪子 m_58_49   o9o   
	 * @param timeIdxType the timeIdxType to set
	 */
	public void setTimeIdxType(String timeIdxType) {
		this.timeIdxType = timeIdxType;
	}


	Integer timeIdx;
     /**
	//  attilax 老哇的爪子 m_40_56   o9o   
	 * @return the timeIdx
	 */
     @Transient
	public Integer getTimeIdx() {
		return timeIdx;
	}


	/**
	//  attilax 老哇的爪子 m_40_56   o9o   
	 * @param timeIdx the timeIdx to set
	 */
	public void setTimeIdx(Integer timeIdx) {
		this.timeIdx = timeIdx;
	}


	/**
	//  attilax 老哇的爪子 m_40_56   o9o   
	 * @return the timeRang
	 */
    @Transient   @GroupBy
	public String getTimeRang() {
		return timeRang;
	}


	/**
	//  attilax 老哇的爪子 m_40_56   o9o   
	 * @param timeRang the timeRang to set
	 */
	public void setTimeRang(String timeRang) {
		this.timeRang = timeRang;
	}


	String timeRang;
     

    // Constructors

    /** default constructor */
    public GvPlayRecord() {
    }

    
    /** full constructor */
    public GvPlayRecord(Integer screen, Timestamp playTime, Integer publishType, Integer materialId, Integer equipmentId) {
        this.screen = screen;
        this.playTime = playTime;
        this.publishType = publishType;
        this.materialId = materialId;
        this.equipmentId = equipmentId;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="pr_id", unique=true, nullable=false)
    @RemoteProperty
    public Integer getPrId() {
        return this.prId;
    }
    
    public void setPrId(Integer prId) {
        this.prId = prId;
    }
    
    @Column(name="screen")
    @RemoteProperty
    public Integer getScreen() {
        return this.screen;
    }
    
    public void setScreen(Integer screen) {
        this.screen = screen;
    }
    
    @Column(name="play_time", length=23)
    @RemoteProperty @GroupByDate
    public Timestamp getPlayTime() {
        return this.playTime;
    }
    
    public void setPlayTime(Timestamp playTime) {
        this.playTime = playTime;
    }
    
    @Column(name="publish_type")

    public Integer getPublishType() {
        return this.publishType;
    }
    
    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }
    
    @Column(name="material_id")
    @RemoteProperty   @GroupBy
    public Integer getMaterialId() {
        return this.materialId;
    }
    
   
    
    @CountRelt  
    GvMaterial mtrl;
    
    
    
    
    /**
	//  attilax 老哇的爪子 5_l_39   o9n   
	 * @return the mtrl
	 */
    @ManyToOne
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name = "material_id", insertable = false,updatable = false)
  //    @Column(name="material_id") 
    @RemoteProperty
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

	
	public	 GvProgramme  prgrm;

	 @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "programme_id", insertable = false,updatable = false)
	public GvProgramme getPrgrm() {
		return prgrm;
	}


	public void setPrgrm(GvProgramme prgrm) {
		this.prgrm = prgrm;
	}


	public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
	
	
	@CriteriaRelt(fld="departId",uiFld="groupid" )  @CountRelt
	//@Join(fld="departId",uiFld="groupid" ) 
	Equipment eq;
	
    
    /**
	//  attilax 老哇的爪子 5_u_8   o9n   
	 * @return the eq
	 */
	  @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "equipment_id", insertable = false,updatable = false)
	   @RemoteProperty
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


	@Column(name="equipment_id")
	   @RemoteProperty
    public Integer getEquipmentId() {
        return this.equipmentId;
    }
    
    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
   








}