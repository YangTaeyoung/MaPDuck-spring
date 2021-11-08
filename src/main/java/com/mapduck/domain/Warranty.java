package com.mapduck.domain;

import lombok.*;

import javax.persistence.*;


/**
 * 작성자: 강동연
 * 작성일: 2021.11.3
 * 설명: Warranty Entity
 *
 * 수정자: 양태영
 * 수정일: 21.11.07
 * 수정 내용: 실제 테이블 반영 후 매핑.
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WARRANTY")
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WR_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PR_ID")
    private Product prId;

    @Column(name = "WR_MONTH")
    private Integer month;

    @Column(name = "COUNT")
    private Integer count = 1; // ??


}
