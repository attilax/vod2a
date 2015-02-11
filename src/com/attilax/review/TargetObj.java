/**
 * 
 */
package com.attilax.review;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author ASIMO
 *
 */
@DataTransferObject 
public class TargetObj {
	Integer id;
	String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
