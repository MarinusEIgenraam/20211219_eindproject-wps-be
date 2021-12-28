package com.willpowered.eindprojectwpsbe.dto.elements.Category;

import com.willpowered.eindprojectwpsbe.model.elements.Category;
import lombok.var;

public class CategoryDTO {
    public Long id;
    public String name;
    public String description;

    public static CategoryDTO fromCategory(Category category) {

        var dto = new CategoryDTO();
        dto.id = category.getId();
        dto.name = category.getName();
        dto.description = category.getDescription();

        return dto;
    }
}