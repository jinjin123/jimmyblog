package com.jimmyblog.service;

import java.util.List;

import com.jimmyblog.entity.Category;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
public interface CategoryService {

	/**
	 * @Description: category list
	 * @return    
	 */
	
	List<Category> listCategory();

	/**
	 * @Description: get category list
	 * @return    
	 */
	
	List<Category> listCategoryWithCount();

	/**
	 * @Description: add category
	 * @param category    
	 */
	
	void insertCategory(Category category);

	/**
	 * @Description: delete category
	 * @param id    
	 */
	
	void deleteCategory(Integer id);

	/**
	 * @Description: get category list
	 * @param id
	 * @return    
	 */
	
	Category getCategoryById(Integer id);

	/**
	 * @Description: update category
	 * @param category    
	 */
	
	void updateCategory(Category category);

}
