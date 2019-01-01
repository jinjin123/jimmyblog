package com.jimmyblog.controller.home;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.Comment;
import com.jimmyblog.entity.Link;
import com.jimmyblog.entity.Notice;
import com.jimmyblog.entity.Tag;
import com.jimmyblog.enums.ArticleStatus;
import com.jimmyblog.enums.LinkStatus;
import com.jimmyblog.enums.NoticeStatus;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.CommentService;
import com.jimmyblog.service.LinkService;
import com.jimmyblog.service.NoticeSerivce;
import com.jimmyblog.service.TagService;
import com.jimmyblog.util.RedisUtil;



/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private NoticeSerivce noticeSerivce;
	@Autowired
	private TagService tagService;
	@Autowired
	private CommentService commentService;
	@RequestMapping(value = {"/","/article"})
	public String index(@RequestParam(required = false, defaultValue="1")Integer pageIndex,
						@RequestParam(required = false, defaultValue="10")Integer pageSize,Model model) {
		HashMap<String,Object> criteria = new HashMap<>(1);
		criteria.put("status", ArticleStatus.PUBLISH.getValue());
		//article list
		PageInfo<Article> articleList =  articleService.pageArticle(pageIndex, pageSize, criteria);
		model.addAttribute("pageInfo",articleList);
		//notice
		List<Notice> noticeList = noticeSerivce.listNotice(NoticeStatus.NORMAL.getValue());
		model.addAttribute("noticeList", noticeList);
		//link 
		List<Link> linkList = linkService.listLink(LinkStatus.NORMAL.getValue());
		model.addAttribute("linkList",linkList);
		//tag
		List<Tag> tagList = tagService.listTag();
		model.addAttribute("tagList",tagList);
		//new comment
		List<Comment> recentCommentList = commentService.listRecentComment(10);
		model.addAttribute("recentCommentList",recentCommentList);
		model.addAttribute("pageUrlPrefix","/article?pageIndex");
		return "Home/index";
	}
	@RequestMapping(value = "/search")
	public String Search(
			@RequestParam("keywords") String keywords,
			@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
			@RequestParam(required = false, defaultValue = "10")Integer pageSize,Model model) {
		//article list
		HashMap<String, Object> criteria = new HashMap<>(2);
		criteria.put("status",ArticleStatus.PUBLISH.getValue());
		criteria.put("keywords",keywords);
		PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
		model.addAttribute("pageInfo",articlePageInfo);
		//tag menu 
		List<Tag> tagList = tagService.listTag();
		model.addAttribute("tagList",tagList);
		//get random article
		List<Article> randomArticleList = articleService.listRandomArticle(8);
		model.addAttribute("randomarticleList",randomArticleList);
		//get hot article
		List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8); 
		model.addAttribute("mostCommentArticleList",mostCommentArticleList);
		// recent comment
		List<Comment> recentCommentList = commentService.listRecentComment(8);
		model.addAttribute("recentCommentList",recentCommentList);
		model.addAttribute("pageUrlPrefix","/search?pageIndex");
		return "Home/Page/search";
 	}
	@RequestMapping("/404")
	public String NotFound(@RequestParam(required = false)String message,Model model) {
		model.addAttribute("message",message);
		return "Home/Error/404";
	}
	@RequestMapping("/500")
	public String ServerError(@RequestParam(required = false )String message,Model model) {
		model.addAttribute("message",message);
		return "Home/Error/500";
	}
	@Autowired(required = false)
	private RedisUtil redisUtil;
 	
	@RequestMapping("/testRedis")
	@ResponseBody
	public String testRedis(@RequestParam(name = "key")String key) {
		return (String) redisUtil.get(key);
	}
}
