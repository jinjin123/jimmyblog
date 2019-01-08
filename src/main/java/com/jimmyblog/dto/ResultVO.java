package com.jimmyblog.dto;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Data
public class ResultVO<T> {
	/**
	 *  error code
	 */
	private Integer code;
	
	/**
	 * notice
	 */
	private String msg;
	/**
	 * return data
	 */
	private T data;
	
	public ResultVO(Integer code,String msg,T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public ResultVO() {
		
	}
}
