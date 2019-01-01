package com.jimmyblog.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jimmyblog.entity.Article;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Mapper
public interface ArticleMapper {
	/**
	 * 
	 * @Description: delete article from id
	 * @param articleId
	 * @return
	 */
	Integer deleteById(Integer articleId);
	/**
	 * 
	 * @Description: add article
	 * @param article
	 * @return
	 */
	Integer insert(Article article);
	
	/**
	 * 
	 * @Description: update article
	 * @param article
	 * @return
	 */
	Integer update(Article article);
	
	List<Article> findAll(HashMap<String,Object> criteria);
	/**
	 * @Description: get sum article
	 * @return    
	 */
	
	Integer countArticle(@Param(value="status")Integer status);
	/**
	 * @Description: get sum comment 
	 * @return    
	 */
	
	Integer countArticleComment();
	/**
	 * @Description: get read article count
	 * @return    
	 */
	
	Integer countArticleView();
	/**
	 * @Description: get recent article
	 * @param limit
	 * @return    
	 */
	
	List<Article> listArticleByLimit(int limit);
	/**
	 * @Description: delete most article 
	 * @param ids    
	 */
	
	Integer deleteBatch(@Param("ids") List<Integer> ids);
	/**
	 * @Description: get article status
	 * @param status
	 * @param id
	 * @return    
	 */
	
	Article getArticleByStatusAndId(@Param(value = "status")Integer status,@Param(value = "id")  Integer id);
	/**
	 * @Description: get hot article 
	 * @param limit
	 * @return    
	 */
	
	List<Article> listArticleByViewCount(int limit);
	/**
	 * @Description: get last article
	 * @param articleId
	 * @return    
	 */
	
	Article getAfterArtcile(Integer articleId);
	/**
	 * @Description: get next article
	 * @param articleId
	 * @return    
	 */
	
	Article getPreArticle(@Param(value = "id") Integer articleId);
	/**
	 * @Description: get random article
	 * @param limit
	 * @return    
	 */
	
	List<Article> listRandomArticle(@Param(value = "limit") int limit);
	/**
	 * @Description: get hot article
	 * @param limit
	 * @return    
	 */
	
	List<Article> listArticleByCommentCount(@Param(value = "limit") int limit);
	/**
	 * @Description: update comment count
	 * @param articleId    
	 */
	
	void updateCommentCount(@Param(value = "articleId") Integer articleId);
	/**
	 * @Description: get last update record
	 * @return    
	 */
	
	Article getLastUpdateArticle();
	/**
	 * @Description: 
	 * @param cateId
	 * @param limit
	 * @return    
	 */
	
    List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,
            @Param("limit") Integer limit);
	/**
	 * @Description: get article by more category id
	 * @param cateIds
	 * @param limit
	 * @return    
	 */
	
	List<Article> findArticleByCategoryIds(List<Integer> cateIds, Integer limit);
	
	/**
	 * 
	 * @Description: article of category
	 * @return
	 */
    List<Article> listAllNotWithContent();
    
    /**
     * 
     * @Description: compute article of user
     * @param id
     * @return
     */
    Integer countArticleByUser(@Param(value = "id") Integer id);

}
