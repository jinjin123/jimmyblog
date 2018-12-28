package com.jimmyblog.controller.service;

import java.util.List;

import com.jimmyblog.entity.Comment;


/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
public interface CommentService {

	/**
	 * @Description: get response from article <id>
	 * @param articleId
	 * @return   list 
	 */
	
	List<Comment> listCommentByArticleId(Integer articleId);

	/**
	 * @Description: get comment list
	 * @param limit 
	 * @return    
	 */
	
	List<Comment> listRencentComment(int limit);
	
	

}
