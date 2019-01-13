package com.jimmyblog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jimmyblog.entity.Options;
import com.jimmyblog.service.OptionsService;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
@Controller
@RequestMapping("/admin/options")
public class BackOptionsController {
	@Autowired
	private OptionsService optionService;
	/**
	 * 
	 * @Description: base page
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		Options option = optionService.getOptions();
		modelAndView.addObject("option",option);
		
		modelAndView.setViewName("Admin/Options/index");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Description: edit setting
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView editOptionView() {
		ModelAndView modelAndView = new ModelAndView();
		Options option = optionService.getOptions();
		modelAndView.addObject("option",option);
		
		modelAndView.setViewName("Admin/Options/edit");
		return modelAndView;
	}
	/**
	 * 
	 * @Description: add or update settings
	 * @param option
	 * @return
	 */
	@RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
	public String editOptionSubmit(Options option) {
		Options optionsCustom = optionService.getOptions();
		if(optionsCustom.getOptionId()==null) {
			optionService.insertOptions(option);
		}else {
			optionService.updateOptions(option);
		}
		return "redirect:/admin/options";
	}
}
