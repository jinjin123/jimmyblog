package com.jimmyblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jimmyblog.entity.Options;
import com.jimmyblog.mapper.OptionsMapper;
import com.jimmyblog.service.OptionsService;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
public class OptionsServiceImpl implements OptionsService {
	
    @Autowired(required = false)
    private OptionsMapper optionsMapper;

	@Override
    @Cacheable(value = "default", key = "'options'")
	public Options getOptions() {
		return optionsMapper.getOptions();
	}

	@Override
	public void insertOptions(Options option) {
		 optionsMapper.insert(option);
	}

	@Override
    @CacheEvict(value = "default", key = "'options'")
	public void updateOptions(Options option) {
		optionsMapper.update(option);
	}

}
