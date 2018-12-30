package com.jimmyblog.service;

import com.jimmyblog.entity.Options;

/**
 * Created  by  Jimmy  on 2018/12/30
 *
 */
public interface OptionsService {

	/**
	 * @Description: system settings
	 * @return    
	 */
	
	Options getOptions();

	/**
	 * @Description: get settings
	 * @param option
	 * @return    
	 */
	
	Options getOptions(Options option);

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
