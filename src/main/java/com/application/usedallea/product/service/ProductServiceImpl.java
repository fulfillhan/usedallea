package com.application.usedallea.product.service;

import java.io.IOException;
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
	
	@Autowired
	private ImgService imgService;

	@Override
	public List<ProductDTO> getAllProudctList(ProductDTO productDTO) {
		
		return null;
	}

	@Override
	public void createProduct(MultipartFile uploadImg, ProductDTO productDTO, ImgDTO imgDTO) throws Exception, IOException {
		
		// img테이블에 이미지 저장하기 -> 이미지 id 생성
		 long imgId = imgService.createImg(uploadImg, imgDTO);
		
		 productDTO.setStatus(ProductStatus.SELLING.name());
		//상품 테이블에 이미지 id 저장하여 등록된 상품테이블 모두 저장하기
		productDAO.createProduct(productDTO);
		
	}

	

}
