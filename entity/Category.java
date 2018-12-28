package com.jimmyblog.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/28
 *
 */
@Data
public class Category implements Serializable {

	private static final long serialVersionUID = 6687286913317513141L;
	
	private Integer categoryId;

	public Category(Integer categoryId) {
		super();
		this.categoryId = categoryId;
	}
	
	
    
}
