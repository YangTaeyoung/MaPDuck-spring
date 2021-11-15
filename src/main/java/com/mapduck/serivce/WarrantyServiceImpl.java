package com.mapduck.serivce;

import com.mapduck.domain.Warranty;
import com.mapduck.dto.WarrantyDto;
import com.mapduck.dto.WarrantyReqDto;
import com.mapduck.repository.WarrantyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarrantyServiceImpl implements WarrantyService {
    private final WarrantyRepository warrantyRepository;
    private final ProductService productService;
    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: 보증기간 entity객체에서 warrantyDto 객체로 변환하는 함수
     * @param warranty: 변환할 warranty entity 객체
     * @return warrantyDto: 변환 후의 Dto 객체
     */
    @Override
    public WarrantyDto entityToDto(Warranty warranty) {
        WarrantyDto warrantyDto = new WarrantyDto();
        warrantyDto.setWrId(warranty.getId());
        warrantyDto.setCount(warranty.getCount());
        warrantyDto.setWrMonth(warranty.getMonth());
        return warrantyDto;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: dto에서 Entity 객체로 변환하는 함수
     * @param warrantyDto: 변환할 DTO객체
     * @return 변환된 Warranty객체
     */
    @Override
    public Warranty dtoToEntity(WarrantyDto warrantyDto) {
        var warranty = warrantyRepository.findFirstByPrId_PrIdAndMonth(warrantyDto.getPrId(), warrantyDto.getWrMonth());
        return warranty;
    }

    @Override
    public Warranty reqDtoToEntity(WarrantyReqDto warrantyReqDto) {
        Warranty warranty = warrantyRepository.findFirstByPrId_PrIdAndMonth(warrantyReqDto.getPrId(), warrantyReqDto.getWrMonth());
        if(warranty == null)
        {
            warranty = new Warranty();
            warranty.setMonth(warrantyReqDto.getWrMonth());
            warranty.setPrId(productService.getById(warrantyReqDto.getPrId()));
        }
        return warranty;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.13
     * 설명: 사람들이 가장 많이 등록한 횟수의 워런티를 Dto로 변경하여 반환
     * @param warranties: 카운트가 맥스인 것을 고를 타겟 워런티 리스트
     * @return warranty or null
     */
    @Override
    public WarrantyDto getMaxWarrantyDto(List<Warranty> warranties) {
        if (!warranties.isEmpty()) {
            int maxIdx = 0;
            int maxCount = warranties.get(0).getCount();
            for (int i = 0; i < warranties.size(); i++) {
                if(warranties.get(i).getCount() > maxCount){
                    maxIdx = i;
                    maxCount = warranties.get(i).getCount();
                }
            }
            Warranty maxWarranty = warranties.get(maxIdx);
            WarrantyDto warrantyDto = new WarrantyDto();
            warrantyDto.setWrId(maxWarranty.getId());
            warrantyDto.setWrMonth(maxWarranty.getMonth());
            warrantyDto.setCount(maxWarranty.getCount());
            return warrantyDto;
        }
        return null;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: warranty entity객체를 받아 테이블에 저장하는 함수
     * @param warranty: 저장할 Warranty entity 객체
     * @return warranty: 저장 후 warranty entity 객체(id가 부여됨)
     */
    public Warranty save(Warranty warranty){
        return warrantyRepository.save(warranty);
    }

    public Warranty saveOrUpdate(WarrantyReqDto warrantyReqDto){
        Warranty warranty = warrantyRepository.findFirstByPrId_PrIdAndMonth(warrantyReqDto.getPrId(), warrantyReqDto.getWrMonth());
        if(warranty == null){
            warranty = new Warranty();
            warranty.setPrId(productService.getById(warrantyReqDto.getPrId()));
            warranty.setMonth(warrantyReqDto.getWrMonth());
            warranty.setCount(1);
        }
        else{
            warranty.setCount(warranty.getCount()+1);
        }
        return warrantyRepository.save(warranty);
    }
}
