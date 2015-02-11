/**
 * 
 */
package com.focusx.eq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.biz.orgGroup.CantFindAnySubNodes;
import com.attilax.biz.orgGroup.grpX;
import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.collection.CollX;
import com.attilax.ioc.IocX;
import com.attilax.spri.SpringUtil;
import com.attilax.sso.loginOverTimeEx;
import com.attilax.sso.loginOverTimeEx_RE;
import com.attilax.text.strUtil;
import com.focusx.auth.authx;
import com.focusx.entity.TUserUsers;
import com.focusx.pojo.Equipment;
import com.focusx.service.IEquipmentService;
import com.focusx.service.UserInfoManagerService;

/**
 * @author ASIMO
 *
 */
public class EqX {
	
	public static void main(String[] args) {
		UserInfoManagerService uc=SpringUtil.getBean(UserInfoManagerService.class);
		TUserUsers user =uc.getUserById( Integer.parseInt("58")  );
		core.print_wzFmt(user);
	}

		/**ret eq ids
		@author attilax 老哇的爪子
		 * @throws loginOverTimeEx 
		 * @throws isSuperAdminEx 
		 * @throws CantFindAnyDeviceEx 
		@since   oah i_1_55
		 
		 */
	@SuppressWarnings("all")
	public static String FindByLoginUid(HttpServletRequest request) throws  isSuperAdminEx, CantFindAnyDeviceEx {
		
		HttpSession session = request.getSession();
		String uid;
		try {
			uid = authx.getuid(request);
		} catch (loginOverTimeEx e) {
			 throw new loginOverTimeEx_RE("");
		}
		UserInfoManagerService uc=SpringUtil.getBean(UserInfoManagerService.class);
		TUserUsers user =uc.getUserById( Integer.parseInt(uid)  );
		grpX.superAdmincheck(user);
		Integer grpid = user.getDefaultGroup();
        String grpIds;
		try {
			grpIds = CollX.join( grpX.getALLSubNodeIds_ex(grpid.toString()));
		} catch (CantFindAnySubNodes e) {
			grpIds=grpid.toString();
		}
		
		IEquipmentService ec=SpringUtil.getBean(IEquipmentService.class);
		 List<Equipment> liOae=ec.  getListByDepartId(grpIds );
		 if( liOae.size()==0)
			 throw new CantFindAnyDeviceEx("");
		 String  ids=CollX.joinIds(liOae, new Closure<Equipment, String>() {

			@Override
			public String execute(Equipment arg0)
					throws Exception {
				 
				return arg0.getEquipmentId().toString();
			}
		});
		return ids;
	}
	
	
public static String FindByDptIds(String grpIds ) throws   CantFindAnyDeviceEx {
		
	grpIds=strUtil.trimx(",", grpIds);
		IEquipmentService ec=SpringUtil.getBean(IEquipmentService.class);
		 List<Equipment> liOae=ec.  getListByDepartId(grpIds );
		 if( liOae.size()==0)
			 throw new CantFindAnyDeviceEx("");
		 String  ids=CollX.joinIds(liOae, new Closure<Equipment, String>() {

			@Override
			public String execute(Equipment arg0)
					throws Exception {
				 
				return arg0.getEquipmentId().toString();
			}
		});
		return ids;
	}
	
public static HashMap FindByLoginUid_jsonFmt(HttpServletRequest request)  {
		
		 
		try {
			final String eqids = FindByLoginUid(request);
			return new HashMap() {
				{
					this.put("isData", true);
					this.put("equipmentIds", eqids);
				}
			};
		} catch (isSuperAdminEx e) {
			return new HashMap() {
				{
					this.put("isData", true);
					this.put("equipmentIds", "");
				}
			};
		} catch (CantFindAnyDeviceEx e) {
			return new HashMap() {
				{
					this.put("isData", false);
					this.put("equipmentIds", "");
				}
			};
		}
			//return null;
		 
	}


}
