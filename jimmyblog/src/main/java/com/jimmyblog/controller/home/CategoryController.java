package com.jimmyblog.controller.home;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.Category;
import com.jimmyblog.entity.Tag;
import com.jimmyblog.enums.ArticleStatus;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.CategoryService;
import com.jimmyblog.service.TagService;


/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TagService tagService;
	
	/**
	 * 
	 * @Description: get article from category 
	 * @param cateId
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/{cateId}")
	public String getArticleListByCategory(@PathVariable("cateId")Integer cateId,
										   @RequestParam(required = false,defaultValue = "1")Integer  pageIndex,
										   @RequestParam(required = false,defaultValue = "10")Integer pageSize,
										   Model model) {
		Category category = categoryService.getCategoryById(cateId);
		if(category == null) {
			return "redirect:/404";
		}
		model.addAttribute("category", category);
		
		//article list
		HashMap<String, Object> criteria = new HashMap<>(2);
		criteria.put("categoryId", cateId);
		criteria.put("status", ArticleStatus.PUBLISH.getValue());
		PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
		model.addAttribute("pageInfo",articlePageInfo);
		
		//tag menu show
		List<Tag> allTagList = tagService.listTag();
		model.addAttribute("allTagList",allTagList);
		//get random article
		List<Article> randomArticleList = articleService.listRandomArticle(8);
		model.addAttribute("randomArticleList",randomArticleList);
		//get hot article
		List<Article> mostCommentArticleList = articleService.listRandomArticle(8);
		model.addAttribute("mostCommentArticleList",mostCommentArticleList);
		model.addAttribute("pageUrlPrefix","/category/"+pageIndex+"?pageIndex");
		return "Home/Page/articleListByCategory";
	}
	
}
