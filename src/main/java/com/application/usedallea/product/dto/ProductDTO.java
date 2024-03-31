package com.application.usedallea.product.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.application.usedallea.img.dto.ImgDTO;

import lombok.Data;

@Data
public class ProductDTO {
	
	private long productId;
	private String sellerId;
	private String title;
	private int price;
	private String description;
	private String qualityCondition;
	private String category;
	private String status;
	private int readCount;	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	private Date updatedAt;


}
