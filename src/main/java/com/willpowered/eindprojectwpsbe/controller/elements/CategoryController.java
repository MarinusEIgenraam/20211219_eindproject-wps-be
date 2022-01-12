package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Category.CategoryDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Category.CategoryInputDto;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.service.elements.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable("id") Long id) {
        var category = categoryService.getCategory(id);
        return CategoryDto.fromCategory(category);
    }

    @GetMapping("/all")
    public List<CategoryDto> getCategories() {
        var dtos = new ArrayList<CategoryDto>();
        List<Category> categories;

        categories = categoryService.getCategories();
        for (Category category : categories) {
            dtos.add(CategoryDto.fromCategory(category));
        }
        return dtos;
    }

    @PostMapping
    public CategoryDto saveCategory(@RequestBody CategoryInputDto dto) {
        var category = categoryService.saveCategory(dto.toCategory());
        return CategoryDto.fromCategory(category);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryInputDto dto) {
        categoryService.updateCategory(id, dto.toCategory());
        return CategoryDto.fromCategory(dto.toCategory());
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}