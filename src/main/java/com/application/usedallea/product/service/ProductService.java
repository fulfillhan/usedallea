package com.application.usedallea.product.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.application.usedallea.img.dto.ProductImgDTO;
import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.product.dto.ProductDTO;

public interface ProductService {
	//List<ProductDTO> getAllProudctList(ProductDTO productDTO);


	long createProduct(List<MultipartFile> uploadImg, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception, IOException;

	 ProductDTO getProductDetail(long productId, boolean isCheckReadCnt);

	List<String> getImgUUIDList(long productId);

	void updateProduct(ProductDTO productDTO);

	void deleteProduct(long productId);

	int getAllProductCnt(Map<String, String> searchCntMap);

	List<ProductDTO> getProductList(Map<String, Object> searchMap);

	int getAllProductCntBySeller(Map<String, String> searchCntMap);

	List<ProductDTO> getProductListBySeller(Map<String, Object> searchMap);

	ProductStatus updateProductStatus(long productId, ProductStatus status);
}

