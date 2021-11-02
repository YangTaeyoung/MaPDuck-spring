package com.mapduck.repository;

import com.mapduck.dto.ProductDto;

import java.util.List;

/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: 제품 저장소 인터페이스
 */
public interface ProductRepository {

    ProductDto save(ProductDto productDto);
    List<ProductDto> findByKeyword(String keyword);

}
