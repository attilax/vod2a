package com.attilax.collection;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/** GvCycleQueue entity. @author MyEclipse Persistence Tools */
@DynamicInsert @DynamicUpdate
@Entity @Table(name = "gv_cycleQueue") public class GvCycleQueue implements java.io.Serializable {

	// Fields

	/**
	 *  * @author  attilax 老哇的爪子
	 *@since  o8q 0_a_o$
	 */
	private static final long serialVersionUID = -8094268729574940010L;
	private Integer id;
	private Integer cirTimes;
	private Timestamp createtime;
	private String rectype;
	private Integer del;
	private String rltRecId;
	 String   sendRetMsg;
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

	   String   sendMsg;
	  
	  
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

		String lastSuccRetMsg;
	     private Timestamp lastSuccTime;
		private Integer retryTimes;
	     
	 // String   sendRetMsg;
		/**
		//  attilax 老哇的爪子 h_q_z   o8r   
		 * @return the lastSuccTime
		 */ 
		@Column(name="lastSuccTime")
		public Timestamp getLastSuccTime() {
			return lastSuccTime;
		}
	    /**
		//  attilax 老哇的爪子 m_r_t   o8r   
		 * @param lastSuccTime the lastSuccTime to set
		 */
		public void setLastSuccTime(Timestamp lastSuccTime) {
			this.lastSuccTime = lastSuccTime;
		}


		public Integer getRetryTimes() {
			return retryTimes;
		}


		public void setRetryTimes(Integer retryTimes) {
			this.retryTimes = retryTimes;
		}
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

	// Constructors

	/** default constructor */
	public GvCycleQueue() {}

	/** minimal constructor */
	public GvCycleQueue(Integer cirTimes, Timestamp createtime, String rectype, Integer del) {
		this.cirTimes = cirTimes;
		this.createtime = createtime;
		this.rectype = rectype;
		this.del = del;
	}

	/** full constructor */
	public GvCycleQueue(Integer cirTimes, Timestamp createtime, String rectype, Integer del, String rltRecId) {
		this.cirTimes = cirTimes;
		this.createtime = createtime;
		this.rectype = rectype;
		this.del = del;
		this.rltRecId = rltRecId;
	}

	  
	    
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "native") 
	@Id @GeneratedValue(generator = "generator") @Column(name = "id", unique = true, nullable = false) public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "cirTimes", nullable = false) public Integer getCirTimes() {
		return this.cirTimes;
	}

	public void setCirTimes(Integer cirTimes) {
		this.cirTimes = cirTimes;
	}

	@Column(name = "createtime", nullable = false, length = 23) public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "rectype", nullable = false, length = 50) public String getRectype() {
		return this.rectype;
	}

	public void setRectype(String rectype) {
		this.rectype = rectype;
	}

	@Column(name = "del", nullable = false) public Integer getDel() {
		return this.del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	@Column(name = "rltRecID", length = 50) public String getRltRecId() {
		return this.rltRecId;
	}

	public void setRltRecId(String rltRecId) {
		this.rltRecId = rltRecId;
	}

}