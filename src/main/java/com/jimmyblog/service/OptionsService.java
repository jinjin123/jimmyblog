package com.jimmyblog.service;

import com.jimmyblog.entity.Options;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
public interface OptionsService {

	/**
	 * @Description: get basic info
	 * @return    
	 */
	
	Options getOptions();


	/**
	 * @Description: add settings
	 * @param option    
	 */
	
	void insertOptions(Options option);

	/**
	 * @Description: update settings
	 * @param option    
	 */
	
	void updateOptions(Options option);




}
