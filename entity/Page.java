package com.jimmyblog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Data
public class Page implements Serializable {
    private static final long serialVersionUID = 3927496662110298634L;
    private Integer pageId;

	private String pageKey;
	
	private Date pageCreateTime;
	
	private Date pageUpdateTime;
	
	private Integer pageStatus;
	
}
