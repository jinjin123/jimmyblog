package com.jimmyblog.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jimmyblog.entity.User;
import com.jimmyblog.service.UserSerivce;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
@RequestMapping("/admin/user")
public class BackUserController {
	@Autowired
	private UserSerivce userSerivce;
	/**
	 * 
	 * @Description: user index
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView userList() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<User> userList = userSerivce.listUser();
		modelAndView.addObject("userList",userList);
		
		modelAndView.setViewName("Admin/User/index");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: insert user page
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ModelAndView insertUserView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Admin/User/insert");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: check user else true
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
	@ResponseBody
	public String checkUserName(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		String username  = request.getParameter("username");
		User user = userSerivce.getUserByName(username);
		int id  = Integer.valueOf(request.getParameter("id"));
		if(user != null) {
			if (user.getUserId()!=id) {
				map.put("code",1);
				map.put("msg","用户名已存在");
			}
		}else {
			map.put("code", 0);
			map.put("msg", "");
		}
		String result = new JSONObject(map).toString();
		return result;
	}
	/**
	 * 
	 * @Description: check email
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkUserEmail",method = RequestMethod.POST)
	@ResponseBody
	public String checkUserEmail(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		String email = request.getParameter("email");
		User user = userSerivce.getUserByEmail(email);
		int id  = Integer.valueOf(request.getParameter("id"));
		if(user!=null) {
			if(user.getUserId()!=id) {
				map.put("code", 1);
				map.put("msg", "电子邮箱已存在");
			}
		}else {
			map.put("code", 0);
			map.put("msg", "");
		}
		String result = new JSONObject(map).toString();
		return result;
	}
	/**
	 * 
	 * @Description: add user
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
	public String insertUserSubmit(User user) {
		User user2 = userSerivce.getUserByName(user.getUserName());
		User user3 = userSerivce.getUserByEmail(user.getUserEmail());
		if(user2==null&&user3==null) {
			user.setUserRegisterTime(new Date());
			user.setUserStatus(1);
			userSerivce.insertUser(user);
		}
		return "redirect:/admin/user";
	}
	/**
	 * 
	 * @Description: delete user
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable("id")Integer id) {
		userSerivce.deleteUser(id);
		return "redirect:/admin/user";
	}
	/**
	 * 
	 * @Description: edit user
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editUserView(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		User user =  userSerivce.getUserById(id);
		modelAndView.addObject("user",user);
		
		modelAndView.setViewName("Admin/user/edit");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: edit user submit
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
	public String editUserSubmit(User user) {
		userSerivce.updateUser(user);
		return "redirect:/admin/user";
	}
	
	/**
	 * 
	 * @Description: user profilt 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profile/{id}")
	public ModelAndView userProfileView(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		User user = userSerivce.getUserById(id);
		modelAndView.addObject("user",user);
		
		modelAndView.setViewName("Admin/User/Profile");
		return modelAndView;
 	}
}
