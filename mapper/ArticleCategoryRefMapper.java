package com.jimmyblog.mapper;

import java.util.List;

import com.jimmyblog.entity.ArticleCategoryRef;
import com.jimmyblog.entity.Category;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
public interface ArticleCategoryRefMapper {

	/**
	 * @Description:  get article count by categoryid
	 * @param categoryId
	 * @return    
	 */
	
	Integer countArticleByCategoryId(Integer categoryId);

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
	
	int insert(ArticleCategoryRef record);

	/**
	 * @Description: get category list
	 * @param articleId
	 * @return    
	 */
	
	List<Category> listCategoryByArticleId(Integer articleId);
	/**
	 * 
	 * @Description: get category id by ariticle id
	 * @param articleId
	 * @return
	 */
    List<Integer> selectCategoryIdByArticleId(Integer articleId);

	/**
	 * @Description: delete category
	 * @param id    
	 */
	
	int deleteByCategoryId(Integer id);

}
