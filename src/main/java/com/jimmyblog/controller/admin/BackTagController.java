package com.jimmyblog.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jimmyblog.entity.Tag;
import com.jimmyblog.service.ArticleService;
import com.jimmyblog.service.TagService;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {
	
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TagService tagService;
	
	/**
	 * 
	 * @Description: backend tag list
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		List<Tag> tagList = tagService.listTagWithCount();
		modelAndView.addObject("tagList",tagList);
		
		modelAndView.setViewName("Admin/Tag/index");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: add tag 
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
	public String insertTagSubmit(Tag tag) {
		tagService.insertTag(tag);
		return "redirect:/admin/tag";
	}
	/**
	 * 
	 * @Description:  delete tag 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	public String deleteTag(@PathVariable("id")Integer id) {
		Integer count = articleService.countArticleByTagId(id);
		if(count == 0) {
			tagService.deleteTag(id);
		}
		return "redirect:/admin/tag";
	}
	/**
	 * 
	 * @Description: edit tag page
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editTagView(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Tag tag = tagService.getTagById(id);
		modelAndView.addObject("tag",tag);
		
		List<Tag> tagList = tagService.listTagWithCount();
		modelAndView.addObject("tagList",tagList);
		
		modelAndView.setViewName("Admin/Tag/edit");
		
		return modelAndView;
	}
	/**
	 * 
	 * @Description: update tag
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
	public String editTagSubmit(Tag tag) {
		tagService.updateTag(tag);
		return "redirect:/admin/tag";
	}
}
