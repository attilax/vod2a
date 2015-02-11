/**
 * 
 */
package System.Web;

import com.attilax.dotnet.System.Web.HttpContext;

import System.StringN;

/**
 * @author ASIMO
 *
 */
public class Request {

		/**
		@author attilax 老哇的爪子
		@since   obs f_3_45
		 
		 */
	public static    StringN Form(String arg0) {
		String s=HttpContext.Request.get().getParameter(arg0);
		if(s==null)
			return new StringN("");
		return   new StringN(s);	
		// null;
	}

}
