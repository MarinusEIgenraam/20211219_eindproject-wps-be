package com.willpowered.eindprojectwpsbe.mapping;

import com.willpowered.eindprojectwpsbe.dto.elements.CategoryDTO;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "numberOfProjects", expression = "java(mapProjects(category.getProjects()))")
    public abstract CategoryDTO mapCategoryToDto(Category category);

    default Integer mapProjects(List<Project> numberOfProjects) {
        return numberOfProjects.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Category mapDtoToCategory(Category category);

}
