package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.models.Category;
import com.ecommerceapp.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found By Id: " + id));
    }

    public Category updateCategory(Long id, Category toUpdateCategory){
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found By Id: " + id));

        existingCategory.setName(toUpdateCategory.getName());
        existingCategory.setDescription(toUpdateCategory.getDescription());

        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
