package com.jimmyblog.controller.home;

import java.util.List;

import com.jimmyblog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jimmyblog.entity.*;
import com.jimmyblog.enums.ArticleStatus;


/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentSerivce;
	
	@Autowired
	private UserSerivce userSerivce;
	
	@Autowired
	private TagService tagService;
	
	/**
	 * 
	 * @Description: article  page 
	 * @param articleId
	 * @return modelAndView
	 */
	@RequestMapping(value="/article/{articleId}")
	public String getArticleDetailPage(@PathVariable("articleId") Integer articleId, Model model) {
		
		//articleinfo， category,tag ,author,response
		Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),articleId);
		if(article == null) {
			return "HOME/Error/404";
		}
		//userinfo
		User user = userSerivce.getUserById(article.getArticleId());
		article.setUser(user);
		
		//articleinfo
		model.addAttribute("article", article);
		
		//response
		List<Comment> commentList = commentSerivce.listCommentByArticleId(articleId);
		model.addAttribute("commentList", commentList);
		
		//other article
		List<Integer> categoryIds = articleService.listCategoryByArticleId(articleId);
		List<Article> similarArticleList = articleService.listArticleByCategoryIds(categoryIds,5);
		model.addAttribute("similarArticleList", similarArticleList);
		
		//get max req article
		List<Article> mostViewArticleList = articleService.ListArticleByViewCount(5);
		model.addAttribute("mostViewArticleList", mostViewArticleList);
		
		//get next article 
		Article afterArticle = articleService.getAfterArticle(articleId);
		model.addAttribute("afterArticle", afterArticle);
		
		//get last article
		Article preArticle = articleService.getPreArticle(articleId);
		model.addAttribute("preArticle", preArticle);
		
		//Tag bar
		List<Tag> allTagList = tagService.listTag();
		model.addAttribute("allTagList", allTagList);
		
		//get random article
		List<Article> randomArticleList =  articleService.listRandomArticle(8);
		model.addAttribute("randomArticleList", randomArticleList);
		
		//get hot article
		List<Article> mostCommentArticle = articleService.listArticleByCommentArticle(8);
		model.addAttribute("mostCommentArticle", mostCommentArticle);
		
		return "HOME/Page/articleDetail";
	}
	/**
	 * 
	 * @Description: nice  click number
	 * @param id
	 * @return nice response number
	 */
	@RequestMapping(value="/article/like/{id}", method = {RequestMethod.POST})
	@ResponseBody
	public String increaseLikeCount(@PathVariable("id") Integer id) {
		Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue() ,id);
		Integer articleCount = article.getArticleLikeCount()  + 1;
		article.setArticleLikeCount(articleCount);
		articleService.updateArticle(article);
		return JSON.toJSONString(articleCount);
	}
	/**
	 * 
	 * @Description: request article number 
	 * @param id
	 * @return request number
	 */
	@RequestMapping(value="/article/view/{id}", method = {RequestMethod.POST})
	@ResponseBody
	public String increaseViewCount(@PathVariable("id") Integer id) {
		Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), id);
		Integer articleCount = article.getArticleViewCount() + 1;
		article.setArticleViewCount(articleCount);
		articleService.updateArticle(article);
		return JSON.toJSONString(articleCount);
	}
	
}
