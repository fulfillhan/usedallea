package com.application.usedallea.old.img.dao;

import com.application.usedallea.old.img.dto.ProductImgDTO;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface ProductImgDAO {

	long getMaxImgId();

	void saveImg(ProductImgDTO productImgDTO);

   // void deleteImg(long productId);
}
