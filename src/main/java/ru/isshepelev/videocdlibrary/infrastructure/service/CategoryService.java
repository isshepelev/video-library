package ru.isshepelev.videocdlibrary.infrastructure.service;

import org.springframework.stereotype.Service;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Category;
import ru.isshepelev.videocdlibrary.ui.dto.CreateCategoryDto;

import java.util.List;

public interface CategoryService {

    List<Category> allCategories();

    void createCategory(CreateCategoryDto createCategoryDto);

    void deleteCategoryById(Long id);
}
