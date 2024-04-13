package com.application.usedallea.img.dao;

import com.application.usedallea.img.dto.ProductImgDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductImgDAO {

	public long getMaxImgId();

	 public void saveImg(ProductImgDTO productImgDTO);

}
