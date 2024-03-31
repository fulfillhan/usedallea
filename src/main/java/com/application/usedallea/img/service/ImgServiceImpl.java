package com.application.usedallea.img.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl implements ImgService {

	@Value("${file.repo.path}")
	private String imgRepositoryPath;	
	
	
	
}
