package com.application.usedallea.product.dto;

import com.application.usedallea.product.domain.entity.Product;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Builder
public class ProductRegisterDto {

    private String sellerId;
    private long productId;
    private long imgId;
    private String title;
    private String price;
    private String description;
    private String qualityCondition;
    private String category;
    private String status;
    private int readCount;
    private String validatedYn;
    private LocalDateTime createdAt;
    private long daysAgo;
    private long hoursAgo;


    public static ProductRegisterDto toDto(Product product, long daysAgo, long hoursAgo){
      return  ProductRegisterDto.builder().productId(product.getProductId())
                .sellerId(product.getSellerId())
                .imgId(product.getImgId())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .qualityCondition(product.getQualityCondition())
                .category(product.getCategory())
                .status(product.getStatus())
                .readCount(product.getReadCount())
              .daysAgo(daysAgo)
              .hoursAgo(hoursAgo)
                .build();
    }

}
