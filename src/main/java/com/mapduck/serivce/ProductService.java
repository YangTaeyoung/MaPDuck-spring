package com.mapduck.serivce;

import com.mapduck.domain.Product;
import com.mapduck.dto.ProductDto;
import com.mapduck.dto.ProductReqDto;

import java.util.List;

/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: 제품서비스 인터페이스
 */
public interface ProductService {

    Product getById(Long id);
    ProductDto entityToResDto(Product product);
    Product save(ProductReqDto productReqDto);
    List<ProductDto> findByKeyword(String keyword);
}
