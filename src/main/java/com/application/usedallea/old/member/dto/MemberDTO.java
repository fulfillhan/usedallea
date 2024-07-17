package com.application.usedallea.old.member.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String userId;
	private String nickname;
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
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int productCnt;

	public boolean unknownAgreeToReceiveSMS() {
		return this.smsstsYn == null;
	}

	public void dontReceiveSMS() {
		this.smsstsYn = "U001";
	}
}
