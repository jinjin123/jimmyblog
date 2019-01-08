package com.jimmyblog.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.CategoryService;
import com.jimmyblog.entity.Category;

/**
 * Created  by  Jimmy  on 2018/12/28
 *
 */
public class BackCategoryController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * 
	 * @Description: category list 
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView categoryList() {
		ModelAndView  modelAndView = new ModelAndView();
		List<Category> categoryList = categoryService.listCategoryWithCount();
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("Admin/Category/index");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: add category
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
	public String insertCategorySubmit(Category category) {
		categoryService.insertCategory(category);
		return "redirect:/admin/category";
	}
	/**
	 * 
	 * @Description: delete category
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	public String deleteCategory(@PathVariable("id")Integer id) {
		int count = articleService.countArticleByCategoryId(id);
		if(count == 0 ) {
			categoryService.deleteCategory(id);
		}
		return "redirect:/admin/category";
	}
	/**
	 * 
	 * @Description: edit category page
	 * @param id
	 * @return modelview
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editCategoryView(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Category category = categoryService.getCategoryById(id);
		modelAndView.addObject("category",category);
		
		List<Category> categoryList = categoryService.listCategoryWithCount();
		modelAndView.addObject("categoryList",categoryList);
		
		modelAndView.setViewName("/Admin/Category/edit");
		return 	modelAndView;
	}
	@RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
	public String editCategorySubmit(Category category) {
		categoryService.updateCategory(category);
		return "redirect:/admin/category";
	}
	
	
}
