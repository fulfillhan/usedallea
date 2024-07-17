package com.application.usedallea.img.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ImgRegisterDto {

    long imgId;

    long imgSeq;

    String imgUUID;

    String originalName;

    LocalDateTime createdAt;
}
