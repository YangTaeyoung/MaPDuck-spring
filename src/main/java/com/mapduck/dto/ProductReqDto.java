package com.mapduck.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductReqDto {
    private String prName;
    private String moName;
    private String description;
    private String imgPath;
    private CompanyDto companyDto;
    private int wrMonth;
}
