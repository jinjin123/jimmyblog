package com.jimmyblog.entity;
import lombok.Data;
import java.io.Serializable;



/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
@Data
public class Article implements Serializable{
	
    private static final long serialVersionUID = 5207865247400761539L;

	private Integer ArticleId;
	
	private User user;

	private Integer articleLikeCount;

	
	public Integer articleViewCount;
	

 
	
}
