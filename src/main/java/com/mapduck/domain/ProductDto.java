package com.mapduck.domain;

import lombok.*;

/**
 * 작성자: 강동연
 * 작성일: 2021.10.31
 * 설명: ProductDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {


    private String title;
    private String description;
    private String imgPath;

}
