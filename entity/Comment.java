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

	
	private String commentIp;
	
	private Date commentCreateTime;
	
	private String commentAuthorEmail;
	
	private String commentAuthorAvatar;

	private Integer commentArticleId;
	
	/**
	 * role(admin1,visitor0)
	 */
	private Integer commentRole;
}
