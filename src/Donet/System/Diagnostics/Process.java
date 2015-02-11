/**
 * 
 */
package Donet.System.Diagnostics;

import com.attilax.lang.CmdX;

import System.Diagnostics.ProcessStartInfo;

/**
 * @author ASIMO
 *
 */
public class Process {

	public String FileName;
	public String Arguments;
	public boolean CreateNoWindow;
	public ProcessStartInfo StartInfo;
	public String retMsg="";
		/**
		@author attilax 老哇的爪子
		@since   p19 9_l_3
		 
		 */
	public Boolean Start() {
	
		retMsg=CmdX.exec(FileName+ " "+Arguments );
		return true;
	}
			/**
			@author attilax 老哇的爪子
			@since   p19 9_l_8
			 
			 */
		public void WaitForExit(int i) {
			// TODO Auto-generated method stub
			
		}

}
