package com.jimmyblog.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.github.pagehelper.PageInfo;
import com.jimmyblog.controller.service.ArticleService;
import com.jimmyblog.controller.service.CommentService;
import com.jimmyblog.entity.Article;
import com.jimmyblog.entity.Comment;
import com.jimmyblog.enums.ArticleStatus;
import com.jimmyblog.util.Functions;

/**
 * Created  by  Jimmy  on 2018/12/29
 *
 */
@Controller
@RequestMapping(value = "/admin/comment")
public class BackCommentController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	/**
	 * 
	 * @Description: comment page
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return modelView
	 */
	@RequestMapping(value = "")
	public String commentListView(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
								  @RequestParam(required = false,defaultValue = "10") Integer pageSize,
								  Model  model) {
		
		PageInfo<Comment> commenetPageInfo = commentService.listCommentByPage(pageIndex,pageSize);
		model.addAttribute("PageInfo", commenetPageInfo);
		model.addAttribute("PageUrlPrefix", "/admin/comment?pageIndex");
		return "Admin/Comment/index";
	}
	/**
	 * 
	 * @Description: add comment
	 * @param request
	 * @param comment
	 */
	@RequestMapping(value = "/insert",method = {RequestMethod.POST})
	@ResponseBody
	public void insertComment(HttpServletRequest request,Comment comment) {
		//aad comment
		comment.setCommentIp(Functions.getIpAddr(request));
		comment.setCommentCreateTime(new Date());
		commentService.insertComment(comment);
		//update article comment
		Article article = articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
		articleService.updateCommentCount(article.getArticleId());
	}
	/**
	 * 
	 * @Description: delete comment
	 * @param id
	 */
	@RequestMapping(value = "/delete/{id}")
	public void deleteComment(@PathVariable("id")Integer id) {
		Comment comment = commentService.getCommentById(id);
		//delete comment
		commentService.deleteComment(id);
		//delete child comment
		List<Comment> childCommentList = commentService.listChildComment(id);
		for(int i = 0;i<childCommentList.size();i++) {
			commentService.deleteComment(childCommentList.get(i).getCommentId());
		}
		//update article comment
		Article article = articleService.getArticleByStatusAndId(null,comment.getCommentArticleId()) ;
		articleService.updateCommentCount(article.getArticleId());
	}
	/**
	 * 
	 * @Description: edit comment
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public String editCommentView(@PathVariable("id")Integer id,Model model) {
		Comment comment = commentService.getCommentById(id);
		model.addAttribute("comment", comment);
		return "Admin/comment/edit";
	}
	/**
	 * 
	 * @Description: edit comment&submit
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment) {
    	commentService.updateComment(comment);
    	return "redirect:/admin/comment";
    }
	/**
	 * 
	 * @Description: reply page 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reply/{id}")
	public String replyCommentView(@PathVariable("id")Integer id ,Model model) {
		Comment comment = commentService.getCommentById(id);
		model.addAttribute("comment", comment);
		return "Admin/comment/reply";
	}
	/**
	 * 
	 * @Description: reply comment submit
	 * @param request
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/replySubmit",method = RequestMethod.POST)
	public String replyCommentSubmit(HttpServletRequest request, Comment comment) {
		Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
		article.setArticleCommentCount(article.getArticleCommentCount() + 1);
		articleService.updateArticle(article);
		//add comment
		comment.setCommentCreateTime(new Date());
		comment.setCommentIp(Functions.getIpAddr(request));
		return "redirect:/admin/comment";
	}
}

