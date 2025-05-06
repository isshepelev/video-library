package ru.isshepelev.videocdlibrary.infrastructure.service;

import org.springframework.web.multipart.MultipartFile;
import ru.isshepelev.videocdlibrary.ui.dto.VideoDto;

import java.io.IOException;
import java.util.List;

public interface VideoService {

    void addNewVideo(MultipartFile file, VideoDto videoDto, List<Long> categoryIds);
}
