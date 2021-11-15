package com.mapduck.controller;

import com.mapduck.domain.Member;
import com.mapduck.dto.OwnReqDto;
import com.mapduck.dto.OwnResDto;
import com.mapduck.dto.ProductDto;
import com.mapduck.serivce.MemberService;
import com.mapduck.serivce.OwnService;
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
    private final OwnService ownService;
    private final MemberService memberService;


    /**
     * 작성자: 강동연
     * 작성일: 2021-11-14
     * 설명: keyword를 통한 검색 시, db검색과 크롤링 분리
     * @param keyword
     * @return List<ProductDto> : 리스트 형식의 제품
     *
     */


    @GetMapping
    public List<ProductDto> findDbProduct(@RequestParam String keyword) {

        return productService.findByKeyword(keyword);
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
     *
     *
     * 수정일: 2021-11-14
     * 설명: keyword를 통한 검색 시, db검색과 크롤링 분리
     *
     * 수정일: 2021-11-15
     * 설명: danawa -> search(URI)
     *
     */

    @GetMapping("/search")
    public List<ProductDto> findProducts(@RequestParam String keyword) {

        return templateService.keyword(keyword);
    }

    /**
     * 작성자:강동연
     * 작성일:2021.11.03
     * 설명:제품 등록
     * 출력: productDto.toString()
     *
     * 수정자: 양태영
     * 수정일: 21.11.11
     * 수정 내용: danawa는 Danawa라는 DB가 존재하지 않기 때문에 Product를 저장하는 것에 대해 의미가 맞지 않을 수 있음
     * 따라서 /product에서 끝나게 함으로써 의미를 맞춤.
     *
     * @param productDto
     * @return productDto
     *
     * 수정일: 21.11.15
     * 수정내용: URI /search로 변경경     */
    @PostMapping("/search")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {

        log.info("productDto: {}", productDto.toString());
        productService.save(productDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: 로그인 정보를 토대로 테이블을 조회하며, 해당 조회한 사용자와 일치한 소유정보를 모두 가져오는 함수.
     * @param metaUser
     * @return
     */
    @GetMapping("/owns")
    public ResponseEntity<List<OwnResDto>> getMyOwns(@AuthenticationPrincipal User metaUser){
        Member loginedMember = memberService.metaUserToMember(metaUser);
        List<OwnResDto> ownResDtos = ownService.getOwns(loginedMember);
        return new ResponseEntity<>(ownResDtos, HttpStatus.OK);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: request json을 받아 사용자의 소유 정보를 저장하는 컨트롤러 함수
     * @param ownReqDto
     * @return
     *
     */
    @PostMapping("/own/")
    public ResponseEntity addOwn(@RequestBody OwnReqDto ownReqDto){
        var own = ownService.reqDtoToEntity(ownReqDto);
        ownService.save(own);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * 작성자: 양태영
     * 작성일: 21.11.13
     * 설명: id를 통해 사용자의 상품 소유 정보를 불러옴
     * @param id
     * @return OwnResDto: 사용자의 소유 정보
     */
    @GetMapping("/own/{id}")
    public ResponseEntity<OwnResDto> getOwn(@PathVariable("id") int id){
        return new ResponseEntity<>(ownService.getResById((long) id), HttpStatus.OK);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: own_id를 기준으로 테이블을 검색하고 삭제하는 함수
     * @param id
     * @return 다른 응답없이 Http status만 전송
     */
    @DeleteMapping("/own/{id}")
    public ResponseEntity deleteOwn(@PathVariable("id") int id){
        ownService.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
