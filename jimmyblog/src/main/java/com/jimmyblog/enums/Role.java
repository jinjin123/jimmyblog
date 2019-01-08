package com.jimmyblog.enums;

import java.io.Serializable;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
public enum Role implements Serializable {
	ADMIN(1,"博主"),
	VISITOR(0,"访客");
	
	private Integer value;
	
	private String message;

	private Role(Integer value, String message) {
		this.value = value;
		this.message = message;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
