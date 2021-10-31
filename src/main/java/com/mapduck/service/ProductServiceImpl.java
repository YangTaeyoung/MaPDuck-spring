package com.mapduck.service;


import com.mapduck.domain.ProductDto;

import com.mapduck.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


/**
 * 작성자 : 강동연
 * 작성일 : 2021.10.31
 * 설명 : 제품 서비스 구현체
 */
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    /**
     * 작성자 : 강동연
     * 작성일 : 2021.10.31
     * 설명: 제품dto 받아서 저장소에 저장
     * @param productDto
     */
    @Override
    public void save(ProductDto productDto) {
        productRepository.save(productDto);
    }

    /**
     * 작성자 : 강동연
     * 작성일 : 2021.10.31
     * 설명: keyword로 db에 관련 있는 제품 찾기
     * @param keyword: 검색어
     * @return List<ProductDto>
     */
    @Override
    public List<ProductDto> findByKeyword(String keyword) {

        return productRepository.findByKeyword(keyword);
    }
}
