package com.mapduck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mapduck.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/*
*  작성자: 양태영
*  작성일: 21.11.07
*  작성 내용: Django 서버에서 상품 정보를 받아오기 위해서 사용하는 Dto 클래스
* */

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) // camelCase to snake
public class DanawaProductDto {
    private int coId;
    private String prName;
    private String moName;
    private int warranty;
    private String imgPath;
    @JsonProperty("desc")
    private String description;
}
