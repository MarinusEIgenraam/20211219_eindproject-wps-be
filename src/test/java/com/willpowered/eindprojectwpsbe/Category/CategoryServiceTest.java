package com.willpowered.eindprojectwpsbe.Category;

import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;
    @Captor
    ArgumentCaptor<Category> categoryCaptor;
    @InjectMocks
    CategoryService categoryService;

    @Mock
    List<Category> categoryList;
    @Mock
    List<Project> projectList;

    Category firstCategory;
    Category secondCategory;

    @BeforeEach
    void setUp() {
        Set<Authority> authorities = new HashSet<>();
        firstCategory = Category.builder()
                .id(1L)
                .name("Best category ever")
                .projects(projectList)
                .description("This is the best of the best")
                .build();
        secondCategory = Category.builder()
                .id(2L)
                .name("Second best category ever")
                .projects(projectList)
                .description("This is the second best of the best")
                .build();
    }


    //////////////////////////////
    //// Create


    @Test
    void saveCategory() {
        when(categoryRepository.save(firstCategory)).thenReturn(firstCategory);

        categoryService.saveCategory(firstCategory);

        verify(categoryRepository, times(1)).save(categoryCaptor.capture());
        var capturedCategory = categoryCaptor.getValue();
        assertThat(firstCategory).isEqualTo(capturedCategory);
    }

    //////////////////////////////
    //// Read

    @Test
    void getCategories() {
        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<Category> newList = categoryService.getCategories();

        verify(categoryRepository, times(1)).findAll();
        assertThat(categoryList).isEqualTo(newList);
    }

    @Test
    void getCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(firstCategory));

        Category foundCategory = categoryService.getCategory(1L);

        verify(categoryRepository, times(1)).findById(1L);
        assertThat(foundCategory).isEqualTo(firstCategory);
    }

    //////////////////////////////
    //// Update

    @Test
    void updateCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(firstCategory));
        when(categoryRepository.save(firstCategory)).thenReturn(firstCategory);

        categoryService.updateCategory(1L, firstCategory);

        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(categoryCaptor.capture());
        var capturedCategory = categoryCaptor.getValue();
        assertThat(capturedCategory).isEqualTo(firstCategory);
    }

    //////////////////////////////
    //// Delete

    @Test
    void deleteCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(firstCategory));
        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void getDeleteException() {
        assertThrows(RecordNotFoundException.class, () -> categoryService.deleteCategory(null));
    }
}