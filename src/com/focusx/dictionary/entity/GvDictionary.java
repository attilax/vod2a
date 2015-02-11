package com.focusx.dictionary.entity;

import java.sql.Timestamp;
 


/**
 * Dictionary entity. @author MyEclipse Persistence Tools
 */
public class GvDictionary implements java.io.Serializable {


	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @since Ver 1.1    
	 */    
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String DCode;
	private String DName;
	private String DPcode;
	private String DIsvalid;
	private Integer DLevel;
	private String DRemar;
	private Timestamp createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDCode() {
		return DCode;
	}
	public void setDCode(String dCode) {
		DCode = dCode;
	}
	public String getDName() {
		return DName;
	}
	public void setDName(String dName) {
		DName = dName;
	}
	public String getDPcode() {
		return DPcode;
	}
	public void setDPcode(String dPcode) {
		DPcode = dPcode;
	}
	public String getDIsvalid() {
		return DIsvalid;
	}
	public void setDIsvalid(String dIsvalid) {
		DIsvalid = dIsvalid;
	}
	public String getDRemar() {
		return DRemar;
	}
	public void setDRemar(String dRemar) {
		DRemar = dRemar;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getDLevel() {
		return DLevel;
	}
	public void setDLevel(Integer dLevel) {
		DLevel = dLevel;
	}
	 
 
	
 
 

}
