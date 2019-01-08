package com.jimmyblog.enums;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
public enum LinkStatus {
	NORMAL(1,"显示"),
	HIDDEN(0,"隐藏");
	
	private Integer value;
	
	private String message;

	private LinkStatus(Integer value, String message) {
		this.value = value;
		this.message = message;
	}

	public  Integer getValue() {
		return value;
	}
	
}
