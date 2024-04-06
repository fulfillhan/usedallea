package com.application.usedallea.product.service;

import java.io.IOException;
import java.util.List;
import com.application.usedallea.img.dto.ProductImgDTO;
import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.product.dto.ProductDTO;

public interface ProductService {

	public List<ProductDTO> getAllProudctList(ProductDTO productDTO);

	//public File createProduct(MultipartFile uploadFile, ProductDTO productDTO, ProductImgDTO ProductImgDTO) throws Exception;

	  public void createProduct(List<MultipartFile> uploadImg, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception, IOException;
	  public ProductDTO getProductDetail(long productId, boolean isCheckReadCnt);
	  public String getImgUUID(long productImgId);
}

