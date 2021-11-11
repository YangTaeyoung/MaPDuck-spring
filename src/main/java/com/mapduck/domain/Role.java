package com.mapduck.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long roleId;

    @Column(name = "NAME")
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name= "USER_ROLES",
        joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name= "USER_ID", referencedColumnName = "ID")
    )
    private List<User> users;

}
