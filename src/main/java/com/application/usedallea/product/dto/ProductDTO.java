package com.application.usedallea.product.dto;

import java.time.LocalDateTime;
import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductDTO {
	
	private long productId;
	private String sellerId;
	private long imgId;
	private String title;
	private int price;
	private String description;
	private String qualityCondition;
	private String category;
	private String status;
	private int readCount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private long daysAgo;
	private long hoursAgo;
	private String firstImgUUID;
	private int zzimCount;

	
	
	
	
}
