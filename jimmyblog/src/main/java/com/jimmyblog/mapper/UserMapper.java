package com.jimmyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jimmyblog.entity.User;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Mapper
public interface UserMapper {
	/**
	 * @Description: get user list
	 * @return    
	 */
    List<User> listUser() ;
    
	/**
	 * @Description: TODO
	 * @param articleId
	 * @return    
	 */
    User getUserById(Integer userId);
    
	/**
	 * @Description:edit user info
	 * @param user    
	 */
	
    int update(User user);
    
	/**
	 * @Description: delete user
	 * @param id    
	 */
    int deleteById(Integer userId);
    
	/**
	 * @Description: add user
	 * @param user    
	 */
    int insert(User user);
    
	/**
	 * @Description:check user  acount from username | email
	 * @param username or email
	 * @return    
	 */
    User getUserByNameOrEmail(String str) ;
    
	/**
	 * @Description: get user from user
	 * @param username
	 * @return    
	 */
    User getUserByName(String name) ;
    
	/**
	 * @Description: check user from email
	 * @param email
	 * @return    
	 */
    User getUserByEmail(String email) ;
}
