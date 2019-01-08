package com.jimmyblog.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jimmyblog.entity.Notice;
import com.jimmyblog.enums.NoticeStatus;
import com.jimmyblog.service.NoticeSerivce;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {
	@Autowired
	private NoticeSerivce noticeService;
	/**
	 * 
	 * @Description: return backend notice 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(Model model) {
		List<Notice> noticeList = noticeService.listNotice(null);
		model.addAttribute("noticeList",noticeList);
		return "Admin/Notice/index";
	}
	/**
	 * 
	 * @Description:add Notice
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String IndexNoticeView() {
		return  "Admin/Notice/insert";
	}
	@RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
	public String insertNoticeSubmit(Notice notice) {
		notice.setNoticeCreateTime(new Date());
		notice.setNoticeUpdateTime(new Date());
		notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
		notice.setNoticeOrder(1);
		noticeService.insertNotice(notice);
		return "redirect:/admin/notice";
	}
	/**
	 * 
	 * @Description: delete notice
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	public String deleteNotice(@PathVariable("id")Integer id) {
		noticeService.deleteNotice(id);
		
		return "redirect:/admin/notice";
	}
	/**
	 * 
	 * @Description: edit  notice 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public String editNoticeView(@PathVariable("id") Integer id, Model model) {
		Notice notice = noticeService.getNoticeById(id);
		model.addAttribute("notice", notice);
		return "Admin/Notice/edit";
	}
	
	@RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
	public String editNoticeSubmit(Notice notice) {
		notice.setNoticeUpdateTime(new Date());
		noticeService.updateNotice(notice);
		return "redirect:/admin/notice";
	}
}
