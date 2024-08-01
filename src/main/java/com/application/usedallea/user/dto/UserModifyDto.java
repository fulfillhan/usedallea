package com.application.usedallea.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserModifyDto {

    private String userId;

    private String nickname;

    private String activeYn;

    private String email;

    private String emailStsYn;

    private String phoneNumber;

    private String smsStsYn;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    private String roadAddress;

    private String jibunAddress;

    private String namujiAddress;

    private String zipCode;
}
