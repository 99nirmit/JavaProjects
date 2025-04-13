package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.dataHelper.ProductDataHelper;
import com.ecommerceapp.ecommerce.models.Products;
import com.ecommerceapp.ecommerce.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {

    @InjectMocks
    private ProductsService productsService;

    @Mock
    private ProductsRepository productsRepository;

    @Test
    void saveProduct() {

        //Arrange

        //I/P Prepare
        Products newProducts = ProductDataHelper.createNewProducts();

        //Expected O/P
        Products expected = ProductDataHelper.createSavedProducts();

        //Mock Behaviour
        when(productsRepository.save(newProducts))
                .thenReturn(expected);

        //Act
        //Actual function call
        Products result = productsService.saveProduct(newProducts);

        //Assert
        //Verify Result

        assertEquals(expected, result);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getDescription(), result.getDescription());

        verify(productsRepository).save(newProducts);
    }

    @Test
    void getAllProducts() {
        //Arrange
        //I/P Prepare
        Long userId = 1L;

        //Expected O/P
        List<Products> expected = ProductDataHelper.createAllProducts(5);

        //Mock Behaviour
        when(productsRepository.findAllProductsByUserId(userId))
                .thenReturn(expected);

        //Act
        List<Products> result = productsService.getAllProductsByUserId(userId);

        //Assert
        //Result verify
        assertEquals(5, result.size());

        verify(productsRepository).findAllProductsByUserId(userId);
    }

    @Test
    void getProduct() {
        //Arrange
        //I/P Prepare
        Long userId = 1L;
        Long id = 1L;

        //Expected
        Products expected = ProductDataHelper.createSavedProducts();

        //Mock Behaviour
        when(productsRepository.findProductByUserIdAndId(userId,id))
                .thenReturn(expected);

        //Act
        Products result = productsService.getProduct(userId, id);

        //Assert
        assertEquals(expected, result);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());

        verify(productsRepository).findProductByUserIdAndId(userId, id);
    }

    @Test
    void updateProduct() {
        //Arrange
        Long userId = 1L;
        Long id = 1L;
        Products existingProduct = ProductDataHelper.createProducts(1L, "TV", "Smart TV", 9824.8, 10L);
        Products newProduct = ProductDataHelper.createProducts(1L, "Mobile", "Smart Mobile", 2824.8, 11L);

        //Expected
        Products expectedProduct = ProductDataHelper.createProducts(1L, "Mobile", "Smart Mobile", 2824.8, 11L);

        //Mock
        when(productsRepository.findProductByUserIdAndId(userId, id))
                .thenReturn(existingProduct);

        when(productsRepository.save(any(Products.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Act
        Products result = productsService.updateProduct(userId, id, newProduct);

        //Assert
        //Verify Result

        assertEquals(1L, result.getId());
        assertEquals("Mobile", result.getName());

        verify(productsRepository).findProductByUserIdAndId(userId, id);
        verify(productsRepository).save(existingProduct);
    }

    @Test
    void deleteProducts() {

        //Arrange
        Long userId = 1L;
        Long id = 1L;

        //Expected Nothing in delete
        //Mock
        doNothing().when(productsRepository).deleteProductByUserIdAndId(userId, id);

        //Act
        productsService.deleteProducts(userId, id);

        //Assert
        verify(productsRepository).deleteProductByUserIdAndId(userId, id);

    }
}