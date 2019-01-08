package com.jimmyblog.controller.home;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.Link;
import com.jimmyblog.enums.LinkStatus;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.LinkService;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
public class LinkController {
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private ArticleService articleService;
	@RequestMapping("/applyLink")
	public String applyLinkView(Model model) {
		//get hot article
		List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8); 
		model.addAttribute("mostCommentArticleList",mostCommentArticleList);
		return "Home/Page/applyLink";
	}
	/**
	 * 
	 * @Description:  add new link
	 * @param link
	 */
	@RequestMapping(value = "/applyLinkSubmit",method = {RequestMethod.POST})
	@ResponseBody
	public void applyLinkSubmit(Link link) {
		link.setLinkStatus(LinkStatus.NORMAL.getValue());
		link.setLinkCreateTime(new Date());;
		link.setLinkUpdateTime(new Date());
		linkService.insertLink(link);
	}
}
