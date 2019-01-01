package com.jimmyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jimmyblog.entity.Comment;


/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Mapper
public interface CommentMapper {

	/**
	 * @Description: add comment
	 * @param comment    
	 */
	
	int insert(Comment comment);

	/**
	 * @Description: get all comment by article id
	 * @param articleId
	 * @return    
	 */
	
	List<Comment> listCommentByArticleId(@Param(value = "id") Integer articleId);
	
	/**
	 * 
	 * @Description: get comment by comment id 
	 * @param commentId
	 * @return
	 */
	Comment getCommentById(Integer commentId);
	
	/**
	 * 
	 * @Description: get comment list
	 * @return
	 */
    List<Comment> listComment();

	/**
	 * @Description: delete comment
	 * @param id    
	 */
	
	int deleteById(Integer id);
	
	/**
	 * 
	 * @Description: update comment
	 * @param comment
	 * @return
	 */
	int update(Comment comment);
	
	/**
	 * 
	 * @Description: compute comment 
	 * @return
	 */
	Integer countComment();
	
	/**
	 * 
	 * @Description: get comment list
	 * @param limit
	 * @return
	 */
    List<Comment> listRecentComment(@Param(value = "limit") Integer limit);
    
    /**
     * 
     * @Description: get child comment from  parent comment
     * @param id
     * @return
     */
    List<Comment> listChildComment(@Param(value = "id") Integer id);
}
