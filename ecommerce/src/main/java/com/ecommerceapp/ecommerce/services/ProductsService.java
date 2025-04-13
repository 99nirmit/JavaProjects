package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.models.Products;
import com.ecommerceapp.ecommerce.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public Products saveProduct(Products products){
        return productsRepository.save(products);
    }

    public List<Products> getAllProductsByUserId(Long userId){
        return productsRepository.findAllProductsByUserId(userId);
    }

    public Products getProduct(Long userId, Long id){
        return productsRepository.findProductByUserIdAndId(userId, id);
    }

    public Products updateProduct(Long userId, Long id, Products toUpdateProduct){
        Products existingProduct = productsRepository.findProductByUserIdAndId(userId, id);

        existingProduct.setName(toUpdateProduct.getName());
        existingProduct.setDescription(toUpdateProduct.getDescription());
        existingProduct.setPrice(toUpdateProduct.getPrice());
        existingProduct.setQuantity(toUpdateProduct.getQuantity());
        existingProduct.setBrand(toUpdateProduct.getBrand());

        return productsRepository.save(existingProduct);
    }

    public void deleteProducts(Long userId, Long id){
        productsRepository.deleteProductByUserIdAndId(userId, id);
    }
}
