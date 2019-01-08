package com.jimmyblog.enums;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
public enum MenuLevel {
	
	TOP_MENU(1,"顶部菜单"),
	MAIN_MENU(2,"主题菜单");
	
	private Integer value;
	
	private String message;
	
	public Integer getValue() {
		return value;
	}

	MenuLevel(Integer value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
