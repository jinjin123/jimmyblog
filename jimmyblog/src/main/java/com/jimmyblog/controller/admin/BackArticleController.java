package com.jimmyblog.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.CategoryService;
import com.jimmyblog.service.TagService;
import com.jimmyblog.dto.ArticleParam;
import com.jimmyblog.entity.*;

import org.springframework.ui.Model;

/**
 * Created  by  Jimmy  on 2018/12/28
 *
 */
@Controller
@RequestMapping(value="/admin/article")
public class BackArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TagService tagService;
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 
	 * @Description:  backend article
	 * @param pageIndex
	 * @param pageSize
	 * @param status
	 * @param model
	 * @return  modelView
	 */
	@RequestMapping(value = "")
	public String index (@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
						 @RequestParam(required = false,defaultValue = "10")Integer pageSize,
						 @RequestParam(required = false) String status,Model model) {
		HashMap<String, Object> crieria = new HashMap<>(1);
		if(status ==null) {
			model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
		}else {
			crieria.put("status", status);
			model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
		}
		PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex,pageSize,crieria);
		model.addAttribute("pageInfo", articlePageInfo);
		return "Admin/Article/index";
	}
	/**
	 * 
	 * @Description: add article in backend
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String insertArticleView(Model model) {
		List<Category> categoryList = categoryService.listCategory();
		List<Tag> tagList = tagService.listTag();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tagList", tagList);
		return "Admin/Article/insert";
	}
	
	/**
	 * 
	 * @Description: submit article 
	 * @param session
	 * @param articleParam
	 * @return
	 */
	@RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
	public String insertArticleSubmit(HttpSession session,ArticleParam articleParam) {
		Article article = new Article();
		//user id
		User user = (User) session.getAttribute("user");
		if(user !=null) {
			article.setArticleUserId(user.getUserId());
		}
		article.setArticleTitle(articleParam.getArticleTitle());
		article.setArticleContent(articleParam.getArticleContent());
		article.setArticleStatus(articleParam.getArticleStatus());
		//input category
		List<Category> categoryList = new ArrayList<>();
		if(articleParam.getAritcleChildCategoryId() != null) {
			categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
		}
		if(articleParam.getAritcleChildCategoryId() != null) {
			categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
		}
		article.setCategoryList(categoryList);
		// input tag
		List<Tag>tagList = new ArrayList<>();
		if(articleParam.getArticleTagIds() != null ) {
			for(int i=0;i<articleParam.getArticleTagIds().size();i++) {
				Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
				tagList.add(tag);
			}
		}
		article.setTagList(tagList);
		articleService.insertArticle(article);
		return "redirect:/admin/article/";
	}
	/**
	 * 
	 * @Description: delete article
	 * @return
	 */
	@RequestMapping(value="/delete/{id}")
	public void  deleteArticle(@PathVariable("id")Integer id) {
		articleService.deleteArticle(id);
	}
	/**
	 * 
	 * @Description: edit  article content
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editArticleView(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Article article = articleService.getArticleByStatusAndId(null, id);
		modelAndView.addObject("article",article);
		
		List<Category> categoryList = categoryService.listCategory();
		modelAndView.addObject("categoryList",categoryList);
		
		List<Tag> tagList = tagService.listTag();
		modelAndView.addObject("tagList",tagList);
		
		modelAndView.setViewName("Admin/Article/edit");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: edit aritcle done submit
	 * @param articleParam
	 * @return
	 */
	@RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
	public String editArticleSubmit(ArticleParam articleParam) {
		Article article = new Article();
		article.setArticleId(articleParam.getArticleId());
		article.setArticleTitle(articleParam.getArticleTitle());
		article.setArticleContent(articleParam.getArticleContent());
        //input category
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //input tag
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        articleService.updateArticleDetail(article);
 		return "redirect:/admin/article";
	}
}
