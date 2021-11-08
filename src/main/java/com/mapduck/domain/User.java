package com.mapduck.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


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

    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "USER_ROLES",
        joinColumns = @JoinColumn(name= "USER_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name= "ROLE_ID", referencedColumnName = "ID")
    )
    List<Role> roles;


}
