package com.jimmyblog.service;

import java.util.List;

import com.jimmyblog.entity.Menu;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
public interface MenuService {

	/**
	 * @Description: menu list
	 * @return    
	 */
	
	List<Menu> listMenu();

	/**
	 * @Description: add menu
	 * @param menu    
	 * @return 
	 */
	
	Menu insertMenu(Menu menu);

	/**
	 * @Description: delete id
	 * @param id    
	 */
	
	void deleteMenu(Integer id);

	/**
	 * @Description: get menu from  id
	 * @param id
	 * @return    
	 */
	
	Menu getMenuById(Integer id);

	/**
	 * @Description: update menu
	 * @param menu    
	 */
	
	void updateMenu(Menu menu);

}
