package ru.isshepelev.videocdlibrary.infrastructure.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Attachment;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Category;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.AttachmentRepository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.CategoryRepository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.VideoRepository;
import ru.isshepelev.videocdlibrary.infrastructure.service.VideoService;
import ru.isshepelev.videocdlibrary.ui.dto.VideoDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final AttachmentRepository attachmentRepository;
    private final CategoryRepository categoryRepository;

    @Value("${upload.dir}")
    private String UPLOAD_DIR;


    @Override
    public void addNewVideo(MultipartFile file, VideoDto videoDto, List<Long> categoryIds) {
        if (file.isEmpty()) {
            log.warn("файл пустой");
            throw new IllegalArgumentException("File cannot be empty");
        }

        try {
            Attachment attachment = saveUploadedFile(file);
            Video video = createVideo(videoDto, attachment);

            if (categoryIds != null && !categoryIds.isEmpty()) {
                linkVideoToCategories(video, categoryIds);
            }

            log.info("добавлено новое видео с именем: " + video.getName());
        } catch (IOException e) {
            log.error("ошибка сохранения файла", e);
            throw new RuntimeException("Failed to save file", e);
        }
    }

    private Attachment saveUploadedFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR).resolve(fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        Attachment attachment = new Attachment();
        attachment.setFilePath(path.toString());
        attachment.setSize(file.getSize());

        return attachmentRepository.save(attachment);
    }

    private Video createVideo(VideoDto videoDto, Attachment attachment) {
        Video video = new Video();
        video.setAttachment(attachment);
        video.setName(videoDto.getVideoName());
        video.setDescription(videoDto.getVideoDescription());
        return videoRepository.save(video);
    }

    private void linkVideoToCategories(Video video, List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        for (Category category : categories) {
            video.getCategories().add(category);
            category.getVideos().add(video);
            categoryRepository.save(category);
        }
        videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideo(){
        return videoRepository.findAll();
    }
}
