package com.jimmyblog.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Data
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -5816783232020910492L;
    
    private Integer articleId;
    
    private Integer tagId;
	public ArticleTagRef(Integer articleId, Integer tagId) {
		this.articleId = articleId;
		this.tagId = tagId;
	}
	public ArticleTagRef() {
	}



}
