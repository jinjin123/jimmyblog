package com.jimmyblog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = -4415517704211731385L;

	private Integer userId;

	private String userName;

	private String userPass;

	private String userNickname;

	private String userEmail;

	private String userUrl;

	private String userAvatar;

	private String userLastLoginIp;

	private Date userRegisterTime;

	private Date userLastLoginTime;

	private Integer userStatus;
	
	//not db columns
    private Integer articleCount;
}
