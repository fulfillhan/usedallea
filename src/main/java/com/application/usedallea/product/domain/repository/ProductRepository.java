package com.application.usedallea.product.domain.repository;

import com.application.usedallea.product.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ProductRepository {

    long create(Product Product);

    void update(Product product);

    Product findByProductId(long productId);
}
