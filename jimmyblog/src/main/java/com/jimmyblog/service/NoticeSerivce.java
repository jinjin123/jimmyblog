package com.jimmyblog.service;

import java.util.List;

import com.jimmyblog.entity.Notice;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
public interface NoticeSerivce {

	/**
	 * @Description: get notice list
	 * @param 
	 * @return list    
	 */
	
	List<Notice> listNotice(Integer status);

	/**
	 * @Description: add notice
	 * @param notice    
	 */
	
	void insertNotice(Notice notice);

	/**
	 * @Description: delete notice
	 * @param id    
	 */
	
	void deleteNotice(Integer id);

	/**
	 * @Description: get notice from id; 
	 * @param id
	 * @return    
	 */
	
	Notice getNoticeById(Integer id);

	/**
	 * @Description: update notice
	 * @param notice    
	 */
	
	void updateNotice(Notice notice);

}
