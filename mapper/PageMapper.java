package com.jimmyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jimmyblog.entity.Page;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Mapper
public interface PageMapper {

	/**
	 * @Description: get index list
	 * @param status
	 * @return    
	 */
    List<Page> listPage(@Param(value = "status") Integer status);
    
	/**
	 * 
	 * @Description: get page from key
	 * @param status
	 * @param key
	 * @return
	 */
    Page getPageByKey(@Param(value = "status") Integer status,
            @Param(value = "key") String key);
    
	/**
	 * @Description: get page from id
	 * @param id
	 * @return    
	 */
    Page getPageById(Integer pageId);
    
	/**
	 * @Description: add page
	 * @param page    
	 */
	
    int insert(Page page);
    
	/**
	 * @Description: update page
	 * @param page    
	 */
    int update(Page page);
}
