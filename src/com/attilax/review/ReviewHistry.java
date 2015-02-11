package com.attilax.review;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * Review entity. @author MyEclipse Persistence Tools
 */
@DataTransferObject 
@Entity
@Table(name = "reviewHistr", schema = "dbo", catalog = "gialweixin")
public class ReviewHistry implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer targetid;
	private String targettype;
	private Integer state;
	private String reviewer;
	private Integer reviewerid;
	private Timestamp timex;
	
	

	TargetObj tar;
	@Transient
	public TargetObj getTar() {
		return tar;
	}

	public void setTar(TargetObj tar) {
		this.tar = tar;
	}

	private String rvw_txt;

	// Constructors
	@Column(name = "rvw_txt")
	public String getRvw_txt() {
		return rvw_txt;
	}

	public void setRvw_txt(String rvw_txt) {
		this.rvw_txt = rvw_txt;
	}

	/** default constructor */
	public ReviewHistry() {
	}

	/** minimal constructor */
	public ReviewHistry(Integer targetid, String targettype) {
		this.targetid = targetid;
		this.targettype = targettype;
	}

	/** full constructor */
	public ReviewHistry(Integer targetid, String targettype, Integer state,
			String reviewer, Integer reviewerid, Timestamp timex) {
		this.targetid = targetid;
		this.targettype = targettype;
		this.state = state;
		this.reviewer = reviewer;
		this.reviewerid = reviewerid;
		this.timex = timex;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "targetid", nullable = false)
	public Integer getTargetid() {
		return this.targetid;
	}

	public void setTargetid(Integer targetid) {
		this.targetid = targetid;
	}

	@Column(name = "targettype", nullable = false, length = 50)
	public String getTargettype() {
		return this.targettype;
	}

	public void setTargettype(String targettype) {
		this.targettype = targettype;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "reviewer", length = 50)
	public String getReviewer() {
		return this.reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	@Column(name = "reviewerid")
	public Integer getReviewerid() {
		return this.reviewerid;
	}

	public void setReviewerid(Integer reviewerid) {
		this.reviewerid = reviewerid;
	}

	@Column(name = "timex", length = 23)
	public Timestamp getTimex() {
		return this.timex;
	}

	public void setTimex(Timestamp timex) {
		this.timex = timex;
	}

}