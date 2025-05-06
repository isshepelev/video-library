package ru.isshepelev.videocdlibrary.infrastructure.service;

import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Category;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;
import ru.isshepelev.videocdlibrary.ui.dto.CreateCategoryDto;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void createCategory(CreateCategoryDto createCategoryDto);

    void deleteCategoryById(Long id);
}
