package com.application.usedallea.product.dao;

import com.application.usedallea.product.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDAO {

	void createProduct(ProductDTO productDTO);

	long getProductId(long imgId);

	ProductDTO getProductDetail(long productId);

	void updateReadCnt(long productId);

	List<String> getImgUUID(long productId);
}
