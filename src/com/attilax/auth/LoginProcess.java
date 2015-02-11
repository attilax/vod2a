/**
 * 
 */
package com.attilax.auth;

import java.io.File;

/**   adminAction.java
 * @author ASIMO
 *
 */
public class LoginProcess {
	public static void main(String[] args) {
		System.out.println( String.valueOf(  new File("C:\\captchDis").exists() )  );
	}

}
