package com.mapduck.repository;

import com.mapduck.domain.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: 메모리 임시 저장소 구현체
 */
public class MemoryProductRepository implements ProductRepository{


    private List<ProductDto> store = new ArrayList<>();

    /**
     * 작성자: 강동연
     * 작성일: 2021.10.31
     * 설명: 제품 임시DB에 product 객체 저장
     * @param productDto
     * @return productDto
     */
    @Override
    public ProductDto save(ProductDto productDto) {
        store.add(productDto);
        return productDto;
    }

    /**
     * 작성자 : 강동연
     * 작성일 : 2021.10.31
     * 설명: keyword로 임시 DB에 검색 후 반환, contains 함수로 검색
     * @param keyword: 검색어
     * @return List<ProductDto>
     */
    @Override
    public List<ProductDto> findByKeyword(String keyword) {
         List<ProductDto> productDtos = store.stream().filter(product -> product.getTitle().contains(keyword)).collect(Collectors.toList());
         return productDtos;

    }
}
