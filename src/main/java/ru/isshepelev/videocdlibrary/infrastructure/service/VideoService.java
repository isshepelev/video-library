package ru.isshepelev.videocdlibrary.infrastructure.service;

import org.springframework.web.multipart.MultipartFile;
import ru.isshepelev.videocdlibrary.ui.dto.VideoDto;

import java.io.IOException;

public interface VideoService {

    void addNewVideoToCategory(Long categoryId, MultipartFile file, VideoDto videoDto) throws IOException;
}
