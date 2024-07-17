package com.application.usedallea.user.domain.entity;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder(toBuilder = true)
public class User {
    private String userId;

    private String nickname;

    private String password;  // int 로 바꾸야함

    private String name;

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

    private String personalInfYn;
}
