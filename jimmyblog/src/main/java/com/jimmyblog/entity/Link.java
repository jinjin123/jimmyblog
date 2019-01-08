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

   private Integer linkId;

   private String linkUrl;

   private String linkName;

   private String linkImage;

   private String linkDescription;

   private String linkOwnerNickname;

   private String linkOwnerContact;

   private Date linkUpdateTime;

   private Date linkCreateTime;

   private Integer linkOrder;

   private Integer linkStatus;
}
