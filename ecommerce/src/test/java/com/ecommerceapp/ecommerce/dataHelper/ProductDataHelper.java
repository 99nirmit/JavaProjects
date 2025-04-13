package com.ecommerceapp.ecommerce.dataHelper;

import com.ecommerceapp.ecommerce.models.Products;
import com.ecommerceapp.ecommerce.repository.ProductsRepository;
import com.ecommerceapp.ecommerce.services.ProductsService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


public class ProductDataHelper {

    public static Products createProducts(Long id, String name, String description, Double price, Long quantity){
        Products products = new Products();
        products.setId(id);
        products.setName(name);
        products.setDescription(description);
        products.setPrice(price);
        products.setQuantity(quantity);

        return products;
    }

    public static List<Products> createAllProducts(int count){
        List<Products> productsList = new ArrayList<>();
        for(int i = 0; i < count; i++){
            productsList.add(createProducts((long) i, "TV", "Smart TV", 9824.8, 10L));
        }
        return productsList;
    }

    public static Products createSavedProducts(){
        return createProducts(null, "TV", "Smart TV", 9824.8, 10L);
    }

    public static Products createNewProducts(){
        return createProducts(1L, "TV", "Smart TV", 9824.8, 10L);
    }

}
