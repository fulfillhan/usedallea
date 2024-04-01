package com.application.usedallea.img.service;



import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.img.dto.ImgDTO;


public interface ImgService {

	// 이미지 저장하기
	//public void saveImg(MultipartFile uploadImg, ImgDTO imgDTO, long productId) throws Exception, IOException;
	
	public long createImg(MultipartFile uploadImg, ImgDTO imgDTO) throws IOException;
}
