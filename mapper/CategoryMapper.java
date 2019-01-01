package com.jimmyblog.mapper;

import java.util.List;

import com.jimmyblog.entity.Category;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
public interface CategoryMapper {

	/**
	 * @Description: delete category
	 * @param id    
	 */
	
	int deleteCategory(Integer id);

	/**
	 * @Description: get category info 
	 * @param id
	 * @return    
	 */
	
	Category getCategoryById(Integer id);

	/**
	 * @Description: update
	 * @param category    
	 */
	
	int update(Category category);

	/**
	 * @Description: add
	 * @param category    
	 */
	
	int insert(Category category);

	/**
	 * @Description:sum category
	 * @return   
	 */
	
	Integer countCategory();

	/**
	 * @Description:	category list
	 * @return    
	 */
	
	List<Category> listCategory();

	/**
	 * @Description: get category by category name
	 * @param name
	 * @return    
	 */
	
	Category getCategoryByName(String name);

}
