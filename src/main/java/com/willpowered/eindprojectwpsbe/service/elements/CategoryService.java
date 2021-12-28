package com.willpowered.eindprojectwpsbe.service.elements;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.repository.elements.CategoryRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProjectRepository projectRepository;
    
    public List<Category> getCategorys() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()) {
            return category.get();
        } else {
            throw new RecordNotFoundException("Machine does not exist");
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
            throw new RecordNotFoundException("category does not exist");
        }
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
