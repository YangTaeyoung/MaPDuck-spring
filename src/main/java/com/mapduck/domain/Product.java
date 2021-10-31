package com.mapduck.domain;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    private String title;
    private String description;
    private String img_path;

}
