package com.mapduck.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: ProductDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 property는 대상에서 제외된다.
@JsonIgnoreProperties(ignoreUnknown = true) // Json 입력값에 대해 Mapping시 클래스에 선언되지 않는 property는 무시
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) // camelCase to snake
public class ProductDto {

    // keyword 기준이 필요.. 값을 뭘로? name?company?modelName?
    @NonNull
    private String name;
    @NonNull
    private Long companyId;
    @NonNull
    private String modelName;
    private String description;
    private String imgPath;

}
