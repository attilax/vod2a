package com.focusx.downtask;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.attilax.Closure;
import com.attilax.MDA.DateAdptr_rang;
import com.attilax.MDA.TimeConverterO7;
import com.attilax.MDA.rang;
import com.attilax.anno.Conditional;
import com.attilax.anno.CriteriaRelt;
import com.attilax.anno.Orderby;
import com.attilax.anno.displayType;
import com.attilax.anno.op;
import com.attilax.collection.GvCycleQueue;
import com.attilax.count.CountRelt;
import com.attilax.count.GroupBy;
import com.attilax.dsm.BizTypeEnum;
import com.attilax.dsm.anno.BizType;
import com.attilax.dsm.anno.NoFilt4condQuery;
import com.attilax.queue.baseQueueData;
import com.focusx.elmt.GvMaterial;
import com.focusx.pojo.Equipment;
import com.focusx.programme.entity.GvProgramme;
import com.focusx.publish.entity.GvPublish;


/**   com.focusx.downtask.GvDownloadTask
 * GvDownloadTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="gv_download_task"
    ,schema=""
    ,catalog=""
)
@DynamicInsert @DynamicUpdate  @DataTransferObject 
public class GvDownloadTask  extends baseQueueData   implements java.io.Serializable {


    // Fields    
@Conditional
     private Integer dsId;
     private Integer noticeFlag;
     @Conditional(nullval=8)
     private Integer downloadStatus;
     private Integer materialId;
     @Conditional
     private Integer equipmentId;
 	@Conditional(displayType=displayType.rang)
 	@NoFilt4condQuery
     private Timestamp downloadCreateTime;
     private Timestamp downloadEndTime;
     @Transient
     @Orderby(value="desc",order=1)
     @BizType(BizTypeEnum.date)
     String timRang;
     private Integer shouldDown;
     private Integer actDown;
 	@Conditional(nullval=8)
     Integer startdownFlag;
 	@Conditional
 	Integer trueDownFlag;
     public Integer getTrueDownFlag() {
		return trueDownFlag;
	}


	public void setTrueDownFlag(Integer trueDownFlag) {
		this.trueDownFlag = trueDownFlag;
	}
	@Transient
	 public Long getSize() {
		return size;
	}


	public void setSize(Long size) {
		this.size = size;
	}
	private Long size;

	/**
	//  attilax 老哇的爪子 1_3_49   o9r   
	 * @return the timRang
	 */
     @Transient
	public String getTimRang() {
		return timRang;
	}


	public Integer getStartdownFlag() {
		return startdownFlag;
	}


	public void setStartdownFlag(Integer startdownFlag) {
		this.startdownFlag = startdownFlag;
	}


	/**
	//  attilax 老哇的爪子 1_3_49   o9r   
	 * @param timRang the timRang to set
	 */
	public void setTimRang(String timRang) {
		this.timRang = timRang;
	}


	/**
	//  attilax 老哇的爪子 1_3_49   o9r   
	 * @return the shouldDown
	 */ @Transient
	public Integer getShouldDown() {
		return shouldDown;
	}


	/**
	//  attilax 老哇的爪子 1_3_49   o9r   
	 * @param shouldDown the shouldDown to set
	 */ @Transient
	public void setShouldDown(Integer shouldDown) {
		this.shouldDown = shouldDown;
	}


	/**
	//  attilax 老哇的爪子 1_3_49   o9r   
	 * @return the actDown
	 */
	 @Transient
	public Integer getActDown() {
		return actDown;
	}


	/**
	//  attilax 老哇的爪子 1_3_49   o9r   
	 * @param actDown the actDown to set
	 */
	public void setActDown(Integer actDown) {
	//	
		this.actDown = actDown;
	}


	String opr;
     private Timestamp optim;
     
     
     /**
	//  attilax 老哇的爪子 j_f_44   o9o   
	 * @param optim the optim to set
	 */
	public void setOptim(Timestamp optim) {
		this.optim = optim;
	}


	/**
	//  attilax 老哇的爪子 i_r_0   o9o   
	 * @return the opr
	 */
	public String getOpr() {
		return opr;
	}


	/**
	//  attilax 老哇的爪子 i_r_0   o9o   
	 * @param opr the opr to set
	 */
	public void setOpr(String opr) {
		this.opr = opr;
	}


	/**
	//  attilax 老哇的爪子 i_r_0   o9o   
	 * @return the optim
	 */
	public Timestamp getOptim() {
		return optim;
	}

	
     GvProgramme prgrm;
     
     /**
	//  attilax 老哇的爪子 2_58_t   o9o   
	 * @return the prgrm
	 */
     @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "rltID2", insertable = false,updatable = false)

	public GvProgramme getPrgrm() {
		return prgrm;
	}


	/**
	//  attilax 老哇的爪子 2_58_t   o9o   
	 * @param prgrm the prgrm to set
	 */
	public void setPrgrm(GvProgramme prgrm) {
		this.prgrm = prgrm;
	}



	GvPublish pub;
     
     /**
	//  attilax 老哇的爪子 2_56_40   o9o   
	 * @return the pub
	 */
     @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "rltID", insertable = false,updatable = false)

	public GvPublish getPub() {
		return pub;
	}


	/**
	//  attilax 老哇的爪子 2_56_40   o9o   
	 * @param pub the pub to set
	 */
	public void setPub(GvPublish pub) {
		this.pub = pub;
	}


	@CriteriaRelt
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

		@GroupBy
		@CountRelt(uiFld="groupid",fld="departId")
		@CriteriaRelt(uiFld="groupid",fld="departId")
	Equipment eq;
	
 
 /**
	//  attilax 老哇的爪子 5_u_8   o9n   
	 * @return the eq
	 */
	  @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "equipment_id", insertable = false,updatable = false  )
	   
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

     
     
     
     Timestamp noticeBackTim;
     /**
	//  attilax 老哇的爪子 h_55_f   o90   
	 * @return the noticeBackTim
	 */
	public Timestamp getNoticeBackTim() {
		return noticeBackTim;
	}


	/**
	//  attilax 老哇的爪子 h_55_f   o90   
	 * @param noticeBackTim the noticeBackTim to set
	 */
	public void setNoticeBackTim(Timestamp noticeBackTim) {
		this.noticeBackTim = noticeBackTim;
	}
	//o8f
     private Integer materialType;
        private Integer retryTimes=0;  
        @Conditional
        private Integer  prgrmID=0;
        private Integer  pubID=0;
     /**
		//  attilax 老哇的爪子 2_47_40   o9i   
		 * @return the pubID
		 */
		public Integer getPubID() {
			return pubID;
		}


		/**
		//  attilax 老哇的爪子 2_47_40   o9i   
		 * @param pubID the pubID to set
		 */
		public void setPubID(Integer pubID) {
			this.pubID = pubID;
		}


	/**
		//  attilax 老哇的爪子 1_52_j   o9i   
		 * @return the prgrmID
		 */
		public Integer getPrgrmID() {
			return prgrmID;
		}


		/**
		//  attilax 老哇的爪子 1_52_j   o9i   
		 * @param prgrmID the prgrmID to set
		 */
		public void setPrgrmID(Integer prgrmID) {
			this.prgrmID = prgrmID;
		}
	String   sendMsg;
     String   sendMsgA;//  ati fmt
     /**
	//  attilax 老哇的爪子 下午11:09:57   2014-9-1   
	 * @return the sendMsgA
	 */
	public String getSendMsgA() {
		return sendMsgA;
	}


	/**
	//  attilax 老哇的爪子 下午11:09:57   2014-9-1   
	 * @param sendMsgA the sendMsgA to set
	 */
	public void setSendMsgA(String sendMsgA) {
		this.sendMsgA = sendMsgA;
	}
	Integer notice_back;
     private Integer curCursor;
     private Integer curCursorMaxIdx;
     public Integer getCurCursorMaxIdx() {
		return curCursorMaxIdx;
	}


	public void setCurCursorMaxIdx(Integer curCursorMaxIdx) {
		this.curCursorMaxIdx = curCursorMaxIdx;
	}


	public Integer getCurCursor() {
		return curCursor;
	}


	public void setCurCursor(Integer curCursor) {
		this.curCursor = curCursor;
	}


	/**
	//  attilax 老哇的爪子 i_5_m   o8r   
	 * @return the notice_back
	 */
      @Column(name="notice_back")
	public Integer getNotice_back() {
		return notice_back;
	}


	/**
	//  attilax 老哇的爪子 i_5_m   o8r   
	 * @param notice_back the notice_back to set
	 */
	public void setNotice_back(Integer notice_back) {
		this.notice_back = notice_back;
	}


	/**
	//  attilax 老哇的爪子 h_q_z   o8r   
	 * @return the lastSuccRetMsg
	 */
     @Column(name="lastSuccRetMsg")
	public String getLastSuccRetMsg() {
		return lastSuccRetMsg;
	}


	/**
	//  attilax 老哇的爪子 h_q_z   o8r   
	 * @param lastSuccRetMsg the lastSuccRetMsg to set
	 */
	public void setLastSuccRetMsg(String lastSuccRetMsg) {
		this.lastSuccRetMsg = lastSuccRetMsg;
	}


	/**
	//  attilax 老哇的爪子 h_q_z   o8r   
	 * @return the lastSuccTime
	 */ 
	@Column(name="lastSuccTime")
	public Timestamp getLastSuccTime() {
		return lastSuccTime;
	}
    public Integer getRetryTimes() {
		return retryTimes;
	}


	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}

	/**
	//  attilax 老哇的爪子 h_q_z   o8r   
	 S* @param lastSuccTime the lastSuccTime to set
	 */
	public void setLastSuccTime(Timestamp lastSuccTime) {
		this.lastSuccTime = lastSuccTime;
	}
	String lastSuccRetMsg;
     private Timestamp lastSuccTime;
     
  String   sendRetMsg;
  /**
//  attilax 老哇的爪子 1_48_o   o8r   
 * @return the sendMsg
 */
public String getSendMsg() {
	return sendMsg;
}


/**
//  attilax 老哇的爪子 1_48_o   o8r   
 * @param sendMsg the sendMsg to set
 */
public void setSendMsg(String sendMsg) {
	this.sendMsg = sendMsg;
}


/**
//  attilax 老哇的爪子 1_48_o   o8r   
 * @return the rltID
 */
public Integer getRltID() {
	return rltID;
}


/**pubid
//  attilax 老哇的爪子 1_48_o   o8r   
 * @param rltID the rltID to set
 */
public void setRltID(Integer rltID) {
	this.rltID = rltID;
}


/** prgrm id
//  attilax 老哇的爪子 1_48_o   o8r   
 * @return the rltID2
 */
public Integer getRltID2() {
	return rltID2;
}


/**
//  attilax 老哇的爪子 1_48_o   o8r   
 * @param rltID2 the rltID2 to set
 */
public void setRltID2(Integer rltID2) {
	this.rltID2 = rltID2;
}


/** 
//  attilax 老哇的爪子 1_48_o   o8r   
 * @return the rltID3
 */
public Integer getRltID3() {
	return rltID3;
}


/**
//  attilax 老哇的爪子 1_48_o   o8r   
 * @param rltID3 the rltID3 to set
 */
public void setRltID3(Integer rltID3) {
	this.rltID3 = rltID3;
}


/**
//  attilax 老哇的爪子 1_48_o   o8r   
 * @return the rltID4
 */
public Integer getRltID4() {
	return rltID4;
}


/**
//  attilax 老哇的爪子 1_48_o   o8r   
 * @param rltID4 the rltID4 to set
 */
public void setRltID4(Integer rltID4) {
	this.rltID4 = rltID4;
}
private Integer rltID;  private Integer rltID2;  private Integer rltID3;  private Integer rltID4;
     
 	/**
//  attilax 老哇的爪子 0_0_4   o8q   
 * @return the sendRetMsg
 */
  @Column(name="SendRetMsg")
public String getSendRetMsg() {
	return sendRetMsg;
}


/**
//  attilax 老哇的爪子 0_0_4   o8q   
 * @param sendRetMsg the sendRetMsg to set
 */
public void setSendRetMsg(String sendRetMsg) {
	this.sendRetMsg = sendRetMsg;
}
	@com.attilax.anno.Converter(TimeConverterO7.class)
 	 @Transient
 	@Conditional(displayType = displayType.rang, rangStart = rang.start, rangEnd = rang.end,op=op.range) private Integer playtime;

     /**
	//  attilax 老哇的爪子 0_2_l   o8q   
	 * @return the playtime
	 */ @Transient
	public Integer getPlaytime() {
		return playtime;
	}


	/**
	//  attilax 老哇的爪子 0_2_l   o8q   
	 * @param playtime the playtime to set
	 */ @Transient
	public void setPlaytime(Integer playtime) {
		this.playtime = playtime;
	}


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


	/**
	//  attilax 老哇的爪子 1_d_h   o8j   
	 * @return the effectieTime
	 */  @Transient
	public Timestamp getEffectieTime() {
		return effectieTime;
	}


	/**
	//  attilax 老哇的爪子 1_d_h   o8j   
	 * @param effectieTime the effectieTime to set
	 */  @Transient
	public void setEffectieTime(Timestamp effectieTime) {
		this.effectieTime = effectieTime;
	}


	/**
	//  attilax 老哇的爪子 1_d_h   o8j   
	 * @return the failureTime
	 */  @Transient
	public Timestamp getFailureTime() {
		return failureTime;
	}


	/**
	//  attilax 老哇的爪子 1_d_h   o8j   
	 * @param failureTime the failureTime to set
	 */  @Transient
	public void setFailureTime(Timestamp failureTime) {
		this.failureTime = failureTime;
	}
	  @Transient
	private Timestamp effectieTime;
     private Timestamp failureTime;
     private String materialDescription;
   
/**
	//  attilax 老哇的爪子 1_42_47   o8j   
	 * @return the materialDescription
	 */  @Transient
	public String getMaterialDescription() {
		return materialDescription;
	}


	/**
	//  attilax 老哇的爪子 1_42_47   o8j   
	 * @param materialDescription the materialDescription to set
	 */
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

public String file ;

    // Constructors

    /**
//  attilax 老哇的爪子 i_49_o   o7r   
 * @return the file
 */
//@  ,,,Column(name="[file]")
     @Transient
public String getFile() {
	return file;
}


/**
//  attilax 老哇的爪子 i_49_o   o7r   
 * @param file the file to set
 */
public void setFile(String file) {
	this.file = file;
}


	/** default constructor */
    public GvDownloadTask() {
    }

    
    /** full constructor */
    public GvDownloadTask(Integer noticeFlag, Integer downloadStatus, Integer materialId, Integer equipmentId, Timestamp downloadCreateTime, Timestamp downloadEndTime) {
        this.noticeFlag = noticeFlag;
        this.downloadStatus = downloadStatus;
        this.materialId = materialId;
        this.equipmentId = equipmentId;
        this.downloadCreateTime = downloadCreateTime;
        this.downloadEndTime = downloadEndTime;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="ds_id", unique=true, nullable=false)

    public Integer getDsId() {
        return this.dsId;
    }
    
    public void setDsId(Integer dsId) {
        this.dsId = dsId;
    }
    
    @Column(name="notice_flag")

    public Integer getNoticeFlag() {
        return this.noticeFlag;
    }
    
    public void setNoticeFlag(Integer noticeFlag) {
        this.noticeFlag = noticeFlag;
    }
    
    @Column(name="download_status")

    public Integer getDownloadStatus() {
        return this.downloadStatus;
    }
    
    public void setDownloadStatus(Integer downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
    
    @Column(name="material_id")

    public Integer getMaterialId() {
        return this.materialId;
    }
    
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    
    @Column(name="equipment_id")

    public Integer getEquipmentId() {
        return this.equipmentId;
    }
    
    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
    
    @Column(name="download_create_time", length=23)

    public Timestamp getDownloadCreateTime() {
        return this.downloadCreateTime;
    }
    
    public void setDownloadCreateTime(Timestamp downloadCreateTime) {
        this.downloadCreateTime = downloadCreateTime;
    }
    
    @Column(name="download_end_time", length=23)

    public Timestamp getDownloadEndTime() {
        return this.downloadEndTime;
    }
    
    public void setDownloadEndTime(Timestamp downloadEndTime) {
        this.downloadEndTime = downloadEndTime;
    }


	/**
	@author attilax 老哇的爪子
		@since  o9r f_r_51   
	
	 * @param closure
	 */
	public void setOpr(Closure<Object, String> closure) {
		// attilax 老哇的爪子  f_r_51   o9r 
		
	}
   








}