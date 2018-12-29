package com.jimmyblog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created  by  Jimmy  on 2018/12/29
 *
 */
@Data
public class Link  implements Serializable{
	
   private static final long serialVersionUID = -259829372268790508L;
   
   private Date linkCreateTime;
   
   private Date linkUpdateTime;
   
   private Integer linkStatus;
}
