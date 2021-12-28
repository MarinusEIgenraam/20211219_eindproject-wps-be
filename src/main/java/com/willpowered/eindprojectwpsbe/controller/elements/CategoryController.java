package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.CategoryDto;
import com.willpowered.eindprojectwpsbe.service.elements.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.save(categoryDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategorys() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getCategory(id));
    }
}