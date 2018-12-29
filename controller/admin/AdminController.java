package com.jimmyblog.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.CommentService;
import com.jimmyblog.service.UserSerivce;
import com.jimmyblog.entity.*;

import org.springframework.ui.Model;

import static com.jimmyblog.util.Functions.getIpAddr;
/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */

@Controller
public class AdminController {
	@Autowired
	private UserSerivce userSerivce;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	/**
	 * 
	 * @Description: backend index
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin")
	public String index(Model model) {
		//ariticle list 
		List<Article> articleList  = articleService.listRencentArticle(5);
		model.addAttribute("articleList", articleList);
		//comment list
		List<Comment> commenetsList = commentService.listRencentComment(5);
		model.addAttribute("commenetsList", commenetsList);
		return "Admin/index";
	}
	
	/**
	 * 
	 * @Description: login page
	 * @return
	 */
	@RequestMapping("/login")
	public String loginPage() { return "Admin/login"; }
	
	@RequestMapping(value = "/loginVerify", method =  RequestMethod.POST)
	@ResponseBody
    public String loginVerify(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map =  new HashMap<String,Object >();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberme = request.getParameter("rememberme");
		User user = userSerivce.getUserByNameOrEmail(username);
		if(user==null) {
			map.put("code", 0);
			map.put("msg", "用户名无效");
		}else if (!user.getUserPass().equals(password)) {
		    map.put("code", 0);
		    map.put("msg","密码错误!");
		}else {
			//login success
			map.put("code",1);
			map.put("msg", "");
			//add session
			request.getSession().setAttribute("user", user);
			if(rememberme!=null) {
				//create cookie
				Cookie nameCookie = new Cookie("username", username);
				//set cookie expire
				nameCookie.setMaxAge(60 * 60 * 24 * 3);
				Cookie pwdCookie = new Cookie("password",password);
				pwdCookie.setMaxAge(60 * 60 * 24 * 3);
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);
			}
				user.setUserLastLoginTime(new Date());
				user.setUserLastLoginIp(getIpAddr(request));
				userSerivce.updateUser(user);
		}
		String result = new JSONObject(map).toString();
		return result;
    }
	/**
	 * 
	 * @Description: logout
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/admin/logout")
	public String logout(HttpSession session) { 
		session.removeAttribute("user");
		session.invalidate();
		return "redirect:/login";
	}
}
