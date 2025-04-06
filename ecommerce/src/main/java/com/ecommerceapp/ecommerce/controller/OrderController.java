package com.ecommerceapp.ecommerce.controller;

import com.ecommerceapp.ecommerce.DTOs.BuyNowDTO;
import com.ecommerceapp.ecommerce.models.Order;
import com.ecommerceapp.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/buynow/{userId}")
    public Order placeDirectOrder(@PathVariable Long userId, @RequestBody BuyNowDTO buyNowDTO){
        return orderService.placeDirectOrder(userId, buyNowDTO);
    }

    @PostMapping("/buycart/{userId}/{cartId}")
    public Order placeOrderFromCart(@PathVariable Long userId, @PathVariable Long cartId){
        return orderService.placeOrderFromCart(userId, cartId);
    }

    @GetMapping("/{userId}/{id}")
    public Order getOrderById(@PathVariable Long userId, @PathVariable Long id){
        return orderService.getOrderById(userId, id);
    }

    @GetMapping("/{userId}")
    public List<Order> getAllOrdersFromUserId(@PathVariable Long userId){
        return orderService.getAllOrderByUerId(userId);
    }

    @GetMapping("/")
    public List<Order> getAllOrdersFromHistory(){
        return orderService.getALlOrdersHistory();
    }

}
