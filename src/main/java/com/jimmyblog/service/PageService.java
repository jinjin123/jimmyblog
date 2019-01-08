package com.jimmyblog.service;

import java.util.List;

import com.jimmyblog.entity.Page;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
public interface PageService {

	/**
	 * @Description: get index list
	 * @param status
	 * @return    
	 */
	
	List<Page> listPage(Integer status);

	/**
	 * 
	 * @Description: get page from key
	 * @param status
	 * @param key
	 * @return
	 */
	Page getPageByKey(Integer status,String key);

	/**
	 * @Description: add page
	 * @param page    
	 */
	
	void insertPage(Page page);

	/**
	 * @Description: get page from id
	 * @param id
	 * @return    
	 */
	
	Page getPageById(Integer id);

	/**
	 * @Description: update page
	 * @param page    
	 */
	
	void updatePage(Page page);
}
