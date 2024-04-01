package com.application.usedallea.img.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.img.dao.ImgDAO;
import com.application.usedallea.img.dto.ImgDTO;

@Service
public class ImgServiceImpl implements ImgService {
	
	@Value("${file.repo.path}")
	private String imgRepositoryPath;
	
	@Autowired
	private ImgDAO imgDAO;
	
	@Override
	public long createImg(MultipartFile uploadImg, ImgDTO imgDTO) throws IOException {
		
		
		if(!uploadImg.isEmpty()) {
			String originalImgName = uploadImg.getOriginalFilename();
			imgDTO.setOriginalName(originalImgName);
			
			UUID uuid = UUID.randomUUID();
			String extenstion = originalImgName.substring(originalImgName.lastIndexOf("."));
		     String imgUUID = uuid + extenstion;
			imgDTO.setImgUUID(imgUUID);
			
			uploadImg.transferTo(new File(imgRepositoryPath+imgUUID));
		}
		//이미지 등록하기-> imgId 생성
		return imgDAO.saveImg(imgDTO);
	}

	
	

	





	
}
