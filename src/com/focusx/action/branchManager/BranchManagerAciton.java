package com.focusx.action.branchManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.hibernate.NonUniqueResultException;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.attilax.Closure;
import com.attilax.SafeVal;
import com.attilax.core;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.dsm.adapt.PropFilter;
import com.attilax.sso.loginOverTimeEx;
import com.focusx.dao.impl.BranchManagerDaoImpl;
import com.focusx.downRec.GvDownloadRecord;
import com.focusx.entity.TMbGroup;
import com.focusx.entity.TUserUsers;
import com.focusx.eq.EqX;
import com.focusx.service.BranchManagerService;
import com.focusx.util.CSVUtils;
import com.focusx.util.Constant;
import com.focusx.util.DateUtil;
import com.focusx.util.JsonUtil;
import com.focusx.util.MyHttpUtils;
import com.focusx.util.OperLogUtil4gial;
import com.focusx.util.Tree;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.focusx.pager.PageUtil;
import com.focusx.pager.Pager;
//import com.focusx.publish.action.PublishAction.OrgFilter;
import com.focusx.publish.dao.Impl.PublishDaoImpl;

public class BranchManagerAciton extends ActionSupport{
	
	protected static Logger logger = Logger.getLogger("BranchManagerAciton");
	private BranchManagerService branchManagerService;
	private TMbGroup group;
	private String groupname;//分组名称
	private Integer groupid;//分组ID
	private String remark;//地址
	private Double latitude;//经度
	private Double longitude;//纬度
	
	private Pager pager;// 分页对象
	private List<TMbGroup> groupList;//分组集合
	
	private String p;
	private String ps;
	private List<TMbGroup> groups;
	private String tree;//所有分组组成的树的字符串
	private Integer parentId;
	private String warning;
	private List<TMbGroup> listData;//返回的数据
	
	/**
	 *  获取所有一级分公司分组信息，并转化为json格式传到页面
	 */
	public String listBranch(){
		
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			HttpServletResponse response = ServletActionContext.getResponse();
			TUserUsers user =  (TUserUsers) session.getAttribute(session.getId());
			List<Tree> list = new ArrayList<Tree>();
			try {
				if(user != null){
					if(user.getIsSystem() != null && user.getIsSystem() == Constant.ONE){//管理员
						list = getTreejson(0);
					}else{
						if(user.getDefaultGroup() == null || user.getDefaultGroup() < 0){
							warning = "当前登陆用不属于任何一个分公司";
							return "warning";
						}else {
							list = getTreejsonOnlyOne(user.getDefaultGroup());
						}
					}
				}else {//user is null
					return "login";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray jsonArray = JSONArray.fromObject(list);
			tree = jsonArray.toString();
			OperLogUtil4gial.log(   "查询分公司分组信息:"+SafeVal.val(""), ServletActionContext.getRequest());
			return "list";
		} catch (Exception e) {
			core.err(e);
			return null;
		}
		
	}

	/**
	 *  获取所有一级分公司分组信息，并转化为json格式传到页面
	 */
	public String chooseListBranch(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		TUserUsers user =  (TUserUsers) session.getAttribute(session.getId());
		List<Tree> list = new ArrayList<Tree>();
		try {
			if(user != null){
				if(user.getIsSystem() != null && user.getIsSystem() == Constant.ONE){//管理员
					list = getTreejson(0);
				}else{
					if(user.getDefaultGroup() == null || user.getDefaultGroup() < 0){
						warning = "当前登陆用不属于任何一个分公司";
						return "warning";
					}else {
						list = getTreejsonOnlyOne(user.getDefaultGroup());
					}
				}
			}else {
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		tree = jsonArray.toString();
		OperLogUtil4gial.log(   "查询分公司分组信息:"+SafeVal.val(""), ServletActionContext.getRequest());
		return "chooseBranch";
	}
	
	// 根据parentId获取所有子分组或子分公司
	public List<Tree> getTreejson(int parentId){
		List<Tree> list = new ArrayList<Tree>();
		try {
			List<TMbGroup> one = branchManagerService.getTreeByParentId(parentId);
			if(one != null && one.size() > 0){//一级
				for(int i = 0; i < one.size(); i++){
					Tree tree = new Tree();//树分组对象
					tree.setIsexpand("false");//设置是否展开，这里不展开
					tree.setText(one.get(i).getGroupname());//设置分组名称
					tree.setUrl("weixin/branchManager!branchs?groupid="+one.get(i).getGroupid());
					//if(parentId != 0){//二级都是子分公司
					//	tree.setChildren(null);
					//}else {
						boolean result = branchManagerService.isParent(one.get(i).getGroupid());
						if(result){//二级	
							List<Tree> twolist = new ArrayList<Tree>();
							tree.setChildren(twolist);
						}else{
							tree.setChildren(null);
						}
					//}
					list.add(tree);
				}
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据parentId获取所有子分组或子分公司出现异常：BranchManagerAciton.getTreejson()，parentId为"+parentId, e);
		}
		return list;
	}
	
	//获取一个分公司分组信息
	public List<Tree> getTreejsonOnlyOne(int groupid){
		List<Tree> list = new ArrayList<Tree>();
		try {
			TMbGroup groupTree = branchManagerService.getGroup(groupid);
			if(groupTree != null){//一级
				Tree tree = new Tree();//树分组对象
				tree.setIsexpand("false");//设置是否展开，这里不展开
				tree.setText(groupTree.getGroupname());//设置分组名称
				tree.setUrl("weixin/branchManager!branchs?groupid="+groupid);
				boolean result = branchManagerService.isParent(groupid);
				if(result){//二级	
					List<Tree> twolist = new ArrayList<Tree>();
					tree.setChildren(twolist);
				}else{
					tree.setChildren(null);
				}
				list.add(tree);
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取一个分公司分组信息出现异常：BranchManagerAciton.getTreejsonOnlyOne()，groupid为"+groupid, e);
		}
		return list;
	}
	
	//根据parentId获取下一级分公司
	public void getTreejson(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");  
        response.setContentType("text/json;charset=UTF-8");  
		try {
			List<Tree> list = getTreejson(parentId);
			JSONArray jsonArray = JSONArray.fromObject(list);
			JsonUtil.write(response, jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JsonUtil.write(response, "error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 *  新增分组或分公司
	 */
	/**
	 * add branch  ajax type
	@author attilax 老哇的爪子
		@since  o8h 2_p_y
	 */
 	public void save(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(groupname != null && !StringUtils.trim(groupname).equals("")){
				TMbGroup group = new TMbGroup();
				group.setGroupname(StringUtils.trim(groupname));
				group.setCreatetime(new Date());
				if(parentId > 0){
					group.setParentId(parentId);
				}else {
					group.setParentId(0);
				}
				group.setRemark(StringUtils.trim(remark));
				group.setLongitude(longitude);
				group.setLatitude(latitude);
				branchManagerService.add(group);
				groupid = group.getGroupid();
				
				OperLogUtil4gial.log(   "新增分组或分公司"+groupname, ServletActionContext.getRequest());	
				JsonUtil.write(response, groupid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JsonUtil.write(response, "false");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 *  删除一个分组或分公司前检查是否符合要求，如果分组或分公司下有粉丝不能删除该分组
	 * @throws IOException 
	 */
	public void deleteCheck(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			boolean isuser = branchManagerService.checkGroup(groupid);//判断分组下是否有粉丝  true 没有/false 有
		    boolean isparent = branchManagerService.checkParent(groupid);//判断是否有子分组或子分公司  true 有/false 没有
		    if(isuser == false){ 
				JsonUtil.getInstance().write(response, "isuser");
		    }else if(isparent){
				JsonUtil.getInstance().write(response, "isparent");
		    }else {
		    	branchManagerService.delete(groupid);
				JsonUtil.getInstance().write(response, "success");
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查分组或分公司出现异常：BranchManagerAciton.deleteCheck()", e);
		}
	}
	
	/**
	 *  删除一个分组
	 */
	public String deleteGroup(){
		try {
			branchManagerService.delete(groupid);
			OperLogUtil4gial.log(   "删除分组或分公司"+groupname, ServletActionContext.getRequest());	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除分组或分公司出现异常：BranchManagerAciton.deleteGroup()，groupid为"+groupid, e);
		}
		return "listGroupAction";
	}
	
	/**
	 *  根据id获取分公司信息
	 */
	public void getGroupByGroupid(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");  
        response.setContentType("text/json;charset=UTF-8");  
		try {
			//if(groupid > 0){
				group = branchManagerService.getGroup(groupid);
				JSONObject json = JSONObject.fromObject(group);
				JsonUtil.write(response, json);	
			//}else {
			//	JsonUtil.write(response, "null");	
			//}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JsonUtil.write(response, "error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}

	}
	
	/**
	 *  更新分组或分公司
	 */
	public void edit(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			group = branchManagerService.getGroup(groupid);
			if(groupname != null && !groupname.equals("")){
				group.setGroupname(groupname);
			}else {
				group.setGroupname(group.getGroupname());
			}
			group.setRemark(remark);
			group.setLatitude(latitude);
			group.setLongitude(longitude);
			branchManagerService.update(group);
			JsonUtil.write(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新分组或分公司出现异常：BranchManagerAciton.edit()，groupid为"+groupid, e);
		}
	}
	
	/**only ajax invoke ,oac arg deu modify to true, for can dulipe name branch ..
	 *  检查是否存在该名称的分组或分公司
	 *  @throws IOException 
	 */
	public void checkAddGroup(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			//if exist ,return false;
			String result = branchManagerService.getGroupByGroupname(groupname);
			result="true";
			JsonUtil.getInstance().write(response, result);
		}catch(NonUniqueResultException e) //oac
		{
			try {
				JsonUtil.getInstance().write(response, "true");
			//	JsonUtil.getInstance().write(response, "false");
			} catch (IOException e1) {
			 
				e1.printStackTrace();
			}
		}
		
		
		catch (Exception e) {
			try {
				JsonUtil.getInstance().write(response, "true");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			logger.error("检查是否存在该名称的分组或分公司出现异常：BranchManagerAciton.checkAddGroup()，groupname为"+groupname, e);
		}
	}
	
	/**
	 *  分组列表，包含查询条件
	 *  三种情况：1、全部查询；2、查询某个分组；3、查询单个（id，groupname）
	 */
	public String branchs(){
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> data = new HashMap<String, Object>();

			pager = new Pager();
			PageUtil.init(pager, request);
			
			HttpSession session = ServletActionContext.getRequest().getSession();
			TUserUsers user =  (TUserUsers) session.getAttribute(session.getId());
			
			try {
				if(groupid == null && user != null){
					if(user.getIsSystem() != null && user.getIsSystem() == Constant.ONE){//管理员
						groupid = grpX.getHqId("");
					}else{
						if(user.getDefaultGroup() == null || user.getDefaultGroup() < 0){
							warning = "当前用户不属于任何一个分公司！";
							JsonUtil.write(response, warning);
							return "warning";
						}else {
							groupid = user.getDefaultGroup();
						}
					}
				}
				
				if(groupid == null){ 
					groupid = -99;
				}

				// 查询条件
				data.put("groupname", StringUtils.trim(groupname));
				data.put("groupid", groupid);
			    groupList = branchManagerService.getGroups(pager, data);
			    
			    //oad
			    grpX.setProvNsubBrach(groupList, new Closure<Object, TMbGroup>() {

					@Override
					public TMbGroup execute(Object arg0) throws Exception {
						TMbGroup item=(TMbGroup) arg0;
						 
						return  item;
					}
				});
				return "branchs";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("分组列表出现异常：BranchManagerAciton.groups()", e);
				 return null;
			}
		
		} catch (Exception e) {
			core.err(e);
		  return null;
		}
		

	}

	//根据groupid获取该分公司下的分店
	public List<Integer> getAllGroupIdByGroupId(Integer groupid){
		List<Integer> list = null;
		try {
			list = branchManagerService.getAllGroupIdByGroupId(groupid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据groupid获取该分公司下的分店出现异常：BranchManagerAciton.getAllGroupIdByGroupId()，" +
					"groupid为"+groupid, e);
		}
		return list;
	}
	
	
	//根据groupid获取对应分组下的分公司ID和名称
	public Map<Integer, String> getAllGroupByGroupId(Integer groupid){
		Map<Integer, String> data = new HashMap<Integer, String>();
		try {
			data = branchManagerService.getAllGroupByGroupId(groupid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("根据groupid获取对应分组下的分公司ID和名称出现异常",e);
		}
		return data;
	}

	/**
	 *  如果存放当前日期文件的目录结构不存在，则创建对应的目录结构 
	 *  @param path
	 */
	private boolean formatUrl(String path){ 
		boolean bool = false; 
		File file = new File(path);
		if(!file.exists()){ 
			file.mkdirs(); 
		} 
		if(file.isDirectory()){ 
			bool=true;
		}
		return bool; 
	}
	
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                //如果是目录的话这里是不采取操作的，至于目录的打包正在研究中
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据  
                    int nNumber;
                    byte[] buffer = new byte[1024];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象  
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
	//修改跳转
	public String editJsp(){
		try {
			//if(groupid > 0){
				group = branchManagerService.getGroup(groupid);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	
	//判断该parentId是否是分店
	public void checkTwoBranch(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(parentId != null && parentId > 0){
				TMbGroup group = branchManagerService.getGroup(parentId);
				if(group.getParentId() == 0){
					JsonUtil.write(response, "success");
				}else {
					JsonUtil.write(response, "false");
				}
			}else {
				JsonUtil.write(response, "null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JsonUtil.write(response, "error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//获取所有分公司信息（不包括分店）
	public void getTopBranch(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			List<TMbGroup> branchs = branchManagerService.getTopBranch();
			JSONArray jsonArray = JSONArray.fromObject(branchs);			
			String json_data = jsonArray != null ? jsonArray.toString() : "";
			JsonUtil.getInstance().write(response, json_data);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据分公司ID获取其所有门店
	public void getStoresByGroupid(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(groupid != null && groupid > 0){
				List<TMbGroup> branchs = branchManagerService.getStoresByGroupid(groupid);
				JSONArray jsonArray = JSONArray.fromObject(branchs);			
				String json_data = jsonArray != null ? jsonArray.toString() : "";
				JsonUtil.getInstance().write(response, json_data);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//导出门店信息
	public void exportcsv(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(groupid != null && groupid > 0){
				TMbGroup group = branchManagerService.getGroup(groupid);
				List<TMbGroup> dataList = branchManagerService.getStoresByGroupid(groupid);
			    
				List<String> dataListString = new ArrayList<String>();
				dataListString.add("门店ID, 门店名称");
				
				for(TMbGroup g : dataList){
					StringBuffer buf = new StringBuffer();
					buf.append(g.getGroupid()+",").append(g.getGroupname());
					dataListString.add(buf.toString());
				}
				String fileName = group.getGroupname()+"门店信息.csv";
				
		        CSVUtils.exportCsv(new File(Constant.otherPath+fileName), dataListString);
		        
		        response.setContentType("application/csv");
		        response.setHeader("Location",fileName);
				response.setCharacterEncoding("UTF-8");
		        response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO_8859_1")); 
		        OutputStream outputStream = response.getOutputStream();
		        InputStream inputStream = new FileInputStream(Constant.otherPath + fileName);
		        byte[] buffer = new byte[2048];
		        int i = -1;
		        while ((i = inputStream.read(buffer)) != -1) {
		        	outputStream.write(buffer, 0, i);
		        }
		        outputStream.flush();
		        outputStream.close();
		        inputStream.close();
		        File file = new File(Constant.otherPath + fileName);
		        if(file.exists()){
		        	file.delete();
		        }
			}else {
				logger.info("导出门店信息出错，原因是groupid是空或小于等于0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导出门店方法发生异常", e);
		}
	}
	
	private final class OrgFilter extends PropFilter {
		@Override
		public Object getExp() {
			try {
				HttpServletRequest request = ServletActionContext
						.getRequest();
			  String grpids=grpX.FindByLoginUid_retGrpIds(request)	;	
			
				 return " where groupid in (@ids) ".replaceAll("@ids", grpids);
				
			}   catch (isSuperAdminEx e) {
				 return " ";
			}
			
		}
	}
	/**
	 * 异步获取分组信息
	  * Description:
	  *  
	  * CreateTime: 2014-7-23 上午8:40:30
	  *
	  * @return
	 */
	public String ajaxGroups() {
		
		BranchManagerDaoImpl.proFltr.set(new OrgFilter());
		listData = branchManagerService.getGroups();
		return Action.SUCCESS;
	}
	
	
	public BranchManagerService getBranchManagerService() {
		return branchManagerService;
	}

	public void setBranchManagerService(BranchManagerService branchManagerService) {
		this.branchManagerService = branchManagerService;
	}

	public TMbGroup getGroup() {
		return group;
	}

	public void setGroup(TMbGroup group) {
		this.group = group;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	
	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public List<TMbGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<TMbGroup> groups) {
		this.groups = groups;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<TMbGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<TMbGroup> groupList) {
		this.groupList = groupList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		BranchManagerAciton.logger = logger;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<TMbGroup> getListData() {
		return listData;
	}

	public void setListData(List<TMbGroup> listData) {
		this.listData = listData;
	}
	
	
	
}
