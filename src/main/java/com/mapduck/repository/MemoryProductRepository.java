package com.mapduck.repository;


import com.mapduck.domain.Company;
import com.mapduck.domain.Product;
import com.mapduck.dto.CompanyDto;
import com.mapduck.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: 메모리 임시 저장소 구현체
 */

@Repository
@RequiredArgsConstructor
public class MemoryProductRepository implements ProductTestRepository {


    private List<Product> store = new ArrayList<>();

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    /**
     * 작성자: 강동연
     * 작성일: 2021.10.31
     * 설명: 제품 임시DB에 product 객체 저장
     * 수정자: 양태영
     * 수정일: 21.11.07
     * 수정내용: 모델 클래스 재정의로 인한 리펙토링
     *
     * @param productDto 수정일: 2021.11.03
     *                   설명: void로 변경, productDto -> product로 변경 후 저장
     */
    @Override
    public void save(ProductDto productDto) {
        Product product = new Product();
        product.setPrName(productDto.getPrName());
        product.setPrCompany(modelMapper.map(productDto.getPrCompany(), Company.class));
        product.setMoName(productDto.getMoName());
        product.setDescription(productDto.getDescription());
        product.setImgPath(productDto.getImgPath());

        store.add(product);
    }

    /**
     * 작성자 : 강동연
     * 작성일 : 2021.10.31
     * 설명: keyword로 임시 DB에 검색 후 반환, contains 함수로 검색
     *
     * @param keyword: 검색어
     * @return List<ProductDto>
     * <p>
     * 수정일: 2021.11.03
     * 수정사항: product를 productDto로 변환해서 List로 반환
     * <p>
     * 수정자: 양태영
     * 수정일: 21.11.07
     * 수정내용: 모델 재정의로 인한 리펙토링
     */
    @Override
    public List<ProductDto> findByKeyword(String keyword) {
        List<Product> products = store.stream()
                .filter(product -> product.getPrName().contains(keyword)) //
                .collect(Collectors.toList());

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setPrName(product.getPrName());
            productDto.setPrCompany(modelMapper.map(product.getPrCompany(), CompanyDto.class));
            productDto.setMoName(product.getMoName());
            productDto.setDescription(product.getDescription());
            productDto.setImgPath(product.getImgPath());
            productDtos.add(productDto);

        }
        return productDtos;

    }
}
