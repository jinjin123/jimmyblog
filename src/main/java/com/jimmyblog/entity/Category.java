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

    private Integer categoryPid;

    private String categoryName;

    private String categoryDescription;

    private Integer categoryOrder;

    private String categoryIcon;

    /**
     *  all article (not db columns)
     */
    private Integer articleCount;
    
	public Category(Integer categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

	/**
	 * @Description: not category
	 * @return    
	 */
	
	public static Category Default() {
		 return new Category(100000000, "未分类");	
	}

	
	
    
}
