package com.focusx.util;

import java.util.List;

import com.focusx.entity.TUserUsers;

/**
 * 角色类型
 * @author zhoujianbin
 *
 */
public class RoleType {
	public Integer roleId;
	public String roleName;
	public List<TUserUsers> users;
	public Integer state;
	
	
	
	public RoleType() {
		super();
	}
	public RoleType(Integer roleId, String roleName, List<TUserUsers> users) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
	}
	public RoleType(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<TUserUsers> getUsers() {
		return users;
	}
	public void setUsers(List<TUserUsers> users) {
		this.users = users;
	} 
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
