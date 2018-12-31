package com.jimmyblog.enums;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
public enum PageStatus {
	NORMAL(1,"显示"),
	HIDDEN(0,"隐藏");

	private Integer value;
	
	private String message;
	
	public Integer getValue() {
		return value;
	}

	private PageStatus(Integer value, String message) {
		this.value = value;
		this.message = message;
	}
	
	
	
}
