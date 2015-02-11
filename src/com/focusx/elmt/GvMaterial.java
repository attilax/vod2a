package com.focusx.elmt;

import java.sql.Timestamp;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.Closure;
 
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.attilax.ClosureNoExcpt;
import com.attilax.MDA.DateAdptr_rang;
import com.attilax.MDA.DateTimeDefVal;
import com.attilax.MDA.DwrConverter;
import com.attilax.MDA.TimeConverterO7;
import com.attilax.MDA.TimerFormater;
import com.attilax.MDA.rang;
import com.attilax.anno.BeanEditForm;
import com.attilax.anno.ConditionFieldCfg;
import com.attilax.anno.Conditional;
//import com.attilax.anno.DataConverte;
import com.attilax.anno.DataType;
import com.attilax.anno.DataTypeConstants;
import com.attilax.anno.DefVal;
import com.attilax.anno.ListForm;
import com.attilax.anno.NonVisual_list;
import com.attilax.anno.Order;
import com.attilax.anno.Validate;
import com.attilax.anno.ValidateSvrside;
import com.attilax.anno.displayType;
import com.attilax.anno.op;
 
import com.attilax.crud.OrderOP;
import com.attilax.dsm.adapt.DateAdptr_UiRang;
import com.attilax.dsm.anno.NoConditional;
import com.attilax.dsm.anno.NoFilt4condQuery;
import com.attilax.lang.anno.Property;
import com.attilax.review.Review;
import com.focusx.entity.TUserUsers;
import com.opensymphony.xwork2.validator.annotations.ConditionalVisitorFieldValidator;
//import com.sun.org.apache.commons.beanutils.Converter;

/** GvMaterial entity. @author MyEclipse Persistence Tools */
// converter=org.directwebremoting.hibernate.H3BeanConverter.class
@BeanEditForm(tmplt="c:/edit.html",reorder="aa,bb")
@ConditionFieldCfg(order="aa,bb",columns=3) 
@DynamicInsert @DynamicUpdate
@ListForm( reorder="aa,bb")  
@DataTransferObject @Entity @Table(name = "gv_material", schema = "") public class GvMaterial implements java.io.Serializable {

	// Fields // kk38 o7d 老哇的爪子 Attilax
	@com.attilax.anno.Converter(DwrConverter.class)
	@Order(OrderOP.Desc) @RemoteProperty @com.attilax.lang.anno.Translate(value = "") private Integer materialId;
	@Conditional(op = op.like) @RemoteProperty private String materialDescription;
	//------------------------
	@DataType(value=DataTypeConstants.select,datasource="c:/cate.htm")
 	@ValidateSvrside(min = 1)
	@RemoteProperty  @Property()  
	@Conditional(except="null,0")
	private Integer materialType;
	// @RemoteProperty private Integer createUser;
	@Conditional(displayType=displayType.single,adptr=DateAdptr_rang.class)
	@com.attilax.anno.Converter(TimeConverterO7.class)
	@DefVal(DateTimeDefVal.class)
	@RemoteProperty private Timestamp createTime;
	//------------
	//@DataConverte(defVal4OtherTypeAsNull = true, valideFailNotAdd_Querymode = true) 
	@RemoteProperty private Timestamp effectieTime;
	@RemoteProperty  @com.attilax.anno.Formater(TimerFormater.class) 
	@Conditional(adptr=DateAdptr_UiRang.class)
@NoFilt4condQuery
	private Timestamp failureTime;
	@RemoteProperty private String size;
	//@DataConverte(defValStr = "") 
	@NoConditional
	@RemoteProperty private String filePath;
	@RemoteProperty private String thumb;
	@RemoteProperty @Column(name = "thumb", length = 2506) 
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Conditional(displayType=displayType.kstm,adptr=DateAdptr_rang.class)
	@NonVisual_list
	@RemoteProperty private String canDownOrg;
	@RemoteProperty private Integer updateUser;
	@Conditional(nullval=8)
	Integer logicDel;
	public Integer getLogicDel() {
		return logicDel;
	}

	public void setLogicDel(Integer logicDel) {
		this.logicDel = logicDel;
	}

	@RemoteProperty 
	//@DataConverte(converter = Converter.class, nullVal = 0, defValInt = 0)
	@com.attilax.anno.Converter(TimeConverterO7.class)
	@Conditional(displayType = displayType.rang, rangStart = rang.start, rangEnd = rang.end,op=op.range) private Integer playtime;
	/** // attilax 老哇的爪子 jyt o7f
	 * @return the playtime */
	@RemoteProperty @Column(name = "play_time") public Integer getPlaytime() {
	 
	 
	 
		 
		return playtime;
	}

	/** // attilax 老哇的爪子 jyt o7f
	 * @param playtime the playtime to set */
	public void setPlaytime(Integer playtime) {
		this.playtime = playtime;
	}

	@RemoteProperty private Timestamp updateTime;
	//@ValidateSvrside(min = 1, valideFailOp_Querymode = valideFailOp.NotAdd2condt) 
	@Conditional
	@RemoteProperty private Integer applicationType;
	// @RemoteProperty
	 
	  private TUserUsers creator;

	// Constructors

	/** // attilax 老哇的爪子 V385 o7e  cascade = {CascadeType.PERSIST, CascadeType.MERGE}
	 * @return the creator */
	  @NotFound(action=NotFoundAction.IGNORE)
	 @ManyToOne(   )
 @JoinColumn(name="create_user")
	 @RemoteProperty
	 public TUserUsers getCreator() {
	 return creator;
	 }
	
	 /**
	 // attilax 老哇的爪子 V385 o7e
	 * @param creator the creator to set
	 */
	 public void setCreator(TUserUsers creator) {
	 this.creator = creator;
	 }

	/** default constructor */
	public GvMaterial() {}
	private static final long serialVersionUID = -5980674691414173578L;
	/** full constructor */
	public GvMaterial(String materialDescription, Integer materialType, Integer createUser, Timestamp createTime, Timestamp effectieTime, Timestamp failureTime, String size, String filePath, String canDownOrg, Integer updateUser, Timestamp updateTime, Integer applicationType) {
		this.materialDescription = materialDescription;
		this.materialType = materialType;
		// this.createUser = createUser;
		this.createTime = createTime;
		this.effectieTime = effectieTime;
		this.failureTime = failureTime;
		this.size = size;
		this.filePath = filePath;
		this.canDownOrg = canDownOrg;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.applicationType = applicationType;
	}

	// Property accessors
	@RemoteProperty 
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id @com.attilax.anno.Converter(DwrConverter.class)
	@GeneratedValue(generator = "generator")
	@Column(name = "material_id", unique = true, nullable = false) 
	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	@RemoteProperty @Column(name = "material_description", length = 256) public String getMaterialDescription() {
		return this.materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	@RemoteProperty @Column(name = "material_type") public Integer getMaterialType() {
		return this.materialType;
	}

	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}
	// @RemoteProperty
	// @Column(name = "create_user") public Integer getCreateUser() {
	// return this.createUser;
	// }

	// public void setCreateUser(Integer createUser) {
	// this.createUser = createUser;
	// }
	@RemoteProperty @Column(name = "create_time", length = 23) public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@RemoteProperty @Column(name = "effectie_time", length = 23) public Timestamp getEffectieTime() {
		return this.effectieTime;
	}

	public void setEffectieTime(Timestamp effectieTime) {
		this.effectieTime = effectieTime;
	}
	@RemoteProperty @Column(name = "failure_time", length = 23) public Timestamp getFailureTime() {
		return this.failureTime;
	}

	public void setFailureTime(Timestamp failureTime) {
		this.failureTime = failureTime;
	}
	@RemoteProperty @Column(name = "size", length = 10) public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	@RemoteProperty @Column(name = "file_path", length = 256) public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@RemoteProperty @Column(name = "can_down_org", length = 128) public String getCanDownOrg() {
		return this.canDownOrg;
	}
	@RemoteProperty
	Review revi;
	
	
	@RemoteProperty
	@Transient
	public Review getRevi() {
		return revi;
	}

	public void setRevi(Review revi) {
		this.revi = revi;
	}

	public void setCanDownOrg(String canDownOrg) {
		this.canDownOrg = canDownOrg;
	}
	@RemoteProperty @Column(name = "update_user") public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	@RemoteProperty @Column(name = "update_time", length = 23) public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@RemoteProperty @Column(name = "application_type") public Integer getApplicationType() {
		return this.applicationType;
	}

	public void setApplicationType(Integer applicationType) {
		this.applicationType = applicationType;
	}

}