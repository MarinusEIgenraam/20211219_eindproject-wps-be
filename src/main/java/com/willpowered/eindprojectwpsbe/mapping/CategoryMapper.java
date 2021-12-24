package com.willpowered.eindprojectwpsbe.mapping;

import com.willpowered.eindprojectwpsbe.dto.elements.CategoryDto;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "numberOfProjects", expression = "java(mapProjects(category.getProjects()))")
    CategoryDto mapCategoryToDto(Category category);

    default Integer mapProjects(List<Project> numberOfProjects) {
        return numberOfProjects.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "projects", ignore = true)
    Category mapDtoToCategory(CategoryDto categoryDTO);

}
