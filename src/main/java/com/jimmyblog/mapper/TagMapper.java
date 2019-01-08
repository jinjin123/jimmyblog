package com.jimmyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jimmyblog.entity.Tag;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 *
 */
@Mapper
public interface TagMapper {
	/**
	 * @Description: get tag list
	 * @return  tag list
	 */
	
    List<Tag> listTag() ;
    
	/**
	 * @Description: get tag from id
	 * @param id
	 * @return    
	 */
    Tag getTagById(Integer tagId);
    
	/**
	 * @Description: add tag
	 * @param tag    
	 * @return 
	 */
    int insert(Tag tag);
    

	/**
	 * @Description:update tag 
	 * @param tag    
	 */
    int update(Tag tag);
    
	/**
	 * @Description: delte tag from this id
	 * @param id    
	 */
    int deleteById(Integer tagId);
    
	/**
	 * @Description: get tag count
	 * @return    
	 */
    Integer countTag() ;
}
