package com.mapduck.serivce;

import com.mapduck.domain.Warranty;
import com.mapduck.dto.WarrantyDto;

import java.util.List;

public interface WarrantyService {
    WarrantyDto entityToDto (Warranty warranty);
    WarrantyDto getMaxWarrantyDto(List<Warranty> warranties);
    Warranty save(Warranty warranty);
}
