package ru.isshepelev.videocdlibrary.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.isshepelev.videocdlibrary.infrastructure.service.VideoStreamingService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class VideoStreamController {

    private final VideoStreamingService videoStreamingService;

    @GetMapping("/video/{videoId}")
    public ResponseEntity<Resource> streamVideo(@PathVariable Long videoId, Principal principal) {
        return videoStreamingService.prepareVideoStream(videoId, principal.getName());
    }
}