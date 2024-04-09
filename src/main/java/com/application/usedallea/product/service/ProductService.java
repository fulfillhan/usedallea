package com.application.usedallea.product.service;

import java.io.IOException;
import java.util.List;
import com.application.usedallea.img.dto.ProductImgDTO;
import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.product.dto.ProductDTO;

public interface ProductService {
	//List<ProductDTO> getAllProudctList(ProductDTO productDTO);


	long createProduct(List<MultipartFile> uploadImg, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception, IOException;

	public ProductDTO getProductDetail(long productId, boolean isCheckReadCnt);

	//public List<String> getImgUUID(long productImgId);
	List<String> getImgUUID(long productId);

	void updateProduct(ProductDTO productDTO);

	void deleteProduct(long productId);
}

