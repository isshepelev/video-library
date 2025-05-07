package ru.isshepelev.videocdlibrary.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;
import ru.isshepelev.videocdlibrary.infrastructure.service.CategoryService;
import ru.isshepelev.videocdlibrary.infrastructure.service.VideoService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final CategoryService categoryService;
    private final VideoService videoService;

    @GetMapping("")
    public String libraryForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "library";
    }

    @GetMapping("/api/videos/by-category/{categoryId}")
    @ResponseBody
    public List<Video> getVideosByCategory(@PathVariable Long categoryId) {
        return categoryService.getVideosByCategory(categoryId);
    }

    @GetMapping("/api/videos/all")
    @ResponseBody
    public List<Video> getAllVideos() {
        return videoService.getAllVideo();
    }

}
