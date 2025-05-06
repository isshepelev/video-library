package ru.isshepelev.videocdlibrary.infrastructure.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.isshepelev.videocdlibrary.infrastructure.exception.UsernameAlreadyExistsException;
import ru.isshepelev.videocdlibrary.ui.dto.SignUpDto;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Role;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.RoleRepository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.UserRepository;
import ru.isshepelev.videocdlibrary.infrastructure.service.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void registerUser(SignUpDto signUpDto) {
        if (userRepository.findByUsername(signUpDto.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Пользователь уже существует");
        }

        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setEmail(signUpDto.getEmail());

        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setRoleName("ROLE_USER");
            roleRepository.save(userRole);
        }

        user.setRoles(Set.of(userRole));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
