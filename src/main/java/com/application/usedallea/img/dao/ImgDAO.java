package com.application.usedallea.img.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.usedallea.img.dto.ImgDTO;

@Mapper
public interface ImgDAO {
	
	long saveImg(ImgDTO imgDTO);
}
