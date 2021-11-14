package com.mapduck.controller;

import com.mapduck.dto.NaverIdPwDto;
import com.mapduck.dto.NaverProductDto;
import com.mapduck.dto.ProductDto;
import com.mapduck.serivce.NaverRestTemplateService;
import com.mapduck.serivce.ProductService;
import com.mapduck.serivce.RestTemplateService;
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

    private final NaverRestTemplateService naverRestTemplateService;
    private final RestTemplateService restTemplateService;
    private final ProductService productService;

    /**
     * 작성자: 강동연
     * 작성일: 2021.11.08
     * 설명: navereIdPw로 검색 후 List형식의 ProductDto 반환
     * @param naverIdPwDto
     * @return List<NaverProductDto>
     *
     * 수정자: 강동연
     * 수정일: 2021.11.08
     * 수정사항: uri 변경 /api/naver/myproducts -> /api/naver
     *
     */

    @GetMapping()
    public List<NaverProductDto> getNaverProducts(@RequestBody NaverIdPwDto naverIdPwDto) {
        return  naverRestTemplateService.getNaverMyproducts(naverIdPwDto);
    }

    /**
     * 작성자: 강동연
     * 작성일: 2021.11.08
     * 설명: NaverProduct DB에 등록
     * @param naverProductDto
     * @return List<ProductDto>
     *
     * 수정일: 2021.11.08
     * 수정사항: NaverProductDto -> productDto 변환 후 다나와 or db 검색 후 반환
     *
     *
     */

    @GetMapping("/myproducts")
    public List<ProductDto> addNaverProduct(@RequestBody NaverProductDto naverProductDto) {

        if (!productService.findByKeyword(naverProductDto.getTitle()).isEmpty()) {
            return productService.findByKeyword(naverProductDto.getTitle());
        } else {
            return restTemplateService.keyword(naverProductDto.getTitle());
        }

    }



}