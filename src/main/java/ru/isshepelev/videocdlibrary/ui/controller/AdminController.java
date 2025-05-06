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
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;
    private final VideoService videoService;

    @GetMapping("")
    public String mainFrom(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
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

    @PostMapping("/add-video")
    public String addVideo(@RequestParam("file") MultipartFile file,
                           @RequestParam(value = "categoryIds", required = false) List<Long> categoryIds,
                           @ModelAttribute VideoDto videoDto) throws IOException {
        videoService.addNewVideo(file, videoDto, categoryIds);
        return "redirect:/admin";
    }

}
