package com.jimmyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jimmyblog.entity.Menu;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Mapper
public interface MenuMapper {
	/**
	 * 
	 * @Description: add menu
	 * @param menu
	 * @return
	 */
    int insert(Menu menu);

	/**
	 * @Description: menu list
	 * @return    
	 */
    List<Menu> listMenu() ;

	/**
	 * @Description: delete id
	 * @param id    
	 */
    int deleteById(Integer menuId);
    
    
	/**
	 * @Description: get menu from  id
	 * @param id
	 * @return    
	 */
    Menu getMenuById(Integer menuId);
    
	/**
	 * @Description: update menu
	 * @param menu    
	 */
    int update(Menu menu);
}
