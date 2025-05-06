package ru.isshepelev.videocdlibrary;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Category;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Role;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.User;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.CategoryRepository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.RoleRepository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.repository.UserRepository;

import java.util.Set;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        Role role1 = new Role();
        role.setRoleName("ROLE_ADMIN");
        role1.setRoleName("ROLE_USER");
        roleRepository.save(role1);
        roleRepository.save(role);

        User user1 = new User();
        user1.setEmail("user1@gmail.com");
        user1.setPassword(passwordEncoder.encode("1"));
        user1.setUsername("1");
        user1.setRoles(Set.of(role));

        User user2 = new User();
        user2.setEmail("user2@gmail.com");
        user2.setPassword(passwordEncoder.encode("2"));
        user2.setUsername("2");
        user2.setRoles(Set.of(role1));

        userRepository.save(user1);
        userRepository.save(user2);

        Category category = new Category();
        category.setName("Драма");
        category.setDescription("Драма");

        Category category1 = new Category();
        category1.setName("Комедия");
        category1.setDescription("Комедия");

        Category category2 = new Category();
        category2.setName("Ужасы");
        category2.setDescription("Ужасы");

        Category category3 = new Category();
        category3.setName("Фантастика");
        category3.setDescription("Фантастика");

        Category category4 = new Category();
        category4.setName("Боевик");
        category4.setDescription("Боевик");

        categoryRepository.save(category);
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);

    }
}
