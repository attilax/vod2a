package com.attilax.review;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ReviewId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ReviewId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer targetid;
	private String targettype;
	private Integer state;
	private String reviewer;
	private Integer reviewerid;
	private Timestamp timex;

	// Constructors

	/** default constructor */
	public ReviewId() {
	}

	/** minimal constructor */
	public ReviewId(Integer id, Integer targetid, String targettype) {
		this.id = id;
		this.targetid = targetid;
		this.targettype = targettype;
	}

	/** full constructor */
	public ReviewId(Integer id, Integer targetid, String targettype,
			Integer state, String reviewer, Integer reviewerid, Timestamp timex) {
		this.id = id;
		this.targetid = targetid;
		this.targettype = targettype;
		this.state = state;
		this.reviewer = reviewer;
		this.reviewerid = reviewerid;
		this.timex = timex;
	}

	// Property accessors

	@Column(name = "id", nullable = false)
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReviewId))
			return false;
		ReviewId castOther = (ReviewId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTargetid() == castOther.getTargetid()) || (this
						.getTargetid() != null
						&& castOther.getTargetid() != null && this
						.getTargetid().equals(castOther.getTargetid())))
				&& ((this.getTargettype() == castOther.getTargettype()) || (this
						.getTargettype() != null
						&& castOther.getTargettype() != null && this
						.getTargettype().equals(castOther.getTargettype())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null && castOther.getState() != null && this
						.getState().equals(castOther.getState())))
				&& ((this.getReviewer() == castOther.getReviewer()) || (this
						.getReviewer() != null
						&& castOther.getReviewer() != null && this
						.getReviewer().equals(castOther.getReviewer())))
				&& ((this.getReviewerid() == castOther.getReviewerid()) || (this
						.getReviewerid() != null
						&& castOther.getReviewerid() != null && this
						.getReviewerid().equals(castOther.getReviewerid())))
				&& ((this.getTimex() == castOther.getTimex()) || (this
						.getTimex() != null && castOther.getTimex() != null && this
						.getTimex().equals(castOther.getTimex())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTargetid() == null ? 0 : this.getTargetid().hashCode());
		result = 37
				* result
				+ (getTargettype() == null ? 0 : this.getTargettype()
						.hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result
				+ (getReviewer() == null ? 0 : this.getReviewer().hashCode());
		result = 37
				* result
				+ (getReviewerid() == null ? 0 : this.getReviewerid()
						.hashCode());
		result = 37 * result
				+ (getTimex() == null ? 0 : this.getTimex().hashCode());
		return result;
	}

}