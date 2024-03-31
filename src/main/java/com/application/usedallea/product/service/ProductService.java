package com.application.usedallea.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.product.dto.ProductDTO;

public interface ProductService {

	public List<ProductDTO> getAllProudctList(ProductDTO productDTO);

	public void createProduct(MultipartFile uploadFile, ProductDTO productDTO);

}
