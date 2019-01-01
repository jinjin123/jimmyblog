package com.jimmyblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jimmyblog.entity.User;
import com.jimmyblog.mapper.ArticleMapper;
import com.jimmyblog.mapper.UserMapper;
import com.jimmyblog.service.UserSerivce;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Service
public class UserServiceImpl implements UserSerivce {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;
    
	@Override
    @Cacheable(value = "default", key = "'user:'+#id")
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public User getUserByNameOrEmail(String str) {
		return userMapper.getUserByNameOrEmail(str);
	}

	@Override
    @Cacheable(value = "default", key = "'user:'+#id")
	public void updateUser(User user) {
		userMapper.update(user);
	}

	@Override
	public List<User> listUser() {
        List<User> userList = userMapper.listUser();
        for (int i = 0; i < userList.size(); i++) {
            Integer articleCount = articleMapper.countArticleByUser(userList.get(i).getUserId());
            userList.get(i).setArticleCount(articleCount);
        }
        return userList;
	}

	@Override
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}

	@Override
	public User getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	@Override
	public User insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
        return user;
	}

	@Override
    @CacheEvict(value = "default", key = "'user:'+#id")
	public void deleteUser(Integer id) {
		userMapper.deleteById(id);
	}

}
