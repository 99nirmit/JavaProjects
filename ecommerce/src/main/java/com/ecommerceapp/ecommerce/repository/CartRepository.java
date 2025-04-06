package com.ecommerceapp.ecommerce.repository;

import com.ecommerceapp.ecommerce.models.Cart;
import com.ecommerceapp.ecommerce.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);

    Optional<Cart> findByUserIdAndId(Long userId, Long id);

    void deleteCartByUserIdAndId(Long userId, Long id);

    Optional<CartItem> findCartByUserIdAndId(Long userId, Long id);
}
