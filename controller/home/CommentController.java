package com.jimmyblog.controller.home;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.Comment;
import com.jimmyblog.enums.ArticleStatus;
import com.jimmyblog.enums.Role;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.CommentService;
import com.jimmyblog.util.Functions;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 
	 * @Description: append comment
	 * @param request
	 * @param comment
	 */
	@RequestMapping(value = "/comment",method = RequestMethod.POST)
	@ResponseBody
	public void insertComment(HttpServletRequest request,Comment comment) {
		//add comment
		comment.setCommentCreateTime(new Date());
		comment.setCommentIp(Functions.getIpAddr(request));
		if(request.getSession().getAttribute("user")!=null) {
			comment.setCommentRole(Role.ADMIN.getValue());
		}else {
			comment.setCommentRole(Role.VISITOR.getValue());
		}
		comment.setCommentAuthorAvatar(Functions.getGravatar(comment.getCommentAuthorEmail()));
		commentService.insertComment(comment);
		
		//update article comment
		Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
		articleService.updateCommentCount(article.getArticleId());
	}
}
