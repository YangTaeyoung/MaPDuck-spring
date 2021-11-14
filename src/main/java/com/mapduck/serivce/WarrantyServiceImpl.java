package com.mapduck.serivce;

import com.mapduck.domain.Warranty;
import com.mapduck.dto.WarrantyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarrantyServiceImpl implements WarrantyService {

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
}
