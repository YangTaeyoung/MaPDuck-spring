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

    @PostMapping()
    public List<NaverProductDto> getNaverProducts(@RequestBody NaverIdPwDto naverIdPwDto) {
        log.info("naverIdPwDto {}", naverIdPwDto);
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
     * 수정일: 2021.11.14
     * 수정사항: naver 검색 로직 분리, addproduct -> searchProduct 저장이 아닌 검색임으로 의미가 맞지 않아 변경
     */

    @GetMapping("/myproducts")
    public List<ProductDto> searchProduct(@RequestBody NaverProductDto naverProductDto) {


        return productService.findByKeyword(naverProductDto.getTitle());

    }

    /**
     * 작성자: 강동연
     * 작성일: 2021.11.14
     * 설명: naver 검색 로직 분리
     * @param naverProductDto
     * @return
     */

    @GetMapping("/myproducts/danawa")
    public List<ProductDto> searchProductByDanawa(@RequestBody NaverProductDto naverProductDto) {
        return restTemplateService.keyword(naverProductDto.getTitle());
    }

    /**
     * 작성자: 강동연
     * 작성일: 2021.11.08
     * 설명: 제품 DB 등록
     * @param productDto
     * @return productDto
     */

    @PostMapping("/myproducts")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        productService.save(productDto);

        return productDto;
    }



}