package ru.isshepelev.videocdlibrary.ui.dto;

import lombok.Data;

@Data
public class SignUpDto {

    private Long id;
    private String username;
    private String password;
    private String email;
}
