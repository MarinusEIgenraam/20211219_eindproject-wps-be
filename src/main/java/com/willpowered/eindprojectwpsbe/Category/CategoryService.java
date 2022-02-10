package com.willpowered.eindprojectwpsbe.Category;

import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    //////////////////////////////
    //// Create

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    //////////////////////////////
    //// Read

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long categoryId) {
        var category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            return category.get();
        } else {
            throw new RecordNotFoundException("Category does not exist");
        }
    }

    //////////////////////////////
    //// Update

    public void updateCategory(Long id, Category category) {
        var optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.save(category);
        } else {
            throw new RecordNotFoundException("Category does not exist");
        }
    }

    //////////////////////////////
    //// Delete

    public void deleteCategory(Long id) {
        var optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Category does not exist");
        }
    }
}
