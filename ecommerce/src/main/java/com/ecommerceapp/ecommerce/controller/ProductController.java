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

    @GetMapping("/")
    public List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Products getProduct(@PathVariable Long id){
        return productsService.getProduct(id);
    }

    @PutMapping("/update/{id}")
    public Products updateProducts(@PathVariable Long id, @RequestBody Products products){
        return productsService.updateProduct(id, products);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productsService.deleteProducts(id);
    }

}
