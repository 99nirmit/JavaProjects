package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.DTOs.ProductCartDTO;
import com.ecommerceapp.ecommerce.models.Cart;
import com.ecommerceapp.ecommerce.models.CartItem;
import com.ecommerceapp.ecommerce.models.Products;
import com.ecommerceapp.ecommerce.models.User;
import com.ecommerceapp.ecommerce.repository.CartItemRepository;
import com.ecommerceapp.ecommerce.repository.CartRepository;
import com.ecommerceapp.ecommerce.repository.ProductsRepository;
import com.ecommerceapp.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart AddIfCartNotExist(Long userId, Long id, ProductCartDTO productCartDTO){
        Cart cart = cartRepository.findByUserIdAndId(userId, id)
                .orElseThrow(()-> new RuntimeException("Cart Not Found"));

        Products products = productsRepository.findById(productCartDTO.getProductId())
                .orElseThrow(()-> new RuntimeException("Product Not Found"));

        User user = userRepository.findById(productCartDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User Not Found with Id"));

        Optional<CartItem> existingCard = cart.getCartItems().stream()
                .filter(item -> item.getProducts().getId().equals(products.getId())).findFirst();

        if(existingCard.isPresent()){
            updateCart(existingCard, products, productCartDTO);
        }else{
            CartItem newItem = createNewCartItem(productCartDTO, products, user, cart);
            cart.getCartItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    public CartItem createNewCartItem (ProductCartDTO productCartDTO, Products products, User user, Cart cart){

        CartItem cartItem = new CartItem();
        cartItem.setProducts(products);
        cartItem.setQuantity(productCartDTO.getQuantity());
        cartItem.setPriceAtAdded(products.getPrice() * productCartDTO.getQuantity());

        cartItem.setCart(cart);
        return cartItem;
    }

    public CartItem updateCart(Optional<CartItem> existingCard, Products products, ProductCartDTO productCartDTO){
        CartItem cartItem = existingCard.get();
        Long newQuantity = cartItem.getQuantity() + productCartDTO.getQuantity();
        cartItem.setQuantity(newQuantity);
        cartItem.setPriceAtAdded(products.getPrice() * newQuantity);
        return cartItem;
    }

    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }

    public Cart getCartByUser(Long userId, Long id){
        return cartRepository.findByUserIdAndId(userId, id)
                .orElseThrow(()-> new RuntimeException("UserId and CartId Not Found"));
    }

    public void deleteCartByUser(Long userId, Long id){
        cartRepository.deleteCartByUserIdAndId(userId, id);
    }

    public void clearCart(Long cartId){
        cartItemRepository.deleteAllByCartId(cartId);
    }

    public Long getCartItemCount(){
        long count = cartItemRepository.count();
        return count;
    }

}
