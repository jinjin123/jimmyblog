package com.jimmyblog.mapper;

import java.util.List;

import com.jimmyblog.entity.ArticleTagRef;
import com.jimmyblog.entity.Tag;


/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
public interface ArticleTagRefMapper {

	/**
	 * @Description: get article count by tag
	 * @param tagId
	 * @return    
	 */
	
	Integer countArticleByTagId(Integer tagId);

	/**
	 * @Description: delete article by id
	 * @param articleId    
	 */
	
	int deleteByArticleId(Integer articleId);

	/**
	 * @Description: add article 	
	 * @param record    
	 * @return effact row
	 */
	
	int insert(ArticleTagRef record);

	/**
	 * @Description: get tag list by article id
	 * @param articleId
	 * @return    
	 */
	
	List<Tag> listTagByArticleId(Integer articleId);
	
	/**
	 * 
	 * @Description: delete tag 
	 * @param tagId
	 * @return
	 */
    int deleteByTagId(Integer tagId);

}
