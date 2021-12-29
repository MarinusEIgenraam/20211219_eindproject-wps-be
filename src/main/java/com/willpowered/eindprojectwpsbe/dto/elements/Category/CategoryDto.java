package com.willpowered.eindprojectwpsbe.dto.elements.Category;

import com.willpowered.eindprojectwpsbe.model.elements.Category;
import lombok.var;

public class CategoryDto {
    public Long id;
    public String name;
    public String description;

    public static CategoryDto fromCategory(Category category) {

        var Dto = new CategoryDto();
        Dto.id = category.getId();
        Dto.name = category.getName();
        Dto.description = category.getDescription();

        return Dto;
    }
}