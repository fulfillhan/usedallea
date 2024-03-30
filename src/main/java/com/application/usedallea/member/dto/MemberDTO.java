package com.application.usedallea.member.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String userId;
	private String password;
	private String name;
	private String activeYn;
	private String email;
	private String emailstsYn;
	private String phoneNumber;
	private String smsstsYn;
	private String roadAddress;
	private String jibunAddress;
	private String namujiAddress;
	private String zipCode;
	private String personalInfoYn;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	private Date createDate;

}
