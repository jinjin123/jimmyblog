package com.jimmyblog.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Data
public class ArticleCategoryRef implements Serializable {
	private static final long serialVersionUID = -6809206515467725995L;
    private Integer articleId;

    private Integer categoryId;

	public ArticleCategoryRef(Integer articleId, Integer categoryId) {
		super();
		this.articleId = articleId;
		this.categoryId = categoryId;
	}
    
}
