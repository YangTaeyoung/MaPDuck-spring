package com.mapduck.web;


import com.mapduck.domain.ProductDto;
import com.mapduck.service.ProductService;

import com.mapduck.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: 정확한 uri 관계 설정 필요!
 */
@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ApiController {


    private final RestTemplateService templateService;
    private final ProductService productService;


    /**
     * 작성자: 강동연
     * 작성일 : 2021.10.31
     * 설명: 검색어를 받아서 DB에 자료가 있다면 DB 반환, 없다면 django 쪽에서 크롤링 진행 후 결과 반환
     * @param keyword : 검색어
     * @return List<ProductDto> : 리스트 형식의 제품</ProductDto>
     */
    @GetMapping("/danawa")
    public List<ProductDto> getKeyword(@RequestParam String keyword) {


        System.out.println("keyword:" + keyword);

        if (!productService.findByKeyword(keyword).isEmpty()) {
            return productService.findByKeyword(keyword);

        } else {
            return templateService.keyword(keyword);
        }

    }

}
