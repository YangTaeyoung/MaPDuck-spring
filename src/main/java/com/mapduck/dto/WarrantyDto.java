package com.mapduck.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


/*
* 작성자: 양태영
* 작성일: 21.11.07
* 작성 내용: 보증기간 정보를 담는 Dto 클래스
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WarrantyDto {
    private Long wrId;
    private Long prId;
    private int wrMonth;
    private int count;

}
