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

}
