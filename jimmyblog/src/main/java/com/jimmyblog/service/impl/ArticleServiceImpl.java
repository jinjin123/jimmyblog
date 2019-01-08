package com.jimmyblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.ArticleCategoryRef;
import com.jimmyblog.entity.ArticleTagRef;
import com.jimmyblog.entity.Category;
import com.jimmyblog.entity.Tag;
import com.jimmyblog.enums.ArticleCommentStatus;
import com.jimmyblog.mapper.ArticleCategoryRefMapper;
import com.jimmyblog.mapper.ArticleMapper;
import com.jimmyblog.mapper.ArticleTagRefMapper;
import com.jimmyblog.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

	@Autowired(required = false)
	private ArticleMapper articleMapper;
	@Autowired(required = false)
	private ArticleCategoryRefMapper articleCategoryRefMapper;
	@Autowired(required = false)
	private ArticleTagRefMapper articleTagRefMapper;
	
	@Override
	public Article getArticleByStatusAndId(Integer status, Integer id) {
		Article article =  articleMapper.getArticleByStatusAndId(status,id);
		if(article != null) {
			List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
			List<Tag> tagList = articleTagRefMapper.listTagByArticleId(article.getArticleId());
			article.setCategoryList(categoryList);
			article.setTagList(tagList);
		}
		return article;
	}
	
    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return articleCategoryRefMapper.selectCategoryIdByArticleId(articleId);
    }
    @Override
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        return articleMapper.findArticleByCategoryId(cateId, limit);
    }
    
    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        if (cateIds == null || cateIds.size() == 0) {
            return null;
        }
        return articleMapper.findArticleByCategoryIds(cateIds, limit);
    }

	@Override
	public List<Article> ListArticleByViewCount(int limit) {
		return articleMapper.listArticleByViewCount(limit);
	}

	@Override
	public Article getAfterArticle(Integer articleId) {
		return articleMapper.getAfterArtcile(articleId);
	}

	@Override
	public Article getPreArticle(Integer articleId) {
		return articleMapper.getPreArticle(articleId);
	}

	@Override
	public List<Article> listRandomArticle(int limit) {
		return articleMapper.listRandomArticle(limit);
	}

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        return articleMapper.findAll(criteria);
    }
    
	@Override
	public List<Article> listArticleByCommentArticle(int limit) {

		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateArticleDetail(Article article) {
		article.setArticleUpdateTime(new Date());
		articleMapper.update(article);
		if(article.getTagList() != null) {
			//delete article 
			articleTagRefMapper.deleteByArticleId(article.getArticleId());
			//add article 
			for(int i =0;i<article.getTagList().size();i++) {
				ArticleTagRef articleTagRef  = new ArticleTagRef(article.getArticleId(),article.getTagList().get(i).getTagId());
				articleTagRefMapper.insert(articleTagRef);
 			}
		}
		if(article.getCategoryList() != null) {
			//add category 
			articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
			//delete category
			for(int i = 0;i< article.getCategoryList().size();i++) {
				ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(),article.getCategoryList().get(i).getCategoryId());
				articleCategoryRefMapper.insert(articleCategoryRef);
			}
		}
	}
	
    @Override
    public void deleteArticleBatch(List<Integer> ids) {
        articleMapper.deleteBatch(ids);
    }
    
    @Override
    public void updateArticle(Article article) {
        articleMapper.update(article);
    }

	@Override
	public List<Article> listRecentArticle(int limit) {
		return articleMapper.listArticleByLimit(limit);
	}

	@Override
	public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
		PageHelper.startPage(pageIndex,pageSize);
		List<Article> articleList = articleMapper.findAll(criteria);
		for(int i = 0;i<articleList.size();i++) {
			List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleList.get(i).getArticleId());
			if(categoryList == null || categoryList.size() == 0) {
				categoryList = new ArrayList<>();
				categoryList.add(Category.Default());
			}
			articleList.get(i).setCategoryList(categoryList);
		}
		return new PageInfo<>(articleList);
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
        //add article
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        articleMapper.insert(article);
        //add category
        for (int i = 0; i < article.getCategoryList().size(); i++) {
            ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
            articleCategoryRefMapper.insert(articleCategoryRef);
        }
        //add tag
        for (int i = 0; i < article.getTagList().size(); i++) {
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
            articleTagRefMapper.insert(articleTagRef);
        }
    }

	@Override
	public void deleteArticle(Integer id) {
		articleMapper.deleteById(id);
	}

	@Override
	public Integer countArticleByCategoryId(Integer categoryId) {
		Integer count = 0;
		try {
			count = articleCategoryRefMapper.countArticleByCategoryId(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void updateCommentCount(Integer articleId) {
		articleMapper.updateCommentCount(articleId);
	}

	@Override
	public Integer countArticleByTagId(Integer tagId) {
		return articleTagRefMapper.countArticleByTagId(tagId);
	}

	@Override
	public List<Article> listArticleByCommentCount(int limit) {
		return articleMapper.listArticleByCommentCount(limit);
	}

	@Override
	public List<Article> listAllNotWithContent() {
		 return articleMapper.listAllNotWithContent();
	}

	@Override
	public Integer countArticle(Integer status) {
		Integer count = 0;
		try {
			count = articleMapper.countArticle(status);
		}catch(Exception e){
			e.printStackTrace();
			log.error("统计文章评论数失败,cause:{}",e);
		}
		return count;
	}

	@Override
	public Integer countArticleComment() {
		Integer count = 0;
		try {
			count = articleMapper.countArticleComment();
		}catch(Exception e){
			e.printStackTrace();
			log.error("统计文章评论数失败,cause:{}",e);
		}
		return count;
	}

	@Override
	public Integer countArticleView() {
		Integer count = 0;
		try {
			count = articleMapper.countArticleView();
		}catch(Exception e){
			e.printStackTrace();
			log.error("统计文章访问量失败, cause:{}", e);
		}
		return count;
	}

	@Override
	public Article getLastUpdateArticle() {
		return articleMapper.getLastUpdateArticle();
	}

}
