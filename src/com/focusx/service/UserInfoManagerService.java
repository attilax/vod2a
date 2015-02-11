package com.focusx.service;

import java.util.List;
import java.util.Map;

import com.focusx.entity.TUserUsers;
import com.focusx.pager.Pager;
public interface UserInfoManagerService {

	/**
	 * 根据查询条件获取用户列表
	 * @param page 分页
	 * @param pageParams 起始页和每页显示数 数组
	 * @condition 查询条件
	 */
	public List<TUserUsers> getSearchUsers(String condition,Pager page);
	/**
	 * 获取所有用户信息
	 * @param page 分页
	 * @param pageParams 起始页和每页显示数 数组
	 * @return list
	 */
	public List<TUserUsers> getPagedUsers(Pager page);
	
	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return TUserUsers
	 */
	public TUserUsers getUserById(int userId);
	
	/**
	 * 更新修改的用户信息
	 * @param user
	 * @return boolean
	 */
	public boolean updateUserInfo(TUserUsers user);
	
	/**
	 * 插入新增的用户信息
	 * @param user
	 * @return Number  返回插入对象的主键ID
	 */
	public Number insertUserInfo(TUserUsers user);
	
	/**
	 *根据用户ID,批量删除用户 
	 */
	public boolean deleteUsers(String deleteUsersList);
	
	/**
	 * 通过用户编码获取用户
	 */
	public TUserUsers getUserByWorkNo(String workNo);
	
	/**
	 *  根据用户名称和用户密码获取用户
	 *  @param data 查询条件
	 */
	public TUserUsers login(Map<String, String> data);
	
	/**
	 *  根据用户姓名查找用户
	 *  @param name
	 *  @return TUserUsers
	 */
	public TUserUsers getUserByName(String name);
	
	/**
	 * 获取所有角色ID组不为空的 用户
	 * @return
	 */
	public List<TUserUsers> getAllUsersByRoleIds(Map<String, Object> data);
	
	/**
	 * 修改用户角色
	 * @param ids
	 * @return
	 */
	public boolean updateRole(String ids, String allIds, Integer roleId);
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<TUserUsers> getAllUsers();
}