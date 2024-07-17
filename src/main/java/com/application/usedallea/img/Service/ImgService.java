package com.application.usedallea.img.Service;

import com.application.usedallea.img.domain.entity.Img;
import com.application.usedallea.img.dto.ImgRegisterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImgService {
    long saveImg(List<MultipartFile> uploadImg, ImgRegisterDto imgDto) throws IOException;
    Img findById(long imgId);
}
