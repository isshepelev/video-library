package ru.isshepelev.videocdlibrary.ui.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.isshepelev.videocdlibrary.infrastructure.exception.UsernameAlreadyExistsException;
import ru.isshepelev.videocdlibrary.ui.dto.SignUpDto;
import ru.isshepelev.videocdlibrary.infrastructure.service.UserService;

@Controller
@AllArgsConstructor
@Slf4j
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute SignUpDto signUpDto, Model model) {
        try {
            userService.registerUser(signUpDto);
        } catch (UsernameAlreadyExistsException e) {
            model.addAttribute("error", "Пользователь уже существует");
            return "register";
        }
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}
