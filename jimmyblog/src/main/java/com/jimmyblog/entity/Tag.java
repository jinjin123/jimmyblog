package com.jimmyblog.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
@Data
public class Tag implements Serializable {
	private static final long serialVersionUID = 605449151900057035L;
    private Integer tagId;
	private String tagName;

	private String tagDescription;
	public Tag() {
	}
	//not db columns
    private Integer articleCount;

	public Tag(Integer tagId) {
		this.tagId = tagId;
	}

	public Tag(Integer tagId, String tagName, String tagDescription, Integer articleCount) {
		this.tagId = tagId;
		this.tagName = tagName;
		this.tagDescription = tagDescription;
		this.articleCount = articleCount;
	}
}
