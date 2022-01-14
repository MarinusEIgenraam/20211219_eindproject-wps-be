package com.willpowered.eindprojectwpsbe.Category;


import lombok.var;

public class CategoryDto {
    public Long id;
    public String name;
    public String description;

    public static CategoryDto fromCategory(Category category) {

        var dto = new CategoryDto();

        dto.id = category.getId();
        dto.name = category.getName();
        dto.description = category.getDescription();

        return dto;
    }
}