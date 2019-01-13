package com.jimmyblog.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jimmyblog.entity.Link;
import com.jimmyblog.service.LinkService;

/**
 * Created  by  Jimmy  on 2018/12/29
 *
 */
@Controller
@RequestMapping("/admin/link")
public class BackLinkController {
	@Autowired
	private LinkService linkService;
	/**
	 * 
	 * @Description: back link page
	 * @return modelview
	 */
	@RequestMapping(value = "")
	public ModelAndView linkList() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Link> linkList = linkService.listLink(null);
		modelAndView.addObject("linklist",linkList);
		
		modelAndView.setViewName("Admin/Link/index");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: create link page
	 * @return modelview
	 */
	@RequestMapping(value = "/insert")
	public ModelAndView insertLinkView() {
		ModelAndView  modelAndView = new ModelAndView();
		
		List<Link> linkList = linkService.listLink(null);
		modelAndView.addObject("linkList",linkList);
		
		modelAndView.setViewName("Admin/Link/insert");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: subtmit link page
	 * @param link
	 * @return
	 */
	@RequestMapping(value ="/insertSubmit",method = RequestMethod.POST)
	public String  insertLinkSubmit(Link link) {
		link.setLinkCreateTime(new Date());
		link.setLinkUpdateTime(new Date());
		link.setLinkStatus(1);
		linkService.insertLink(link);
		return "redirect:/admin/link/insert";
	}
	/**
	 * 
	 * @Description: delete link
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}")
	public String deleteLink(@PathVariable("id")Integer id) {
		linkService.deleteLink(id);
		return "redirect:/admin/link";
	}
	/**
	 * 
	 * @Description: edit  link page
	 * @param id
	 * @return modelview
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editLinkView(@PathVariable("id")Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Link linkCustom = linkService.getLinkById(id);
		modelAndView.addObject("linkCustom",linkCustom);
		
		List<Link> listList = linkService.listLink(null);
		modelAndView.addObject("linkList",listList);
		
		modelAndView.setViewName("Admin/Link/edit");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: edit link submit
	 * @param link
	 * @return
	 */
	@RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
	public String editLinkSubmit(Link link) {
		link.setLinkUpdateTime(new Date());
		linkService.updateLink(link);
		return "redirect:/admin/link";
	}
	
}
