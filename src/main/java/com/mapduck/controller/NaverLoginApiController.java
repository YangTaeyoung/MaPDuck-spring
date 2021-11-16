package com.mapduck.controller;

import com.mapduck.dto.NaverIdPwDto;
import com.mapduck.dto.NaverProductDto;
import com.mapduck.dto.ProductDto;
import com.mapduck.serivce.NaverRestTemplateService;
import com.mapduck.serivce.ProductService;
import com.mapduck.serivce.RestTemplateService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
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
@Api(description = "네이버 쇼핑에서 구매한 상품 목록을 가져오기 위한 API")
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
    @Operation(
            summary = "네이버 쇼핑에서 자신이 산 제품정보를 가져오는 API",
            description = "자신이 산 제품의 모델명과 구매일자를 가져와서 JSON 형태로 반환함.<br/>단, 아이디와 패스워드는 Base64로 인코딩 되어 있어야 함."
    )
    @PostMapping("")
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

    @Operation(
            summary = "수집한 네이버 상품 정보(모델명, 구매일자)를 리스트 형태로 받아 DB에 검색하여 상품 정보를 가져오는 API"
    )
    @GetMapping("/myproducts")
    public List<ProductDto> searchProduct(@RequestBody NaverProductDto naverProductDto) {


        return productService.findByKeyword(naverProductDto.getMoName());

    }

    /**
     * 작성자: 강동연
     * 작성일: 2021.11.14
     * 설명: naver 검색 로직 분리
     * @param naverProductDto
     * @return
     *
     *                  *************** deprecated 결정 ****************
     * 결정자: 양태영
     * 결정 사유: 다나와 product의 /search와 동일한 역할을 수행.
     * front에서 리스트를 입력 받아 모델명만 파라미터로 요청을 보내면 될 것이라 판단.
     *
     */

//    @GetMapping("/myproducts/from-web")
//    public List<ProductDto> searchProductByDanawa(@RequestBody NaverProductDto naverProductDto) {
//        return restTemplateService.keyword(naverProductDto.getMoName());
//    }



}