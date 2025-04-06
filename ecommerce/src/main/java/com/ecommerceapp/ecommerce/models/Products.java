package com.ecommerceapp.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    private String brand;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @PrePersist
    protected void createdAt(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void updatedAt(){
        updatedAt = LocalDateTime.now();
    }

}
