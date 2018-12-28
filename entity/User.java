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

	
	private String UserPass;

	private String userLastLoginIp;
	
    private Date userLastLoginTime;

	public Integer userId;
}
