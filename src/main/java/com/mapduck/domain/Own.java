package com.mapduck.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 작성자: 강동연
 * 작성일: 2021.11.3
 * 설명: Own Entity
 * 수정자: 양태영
 * 수정일: 21.11.07
 * 수정 내용: 실제 테이블 반영 후 매핑.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OWN")
public class Own {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OWN_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "OWNER")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "OWN_PRODUCT")
    private Product ownProduct;

    @Column(name="PURCHASED_AT")
    private LocalDateTime purchasedAt; // date??
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt; // date??


}
