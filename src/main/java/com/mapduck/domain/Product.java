package com.mapduck.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 작성자: 강동연
 * 작성일: 2021.11.3
 * 설명: Product Entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk를 db에서 알아서 해준다.
    private Long id;


    private String name;
    private Long company;
    private String modelName;
    private String description;



}
