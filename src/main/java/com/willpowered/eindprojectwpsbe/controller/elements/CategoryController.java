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

@RestController
@RequestMapping("/categorys")
@AllArgsConstructor
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable("id") Long id) {
        var category = categoryService.getCategory(id);
        return CategoryDto.fromCategory(category);
    }

    @PostMapping
    public CategoryDto saveCategory(@RequestBody CategoryInputDto Dto) {
        var category = categoryService.saveCategory(Dto.toCategory());
        return CategoryDto.fromCategory(category);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return CategoryDto.fromCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}