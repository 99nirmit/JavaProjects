package com.beans.cruddb.domain;

import com.beans.cruddb.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
