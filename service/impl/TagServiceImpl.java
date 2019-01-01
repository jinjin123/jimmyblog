package com.jimmyblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimmyblog.entity.Tag;
import com.jimmyblog.mapper.ArticleTagRefMapper;
import com.jimmyblog.mapper.TagMapper;
import com.jimmyblog.service.TagService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {

	
    @Autowired(required = false)
    private TagMapper tagMapper;

    @Autowired(required = false)
    private ArticleTagRefMapper articleTagRefMapper;
    
	@Override
	public List<Tag> listTag() {
        List<Tag> tagList = null;
        try {
            tagList = tagMapper.listTag();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得所有标签失败, cause:{}", e);
        }
        return tagList;
	}

	@Override
    @CachePut(value = "default", key = "'tag:'+#result.tagId")
	public Tag insertTag(Tag tag) {
       try {
            tagMapper.insert(tag);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加标签失败, tag:{}, cause:{}", tag, e);
        }
        return tag;
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "default", key = "'tag:'+#id")
	public void deleteTag(Integer id) {
        try {
            tagMapper.deleteById(id);
            articleTagRefMapper.deleteByTagId(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除标签失败, id:{}, cause:{}", id, e);
        }
	}

	@Override
    @Cacheable(value = "default", key = "'tag:'+#id")
	public Tag getTagById(Integer id) {
        Tag tag = null;
        try {
            tag = tagMapper.getTagById(id);
        } catch (Exception e) {           
        	e.printStackTrace();
            log.error("根据ID获得标签失败, id:{}, cause:{}", id, e);
        }
        return tag;
	}

	@Override
	public List<Tag> listTagWithCount() {
        List<Tag> tagList = null;
        try {
            tagList = tagMapper.listTag();
            for (int i = 0; i < tagList.size(); i++) {
                Integer count = articleTagRefMapper.countArticleByTagId(tagList.get(i).getTagId());
                tagList.get(i).setArticleCount(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得所有标签失败, cause:{}", e);
        }
        return tagList;
	}

	@Override
    @CacheEvict(value = "default", key = "'tag:'+#tag.tagId")
	public void updateTag(Tag tag) {
        try {
            tagMapper.update(tag);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新标签失败, tag:{}, cause:{}", tag, e);
        }
	}

	@Override
	public Integer countTag() {
		 return tagMapper.countTag();
	}

}
