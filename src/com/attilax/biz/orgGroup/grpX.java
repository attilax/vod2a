/**
 * 
 */
package com.attilax.biz.orgGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollX;
import com.attilax.ioc.IocX;
import com.attilax.persistence.PX;
import com.attilax.spri.SpringUtil;
import com.attilax.sso.loginOverTimeEx;
import com.attilax.sso.loginOverTimeEx_RE;
import com.focusx.auth.authx;
import com.focusx.dao.impl.BranchManagerDaoImpl;
import com.focusx.entity.TMbGroup;
import com.focusx.entity.TUserUsers;
import com.focusx.pojo.Equipment;
import com.focusx.service.BranchManagerService;
import com.focusx.service.IEquipmentService;
import com.focusx.service.UserInfoManagerService;

/**
 * @author ASIMO
 *
 */
public class grpX {
	
	public static void main(String[] args) {
		 Set<Integer> st=grpX.getALLSubNodeIds("0");
		 core.print_wzFmt(st);
	}

	@SuppressWarnings("all")
	public static void setProvNsubBrach(List li, Closure getGrpAdapter) {
		try {

			for (Object item : li) {
				try {
					TMbGroup grp = (TMbGroup) getGrpAdapter.execute(item);
					BranchManagerService grpx = (BranchManagerService) SpringUtil
							.getBean(BranchManagerService.class);
					TMbGroup parent = (grpx.getGroup(grp.getParentId()));
					if (parent != null) {
						grp.setParentGrp(parent);

						try {
							Integer gradPrtId = parent.getParentId();

							TMbGroup grdPrt = grpx.getGroup(gradPrtId);

							grp.setGradParentGrp(grdPrt);
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {

		}

	}
	public static String FindByLoginUid_retGrpIds(HttpServletRequest request) throws  isSuperAdminEx {
		
		HttpSession session = request.getSession();
		String uid;
		try {
			uid = authx.getuid(request);
		} catch (loginOverTimeEx e) {
			 throw new loginOverTimeEx_RE("");
		}
		UserInfoManagerService uc=SpringUtil.getBean(UserInfoManagerService.class);
		TUserUsers user =uc.getUserById( Integer.parseInt(uid)  );
		Integer grpid = user.getDefaultGroup();
		superAdmincheck(user);
		
        Set<Integer> allSubNodeIds = grpX.getALLSubNodeIds(grpid.toString());
        allSubNodeIds.add(grpid);
		String grpIds=  CollX.join( allSubNodeIds)  ;	 
		return grpIds;
	}
	public static void superAdmincheck(TUserUsers user) throws isSuperAdminEx {
		if(user.getIsSystem()==1)
			throw new isSuperAdminEx("");;
		Integer grpid = user.getDefaultGroup();
		BranchManagerService grpx=	 (BranchManagerService) SpringUtil.getBean(BranchManagerService.class)	;
		TMbGroup grp=grpx.getGroup(grpid);
		if(grp.getParentId()==0)
			throw new isSuperAdminEx("");
	}
	/**
	 * recomm 
		@author attilax 老哇的爪子
	 * @throws CantFindAnySubNodes 
		@since   oak d_56_57
	 */
	public static  Set<Integer> getALLSubNodeIds_ex( String grpIDs) throws CantFindAnySubNodes {
		 Set<Integer> set=getALLSubNodeIds(grpIDs);
		 if(set.size()==0)
			 throw new CantFindAnySubNodes("");
		return set;
	}
	
	
	public static  int  getHqId( String grpName) throws CantFindHqNode {
		BranchManagerService grpx = (BranchManagerService) SpringUtil
				.getBean(BranchManagerService.class);
		TMbGroup grp=grpx.getBranchByGroupName("总部");
		if(grp==null)
			throw new CantFindHqNode("");
		return grp.getGroupid();
		
	}
		/**
		@author attilax 老哇的爪子
		@since   oak d_57_v
		 
		 */
 
	@Deprecated
	public static  Set<Integer> getALLSubNodeIds( String grpIDs) {
		try {
			
		} catch (Exception e) {
			 
		}
	 
		//	try {
			//	TMbGroup grp=(TMbGroup) getGrpAdapter.execute(item);
				BranchManagerService grpx=	 (BranchManagerService) SpringUtil.getBean(BranchManagerService.class)	;
				BranchManagerDaoImpl grpx2=IocX.getBean(BranchManagerDaoImpl.class);
				PX px=IocX.getBean(PX.class);
				grpx2.sessionOaf=px.getSession();
			//	TMbGroup grp= (grpx.getGroup(grpID));
				Set<Integer> st=new HashSet<Integer>();
				List<Integer> subNoteIds=new ArrayList<Integer>();
				try {
					  subNoteIds=grpx2.getAllGroupIdByGroupIds(grpIDs);
					//grpx.getAllGroupIdByGroupId(grpID);
					if(subNoteIds==null)
						return new HashSet<Integer>();
				} catch (Exception e) {
					 return new HashSet<Integer>();
				}
			
				 st.addAll(subNoteIds);
				 String subNoteIds_strFmt=CollX.join(subNoteIds);
			 
				Set<Integer>  subSubIds=getALLSubNodeIds(subNoteIds_strFmt);
					 st.addAll(subSubIds)  ;
				 
				
			
				return st;
			
				
				 
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		 
		
		
	}

			/**
			@author attilax 老哇的爪子
			@since   oaq f_47_59
			 
			 */
		public static void setSubBrach(List list_sub,
				Closure<Map, TMbGroup> closure) {
			// TODO Auto-generated method stub
			
		}
}
