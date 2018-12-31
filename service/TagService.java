package com.jimmyblog.service;

import java.util.List;

import com.jimmyblog.entity.Tag;


/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
public interface TagService {

	/**
	 * @Description: get tag list
	 * @return  tag list
	 */
	
	List<Tag> listTag();

	/**
	 * @Description:get tag 
	 * @return    
	 */
	
	List<Tag> listTagwithCount();

	/**
	 * @Description: add tag
	 * @param tag    
	 */
	
	void insertTag(Tag tag);

	/**
	 * @Description: delte tag from this id
	 * @param id    
	 */
	
	void deleteTag(Integer id);

	/**
	 * @Description: get tag from id
	 * @param id
	 * @return    
	 */
	
	Tag getTagById(Integer id);

	/**
	 * @Description: get tag list
	 * @return    
	 */
	
	List<Tag> listTagWithCount();

	/**
	 * @Description:update tag 
	 * @param tag    
	 */
	
	void updateTag(Tag tag);

}
