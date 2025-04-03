package com.ecommerceapp.ecommerce.models;

import com.ecommerceapp.ecommerce.Enum.Role;
import com.ecommerceapp.ecommerce.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role roles;

    private String address;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    @PrePersist
    protected void createdAt(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected  void updatedAt(){
        updateAt = LocalDateTime.now();
    }
}
