package com.willpowered.eindprojectwpsbe.Category;

import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
    Category category;
    @Mock
    List<Category> categoryList;


    //////////////////////////////
    //// Create


    @Test
    void saveCategory() {
        when(categoryRepository.save(category)).thenReturn(category);

        categoryService.saveCategory(category);

        verify(categoryRepository, times(1)).save(categoryCaptor.capture());
        var capturedCategory = categoryCaptor.getValue();
        assertThat(category).isEqualTo(capturedCategory);
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
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));

        Category foundCategory = categoryService.getCategory(1L);

        verify(categoryRepository, times(1)).findById(1L);
        assertThat(foundCategory).isEqualTo(category);
    }

    //////////////////////////////
    //// Update

    @Test
    void updateCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));
        when(categoryRepository.save(category)).thenReturn(category);

        categoryService.updateCategory(1L, category);

        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(categoryCaptor.capture());
        var capturedCategory = categoryCaptor.getValue();
        assertThat(capturedCategory).isEqualTo(category);
    }

    //////////////////////////////
    //// Delete

    @Test
    void deleteCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));
        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void getDeleteException() {
        assertThrows(RecordNotFoundException.class, () -> categoryService.deleteCategory(null));
    }
}