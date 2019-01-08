package com.jimmyblog.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Objects;
import com.jimmyblog.entity.Page;
import com.jimmyblog.enums.PageStatus;
import com.jimmyblog.service.PageService;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
@RequestMapping("/admin/page")
public class BackPageController {
	@Autowired
	private PageService pageService;
	
	/**
	 * 
	 * @Description: index
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		List<Page> pageList = pageService.listPage(null);
		modelAndView.addObject("pageList",pageList);
		modelAndView.setViewName("Admin/Page/index");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: add page 
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ModelAndView insert() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Admin/Page/insert");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: submit page
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
	public String insertPageSubmit(Page page) {
		//alias else true
		Page checkPage = pageService.getPageByKey(null,page.getPageKey());
		if(checkPage == null) {
			page.setPageCreateTime(new Date());
			page.setPageUpdateTime(new Date());
			page.setPageStatus(PageStatus.NORMAL.getValue());
			pageService.insertPage(page);
		}
		return "redirect:/admin/page";
	}
	/**
	 * 
	 * @Description: edit page
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editPageView(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Page page =  pageService.getPageById(id);
		modelAndView.addObject("page",page);
		
		modelAndView.setViewName("Admin/Page/edit");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: update page
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/editSubmit",method =  RequestMethod.POST)
	public String editPageSubmit(Page page) {
		Page checkPage = pageService.getPageByKey(null, page.getPageKey());
		if(Objects.equal(checkPage.getPageId(), page.getPageId())) {
			page.setPageUpdateTime(new Date());
			pageService.updatePage(page);
		}
		return "redirect:/admin/page";
	}
}
