package com.jimmyblog.controller.common;

import java.io.File;
import java.util.Calendar;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.jimmyblog.dto.ResultVO;
import com.jimmyblog.dto.UploadFileVO;

import lombok.extern.slf4j.Slf4j;

/**
 * Created  by  Jimmy  on 2018/12/31
 *
 */
@Controller
@Slf4j
public class UploadFileController {
	public String uploadFile(@Param("file")MultipartFile file) {
		//upload path
		String rootPath = "/Users/wupeijin/Documents/uploads/";
		//file full name ->spring.jpeg
		String filename  = file.getOriginalFilename();
		//file name
		String name = filename.substring(0, filename.indexOf("."));
		//file format
		String suffix = filename.substring(filename.lastIndexOf("."));
		
		//create date yy-mm-dd file
		Calendar date = Calendar.getInstance();
		File dateDirs = new File(date.get(Calendar.YEAR) 
						+ File.separator + (date.get(Calendar.MONTH) + 1 ));
		
		//target file
		File descFile = new File(rootPath+File.separator+dateDirs+File.separator+filename);
		int i = 1;
		//check file exists
		String newFilename = filename;
		while(descFile.exists()) {
			newFilename = name+"("+i+")"+suffix;
			String parentPath = descFile.getParent();
			descFile = new File(parentPath+File.separator+newFilename);
			i++;
		}
		//chech target file dirs exists
		if(!descFile.getParentFile().exists()) {
			//if not exists create it
			descFile.getParentFile().mkdirs();
		}
		
		//write to disk
		try {
			file.transferTo(descFile);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("上传失败,cause:{}",e);
		}
		
		//full url
		String fileUrl = "/uploads"+dateDirs+"/"+newFilename;
		
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		
		UploadFileVO uploadFileVO = new UploadFileVO();
		uploadFileVO.setTitle(filename);
		uploadFileVO.setSrc(fileUrl);
		resultVO.setData(uploadFileVO);
		return JSON.toJSONString(resultVO);
	}
}
