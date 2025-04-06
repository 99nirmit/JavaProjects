package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.DTOs.BuyNowDTO;
import com.ecommerceapp.ecommerce.models.Order;
import com.ecommerceapp.ecommerce.models.OrderItem;
import com.ecommerceapp.ecommerce.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    ProductsService productsService;

    public OrderItem createOrderItem(BuyNowDTO buyNowDTO, Order order){
        Products getProducts = productsService.getProduct(buyNowDTO.getProductId());
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(buyNowDTO.getQuantity());
        orderItem.setPriceAtOrderTime(getProducts.getPrice());
        orderItem.setOrder(order);
        orderItem.setProduct(getProducts);
        return orderItem;
    }

}
