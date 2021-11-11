package com.mapduck.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "PHONE")
    String phone;

    @Column(name = "UPDATED_AT", columnDefinition = "datetime default now()")
    LocalDateTime updatedAt;

    @Column(name = "JOINED_AT", columnDefinition = "datetime default now()")
    LocalDateTime joinedAt;
}
