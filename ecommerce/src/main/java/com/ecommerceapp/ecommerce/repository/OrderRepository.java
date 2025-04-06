package com.ecommerceapp.ecommerce.repository;

import com.ecommerceapp.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findUserIdAndOrderId(Long userId, Long orderId);

    List<Order> findByUserId(Long userId);

}
