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

        @Autowired
        private CartItemService cartItemService;

        public Cart addProductToCart(Long userId, ProductCartDTO productCartDTO){
                Cart cart = cartRepository.findByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("Cart Not Found"));

            Products products = productsRepository.findById(productCartDTO.getProductId())
                    .orElseThrow(()-> new RuntimeException("Product Not Found"));

            Optional<CartItem> existingCard = cart.getCartItems().stream()
                    .filter(item -> item.getProducts().getId().equals(products.getId()))
                    .findFirst();

            if(existingCard.isPresent()){
                cartItemService.updateCartItemQuantity(existingCard.get(), products, productCartDTO);
            }else{
                CartItem newCartItem = cartItemService.createNewCartItem(productCartDTO, products, cart);
                cart.getCartItems().add(newCartItem);
            }

            return cartRepository.save(cart);
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
            cartItemService.clearCartItems(cartId);
        }

        public Long getCartItemCount(){
            long count = cartRepository.count();
            return count;
        }

    }
