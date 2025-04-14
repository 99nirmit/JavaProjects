package com.ecommerceapp.ecommerce.controller;

import com.ecommerceapp.ecommerce.DTOs.ReviewDTO;
import com.ecommerceapp.ecommerce.models.Review;
import com.ecommerceapp.ecommerce.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{userId}/{productId}")
    public String postReview(@PathVariable Long userId, @PathVariable Long productId, @RequestBody ReviewDTO reviewDTO){
        return reviewService.postReview(userId, productId, reviewDTO);
    }

    @GetMapping("/{userId}")
    public List<Review> getReviewByUserId(@PathVariable Long userId){
        return reviewService.getReviewByUserId(userId);
    }

    @GetMapping("/")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

}
