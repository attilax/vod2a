/**
 * @author attilax 老哇的爪子
\t@since  Sep 1, 2014 5:50:50 AM$
 */
package com.attilax.log;
import com.attilax.core;

import static  com.attilax.core.*;

import java.sql.Timestamp;
import java.util.*;
import java.net.*;
import java.io.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
/**
 * @author  attilax 老哇的爪子
 *@since  Sep 1, 2014 5:50:50 AM$
 */
@DynamicInsert @DynamicUpdate
@Entity @Table(name = "logRec") 
public class logRec {

	/**
	 * @category id
	 */
	private Integer id;
	String thread;
	String logger;
	String classx;
	String cate;
	/**
	//  attilax 老哇的爪子 0_53_z   o93   
	 * @return the cate
	 */
	public String getCate() {
		return cate;
	}
	/**
	//  attilax 老哇的爪子 0_53_z   o93   
	 * @param cate the cate to set
	 */
	public void setCate(String cate) {
		this.cate = cate;
	}

	String meth;
	String send; //recv
	Integer  idx;	 //pub,progrm,mtr id
	 Integer queId;  //dsid 
	 /**
	//  attilax 老哇的爪子 h_38_i   o90   
	 * @return the ret
	 */
	 @Column(name="ret",length=8000)
	public String getRet() {
		return ret;
	}
	/**
	//  attilax 老哇的爪子 h_38_i   o90   
	 * @param ret the ret to set
	 */
	public void setRet(String ret) {
		this.ret = ret;
	}

	String ret;
	/**
	//  attilax 老哇的爪子 h_w_a   o90   
	 * @return the queId
	 */
	public Integer getQueId() {
		return queId;
	}
	/**
	//  attilax 老哇的爪子 h_w_b   o90   
	 * @param queId the queId to set
	 */
	public void setQueId(Integer queId) {
		this.queId = queId;
	}
	public String getSend() {
		return send;
	}
/**
 * set or recv
@author attilax 老哇的爪子
	@since  2014-9-2 上午01:56:22   

 * @param send
 */
	public void setSend(String send) {
		this.send = send;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getIdx2() {
		return idx2;
	}

	public void setIdx2(Integer idx2) {
		this.idx2 = idx2;
	}

	public Integer getIdx3() {
		return idx3;
	}

	public void setIdx3(Integer idx3) {
		this.idx3 = idx3;
	}

	Integer  idx2;	Integer  idx3;
	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getClassx() {
		return classx;
	}

	public void setClassx(String classx) {
		this.classx = classx;
	}

	public String getMeth() {
		return meth;
	}

	public void setMeth(String meth) {
		this.meth = meth;
	}

	public Integer getLinenum() {
		return linenum;
	}

	public void setLinenum(Integer linenum) {
		this.linenum = linenum;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	 String   msg2;
	 /**
	//  attilax 老哇的爪子 1_47_v   o09   
	 * @return the msg2
	 */
	 @Column(length=7900)
	public String getMsg2() {
		return msg2;
	}
	/**
	//  attilax 老哇的爪子 1_47_v   o09   
	 * @param msg2 the msg2 to set
	 */
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	/**
	//  attilax 老哇的爪子 1_47_v   o09   
	 * @return the msg3
	 */
	 @Column(length=7900)
	public String getMsg3() {
		return msg3;
	}
	/**
	//  attilax 老哇的爪子 1_47_v   o09   
	 * @param msg3 the msg3 to set
	 */
	public void setMsg3(String msg3) {
		this.msg3 = msg3;
	}

	String   msg3;
	 
	   String   msgA;//  ati fmt
	     /**
	      * msg content
		//  attilax 老哇的爪子 下午11:09:57   2014-9-1   
		 * @return the MsgA
		 */
		public String getMsgA() {
			return msgA;
		}


		/**msg content
		//  attilax 老哇的爪子 下午11:09:57   2014-9-1   
		 * @param MsgA the MsgA to set
		 */
		public void setMsgA(String MsgA) {
			this.msgA = MsgA;
		}
	public Timestamp getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Timestamp createTime) {
		CreateTime = createTime;
	}

	Integer linenum;

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "native") 
	@Id @GeneratedValue(generator = "generator") @Column(name = "id", unique = true, nullable = false) public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	String level;
	String msg;
	private Timestamp CreateTime;

	/**
	@author attilax 老哇的爪子
	\t@since  Sep 1, 2014 5:50:50 AM$
	
	 * @param args
	 */
	public static void main(String[] args) {
		// attilax 老哇的爪子  5:50:50 AM   Sep 1, 2014 

		{
		}

	}
	//  attilax 老哇的爪子 5:50:50 AM   Sep 1, 2014   
}

//  attilax 老哇的爪子