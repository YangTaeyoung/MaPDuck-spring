package com.mapduck.controller;

import com.mapduck.dto.ProductDto;
import com.mapduck.serivce.ProductService;
import com.mapduck.serivce.RestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: ProductApi 컨트롤러
 * 
 * 수정일: 2021.11.02
 * 설명: ProductApiController로 변경
 */
@RestController
@Slf4j
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ProductApiController {


    private final RestTemplateService templateService;
    private final ProductService productService;

    /**
     * 작성자: 강동연
     * 작성일 : 2021.10.31
     * 설명: 검색어를 받아서 DB에 자료가 있다면 DB 반환, 없다면 django 쪽에서 크롤링 진행 후 결과 반환
     * 출력: keyword 값
     * @param keyword : 검색어
     * @return List<ProductDto> : 리스트 형식의 제품</ProductDto>
     */
    @GetMapping("/danawa")
    public List<ProductDto> getKeyword(@RequestParam String keyword) {

        log.info("keyword: {}", keyword);

        if (!productService.findByKeyword(keyword).isEmpty()) {
            return productService.findByKeyword(keyword);
        } else {
            return templateService.keyword(keyword);
        }
    }



}
