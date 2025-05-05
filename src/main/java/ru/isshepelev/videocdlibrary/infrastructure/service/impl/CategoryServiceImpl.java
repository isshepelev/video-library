package ru.isshepelev.videocdlibrary.infrastructure.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Category;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.CategoryRepository;
import ru.isshepelev.videocdlibrary.infrastructure.service.CategoryService;
import ru.isshepelev.videocdlibrary.ui.dto.CreateCategoryDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> allCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(CreateCategoryDto createCategoryDto) {
        Category category = new Category();
        category.setName(createCategoryDto.getName());
        category.setDescription(createCategoryDto.getDescription());
        categoryRepository.save(category);
        log.info("Создание новой категории {}", category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }else {
            log.error("Категория с id {} не найдена", id);
        }
    }

}
