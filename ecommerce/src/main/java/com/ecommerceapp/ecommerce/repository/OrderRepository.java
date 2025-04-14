package com.ecommerceapp.ecommerce.repository;

import com.ecommerceapp.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUser_IdAndId(Long userId, Long orderId);

    List<Order> findByUser_Id(Long userId);

}
