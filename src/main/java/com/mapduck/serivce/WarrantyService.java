package com.mapduck.serivce;

import com.mapduck.domain.Warranty;
import com.mapduck.dto.WarrantyDto;
import com.mapduck.dto.WarrantyReqDto;

import java.util.List;

public interface WarrantyService {
    WarrantyDto entityToDto (Warranty warranty);
    Warranty dtoToEntity (WarrantyDto warrantyDto);
    Warranty reqDtoToEntity(WarrantyReqDto warrantyReqDto);
    WarrantyDto getMaxWarrantyDto(List<Warranty> warranties);
    Warranty save(Warranty warranty);
    Warranty saveOrUpdate(WarrantyReqDto warrantyReqDto);
}
