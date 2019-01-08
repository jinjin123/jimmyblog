package com.jimmyblog.dto;

import java.util.List;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/28
 *
 */
@Data
public class ArticleParam {

	private String articleTitle;
	
	private Integer articleId;
	
	private String articleContent;
	
	private String articleStatus;
	
	private Integer aritcleChildCategoryId;
	
	private Integer articleParentCategoryId;
	
	private Integer articleChildCategoryId;
	
	private List<Integer> articleTagIds;
	
	

}
