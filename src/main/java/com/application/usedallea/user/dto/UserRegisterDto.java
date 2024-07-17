package com.application.usedallea.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class UserRegisterDto {

    private String userId;

    private String nickname;

    private String password;  // int 로 바꾸야함

    private String name;

    private String activeYn;

    private String email;

    private String emailStsYn;

    private String phoneNumber;

    private String smsStsYn;

    private String roadAddress;

    private String jibunAddress;

    private String namujiAddress;

    private String zipCode;

    private String personalInfYn;



}
