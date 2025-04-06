package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.DTOs.ReviewDTO;
import com.ecommerceapp.ecommerce.models.Products;
import com.ecommerceapp.ecommerce.models.Review;
import com.ecommerceapp.ecommerce.models.User;
import com.ecommerceapp.ecommerce.repository.ProductsRepository;
import com.ecommerceapp.ecommerce.repository.ReviewRepository;
import com.ecommerceapp.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;

    public String postReview(Long userId, Long productId, ReviewDTO reviewDTO){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Products products = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setFeedback(reviewDTO.getFeedback());
        review.setUser(user);
        review.setProduct(products);

        reviewRepository.save(review);

        return "Review Saved Successfully";
    }

    public List<Review> getReviewByUserId(Long userId){
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }
}
