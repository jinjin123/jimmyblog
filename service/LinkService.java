package com.jimmyblog.service;

import java.util.List;

import com.jimmyblog.entity.Link;

/**
 * Created  by  Jimmy  on 2018/12/29
 *
 */
public interface LinkService {

	/**
	 * @Description: get link list
	 * @param status
	 * @return    
	 */
	
	List<Link> listLink(Integer status);

	/**
	 * @Description: submit link
	 * @param link    
	 */
	
	void insertLink(Link link);

	/**
	 * @Description: delete link
	 * @param id    
	 */
	
	void deleteLink(Integer id);

	/**
	 * @Description: get lini from id 
	 * @param id
	 * @return    
	 */
	
	Link getLinkById(Integer id);

	/**
	 * @Description: update link
	 * @param link    
	 */
	
	void updateLink(Link link);

}
