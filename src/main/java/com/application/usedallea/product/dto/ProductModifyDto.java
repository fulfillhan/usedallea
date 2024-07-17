package com.application.usedallea.product.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class ProductModifyDto {

    private long productId;
    private String title;
    private String category;
    private String qualityCondition;
    private String price;
    private String description;

}


