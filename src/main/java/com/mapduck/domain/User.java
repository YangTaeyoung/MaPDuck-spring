package com.mapduck.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 작성자: 강동연
 * 작성일: 2021.11.3
 * 설명: User Entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk를 db에서 알아서 해준다.
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;

    private String joinedAt; // date?
    private String updatedAt; // date?




}
