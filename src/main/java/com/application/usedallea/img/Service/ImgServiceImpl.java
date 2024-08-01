package com.application.usedallea.img.Service;

import com.application.usedallea.img.domain.entity.Img;
import com.application.usedallea.img.domain.repository.ImgRepository;
import com.application.usedallea.img.dto.ImgRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImgServiceImpl implements ImgService{

    @Value("${file.repo.path}")
    private String imgRepositoryPath;

    private final ImgRepository imgRepository;
    @Override
    public long saveImg(List<MultipartFile> uploadImg, ImgRegisterDto imgDto) throws IOException {

        if(uploadImg.isEmpty()){
            throw new RuntimeException("file is empty");
        }

        Img img = imgRepository.findMaxImgId();
        long imgSeq = 1;
        for(MultipartFile imgList : uploadImg){
            String originalImgName = imgList.getOriginalFilename();
            imgDto.setOriginalName(originalImgName);

            UUID uuid = UUID.randomUUID();
            String extension = originalImgName.substring(originalImgName.lastIndexOf("."));
            String imgUUID = uuid + extension;

            Img productImg =Img.builder()
                    .imgId(img.getImgId())
                    .imgSeq(imgSeq)
                    .originalName(originalImgName)
                    .imgUUID(imgUUID)
                    .build();
            imgList.transferTo(new File(imgRepositoryPath + imgUUID)); //이미지 저장

            imgRepository.save(productImg);
            imgSeq++;
        }
        return img.getImgId();
    }

    @Override
    public Img findById(long imgId) {
        return imgRepository.findById(imgId);
    }

}
