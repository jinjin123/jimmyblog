package com.jimmyblog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
@Data
public class Comment implements Serializable {
	
	private static final long serialVersionUID = -1038897351672911219L;
	private Integer commentId;


	private Integer commentPid;

	private String commentPname;

	private Integer commentArticleId;

	private String commentAuthorName;

	private String commentAuthorEmail;

	private String commentAuthorUrl;

	private String commentAuthorAvatar;

	private String commentContent;

	private String commentAgent;

	private String commentIp;

	private Date commentCreateTime;
	/**
	 * role(admin1,visitor0)
	 */
	private Integer commentRole;
	/**
	 *  not db columns
	 */
    private Article article;
}
