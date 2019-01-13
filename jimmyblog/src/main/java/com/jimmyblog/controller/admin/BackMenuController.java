package com.jimmyblog.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jimmyblog.entity.Menu;
import com.jimmyblog.enums.MenuLevel;
import com.jimmyblog.service.MenuService;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {
	@Autowired
	private MenuService menuService;
	/**
	 * 
	 * @Description: menu list
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	public String menuList(Model model) {
		List<Menu> menuList  = menuService.listMenu();
		model.addAttribute("menuList", menuList);
		return "Admin/Menu/index";
	}
	
	/**
	 * 
	 * @Description: add menu
	 * @param menu
	 * @return
	 */
	public String insertMenuSubmit(Menu menu) {
		if(menu.getMenuOrder() == null) {
			menu.setMenuOrder(MenuLevel.TOP_MENU.getValue());
		}
		menuService.insertMenu(menu);
		return "redirect:/admin/menu";
	}
	/**
	 * 
	 * @Description: delete id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public String deleteMenu(@PathVariable("id")Integer id) {
		menuService.deleteMenu(id);
		return "redirect:/admin/menu";
	}
	/**
	 * 
	 * @Description: edit menu page
	 * @param id
	 * @return
	 */
	public ModelAndView editMenuView(@PathVariable("id")Integer id) {
		ModelAndView  modelAndView = new ModelAndView();
		
		Menu menu = menuService.getMenuById(id);
		modelAndView.addObject("menu",menu);
		
		List<Menu> menuList = menuService.listMenu();
		modelAndView.addObject("menuList",menuList);
		
		modelAndView.setViewName("Admin/Menu/edit");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: update menu
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
	public String editMenuSubmit(Menu menu) {
		menuService.updateMenu(menu);
		return "redirect:/admin/menu";
	}
}
