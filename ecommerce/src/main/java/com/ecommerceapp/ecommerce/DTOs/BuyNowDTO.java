package com.ecommerceapp.ecommerce.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuyNowDTO {

    private Long userId;


    private Long quantity;

    private Long productId;

    private Double totalAmount;
}
