package com.jimmyblog.service;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jimmyblog.entity.Article;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
public interface ArticleService {
	/**
	 * 
	 * @Description: article  detail page
	 * @param status
	 * @param id
	 * @return anticle 
	 */
	Article getArticleByStatusAndId(Integer status,Integer id);

	/**
	 * @Description: get categorylist from article <id>
	 * @param articleId
	 * @return  list  
	 */
	
	List<Integer> listCategoryByArticleId(Integer articleId);

	/**
	 * @Description:  get anticle
	 * @param categoryIds gather
	 * @param limit 
	 * @return   list 
	 */
	
	List<Article> listArticleByCategoryIds(List<Integer> categoryIds, int limit);

	/**
	 * @Description: get max req article 
	 * @param limit 
	 * @return list    
	 */
	
	List<Article> ListArticleByViewCount(int limit);

	/**
	 * @Description: get next article
	 * @param articleId
	 * @return  article 
	 */
	
	Article getAfterArticle(Integer articleId);

	/**
	 * @Description: get last article
	 * @param articleId
	 * @return   article 
	 */

	Article getPreArticle(Integer articleId);

	/**
	 * @Description: get random article
	 * @param limit
	 * @return  article
	 */
	
	List<Article> listRandomArticle(int limit);

	/**
	 * @Description: get hot article
	 * @param limit
	 * @return    article
	 */
	
	List<Article> listArticleByCommentArticle(int limit);

	/**
	 * @Description: update article 
	 * @param article    
	 */
	
	void updateArticle(Article article);

	/**
	 * @Description: get new article
	 * @param limit 
	 * @return   list
	 */
	
	List<Article> listRencentArticle(int limit);

	/**
	 * @Description:  paging
	 * @param pageIndex
	 * @param pageSize
	 * @param crieria <query condition>
	 * @return    
	 */
	
	PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> crieria);

	/**
	 * @Description: add article
	 * @param article    
	 */
	
	void insertArticle(Article article);

	/**
	 * @Description: delete article
	 * @param id    
	 */
	
	void deleteArticle(Integer id);

	/**
	 * @Description: accounting article on this category
	 * @param   id
	 * @return    
	 */
	
	Integer countArticleByCategoryId(Integer id);

	/**
	 * @Description: update article comment
	 * @param articleId    
	 */
	
	void updateCommentCount(Integer articleId);

	/**
	 * @Description: get article tag from id 
	 * @param id
	 * @return    
	 */
	
	Integer countArticleByTagId(Integer id);

}
