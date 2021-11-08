package com.mapduck.controller;

import com.mapduck.dto.NaverIdPwDto;
import com.mapduck.dto.ProductDto;
import com.mapduck.serivce.NaverRestTemplateService;
import com.mapduck.serivce.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 작성자: 강동연
 * 작성일: 2021.11.08
 * 설명: Naver 거래내역 조회 후 반환
 * 고려해야할 상황: 로그인 아이디 비번이 틀리면 ?? ...
 */
@Slf4j
@RestController
@RequestMapping("/api/naver")
@RequiredArgsConstructor
public class NaverLoginApiController {

    private NaverRestTemplateService naverRestTemplateService;
    private final ProductService productService;

    /**
     * 작성자: 강동연
     * 작성일: 2021.11.08
     * 설명: navereIdPw로 검색 후 List형식의 ProductDto 반환
     * @param naverIdPwDto
     * @return
     */

    @GetMapping("/myproducts")
    public List<ProductDto> getNaverProducts(@RequestBody NaverIdPwDto naverIdPwDto) {
        return  naverRestTemplateService.getNaverMyproducts(naverIdPwDto);

    }


    /**
     * 작성자: 강동연
     * 작성일: 2021.11.08
     * 설명: NaverProduct DB에 등록
     * @param productDto
     * @return productDto
     */

    @PostMapping("/myproducts")
    public ProductDto addNaverProduct(@RequestBody ProductDto productDto) {

        productService.save(productDto);

        return productDto;
    }


}