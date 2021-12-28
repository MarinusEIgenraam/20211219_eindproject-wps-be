package com.willpowered.eindprojectwpsbe.dto.elements.Category;

import com.willpowered.eindprojectwpsbe.model.elements.Category;

import javax.validation.constraints.NotBlank;

public class CategoryInputDTO {
    public Long id;
    public String name;
    public String description;


    public Category toCategory() {
        Category category = new Category();

        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        return category;
    }
}