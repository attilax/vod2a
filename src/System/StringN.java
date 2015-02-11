/**
 * 
 */
package System;

/**
 * @author ASIMO
 *
 */
public class StringN {
String s;
		/**
 * @param parameter
 */
 
 
		/**
		 * @param parameter
		 */
		public StringN(String parameter) {
			s=parameter;
		//	if(parameter==null)
		}
		/**
		@author attilax 老哇的爪子
		@since   obs f_0_59
		 
		 */
	public java.lang.String Trim() {
		 if(s==null)
			 return "";
		return s.trim();
	}
			/**
			@author attilax 老哇的爪子
			@since   obs f_b_l
			 
			 */
		public StringN Replace(String string, String string2) {
		       
			return new StringN( this.s.replaceAll(string, string2));
		}
				/**
				@author attilax 老哇的爪子
				@since   obs f_c_1
				 
				 */
//			public StringN Replace(String string, String string2) {
//				// TODO Auto-generated method stub
//				return null;
//			}

		/**
		@author attilax 老哇的爪子
		@since   obs f_3_45
		 
		 */
//	public static System.String Form(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
