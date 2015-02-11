package com.focusx.action.roleManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.attilax.Closure;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.spri.SpringUtil;
import com.attilax.sso.loginOverTimeEx;
 
import com.focusx.dao.impl.baseDao;
import com.focusx.entity.TMbGroup;
import com.focusx.entity.TUserUsers;
import com.focusx.eq.CantFindAnyDeviceEx;
import com.focusx.eq.EqX;
import com.focusx.pager.PageUtil;
import com.focusx.pager.Pager;
import com.focusx.pojo.Equipment;
import com.focusx.service.BranchManagerService;
import com.focusx.service.RoleMamageService;
import com.focusx.service.UserInfoManagerService;
import com.focusx.util.RoleType;
import com.focusx.util.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 角色人员配置
 * @author zhoujianbin
 *
 */
public class RoleUserConfigAction extends ActionSupport {
	private UserInfoManagerService userInfoManagerService;
	private List<RoleType> roleTypes;		//角色类型集合 用于展示
	private List<RoleType> queryRole;		//角色类型集合 用于查询
	private Integer roleId;					//角色ID
	private String users;					//人员Id（选中人员）
	private String allUser;					//当前分页中所有人员
	private String userName;				//人员姓名
	private String workNo = "";// 座作员工号(座席类必须为数字组合)
	private String name = "";// 操作员真实姓名
	private String tel;// 电话号码
	private String email;// 电子邮箱地址
	private String chkroleUserConfig;
	List<TUserUsers> userList;
	public Map<String, Object> data;
	private Pager<TUserUsers> pager;
	
	public String list(){
		queryRole = new ArrayList<RoleType>();
		queryRole = initRoleTypes();
		roleTypes = new ArrayList<RoleType>();
		data = new HashMap<String, Object>();
		
		if(userName != null && !userName.equals("")){
			data.put("userName", userName);
		}
		
		if(roleId == null) {
			roleTypes = initRoleTypes();
		}else {
			data.put("roleId", roleId);
			roleTypes.add(queryRole.get(roleId - 1));
		}
		
		/*List<TUserUsers> userList = userInfoManagerService.getAllUsersByRoleIds(data);
		//将人员加入到相应角色中
		if(userList.size() > 0){
			for (TUserUsers tUserUsers : userList) {
				if(tUserUsers.getRoleIds() != null && !tUserUsers.getRoleIds().equals("")){
					String[] item = tUserUsers.getRoleIds().split(",");
					if(item.length > 0){
						for (String s : item) {
							if(roleTypes.get(Integer.parseInt(s)) != null){
								RoleType rt = roleTypes.get(Integer.parseInt(s));
								List<TUserUsers> list = new ArrayList<TUserUsers>();
								if(rt.getUsers() != null){
									list = rt.getUsers();
								}
								list.add(tUserUsers);
								rt.setUsers(list);
							}
						}
						
					}
				}
			}
		}
		//如果用户名称不为空 清除没有人员的角色 不在页面显示
		if(userName != null && !userName.equals("")){
			List<RoleType> rtList = new ArrayList<RoleType>();
			for (RoleType rt : roleTypes) {
				if(rt.getUsers().size() > 0){
					rtList.add(rt);
				}
			}
			roleTypes = rtList;
		}*/
		return "list";
	}
	
	/**
	 * 分配权限
	  * Description:
	  *  
	  * CreateTime: 2014-8-30 下午5:51:58
	  *
	  * @return
	 */
	public String assignRole(){ 
		HttpServletRequest request = ServletActionContext.getRequest();
		String roleIds = request.getParameter("roleIds");
		roleTypes = initRoleTypes();
		if(Utils.isNotEmpty(roleIds)){
			String[] arr = roleIds.split(",");
				for (RoleType roleType : roleTypes) {
					Integer state = 0;
					for (int i = 0; i < arr.length; i++) {  
						if(roleType.getRoleId().equals(Integer.parseInt(arr[i]))){
							state = 1;
						    break;
						} 
					}
					roleType.setState(state);
				}
			 
		}
		return "assignRole";
	}
	private final class OrgFilter extends PropFilter {
		@Override
		public Object getExp() {
			try {
				HttpServletRequest request = ServletActionContext
						.getRequest();
			  String grpids=grpX.FindByLoginUid_retGrpIds(request);
			
				 return " and DefaultGroup in (@ids) ".replaceAll("@ids", grpids);
				
			}   catch (isSuperAdminEx e) {
				return "  ";
			}  
			
		}
	}
	
	public String queryRoleConfig(){
		if(roleId != null){
			HttpServletRequest request = ServletActionContext.getRequest();
			pager = new Pager<TUserUsers>();
			data = new HashMap<String, Object>();
			queryRole = new ArrayList<RoleType>();
			queryRole = initRoleTypes();
			for (RoleType rt : queryRole) {
				if(rt.getRoleId() == roleId){
					data.put("roleType", rt); 
				}
			}
			//List<TUserUsers> userList = userInfoManagerService.getAllUsers();
			PageUtil.init(pager, request);
			String condition = "";
			if (workNo.length() > 0 && name.trim().length() == 0) {
				condition = " and workNo like '%" + workNo + "%'";
			} else if (workNo.length() == 0 && name.trim().length() > 0) {
				condition = " and name like '%" + name.trim() + "%'";
			} else if (workNo.length() > 0 && name.trim().length() > 0) {
				condition = " and (workNo like '%" + workNo + "%'"
						+ " or name like '%" + name.trim() + "%')";
			} 

			StringBuffer dataSb = new StringBuffer();
			if(tel != null && !tel.trim().equals("")){
				dataSb.append(" and tel='"+tel.trim()+"'");
			}
			if(email != null && !email.trim().equals("")){
				dataSb.append(" and email='"+email.trim()+"'");
			}
			dataSb.append(condition);
			//oaf org filter
		   	baseDao.proFltr.set(new OrgFilter());
			userList = userInfoManagerService.getSearchUsers(dataSb.toString(), pager);
			//set Prov n subBrach oab
			grpX.setProvNsubBrach(userList,new Closure<Object, TMbGroup>() {

				@Override
				public TMbGroup execute(Object arg0) throws Exception {
					TUserUsers eq=(TUserUsers) arg0;
				
					BranchManagerService bc=SpringUtil.getBean(BranchManagerService.class);
					TMbGroup grp=bc.getGroup(eq.getDefaultGroup());
					eq.setDpt(grp);
					return grp;
				}
			});
			data.put("userList", userList);
		}
		return "update";
	}
	
	public String saveConfig(){
		data = new HashMap<String, Object>();
		if(roleId != null){
			boolean bl = userInfoManagerService.updateRole(users,allUser,roleId);
			data.put("result", bl ? 1 :0);
		}else{
			data.put("result", 0);
		}
		return Action.SUCCESS;
	}
	
	
	/**
	 * 初始化 角色类型
	 */
	public List<RoleType> initRoleTypes(){
		List<RoleType> list = new ArrayList<RoleType>();
		list.add(new RoleType(1, "系统管理员"));
		list.add(new RoleType(2, "素材管理员"));
		list.add(new RoleType(3, "节目单管理"));
		list.add(new RoleType(4, "发布管理员"));
		list.add(new RoleType(5, "报表管理员"));
		list.add(new RoleType(6, "素材审核员"));
		list.add(new RoleType(7, "节目单审核"));
		return list;
	}
	
	

	public List<RoleType> getRoleTypes() {
		return roleTypes;
	}

	public void setRoleTypes(List<RoleType> roleTypes) {
		this.roleTypes = roleTypes;
	}

	public List<RoleType> getQueryRole() {
		return queryRole;
	}

	public void setQueryRole(List<RoleType> queryRole) {
		this.queryRole = queryRole;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}


	public UserInfoManagerService getUserInfoManagerService() {
		return userInfoManagerService;
	}


	public void setUserInfoManagerService(
			UserInfoManagerService userInfoManagerService) {
		this.userInfoManagerService = userInfoManagerService;
	}

	public Pager<TUserUsers> getPager() {
		return pager;
	}

	public void setPager(Pager<TUserUsers> pager) {
		this.pager = pager;
	}

	public List<TUserUsers> getUserList() {
		return userList;
	}

	public void setUserList(List<TUserUsers> userList) {
		this.userList = userList;
	}

	public String getChkroleUserConfig() {
		return chkroleUserConfig;
	}

	public void setChkroleUserConfig(String chkroleUserConfig) {
		this.chkroleUserConfig = chkroleUserConfig;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAllUser() {
		return allUser;
	}

	public void setAllUser(String allUser) {
		this.allUser = allUser;
	}
	
	
	
}
