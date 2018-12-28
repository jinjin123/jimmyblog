package com.jimmyblog.controller.service;

import com.jimmyblog.entity.User;

/**
 * Created  by  Jimmy  on 2018/12/27
 *
 */
public interface UserSerivce {

	/**
	 * @Description: TODO
	 * @param articleId
	 * @return    
	 */
	
	User getUserById(Integer articleId);

	/**
	 * @Description:check user  acount from username | email
	 * @param username or email
	 * @return    
	 */
	
	User getUserByNameOrEmail(String username);

	/**
	 * @Description:edit user info
	 * @param user    
	 */
	
	void updateUser(User user);

}
