package com.application.usedallea.product.service;

import com.application.usedallea.img.dto.ImgRegisterDto;

import com.application.usedallea.product.dto.ProductModifyDto;
import com.application.usedallea.product.dto.ProductRegisterDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

   long saveProduct(List<MultipartFile> uploadImg, ProductRegisterDto productDto, ImgRegisterDto imgDto) throws Exception;

   void updateProduct(ProductModifyDto productDto);

   ProductStatus updateProductStatus(long productId, ProductStatus status);

   ProductRegisterDto findByProductId(long productId, boolean isCheckReadCnt);

    List<String> findImgUUIDs(long productId);
}
