package com.jimmyblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.jimmyblog.entity.Link;
import com.jimmyblog.mapper.LinkMapper;
import com.jimmyblog.service.LinkService;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
public class LinkServiceImpl implements LinkService {

	@Autowired(required = false)
	private LinkMapper linkMapper;
	
	@Override
	public List<Link> listLink(Integer status) {
		return linkMapper.listLink(status);
	}

	@Override
	@CachePut(value = "default", key = "'link:'+#link.linkId")
	public void insertLink(Link link) {
		linkMapper.insert(link);
	}

	@Override
	@CacheEvict(value = "default", key = "'link:'+#id")
	public void deleteLink(Integer id) {
		linkMapper.deleteById(id);
	}

	@Override
	@Cacheable(value = "default", key = "'link:'+#id")
	public Link getLinkById(Integer id) {
		return linkMapper.getLinkById(id);
	}

	@Override
	@CacheEvict(value = "default", key = "'link:'+#link.linkId")
	public void updateLink(Link link) {
		linkMapper.update(link);
	}

	@Override
	public Integer countLink(Integer status) {
		return linkMapper.countLink(status);
	}

}
