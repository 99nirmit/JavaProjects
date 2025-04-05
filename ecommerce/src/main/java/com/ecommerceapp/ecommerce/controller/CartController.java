package com.ecommerceapp.ecommerce.controller;

import com.ecommerceapp.ecommerce.DTOs.ProductCartDTO;
import com.ecommerceapp.ecommerce.models.Cart;
import com.ecommerceapp.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/{id}")
    public Cart addProductToCart(@PathVariable Long userId, @PathVariable Long id, @RequestBody ProductCartDTO productCartDTO){
        return cartService.addProductToCart(userId, id, productCartDTO);
    }

    @GetMapping("/")
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("/{userId}/{id}")
    public Cart getCartByUser(@PathVariable Long userId, @PathVariable Long id){
        return cartService.getCartByUser(userId, id);
    }

    @DeleteMapping("/{userId}/{id}")
    public void deleteCartByUser(@PathVariable Long userId, @PathVariable Long id){
        cartService.deleteCartByUser(userId, id);
    }

    @DeleteMapping("/{cartId}")
    public void clearCart(@PathVariable Long cartId){
        cartService.clearCart(cartId);
    }

    @GetMapping("/count")
    public Long getCartItemCount(){
        return cartService.getCartItemCount();
    }
}
