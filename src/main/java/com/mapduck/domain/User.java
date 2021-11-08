package com.mapduck.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 작성자: 강동연
 * 작성일: 2021.11.3
 * 설명: User Entity
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
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk를 db에서 알아서 해준다.
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "JOINED_AT")
    private LocalDateTime joinedAt; // date?
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt; // date?




}
