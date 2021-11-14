package com.mapduck.serivce;

import com.mapduck.domain.Member;
import com.mapduck.domain.Own;
import com.mapduck.dto.OwnReqDto;
import com.mapduck.dto.OwnResDto;

import java.util.List;

public interface OwnService {
    Own reqDtoToEntity(OwnReqDto ownReqDto);
    OwnResDto entityToResDto(Own own);
    List<OwnResDto> getOwns(Member member);
    OwnResDto getResById(Long id);
    void deleteById(Long id);
    Own save(Own own);
}
