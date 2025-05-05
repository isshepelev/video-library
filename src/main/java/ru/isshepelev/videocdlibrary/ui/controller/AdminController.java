package ru.isshepelev.videocdlibrary.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.isshepelev.videocdlibrary.infrastructure.service.CategoryService;
import ru.isshepelev.videocdlibrary.infrastructure.service.VideoService;
import ru.isshepelev.videocdlibrary.ui.dto.CreateCategoryDto;
import ru.isshepelev.videocdlibrary.ui.dto.VideoDto;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;
    private final VideoService videoService;

    @GetMapping("")
    public String mainFrom(Model model){
        model.addAttribute("categories", categoryService.allCategories());
        return "admin-form";
    }

    @PostMapping("/add-category")
    @ResponseBody
    public String addCategory(CreateCategoryDto createCategoryDto){
        categoryService.createCategory(createCategoryDto);
        return "redirect:/admin";
    }

    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return "redirect:/admin";
    }

    @PostMapping("/add-video/{categoryId}")
    public String addVideoToCategory(@PathVariable Long categoryId,
                                     @RequestParam("file") MultipartFile file,
                                     @ModelAttribute VideoDto videoDto) throws IOException {

        videoService.addNewVideoToCategory(categoryId,file, videoDto);
        return "redirect:/admin";
    }

}
