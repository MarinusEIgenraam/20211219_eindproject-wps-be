package com.willpowered.eindprojectwpsbe.Category;

public class CategoryInputDto {
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