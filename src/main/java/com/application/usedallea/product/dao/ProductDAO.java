package com.application.usedallea.product.dao;

import com.application.usedallea.product.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDAO {
	
	public long createProduct(ProductDTO productDTO);

	public ProductDTO getProductDetail(long productId);
	
	public void updateReadCnt(long productId);

	public String getImgUUID(long productImgId);
}
