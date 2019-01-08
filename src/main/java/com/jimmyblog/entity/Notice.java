package com.jimmyblog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
@Data
public class Notice implements Serializable {

    private static final long serialVersionUID = -4901560494243593100L;
    private Integer noticeId;

	private String noticeTitle;

	private Date noticeCreateTime;
	
	private Date noticeUpdateTime;
	
	private Integer noticeOrder;
	
	private Integer noticeStatus;
	
	
}
