package com.mapduck.domain;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 작성자: 강동연
 * 작성일: 2021.11.3
 * 설명: Product Entity
 *
 * 수정자: 양태영
 * 수정일: 21.11.07
 * 수정 내용: 실제 테이블 반영 후 매핑.
 *
 */
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk를 db에서 알아서 해준다.
    @Column(name = "PR_ID")
    private Long prId;


    @Column(name = "PR_NAME")
    private String prName;

    @ManyToOne
    @JoinColumn(name = "PR_COMPANY")
    private Company prCompany;

    @Column(name = "MO_NAME")
    private String moName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IMG_PATH")
    private String imgPath;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="PR_ID")
    @ToString.Exclude
    List<Warranty> warranties;

}
