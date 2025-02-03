package com.floxcon.project.caver.fccabApp.entities;

import com.floxcon.project.caver.fccabApp.entities.enums.Role;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING) // to store roles as it is, : ORDINAL: 0 for admmin, 1 for rider, etc.
    private Set<Role> roles; // new table is created name app_user_roles
}
