package com.jimmyblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.jimmyblog.entity.Page;
import com.jimmyblog.mapper.PageMapper;
import com.jimmyblog.service.PageService;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
public class PageServiceImpl implements PageService {
    @Autowired(required = false)
    private PageMapper pageMapper;
    
	@Override
	public List<Page> listPage(Integer status) {
		return pageMapper.listPage(status);
	}

	@Override
	public Page getPageByKey(Integer status, String key) {
		return pageMapper.getPageByKey(status, key);
	}

	@Override
    @CachePut(value = "default", key = "'page:'+#page.pageId")
	public void insertPage(Page page) {
		pageMapper.insert(page);
	}

	@Override
    @Cacheable(value = "default", key = "'page:'+#id")
	public Page getPageById(Integer id) {
		 return pageMapper.getPageById(id);
	}

	@Override
    @CacheEvict(value = "default", key = "'page:'+#page.pageId")
	public void updatePage(Page page) {
		pageMapper.update(page);
	}

}
