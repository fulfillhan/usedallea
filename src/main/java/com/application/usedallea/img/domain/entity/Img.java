package com.application.usedallea.img.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Img {
    long imgId;

    long imgSeq;

    String imgUUID;

    String originalName;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;


}
