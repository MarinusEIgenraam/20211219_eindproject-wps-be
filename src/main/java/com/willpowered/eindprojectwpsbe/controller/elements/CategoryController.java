package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Category.CategoryDTO;
import com.willpowered.eindprojectwpsbe.dto.elements.Category.CategoryInputDTO;
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
    public CategoryDTO getCategory(@PathVariable("id") Long id) {
        var category = categoryService.getCategory(id);
        return CategoryDTO.fromCategory(category);
    }

    @PostMapping
    public CategoryDTO saveCategory(@RequestBody CategoryInputDTO dto) {
        var category = categoryService.saveCategory(dto.toCategory());
        return CategoryDTO.fromCategory(category);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return CategoryDTO.fromCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}