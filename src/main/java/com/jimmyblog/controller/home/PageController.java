package com.jimmyblog.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.Category;
import com.jimmyblog.entity.Page;
import com.jimmyblog.entity.Tag;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.CategoryService;
import com.jimmyblog.service.PageService;
import com.jimmyblog.service.TagService;


/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
public class PageController {
	@Autowired
	private PageService pageService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagService tagService;
	/**
	 * 
	 * @Description: detail page
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{key}")
	public String pageDetail(@PathVariable("key")String key,Model model) {
		Page page = pageService.getPageByKey(1, key);
		if(page == null) {
			return "redirect:/404";
		}
		model.addAttribute("page",page);
		//get hot article
		List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
		model.addAttribute("mostCommentArticleList", mostCommentArticleList);
		return "Home/Page/page";
	}
	/**
	 * 
	 * @Description: all article 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/articleFile")
	public String articleFile(Model model) {
		List<Article>articleList = articleService.listAllNotWithContent();
		model.addAttribute("articleList",articleList);
		//get hot article
		List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
		model.addAttribute("mostCommentArticleList", mostCommentArticleList);
		return "Home/Page/articleFile";
	}
	/**
	 * 
	 * @Description: site map
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/map")
	public String siteMap(Model model) {
		//article
		List<Article>articleList = articleService.listAllNotWithContent(); 
		model.addAttribute("articleList",articleList);
		//category
		List<Category> categoryList = categoryService.listCategory();
		model.addAttribute("categoryList",categoryList);
		//tag 
		List<Tag> tagList = tagService.listTag();
		model.addAttribute("tagList",tagList);
		//get hot article
		List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
		model.addAttribute("mostCommentArticleList", mostCommentArticleList);
		return "Home/Page/siteMap";
	}
	/**
	 * 
	 * @Description: message
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/message")
	public String message(Model model) {
		//get hot article
		List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
		model.addAttribute("mostCommentArticleList", mostCommentArticleList);
		return "Home/Page/message";
	}
	
 }
