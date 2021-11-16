package com.mapduck.controller;

import com.mapduck.domain.Member;

import com.mapduck.dto.*;
import com.mapduck.serivce.*;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@Api(description = "관리 상품과 관련된 API")
@RestController
@Slf4j
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApiController {

    private final WarrantyService warrantyService;
    private final RestTemplateService templateService;
    private final ProductService productService;
    private final OwnService ownService;
    private final MemberService memberService;

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

    @Operation(
            summary = "다나와 크롤링을 통해 상품을 검색하는 API",
            description = "기존 DB에 사용자가 검색한 상품이 없을 때 호출하길 바람"
    )
    @GetMapping("/search")
    public List<ProductDto> findProducts(
            @Parameter(description = "검색할 키워드: 사용자가 입력한 검색어")
            @RequestParam String keyword
    ) {
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
     * @param productReqDto: 새로 추가할 제품 RequestDto
     * @return 생성되었다는 상태만 반환
     *
     * 수정일: 21.11.15
     * 수정내용: URI /search로 변경경     */
    @Operation(
            summary = "사용자가 상품리스트에서 선택한 상품을 DB에 추가하는 API",
            description = "사용자가 선택한 상품의 정보를 JSON형태로 받아 Product DB에 저장함"
    )
    @PostMapping("/search")
    public ResponseEntity addProduct(@RequestBody ProductReqDto productReqDto) {
        productService.save(productReqDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: 로그인 정보를 토대로 테이블을 조회하며, 해당 조회한 사용자와 일치한 소유정보를 모두 가져오는 함수.
     *
     * @param metaUser: 로그인 한 사용자 메타 정보
     * @return 조회 성공 시 OK요청과 함께 소유 정보를 보냄.
     */
    @Operation(
            summary = "로그인한 사용자의 소유 제품 정보를 가져오는 API",
            description = "사용자가 등록한 상품의 정보를 받아 해당 데이터를 JSON형태로 반환.<br/> 반환 JSON은 Example을 참고"
    )
    @GetMapping("/owns")
    public ResponseEntity<List<OwnResDto>> getMyOwns(@AuthenticationPrincipal User metaUser) {
        Member loginedMember = memberService.metaUserToMember(metaUser);
        List<OwnResDto> ownResDtos = ownService.getOwns(loginedMember);
        return new ResponseEntity<>(ownResDtos, HttpStatus.OK);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: request json을 받아 사용자의 소유 정보를 저장하는 컨트롤러 함수
     *
     * @param ownReqDto: 소유 정보 req dto
     * @return 생성되었다는 상태만 반환
     */
    @Operation(
            summary = "상품 정보를 User DB에 저장하는 API",
            description = "상품 정보와 사용자 ID를 JSON 형태로 입력 받고 DB에 등록함."
    )
    @Transactional
    @PostMapping("/own/")
    public ResponseEntity addOwn(@RequestBody OwnReqDto ownReqDto) {
        var own = ownService.reqDtoToEntity(ownReqDto);
        ownService.save(own);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * 작성자: 양태영
     * 작성일: 21.11.13
     * 설명: id를 통해 사용자의 상품 소유 정보를 불러옴
     * @param id: 조회할 own Id
     * @return OwnResDto: 사용자의 소유 정보
     */
    @Operation(
            summary = "특정 소유 정보를 가져올 때 사용하는 API",
            description = "소유 ID를 받아 소유한 상품 정보를 JSON 형태로 반환함"
    )
    @GetMapping("/own/{id}")
    public ResponseEntity<OwnResDto> getOwn(
            @Parameter(description = "가져올 상품의 소유 ID")
            @PathVariable("id") int id
    ) {
        return new ResponseEntity<>(ownService.getResById((long) id), HttpStatus.OK);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: own_id를 기준으로 테이블을 검색하고 삭제하는 함수
     *
     * @param id: 삭제할 소유 id
     * @return 다른 응답없이 Http status만 전송
     */
    @Operation(
            summary = "소유한 상품 정보를 삭제하는 API",
            description = "소유한 상품 정보를 ID값을 기준으로 삭제함."
    )
    @DeleteMapping("/own/{id}")
    public ResponseEntity deleteOwn(@PathVariable("id") int id) {
        ownService.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: Request DTO를 입력받아 해당 상품 정보와 개월이 존재하면 Count+1을 하고 없으면 새로 보증기간을 저장
     * @param warrantyReqDto: 삽입할 RequestDto 객체
     * @return 생성되었다는 상태만 반환
     */
    @Operation(
            summary = "보증기간 정보를 추가하는 API",
            description = "이미 제품에 같은 보증기간이 등록 되어 있는 경우 보증기간 count를 올리고, 같은 보증기간이 아닌 경우 새로운 보증기간을 등록함"
    )
    @PostMapping("/warranty")
    public ResponseEntity addWarranty(@RequestBody WarrantyReqDto warrantyReqDto){
        warrantyService.saveOrUpdate(warrantyReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
