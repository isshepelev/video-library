package ru.isshepelev.videocdlibrary.infrastructure.service;

import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.ui.dto.SignUpDto;

public interface UserService {

    void registerUser(SignUpDto signUpDto);

    User findByUsername(String username);
}
