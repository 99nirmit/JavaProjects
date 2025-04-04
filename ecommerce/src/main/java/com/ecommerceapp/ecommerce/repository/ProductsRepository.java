package com.ecommerceapp.ecommerce.repository;

import com.ecommerceapp.ecommerce.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
}
