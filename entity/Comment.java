package com.jimmyblog.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
@Data
public class Comment implements Serializable {
	
	private static final long serialVersionUID = -1038897351672911219L;
	private Integer commentId;
}
