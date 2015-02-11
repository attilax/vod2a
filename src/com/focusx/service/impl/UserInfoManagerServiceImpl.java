package com.focusx.service.impl;
// com.focusx.service.impl.UserInfoManagerServiceImpl

import java.util.List;
import java.util.Map;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.NonUniqueResultException;

import com.attilax.core;
import com.attilax.spri.SpringUtil;
import com.focusx.dao.UserInfoManagerDao;
import com.focusx.dao.impl.UserInfoManagerDaoImpl;
import com.focusx.entity.TUserUsers;
import com.focusx.service.UserInfoManagerService;
import com.focusx.util.Constant;
import com.focusx.pager.Pager;

@RemoteProxy(name = "ux")
public class UserInfoManagerServiceImpl implements UserInfoManagerService {
	
	private UserInfoManagerDao userInfoManagerDao;
	
	public UserInfoManagerDao getUserInfoManagerDao() {
		return userInfoManagerDao;
	}

	public void setUserInfoManagerDao(UserInfoManagerDao userInfoManagerDao) {
		this.userInfoManagerDao = userInfoManagerDao;
	}
	
	public boolean deleteRoleFromUser(int roleId, String userIdsList) {
		boolean result = true;
		
		if(userIdsList.length()>0){
			String[] userIds=userIdsList.split(",");
			result = userInfoManagerDao.deleteRoleFromUser(roleId, userIds);
		}
		
		return result;
	}

	public boolean insertUserIntoRole(int roleId, String userIdsList) {
		boolean result = true;
		
		if(userIdsList.length()>0){
			String[] userIds=userIdsList.split(",");
			result = userInfoManagerDao.insertUserIntoRole(roleId, userIds);
		}
		
		return result;
	}
	
	public List<TUserUsers> getPagedUsers(Pager page) {
		List<TUserUsers> lst = userInfoManagerDao.getPagedUsers(page.getCurrentPage(),page.getPageSize());
		//设置page中的对象
		page.setData(lst);
		int totalCount = userInfoManagerDao.getUserTotalCount();
		//设置总记录数
		page.setTotal(totalCount);
		return lst;
	}
	
	public List<TUserUsers> getSearchUsers(String condition,Pager page){
		List<TUserUsers> lst = userInfoManagerDao.getSearchUsers(condition,page.getCurrentPage(),page.getPageSize());
		//设置page中的对象
		page.setData(lst);
		int totalCount =userInfoManagerDao.getSearchUsersCount(condition);
				//lst.size();//oal
			//	
		//设置总记录数
		page.setTotal(totalCount);
		return lst;
	}

	public TUserUsers getUserById(int userId) {
		TUserUsers user = userInfoManagerDao.getUserById(userId);
		return user;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.focusx.service.UserInfoManagerService#getUserByWorkNo(java.lang.String)
	 * @author  attilax 老哇的爪子
	 *@since  o8h 4_d_55$
	 */
	//@ 
	public TUserUsers getUserByWorkNo(String workNo){
		TUserUsers user = userInfoManagerDao.getUserByWorkNo(workNo);
		return user;
	}
	
	public static void main(String[] args) {
		UserInfoManagerDao userInfoManagerDao=(UserInfoManagerDao) SpringUtil.getBean(UserInfoManagerDao.class);
	 
		TUserUsers userByWorkNo = userInfoManagerDao.getUserByWorkNo("007");
		System.out.println(userByWorkNo.getId());
		
//		UserInfoManagerService userInfoManagerDao=(UserInfoManagerService) SpringUtil.getBean(UserInfoManagerService.class);
//		TUserUsers userByWorkNo = userInfoManagerDao.getUserByWorkNo("007");
//		System.out.println(userByWorkNo.getId());
	 
	}
	@RemoteMethod
	public TUserUsers getUserByWorkNoO8(String workNo){
		 
	 
		UserInfoManagerService userInfoManagerDao=(UserInfoManagerService) SpringUtil.getBean(UserInfoManagerService.class);
			TUserUsers user = userInfoManagerDao.getUserByWorkNo(workNo);
		return user;
	}
	
	@RemoteMethod
	public boolean checkUnameExist(String workNo) {
		 
try {
	UserInfoManagerService userInfoManagerDao = (UserInfoManagerService) SpringUtil
			.getBean(UserInfoManagerService.class);
	TUserUsers user = userInfoManagerDao.getUserByName(workNo);
	if (user != null)
		return true;
	else
		return false;
}catch (NonUniqueResultException e) {
	return true;
} 
catch (Exception e) {
	return false;
}
		
	}
	
	

	public boolean updateUserInfo(TUserUsers user) {
		return userInfoManagerDao.updateUserInfo(user);
	}

	public Number insertUserInfo(TUserUsers user) {
		return userInfoManagerDao.insertUserInfo(user);
	}
	
	public boolean deleteUsers(String deleteUsersList){
		boolean checked = true;
		if(deleteUsersList!=null && deleteUsersList.length()>0){
			String[] ids = deleteUsersList.split(",");
			checked = userInfoManagerDao.deleteUsersByIds(ids);
			
		}else{
			checked  = false;
		}
		return checked;
	}

	public TUserUsers login(Map<String, String> data){
		return userInfoManagerDao.login(data);
	}

	public TUserUsers getUserByName(String name) {
		return userInfoManagerDao.getUserByName(name);
	}

	public List<TUserUsers> getAllUsersByRoleIds(Map<String, Object> data){
		return userInfoManagerDao.getAllUsersByRoleIds(data);
	}

	@Override
	public boolean updateRole(String ids, String allIds, Integer roleId) {
		try {
			String[] str = ids.split(",");
			List<TUserUsers> list = userInfoManagerDao.getUsersByIds(allIds);//取出所有需要修改权限的用户
			for (TUserUsers tUserUsers : list) {
				if(tUserUsers.getRoleIds()==null)
					tUserUsers.setRoleIds("");
				//如果当前用户存在需要修改的 角色Id，则先清除
				if(!tUserUsers.getRoleIds().equals("")){
					StringBuffer sb = new StringBuffer();
					String[] item = tUserUsers.getRoleIds().split(",");
					if(item.length > 0){
						for (int i = 0; i < item.length; i++) {
							if(!item[i].equals("")){
								if(Integer.parseInt(item[i].toString()) != roleId){
									sb.append(item[i] + ",");
								}
							}
						}
						if(sb.length() > 0){
							tUserUsers.setRoleIds(sb.substring(0, sb.length() - 1));
						}else{
							tUserUsers.setRoleIds("");
						}
					}
				}
			}
			
			

			for (TUserUsers tUserUsers : list) {
				if(str.length > 0){
					for (String s : str) {
						if(!s.equals("")){
							if(tUserUsers.getId() == Integer.parseInt(s)){
								if(!tUserUsers.getRoleIds().equals("")){
									tUserUsers.setRoleIds(tUserUsers.getRoleIds() + "," + roleId.toString());
								}else{
									tUserUsers.setRoleIds(roleId.toString());
								}
							}
						}
					}
				}
				userInfoManagerDao.updateUserInfo(tUserUsers);
			}
			
			/*if(str.length > 0){
				for (TUserUsers tUserUsers : list) {//将要配置的角色ID清除
					String newRoleIds = "";
					if(tUserUsers.getRoleIds() != null){
						for (String string : tUserUsers.getRoleIds().split(",")) {
							if(!String.valueOf(roleId).equals(string)){
								newRoleIds += string + ",";
							}
						}
						if(newRoleIds.length() > 0){
							newRoleIds.substring(0, newRoleIds.length() - 1);
						}
						
						tUserUsers.setRoleIds(newRoleIds);
					}
				}
				
				for (TUserUsers tUserUsers : list) {
					for (String s : str) {
						if(tUserUsers.getId() == Integer.parseInt(s)){
							if(tUserUsers.getRoleIds() == null || tUserUsers.getRoleIds().equals("")){
								tUserUsers.setRoleIds(String.valueOf(roleId));
							}else{
								tUserUsers.setRoleIds(tUserUsers.getRoleIds() + "," + String.valueOf(roleId));
							}
						}
					}
					userInfoManagerDao.updateUserInfo(tUserUsers);
				}
			}*/
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			core.warn(e);
			return false;
		}
		
	}
	
	public List<TUserUsers> getAllUsers(){
		return userInfoManagerDao.getAllUsers();
	}
}
