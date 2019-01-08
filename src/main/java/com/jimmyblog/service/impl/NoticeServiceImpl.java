package com.jimmyblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.jimmyblog.entity.Notice;
import com.jimmyblog.mapper.NoticeMapper;
import com.jimmyblog.service.NoticeSerivce;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
public class NoticeServiceImpl implements NoticeSerivce {
	
    @Autowired(required = false)
    private NoticeMapper noticeMapper;
    
	@Override
	public List<Notice> listNotice(Integer status) {
		return noticeMapper.listNotice(status);
	}

	@Override
    @CachePut(value = "default", key = "'notice:'+#notice.noticeId")
	public void insertNotice(Notice notice) {
		noticeMapper.insert(notice);
	}

	@Override
    @CacheEvict(value = "default", key = "'notice:'+#id")
	public void deleteNotice(Integer id) {
		 noticeMapper.deleteById(id);
	}

	@Override
    @Cacheable(value = "default", key = "'notice:'+#id")
	public Notice getNoticeById(Integer id) {
		return noticeMapper.getNoticeById(id);
	}

	@Override
    @CacheEvict(value = "default", key = "'notice:'+#notice.noticeId")
	public void updateNotice(Notice notice) {
		noticeMapper.update(notice);
	}

}
