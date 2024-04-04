package com.application.usedallea.product.service;

import com.application.usedallea.img.dto.ProductImgDTO;
import com.application.usedallea.img.service.ProductImgService;
import com.application.usedallea.product.dao.ProductDAO;
import com.application.usedallea.product.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductImgService productImgService;

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public List<ProductDTO> getAllProudctList(ProductDTO productDTO) {
		
		return null;
	}

	@Override
	public void createProduct(List<MultipartFile> uploadImg, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception, IOException {

		long imgId = productImgService.saveImg(uploadImg, productImgDTO); 	// img테이블에 이미지 저장하기 -> 이미지 id 생성

		//단위 테스트
	    //System.out.println(imgId);

		productDTO.setImgId(imgId);

		productDTO.setStatus(ProductStatus.판매중.name());                   // 상품의 품질상태 저장하기

		productDAO.createProduct(productDTO);                               //상품 테이블에 이미지 id 저장하여 등록된 상품테이블 모두 저장하기
	}
	
	@Override
	public ProductDTO getProductDetail(long productId, boolean isCheckReadCnt) {
		//title, 가격, readCount, 상품 상태, 상품 설명, 카테고리


		productDAO.updateReadCnt(productId);                                 // 조회수 증가 readCount
		return productDAO.getProductDetail(productId);
	
	}

	@Override
	public String getImgUUID(long productImgId) {

		return productDAO.getImgUUID(productImgId);
	}

}
