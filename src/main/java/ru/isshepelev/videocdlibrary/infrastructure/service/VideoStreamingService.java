package ru.isshepelev.videocdlibrary.infrastructure.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface VideoStreamingService {

    ResponseEntity<Resource> prepareVideoStream(Long videoId, String username);
}
