/**
 * 
 */
package com.attilax.biz.orgGroup;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.attilax.dsm.adapt.PropFilter;
import com.attilax.sso.loginOverTimeEx;
import com.focusx.eq.CantFindAnyDeviceEx;
import com.focusx.eq.EqX;

/**base struts ret map
 * @author ASIMO
 *
 */
public class OrgFilter extends PropFilter {
	@Override
	public Object getExp() {
		System.out.println("");
		try {
			HttpServletRequest request = ServletActionContext
					.getRequest();
		  final String eqIds=EqX.FindByLoginUid(request);
		
			 return  new HashMap (){{
				 this.put("equipmentId", eqIds);
			 }};
			
		}   catch (isSuperAdminEx e) {
			return   new HashMap ();
		} catch (CantFindAnyDeviceEx e) {
			 
			 return  new HashMap (){{
				 this.put("equipmentId", 8888);
			 }};
		}
		
	}
}