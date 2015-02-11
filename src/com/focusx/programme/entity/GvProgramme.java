package com.focusx.programme.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.Transient;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

import com.attilax.review.Review;

/**
 *  com.focusx.programme.entity.GvProgramme
 * @author luojun
 * 
 * Program Name:starx BS坐席
 *
 * Description: 节目单实体类   
 * 
 * CreateTime: 2014-7-16  下午2:07:12
 *  
 *
 */
@DataTransferObject 
public class GvProgramme implements java.io.Serializable {

	// Fields
 
	Review revi;
	
	
	 
	@Transient
	public Review getRevi() {
		return revi;
	}

	public void setRevi(Review revi) {
		this.revi = revi;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	private Integer progarmmeId;
	/**
	 * 描述
	 */
	private String describe;
	/**
	 * 分屏区
	 */
	private Integer screen;
	/**
	 * 总时长
	 */
	private Double totalDuration;
	/**
	 * 素材数量
	 */
	private Integer materialNumber;
	/**
	 * 创建人
	 */
	private Integer createMan;
	/**
	 * 创建人名字
	 */
	private String createManName;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	
	/**
	 * 以下字段为查询节目单门店接收情况需要的字段
	 * g.groupid,g.groupname,e.equipment_id,e.mome,c.del
	 */
	private Integer groupid;             //门店id
	private String groupname;            //门店名称
	private Integer equipmentid;         //设备id
	private String mome;                 //设备描述
	private Integer del;                 //是否接收
	
	/**
	 * 集合属性只能以接口声明。例如在下面的代码中，schools的类型只能是List，不能是ArrayList，但该集合属性必须使用实现类完成初始化.
	 */
	public List list=new ArrayList();
	// Constructors

	/**
	//  attilax 老哇的爪子 j_s_b   o7r   
	 * @return the list
	 */
	public List getList() {
		return list;
	}

	/**
	//  attilax 老哇的爪子 j_s_b   o7r   
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
	// Constructors
	
	/** default constructor */
	public GvProgramme() {
	}

	/** minimal constructor */
	public GvProgramme(Integer progarmmeId) {
		this.progarmmeId = progarmmeId;
	}

	/** full constructor */
	public GvProgramme(Integer progarmmeId, String describe, Integer screen,
			Double totalDuration, Integer materialNumber, Integer createMan,
			Timestamp createTime) {
		this.progarmmeId = progarmmeId;
		this.describe = describe;
		this.screen = screen;
		this.totalDuration = totalDuration;
		this.materialNumber = materialNumber;
		this.createMan = createMan;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getProgarmmeId() {
		return this.progarmmeId;
	}

	public void setProgarmmeId(Integer progarmmeId) {
		this.progarmmeId = progarmmeId;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getScreen() {
		return this.screen;
	}

	public void setScreen(Integer screen) {
		this.screen = screen;
	}

	public Double getTotalDuration() {
		return this.totalDuration;
	}

	public void setTotalDuration(Double totalDuration) {
		this.totalDuration = totalDuration;
	}

	public Integer getMaterialNumber() {
		return this.materialNumber;
	}

	public void setMaterialNumber(Integer materialNumber) {
		this.materialNumber = materialNumber;
	}

	public Integer getCreateMan() {
		return this.createMan;
	}

	public void setCreateMan(Integer createMan) {
		this.createMan = createMan;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateManName() {
		return createManName;
	}

	public void setCreateManName(String createManName) {
		this.createManName = createManName;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getMome() {
		return mome;
	}

	public void setMome(String mome) {
		this.mome = mome;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}
	
}