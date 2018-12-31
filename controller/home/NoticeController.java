package com.jimmyblog.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.Notice;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.NoticeSerivce;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
public class NoticeController {
	@Autowired
	private NoticeSerivce noticeSerivce;
	@Autowired
	private ArticleService articleService;
	/**
	 * 
	 * @Description: show notice
	 * @param noticeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/notice/{noticeId}")
	public String NoticeDetailView(@PathVariable("noticeId")Integer noticeId,Model model) {
		//show notice
		Notice notice = noticeSerivce.getNoticeById(noticeId);
		model.addAttribute("notice",notice);
		//get hot article
		List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
		model.addAttribute("mostCommentArticleList",mostCommentArticleList);
		return "Home/Page/noticeDetail";
	}
}
