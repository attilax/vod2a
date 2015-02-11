/**
 *  com.attilax.biz.Timerang.TimeRangRec
 * com.attilax.biz.Timerang.TimeRangRec
 * @author attilax 老哇的爪子
	@since  o02 2_53_f$
 */
package com.attilax.biz.Timerang;

import static com.attilax.core.*;
import java.sql.Timestamp;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.GenericGenerator;
import com.attilax.MDA.DwrConverter;
import com.attilax.anno.DataType;
import com.attilax.anno.DataTypeConstants;
import com.attilax.dsm.Title;
/** @author attilax 老哇的爪子
 * @since o02 2_53_f$ */
@DataTransferObject
@Title("时间段设置") @Entity
public class TimeRangRec {
	// attilax 老哇的爪子 2_53_f o02
	
	 private Integer id;
	/**
	//  attilax 老哇的爪子 j_56_0   o03   
	 * @return the id
	 */
		@GenericGenerator(name = "generator", strategy = "increment")
		@Id 
		@GeneratedValue(generator = "generator")
	//	@Column(name = "material_id", unique = true, nullable = false) 
	 
	public Integer getId() {
		return id;
	}
	/**
	//  attilax 老哇的爪子 j_56_0   o03   
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	@Name("开始时间") 
	@DataType(value=DataTypeConstants.time)
	String startTime;
	@Name("停止时间") @DataType(value=DataTypeConstants.time)
	String endTime;
	/** // attilax 老哇的爪子 2_55_c o02
	 * @return the startTime */
	public String getStartTime() {
		return startTime;
	}
	/** // attilax 老哇的爪子 2_55_c o02
	 * @param startTime the startTime to set */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/** // attilax 老哇的爪子 2_55_c o02
	 * @return the endTime */
	public String getEndTime() {
		return endTime;
	}
	/** // attilax 老哇的爪子 2_55_c o02
	 * @param endTime the endTime to set */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}

// attilax 老哇的爪子