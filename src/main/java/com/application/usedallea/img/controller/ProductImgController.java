package com.application.usedallea.img.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.application.usedallea.img.service.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/img")
public class ProductImgController {
	
	@Value("${file.repo.path}")
	private String imgRepositoryPath;
	
	@Autowired
	private ProductImgService productimgService;

//		@GetMapping("/setThumbnail")
//		@ResponseBody
//		public UrlResource setThumbnail(@RequestParam("imgNames") List<String> imgNames) throws IOException {
//			 return new UrlResource(imgRepositoryPath+imgNames);
//		}

	//img/detailThumbnails(imgName=${imgUUIDList})}
	//controller 만들고 list 으로 목록을 가지고와서 이미지태그 반복

	@GetMapping("/setThumbnail")
	@ResponseBody
	public UrlResource setThumbnail(@RequestParam("imgName") String imgName) throws IOException {
		return new UrlResource(imgRepositoryPath + imgName);
	}





}
