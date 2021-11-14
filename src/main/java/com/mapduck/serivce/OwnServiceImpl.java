package com.mapduck.serivce;

import com.mapduck.advice.RestException;
import com.mapduck.domain.Member;
import com.mapduck.domain.Own;
import com.mapduck.dto.*;
import com.mapduck.repository.OwnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnServiceImpl implements OwnService {
    private final MemberService memberService;
    private final WarrantyService warrantyService;
    private final ProductService productService;
    private final OwnRepository ownRepository;

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: 소유 엔티티를 Response DTO로 변환하는 함수
     * @param own: 변환할 엔티티
     * @return ownResDto: 변환후 ResDto
     */
    @Override
    public OwnResDto entityToResDto(Own own) {
        OwnResDto ownResDto = new OwnResDto();
        ownResDto.setOwnId(own.getId());
        ownResDto.setPurchaseAt(own.getPurchasedAt());
        ownResDto.setOwnProduct(productService.entityToResDto(own.getOwnProduct()));
        ownResDto.setCreatedAt(own.getCreatedAt());
        return ownResDto;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: Request DTO에서 엔티티 객체로 변환하는 매핑 함수
     * @param ownReqDto: 변환 전 Request DTO
     * @return own: 변환될 entity
     */
    @Transactional
    @Override
    public Own reqDtoToEntity(OwnReqDto ownReqDto){
        Own own = new Own();
        var product = productService.getById(ownReqDto.getPrId());
        var member = memberService.getMember(ownReqDto.getOwnerId());
        log.info("OwnReqDto:{}", ownReqDto);
        log.info("product: {}", product);
        log.info("member: {}", member);
        own.setOwnProduct(product);
        own.setPurchasedAt(ownReqDto.getPurchaseAt());
        own.setOwner(member);
        return own;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.13
     * 설명: 특정 멤버의 OwnList를 DTO로 반환하는 함수
     *
     * @param member: 소유 리스트를 가져올 멤버
     * @return ownResDtos: 소유 리스트 Response Dto List 객체
     */
    @Override
    public List<OwnResDto> getOwns(Member member) {
        List<Own> owns = member.getOwns();
        List<OwnResDto> ownResDtos = new ArrayList<>();
        owns.forEach(own -> ownResDtos.add(entityToResDto(own)));
        return ownResDtos;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: id를 기준으로 Response DTO를 가져오는 함수
     * @param id: 가져올 엔티티의 id
     * @return Response DTO
     */
    @Override
    public OwnResDto getResById(Long id) {
        Own own = ownRepository.findById(id).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "소유 정보를 찾을 수 없습니다."));
        OwnResDto ownResDto = entityToResDto(own);
        return ownResDto;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: id를 기준으로 소유 정보를 삭제하는 함수
     * @param id: 삭제할 own의 id
     */
    @Override
    public void deleteById(Long id){
        ownRepository.deleteById(id);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: 엔티티를 저장하고 저장한 엔티티를 반환하는 함수
     * @param own: 저장할 own 엔티티
     * @return 저장후 엔티티 반환
     */

    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: own객체를 own 테이블에 저장하는 함수
     * @param own: 저장할 own entity 객체
     * @return own: 저장 후 own entity 객체(id가 부여됨)
     */
    @Override
    public Own save(Own own) {
        return ownRepository.save(own);
    }
}
