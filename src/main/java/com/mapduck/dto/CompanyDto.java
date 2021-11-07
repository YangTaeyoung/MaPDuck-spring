package com.mapduck.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


/*
*  작성자: 양태영
*  작성일: 21.11.07
*  작성 내용: 회사 관련 Dto 작성, 실제로 쓰이기 보단 Product에서 Import용으로 사용
* */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CompanyDto {
    private Long coId;
    private String coName;
}
