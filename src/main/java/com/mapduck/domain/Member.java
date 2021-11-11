package com.mapduck.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "PHONE", unique = true)
    String phone;

    @Column(name = "EMAIL", unique = true)
    String email;

    @Column(name = "UPDATED_AT", columnDefinition = "datetime default now()")
    LocalDateTime updatedAt;

    @Column(name = "JOINED_AT", columnDefinition = "datetime default now()")
    LocalDateTime joinedAt;

    @OneToMany
    @JoinColumn(name = "OWNER")
    List<Own> owns;
}
