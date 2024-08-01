package com.application.usedallea.product.service;

import com.application.usedallea.img.Service.ImgService;
import com.application.usedallea.img.domain.entity.Img;
import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.product.domain.entity.Product;
import com.application.usedallea.product.domain.repository.ProductRepository;
import com.application.usedallea.product.dto.ProductModifyDto;
import com.application.usedallea.product.dto.ProductRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService {

    private final ProductRepository productRepository;
    private final ImgService imgService;

    @Override
    public long saveProduct(List<MultipartFile> uploadImg, ProductRegisterDto productDto, ImgRegisterDto imgDto) throws IOException {
        long imgId = imgService.saveImg(uploadImg, imgDto);
        Product newProduct = Product.builder()
                .imgId(imgId)
                .sellerId(productDto.getSellerId())
                .title(productDto.getTitle())
                .status(ProductStatus.판매중.name())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .qualityCondition(productDto.getQualityCondition())
                .category(productDto.getCategory())
                .build();
        return productRepository.create(newProduct);
    }

    @Override
    public ProductRegisterDto findByProductId(long productId, boolean isCheckReadCnt) {
        Product product = productRepository.findByProductId(productId);

        if (isCheckReadCnt) {
            product.increaseReadCount();
        }
        // createdAt 기준으로 날짜 계산
        LocalDateTime createdAt = product.getCreatedAt();
        LocalDateTime now = LocalDateTime.now();

        long daysAgo = Duration.between(createdAt, now).toDays();
        long hoursAgo = Duration.between(createdAt, now).toHours();

        return ProductRegisterDto.toDto(product, daysAgo, hoursAgo);
    }

    @Override
    public List<String> findImgUUIDs(long productId) {
        Product product = productRepository.findByProductId(productId);
        long imgId= product.getImgId();
        Img img = imgService.findById(imgId);
        return null;
    }

    @Override
    public void updateProduct(ProductModifyDto productDto) {

            Product product = productRepository.findByProductId(productDto.getProductId());

            Product newProduct = product.toBuilder()
                    .productId(productDto.getProductId())
                    .title(productDto.getTitle())
                    .category(productDto.getCategory())
                    .qualityCondition(productDto.getQualityCondition())
                    .price(productDto.getPrice())
                    .description(productDto.getDescription())
                    .updatedAt(LocalDateTime.now())
                    .build();
            productRepository.update(newProduct);
    }

    @Override
    public ProductStatus updateProductStatus(long productId, ProductStatus status) {
        switch (status){
            case 판매중 -> {
                // 판매중으로 변경
            }
            case 판매완료 -> {
                // 판매 완료로 변경
            }
            case 삭제 -> {
                // 삭제 처리
            }
        }
        return ProductStatus.삭제;
    }

}

