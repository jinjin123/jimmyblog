package com.jimmyblog.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
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

	/**
	 * @Description: comment page
	 * @param pageIndex
	 * @param pageSize
	 * @return    
	 */
	
	PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize);

	/**
	 * @Description: add comment
	 * @param comment    
	 */
	
	void insertComment(Comment comment);

	/**
	 * @Description: get comment from id
	 * @param id
	 * @return    
	 */
	
	Comment getCommentById(Integer id);

	/**
	 * @Description: delete comment from id
	 * @param id    
	 */
	
	void deleteComment(Integer id);

	/**
	 * @Description: get child comment from  parent comment
	 * @param id
	 * @return    
	 */
	
	List<Comment> listChildComment(Integer id);

	/**
	 * @Description: edit comment
	 * @param comment    
	 */
	
	void updateComment(Comment comment);
	
	

}
