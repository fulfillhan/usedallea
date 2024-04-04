package com.application.usedallea.img.controller;

import java.io.IOException;

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
	
	@Value("${file.repo.path")
	private String imgRepositoryPath;
	
	@Autowired
	private ProductImgService productimgService;
	
	@GetMapping("/thumbnails")
	@ResponseBody
	public UrlResource thumbnails(@RequestParam("imgName") String imgName) throws IOException {
		return new UrlResource(imgRepositoryPath+imgName);
	}



}
