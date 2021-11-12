package com.mapduck.controller;

import com.mapduck.domain.Member;
import com.mapduck.domain.Own;
import com.mapduck.dto.ProductDto;
import com.mapduck.repository.OwnRepository;
import com.mapduck.serivce.MemberService;
import com.mapduck.serivce.ProductService;
import com.mapduck.serivce.RestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApiController {


    private final RestTemplateService templateService;
    private final ProductService productService;
    private final OwnRepository ownRepository;
    private final MemberService memberService;

    /**
     * 작성자: 양태영
     * 작성일: 21.11.12
     * 설명: 내 정보를 기반으로 내 정보와 내가 등록한 제품을 함께 가져오는 컨트롤러 함수
     *
     * @param meta
     * @return ResponseEntity<Member>
     */
    @GetMapping("/myproducts")
    @Transactional
    public ResponseEntity<Member> myProducts(@AuthenticationPrincipal User meta) {
        Member member = memberService.metaUserToMember(meta);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * 작성자: 강동연
     * 작성일 : 2021.10.31
     * 설명: 검색어를 받아서 DB에 자료가 있다면 DB 반환, 없다면 django 쪽에서 크롤링 진행 후 결과 반환
     * 출력: keyword 값
     * @param keyword : 검색어
     * @return List<ProductDto> : 리스트 형식의 제품</ProductDto>
     *
     * 수정일:2021-11-03
     * 설명: 객체명 수정 "findProducts"
     */
    @GetMapping("/danawa")
    public List<ProductDto> findProducts(@RequestParam String keyword) {

        log.info("keyword: {}", keyword);

        if (!productService.findByKeyword(keyword).isEmpty()) {
            return productService.findByKeyword(keyword);
        } else {
            return templateService.keyword(keyword);
        }
    }

    /**
     * 작성자:강동연
     * 작성일:2021.11.03
     * 설명:제품 등록
     * 출력: productDto.toString()
     *
     * 수정일: 21.11.11
     * 수정 내용: danawa는 Danawa라는 DB가 존재하지 않기 때문에 Product를 저장하는 것에 대해 의미가 맞지 않을 수 있음
     * 따라서 /product에서 끝나게 함으로써 의미를 맞춤.
     *
     * @param productDto
     * @return productDto
     */
    @PostMapping("/")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {

        log.info("productDto: {}", productDto.toString());
        productService.save(productDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }







}
