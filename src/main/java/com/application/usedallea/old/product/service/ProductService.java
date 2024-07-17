package com.application.usedallea.old.product.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.application.usedallea.old.img.dto.ProductImgDTO;
import com.application.usedallea.old.product.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
	//List<ProductDTO> getAllProudctList(ProductDTO productDTO);


	long createProduct(List<MultipartFile> uploadImg, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception, IOException;

	 ProductDTO getProductDetail(long productId, boolean isCheckReadCnt);

	List<String> getImgUUIDList(long productId);

	void updateProduct(ProductDTO productDTO);

	void updateValidateProduct(long productId);

	int getAllProductCnt(Map<String, String> searchCntMap);

	List<ProductDTO> getProductList(Map<String, Object> searchMap);

	int getAllProductCntBySeller(Map<String, String> searchCntMap);

	List<ProductDTO> getProductListBySeller(Map<String, Object> searchMap);

	ProductStatus updateProductStatus(long productId, ProductStatus status);

//	List<Integer> getProducCntByUser(String sellerId);

	int getProducCntByUser(String sellerId);

	int getAllProductCntByAdmin(Map<String, String> searchCntMap);

	List<ProductDTO> getProductListByAdmin(Map<String, Object> searchMap);

    List<ProductDTO> getProductIdBySeller(String sellerId);


    //void removeProduct(long productId);
}

