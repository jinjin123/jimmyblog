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
import com.jimmyblog.entity.Tag;
import com.jimmyblog.enums.ArticleStatus;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.TagService;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
public class TagController {
	@Autowired
	private TagService tagService;
	
	@Autowired
	private ArticleService articleService;
	/**
	 * 
	 * @Description: get article from tag id
	 * @param tagId
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tag/{tagId}")
	public String getArticleListByTag(@PathVariable("tagId")Integer tagId,
									  @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
									  @RequestParam(required = false,defaultValue = "10")Integer pageSize,Model model) {
		//get this tag
		Tag tag = tagService.getTagById(tagId);
		if(tag == null) {
			return "redirect:/404";
		}
		model.addAttribute("tag",tag);
		
		//article list
		HashMap<String,Object> criteria = new HashMap<>(2);
		criteria.put("tagId", tagId);
		criteria.put("status", ArticleStatus.PUBLISH.getValue());
		PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
		model.addAttribute("pageInfo",articlePageInfo);
		//tag menu
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //get random article
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //get hot article
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        model.addAttribute("pageUrlPrefix", "/tag?pageIndex");
        return "Home/Page/articleListByTag";
	}
	
}
