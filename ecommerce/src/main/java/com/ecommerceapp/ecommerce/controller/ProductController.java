package com.ecommerceapp.ecommerce.controller;
import com.ecommerceapp.ecommerce.models.Products;
import com.ecommerceapp.ecommerce.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/save")
    public Products saveProduct(@RequestBody Products products){
        return productsService.saveProduct(products);
    }

    @GetMapping("/{userId}")
    public List<Products> getAllProducts(@PathVariable Long userId){
        return productsService.getAllProductsByUserId(userId);
    }

    @GetMapping("/{userId}/{id}")
    public Products getProduct(@PathVariable Long userId, @PathVariable Long id){
        return productsService.getProduct(userId, id);
    }

    @PutMapping("/update/{userId}/{id}")
    public Products updateProducts(@PathVariable Long userId, @PathVariable Long id, @RequestBody Products products){
        return productsService.updateProduct(userId, id, products);
    }

    @DeleteMapping("/delete/{userId}/{id}")
    public void deleteProduct(@PathVariable Long userId, @PathVariable Long id){
        productsService.deleteProducts(userId, id);
    }

}
