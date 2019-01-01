package com.jimmyblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimmyblog.entity.Category;
import com.jimmyblog.mapper.ArticleCategoryRefMapper;
import com.jimmyblog.mapper.CategoryMapper;
import com.jimmyblog.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private ArticleCategoryRefMapper articleCategoryRefMapper;
	@Override
	public List<Category> listCategory() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.listCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章获得分类列表失败, cause:{}", e);
        }
        return categoryList;
	}

	@Override
	public List<Category> listCategoryWithCount() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.listCategory();
            for (int i = 0; i < categoryList.size(); i++) {
                Integer count = articleCategoryRefMapper.countArticleByCategoryId(categoryList.get(i).getCategoryId());
                categoryList.get(i).setArticleCount(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章获得分类列表失败, cause:{}", e);
        }
        return categoryList;
	}

	@Override
	@CachePut(value = "default", key = "'category:'+#result.categoryId")
	public Category insertCategory(Category category) {
        try {
            categoryMapper.insert(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建分类失败, category:{}, cause:{}", category, e);
        }
        return category;
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "default", key = "'category:'+#id")
	public void deleteCategory(Integer id) {
        try {
            categoryMapper.deleteCategory(id);
            articleCategoryRefMapper.deleteByCategoryId(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除分类失败, id:{}, cause:{}", id, e);
        }
	}

	@Override
    @Cacheable(value = "default", key = "'category:'+#id")
	public Category getCategoryById(Integer id) {
	     Category category = null;
	        try {
	            category = categoryMapper.getCategoryById(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.error("根据分类ID获得分类, id:{}, cause:{}", id, e);
	        }
	        return category;
	}

	@Override
    @CacheEvict(value = "default", key = "'category:'+#category.categoryId")
	public void updateCategory(Category category) {
        try {
            categoryMapper.update(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新分类失败, category:{}, cause:{}", category, e);
        }
	}

	@Override
	public Integer countCategory() {
        Integer count = 0;
        try {
            count = categoryMapper.countCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计分类失败, cause:{}", e);
        }
        return count;
	}
	
    @Override
    public Category getCategoryByName(String name) {
        Category category = null;
        try {
            category = categoryMapper.getCategoryByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新分类失败, category:{}, cause:{}", category, e);
        }
        return category;
    }

}
