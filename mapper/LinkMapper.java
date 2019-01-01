package com.jimmyblog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.jimmyblog.entity.Link;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Mapper
public interface LinkMapper {
	/**
	 * 
	 * @Description: get link
	 * @param status
	 * @return
	 */
    Integer countLink(@Param(value = "status") Integer status);
    /**
     * 
     * @Description: get list 
     * @param status
     * @return
     */
    List<Link> listLink(@Param(value = "status") Integer status);
    /**
     * 
     * @Description: add
     * @param link
     * @return
     */
    int insert(Link link);
    /**
     * 
     * @Description: delete link
     * @param linkId
     * @return
     */
    int deleteById(Integer linkId);
    
    /**
     * 
     * @Description: update link
     * @param link
     * @return
     */
    int update(Link link);
    /**
     * 
     * @Description: get link
     * @param linkId
     * @return
     */
    Link getLinkById(Integer linkId);
}
