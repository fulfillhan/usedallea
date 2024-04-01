package com.application.usedallea.product.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.img.dto.ImgDTO;
import com.application.usedallea.product.dto.ProductDTO;

public interface ProductService {

	public List<ProductDTO> getAllProudctList(ProductDTO productDTO);

	//public File createProduct(MultipartFile uploadFile, ProductDTO productDTO, ImgDTO imgDTO) throws Exception;

	public void createProduct(MultipartFile uploadImg, ProductDTO productDTO, ImgDTO imgDTO) throws Exception, IOException;

	
}
