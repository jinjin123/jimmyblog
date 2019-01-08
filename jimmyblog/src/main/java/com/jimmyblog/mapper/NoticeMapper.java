package com.jimmyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jimmyblog.entity.Notice;

/**
 * Created  by  Jimmy  on 2019/01/01
 *
 */
@Mapper
public interface NoticeMapper {

	/**
	 * @Description: get notice list
	 * @param 
	 * @return list    
	 */
	
    List<Notice> listNotice(@Param(value = "status") Integer status);

	/**
	 * @Description: add notice
	 * @param notice    
	 */
	
    int insert(Notice notice);
    
	/**
	 * @Description: delete notice
	 * @param id    
	 */
    int deleteById(Integer noticeId);
    
	/**
	 * @Description: get notice from id; 
	 * @param id
	 * @return    
	 */
    Notice getNoticeById(Integer noticeId);
    
	/**
	 * @Description: update notice
	 * @param notice    
	 */
	
    int update(Notice notice);
}
