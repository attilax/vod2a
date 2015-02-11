/**
 * @author attilax 老哇的爪子
	@since  2014-5-29 下午04:13:35$
 */
package com.focusx.auth;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.dotnet.System.Web.HttpContext;
import com.attilax.ioc.IocX;
import com.attilax.net.cookieUtil;
import com.attilax.persistence.PX;
import com.attilax.review.AlreadyReviewPassedEx;
import com.attilax.review.IReview;
import com.attilax.review.NoFiltEx;
import com.attilax.review.ReviewBackEx;
import com.attilax.review.ReviewHistry;
import com.attilax.review.TargetObj;
import com.attilax.spri.SpringUtil;
import com.attilax.sso.loginOverTimeEx;
import com.attilax.sso.loginOverTimeEx_RE;
import com.attilax.util.tryX;
import com.focusx.entity.TMbGroup;
import com.focusx.entity.TUserUsers;
import com.focusx.service.BranchManagerService;
import com.focusx.service.UserInfoManagerService;
import com.focusx.util.Constant;
import com.google.inject.Inject;

/**
 * @author  attilax 老哇的爪子
 *@since  2014-5-29 下午04:13:35$
 */
public class authx implements IReview {

	/**
	@author attilax 老哇的爪子
		@since  2014-5-29 下午04:13:35$
	
	 * @param args
	 */
 
	
	public static boolean isSuperAdmin(final HttpServletRequest req)
	{
	 
		return new tryX<Boolean>(){

			@Override
			public Boolean item(Object t) throws Exception {
				// attilax 老哇的爪子  下午02:30:07   2014-5-30 
				TUserUsers user;
				// HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = req.getSession();
				Integer userID = (Integer) session.getAttribute(Constant.Login_UserID);// 从session里获取当前用户的userID

				user = (TUserUsers) session.getAttribute(session.getId());
				 if(user.getIsSystem() == 0) return false;
				return true;
				//return null;
			}}.$(false);
			// attilax 老哇的爪子 上午11:51:59 2014-5-15
			
		 
	}
	public static boolean isSuperAdmin2(final HttpServletRequest req)
	{
	  TUserUsers u=	FindUserByLoginUid(req);
	  try {
		  superAdmincheck(u);
	} catch (isSuperAdminEx e) {
		return true;
	}
	  
	return false;
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
	 * is super admin or headqutaor admin
		@author attilax 老哇的爪子
		@since   ob4 m_e_9
	 */
	public static void HqAdmincheck(TUserUsers user) throws isSuperAdminEx {
		if(user.getIsSystem()==1)
			throw new isSuperAdminEx("");;
		Integer grpid = user.getDefaultGroup();
		BranchManagerService grpx=	 (BranchManagerService) SpringUtil.getBean(BranchManagerService.class)	;
		TMbGroup grp=grpx.getGroup(grpid);
		if(grp.getParentId()==0)
			throw new isSuperAdminEx("");
	}
	
	public static boolean isHqAdmincheck(TUserUsers user)   {
		if(user.getIsSystem()==1)
			 return true;
		Integer grpid = user.getDefaultGroup();
		BranchManagerService grpx=	 (BranchManagerService) SpringUtil.getBean(BranchManagerService.class)	;
		TMbGroup grp=grpx.getGroup(grpid);
		if(grp.getParentId()==0)
			return true;
		return false;
	}
	
	public static String getCurUsername(final HttpServletRequest req)
	{
	 
		String uname= new tryX<String>(){

			@Override
			public String item(Object t) throws Exception {
				// attilax 老哇的爪子  下午02:30:07   2014-5-30 
				TUserUsers user;
				// HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = req.getSession();
				Integer userID = (Integer) session.getAttribute(Constant.Login_UserID);// 从session里获取当前用户的userID

				user = (TUserUsers) session.getAttribute(session.getId());
				 
				return user.getName();
				//return null;
			}}.$("");
			if(uname.length()==0)
				return cookieUtil.$_COOKIE("uname", req);
			// attilax 老哇的爪子 上午11:51:59 2014-5-15
			return uname;
			
		 
	}
	@Inject
	public
	PX px;
	public static void main(String[] args) {
		System.out.println("aaa.avi".toLowerCase().endsWith(".avi"));
//		authx c=IocX.getBean(authx.class);
//		TMbGroup grp=(TMbGroup) c.px.get(TMbGroup.class, 112);
//		System.out.println(grp.getGroupname());
		//c.getCurUserDept(req)
	}
	
	/**
	 * 
		@author attilax 老哇的爪子
		@since   ob4 m_h_51
	 */
	public static TUserUsers FindUserByLoginUid(HttpServletRequest request)  {
		
		HttpSession session = request.getSession();
		String uid;
		try {
			uid = authx.getuid(request);
		} catch (loginOverTimeEx e) {
			 throw new loginOverTimeEx_RE("");
		}
		UserInfoManagerService uc=SpringUtil.getBean(UserInfoManagerService.class);
		TUserUsers user =uc.getUserById( Integer.parseInt(uid)  );
		return user;
	}
	/**
	 * 
	@author attilax 老哇的爪子
		@since  o0d 4_n_38   
	
	 * @param req
	 * @return
	 */
	public static String getCurUserDept(final HttpServletRequest req)
	{
	 
		return new tryX<String>(){

			@Override
			public String item(Object t) throws Exception {
				// attilax 老哇的爪子  下午02:30:07   2014-5-30 
				TUserUsers user;
				// HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = req.getSession();
				Integer userID = (Integer) session.getAttribute(Constant.Login_UserID);// 从session里获取当前用户的userID

				user = (TUserUsers) session.getAttribute(session.getId());
				Integer	 grpid=user.getDefaultGroup();
				authx c=IocX.getBean(authx.class);
				TMbGroup grp=(TMbGroup) c.px.get(TMbGroup.class, grpid);
				System.out.println(grp.getGroupname());
				return grp.getGroupname();
				//return null;
			}}.$("..");
			// attilax 老哇的爪子 上午11:51:59 2014-5-15
			
		 
	}
	//  attilax 老哇的爪子 下午04:13:35   2014-5-29   
	public static String getuid(HttpServletRequest req) throws loginOverTimeEx {
		// attilax 老哇的爪子 上午10:13:13 2014-6-17
		// try {

		Integer userID = null;
		try {
			HttpSession session = req.getSession();
			userID = (Integer) session.getAttribute(Constant.Login_UserID);// 从session里获取当前用户的userID
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (userID == null)
			userID = Integer.parseInt(cookieUtil.getCookie("uid", req));
		if (userID == null)
			throw new loginOverTimeEx("overtimex");
		return userID.toString();
		// } catch (Exception e) {
		//
		// }

	}
	
	public static boolean isUserRole(final HttpServletRequest req,Integer roleType){
		HttpSession session = req.getSession();
		TUserUsers user = (TUserUsers) req.getSession().getAttribute(session.getId());
		if(user!=null) {
			if(!user.getRoleIds().equals("")){
				if(user.getRoleIds().contains(String.valueOf(roleType))){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}


	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#setReviewObj4List(java.util.List)
	 */
	@Override
	public void setReviewObj4List(List data) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#convertObj2Targetobj(com.attilax.review.ReviewHistry)
	 */
	@Override
	public TargetObj convertObj2Targetobj(ReviewHistry bizobj) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#reviStateFiltedIds(java.lang.Object)
	 */
	@Override
	public String reviStateFiltedIds(Object p) throws NoFiltEx {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#setReviewPassedPropFilter(java.lang.Object)
	 */
	@Override
	public Object setReviewPassedPropFilter(Object p) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#hasReviewAuth(java.lang.Object)
	 */
	@Override
	public boolean hasReviewAuth(Object roleType) {
	
		HttpServletRequest req =( HttpServletRequest) HttpContext.Request.get();
	TUserUsers u=	FindUserByLoginUid((HttpServletRequest) req);
		if(isHqAdmincheck(u))
			if( isUserRole(req, Integer.parseInt(roleType.toString()) ))
				return true;
		return false;
	}
	
	public static  boolean  hasReviewAuth2(Object roleId)
	{
		try {
			return new authx().hasReviewAuth(roleId);	
		} catch (Exception e) {
			return false;
		}
	
	}

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#reviewPassedCheck(java.lang.Object)
	 */
 

	/* (non-Javadoc)
	 * @see com.attilax.review.IReview#reviewPassedCheck(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean reviewPassedCheck(Object obj, String dataType)
			throws AlreadyReviewPassedEx, ReviewBackEx {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}

//  attilax 老哇的爪子