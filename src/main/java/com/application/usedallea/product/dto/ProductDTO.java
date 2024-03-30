package com.application.usedallea.product.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductDTO {
	
	private long productId;
	private String sellerId;
	private String name;
	private int price;
	private String description;
	private String qualityCondition;
	private String category;
	private int readCount;
	private String status;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	private Date updated_at;

}
