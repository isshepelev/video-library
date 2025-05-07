package ru.isshepelev.videocdlibrary.ui.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VideoDto {

    private String videoName;
    private String videoDescription;
}
