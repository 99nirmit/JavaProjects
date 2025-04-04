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

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public Products getProduct(Long id){
        return productsRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product by Id not found: " + id));
    }

    public Products updateProduct(Long id, Products toUpdateProduct){
        Products existingProduct = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found " + id));

        existingProduct.setName(toUpdateProduct.getName());
        existingProduct.setDescription(toUpdateProduct.getDescription());
        existingProduct.setPrice(toUpdateProduct.getPrice());
        existingProduct.setQuantity(toUpdateProduct.getQuantity());
        existingProduct.setBrand(toUpdateProduct.getBrand());

        return productsRepository.save(existingProduct);
    }

    public void deleteProducts(Long id){
        productsRepository.deleteById(id);
    }
}
