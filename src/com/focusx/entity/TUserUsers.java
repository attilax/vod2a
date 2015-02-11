package com.focusx.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.directwebremoting.spring.DwrSpringServlet;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
//com.focusx.entity.TUserUsers
@DataTransferObject 
public class TUserUsers implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	@RemoteProperty
	private Integer id;//主键
	@RemoteProperty 
	private String workNo;//座作员工号(座席类必须为数字组合)
	private String name;//操作员真实姓名
	private String shortName;//操作员简称
	private String byname;//别名
	private String passWord;//操作员密码
	private String tel;//电话号码
	private String extensionTel;//分机号码
	private String mobileTel;//移动电话
	private String email;//电子邮箱地址
	private String address;//详细地址
	private String computerName;//计算机名
	private String ipaddress;//计算机IP地址
	private Integer isSystem;//是否是系统操作员(1表示是,0表示不是)\0)\0aramete
	private Short isValid;//操作员记录是否有效(=0无效即逻辑删除,=1有效)\0mete
	private Integer agentActivate;//激活条件
	private Integer defaultGroup;//默认组    belong subcom ati
	private String workno2;

	//角色ID  ，将利用这个Id，列出属于这个角色的所有用户
	private int roleId;
	
	private String roleIds="";		//角色Id组 用","分割
	
	
	TMbGroup org;

	// Constructors

	/**
	//  attilax 老哇的爪子 0_47_y   o0m   
	 * @return the org
	 */
	public TMbGroup getOrg() {
		return org;
	}

	/**
	//  attilax 老哇的爪子 0_47_y   o0m   
	 * @param org the org to set
	 */
	public void setOrg(TMbGroup org) {
		this.org = org;
	}

	/**
	//  attilax 老哇的爪子 5_w_39   o9n   
	 * @return the dpt
	 */
 
	 @ManyToOne
	    @NotFound(action=NotFoundAction.IGNORE)
	    @JoinColumn(name = "departId", insertable = false,updatable = false)
	public TMbGroup getDpt() {
		return org;
	}

	/**
	//  attilax 老哇的爪子 5_w_39   o9n   
	 * @param dpt the dpt to set
	 */
	public void setDpt(TMbGroup dpt) {
		this.org = dpt;
	}
	
	public TUserUsers() {
	}
	@RemoteProperty 
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
	//	DwrSpringServlet d=new DwrSpringServlet();
		this.id = id;
	}
	@RemoteProperty 
	public String getWorkNo() {
		return this.workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getByname() {
		return this.byname;
	}

	public void setByname(String byname) {
		this.byname = byname;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getExtensionTel() {
		return this.extensionTel;
	}

	public void setExtensionTel(String extensionTel) {
		this.extensionTel = extensionTel;
	}

	public String getMobileTel() {
		return this.mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComputerName() {
		return this.computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public Short getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Short isValid) {
		this.isValid = isValid;
	}

	public Integer getAgentActivate() {
		return this.agentActivate;
	}

	public void setAgentActivate(Integer agentActivate) {
		this.agentActivate = agentActivate;
	}


	public Integer getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(Integer defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public String getWorkno2() {
		return this.workno2;
	}

	public void setWorkno2(String workno2) {
		this.workno2 = workno2;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

}