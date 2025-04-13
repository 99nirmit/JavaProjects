package com.ecommerceapp.ecommerce.repository;

import com.ecommerceapp.ecommerce.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    List<Products> findAllProductsByUserId(Long userId);

    Products findProductByUserIdAndId(Long userId, Long id);

    void deleteProductByUserIdAndId(Long userId, Long id);

}
