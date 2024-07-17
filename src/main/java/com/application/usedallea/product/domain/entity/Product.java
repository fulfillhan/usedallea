package com.application.usedallea.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
public class Product {

    private long productId;
    private String sellerId;
    private long imgId;
    private String title;
    private String price;
    private String description;
    private String qualityCondition;
    private String category;
    private String status;
    private String validatedYn;
    private int readCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void increaseReadCount() {
        readCount++;
    }
}
