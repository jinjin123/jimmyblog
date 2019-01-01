package com.jimmyblog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jimmyblog.entity.Options;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Mapper
public interface OptionsMapper {
	
	/**
	 * @Description:get basic info
	 * @return    
	 */
    Options getOptions();
    
	/**
	 * @Description: add settings
	 * @param option    
	 */
    int insert(Options options);
    
	/**
	 * @Description: update settings
	 * @param option    
	 */
	
    int update(Options options);
    
}
