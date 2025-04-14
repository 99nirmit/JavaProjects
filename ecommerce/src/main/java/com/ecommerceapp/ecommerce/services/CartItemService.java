package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.DTOs.ProductCartDTO;
import com.ecommerceapp.ecommerce.models.Cart;
import com.ecommerceapp.ecommerce.models.CartItem;
import com.ecommerceapp.ecommerce.models.Products;
import com.ecommerceapp.ecommerce.models.User;
import com.ecommerceapp.ecommerce.repository.CartItemRepository;
import com.ecommerceapp.ecommerce.repository.CartRepository;
import com.ecommerceapp.ecommerce.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsRepository productsRepository;

    private CartItemRepository cartItemRepository;

    public CartItem createNewCartItem (ProductCartDTO productCartDTO, Products products, Cart cart){

        CartItem cartItem = new CartItem();
        cartItem.setProducts(products);
        cartItem.setQuantity(productCartDTO.getQuantity());
        cartItem.setPriceAtAdded(products.getPrice() * productCartDTO.getQuantity());

        cartItem.setCart(cart);
        return cartItem;
    }

    public void updateCartItemQuantity(CartItem cartItem, Products products, ProductCartDTO productCartDTO){
        Long newQuantity = cartItem.getQuantity() + productCartDTO.getQuantity();
        cartItem.setQuantity(newQuantity);
        cartItem.setPriceAtAdded(products.getPrice() * newQuantity);
    }

    public void clearCartItems(Long cartId){
        cartItemRepository.deleteAllByCartId(cartId);
    }

    public Long getCartItemCount(){
        return cartItemRepository.count();
    }

    public Cart getCartByUser(Long userId, Long id){
        return cartRepository.findByUserIdAndId(userId, id)
                .orElseThrow(()-> new RuntimeException("UserId and CartId Not Found"));
    }

    public void deleteCartByUser(Long userId, Long id){
        cartRepository.deleteCartByUserIdAndId(userId, id);
    }

}
