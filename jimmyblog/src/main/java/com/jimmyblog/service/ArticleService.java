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
	 * 
	 * @Description: get all article not split page
	 * @param criteria
	 * @return
	 */
    List<Article> listArticle(HashMap<String, Object> criteria);
	/**
	 * @Description: get categorylist from article <id>
	 * @param articleId
	 * @return  list  
	 */
	
	List<Integer> listCategoryIdByArticleId(Integer articleId);

	/**
	 * 
	 * @Description: get article by category
	 * @param cateId
	 * @param limit
	 * @return
	 */
    List<Article> listArticleByCategoryId(Integer cateId, Integer limit);
	/**
	 * @Description:  get anticle
	 * @param categoryIds gather
	 * @param limit 
	 * @return   list 
	 */
    List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit);

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
	
	List<Article> listRecentArticle(int limit);

	/**
	 * @Description:  paging
	 * @param pageIndex
	 * @param pageSize
	 * @param crieria <query condition>
	 * @return    
	 */
	
	PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria);

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
	
	Integer countArticleByCategoryId(Integer categoryId);

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
	
	Integer countArticleByTagId(Integer tagId);

	/**
	 * @Description: get article by more comment
	 * @param i
	 * @return    
	 */
	
	List<Article> listArticleByCommentCount(int i);

	/**
	 * @Description: get all article
	 * @return    
	 */
	
	List<Article> listAllNotWithContent();

	/**
	 * @Description: get article 
	 * @param status
	 * @return    
	 */
	
	Integer countArticle(Integer status);

	/**
	 * @Description: get comment count
	 * @return    
	 */
	
	Integer countArticleComment();

	/**
	 * @Description: get read count
	 * @return    
	 */
	
	 Integer countArticleView();

	/**
	 * @Description: get last update artile 
	 * @return    
	 */
	
	Article getLastUpdateArticle();

	/**
	 * @Description: update article
	 * @param article    
	 */
	
	void updateArticleDetail(Article article);
	
    /**
     * delete more article
     *
     * @param ids article id
     */
    void deleteArticleBatch(List<Integer> ids);



}
