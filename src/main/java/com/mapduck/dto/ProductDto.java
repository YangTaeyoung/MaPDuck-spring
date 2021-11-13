package com.mapduck.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: ProductDto
 *
 * 수정자: 양태영
 * 수정일: 21.11.07
 * 수정 내용: 기존 Product의 경우 DB에 집어 넣기 위해서는 따로 매핑을 해줘야 함.
 * ModelMapper을 사용하고, 사용자에게 출력할 때 좀 더 용이하기 위해 해당 클래스를
 * Product Entity 전체를 반영하도록 수정함
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 property는 대상에서 제외된다.
@JsonIgnoreProperties(ignoreUnknown = true) // Json 입력값에 대해 Mapping시 클래스에 선언되지 않는 property는 무시
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) // camelCase to snake
public class ProductDto {
    private Long prId;
    @NonNull
    private String prName;
    @NonNull
    private String moName;
    @NonNull
    private CompanyDto prCompany;
    private String description;
    private String imgPath;

}
