package com.mapduck.domain;

import lombok.*;

import javax.persistence.*;

/**
 * 작성자: 강동연
 * 작성일: 2021.11.3
 * 설명: Company Entity
 *
 * 수정자: 양태영
 * 수정일: 21.11.07
 * 수정 내용: 실제 테이블 반영 후 매핑.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_ID")
    private Long coId;
    @Column(name = "CO_NAME")
    private String coName;

}
