package com.willpowered.eindprojectwpsbe.Category;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
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
    
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            return category.get();
        } else {
            throw new RecordNotFoundException("Category does not exist");
        }
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void updateCategory(Long id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            categoryRepository.save(category);
        } else {
            throw new RecordNotFoundException("Category does not exist");
        }
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
