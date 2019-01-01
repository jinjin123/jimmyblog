package com.jimmyblog.service;

import java.util.List;

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

	/**
	 * @Description: get user list
	 * @return    
	 */
	
	List<User> listUser();

	/**
	 * @Description: get user from user
	 * @param username
	 * @return    
	 */
	
	User getUserByName(String username);

	/**
	 * @Description: check user from email
	 * @param email
	 * @return    
	 */
	
	User getUserByEmail(String email);

	/**
	 * @Description: add user
	 * @param user    
	 * @return 
	 */
	
	User insertUser(User user);

	/**
	 * @Description: delete user
	 * @param id    
	 */
	
	void deleteUser(Integer id);

}
