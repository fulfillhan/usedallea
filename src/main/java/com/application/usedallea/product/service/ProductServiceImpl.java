package com.application.usedallea.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.img.dto.ImgDTO;
import com.application.usedallea.img.service.ImgService;
import com.application.usedallea.product.dao.ProductDAO;
import com.application.usedallea.product.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<ProductDTO> getAllProudctList(ProductDTO productDTO) {
		
		return null;
	}

	@Override
	public void createProduct(MultipartFile uploadFile, ProductDTO productDTO) {
	   String uploadImg  = ImgService.saveImg(uploadFile);
		
	}
	

}
