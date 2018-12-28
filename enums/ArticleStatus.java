package com.jimmyblog.enums;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
public enum ArticleStatus {
	
	PUBLISH(1,"已发布"),
	DRAFT(0,"草稿");

	private Integer value;
	
	private String message;
	
	private ArticleStatus(Integer value, String message) {
		this.value = value;
		this.message = message;
	}

	public Integer getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
