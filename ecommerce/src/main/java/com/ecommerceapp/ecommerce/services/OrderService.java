package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.DTOs.BuyNowDTO;
import com.ecommerceapp.ecommerce.models.*;
import com.ecommerceapp.ecommerce.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public Order placeDirectOrder(Long userId, BuyNowDTO buyNowDTO){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Products product = productsRepository.findById(buyNowDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));


        Order order = new Order();
        OrderItem orderItem = orderItemService.createOrderItem(buyNowDTO, order);
        order.setUser(user);
        order.setOrderItems(List.of(orderItem));
        order.setTotalAmount(product.getPrice() * buyNowDTO.getQuantity());

        return orderRepository.save(order);
    }

    @Transactional
    public Order placeOrderFromCart(Long userId, Long cartId){
        Cart existingCart = cartRepository.findByUserIdAndId(userId, cartId)
                .orElseThrow(() -> new RuntimeException("Cart Not Found"));

        if(existingCart.getCartItems().isEmpty()){
            throw new RuntimeException("Cart is empty.Cannot Place Order");
        }

        Order order = new Order();
        order.setUser(existingCart.getUser());
        order.setTotalAmount(0.00);

        List<OrderItem> orderItems = existingCart.getCartItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtOrderTime(cartItem.getProducts().getPrice());
            orderItem.setProduct(cartItem.getProducts());
            orderItem.setOrder(order);

            return orderItem;
        }).collect(Collectors.toList());

     Double totalAmount = orderItems.stream()
             .mapToDouble(item -> item.getPriceAtOrderTime() * item.getQuantity())
             .sum();

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order saveOrder = orderRepository.save(order);
        cartItemRepository.deleteAllByCartId(cartId);

        return saveOrder;
    }

    public Order getOrderById(Long userId, Long orderId){
        return orderRepository.findByUser_IdAndId(userId, orderId);
    }

    public List<Order> getAllOrderByUerId(Long userId){
        return orderRepository.findByUser_Id(userId);
    }

    public List<Order> getALlOrdersHistory(){
        return orderRepository.findAll();
    }

}
