package com.application.usedallea.img.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ImgDTO {
	
	private long imgId;
	private long productId;
	private String imgUUID;
	private String originalName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	private Date updatedAt;
	
	

}
