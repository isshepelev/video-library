package ru.isshepelev.videocdlibrary.infrastructure.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Attachment;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.UserRepository;
import ru.isshepelev.videocdlibrary.infrastructure.service.TransactionService;
import ru.isshepelev.videocdlibrary.infrastructure.service.VideoService;
import ru.isshepelev.videocdlibrary.infrastructure.service.VideoStreamingService;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoStreamingServiceImpl implements VideoStreamingService {

    private final VideoService videoService;
    private final TransactionService transactionService;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Resource> prepareVideoStream(Long videoId, String username) {
        User user = userRepository.findByUsername(username);
        Video video = videoService.findById(videoId);

        if (!transactionService.hasAccessToVideo(user, video)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Attachment attachment = video.getAttachment();
        Path filePath = Paths.get(attachment.getFilePath());
        Resource resource = new FileSystemResource(filePath.toFile());

        return ResponseEntity.ok()
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
