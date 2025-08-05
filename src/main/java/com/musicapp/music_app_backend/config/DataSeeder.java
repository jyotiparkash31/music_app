package com.musicapp.music_app_backend.config;

import com.musicapp.music_app_backend.model.Role;
import com.musicapp.music_app_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.musicapp.music_app_backend.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Will Create Admin user if not exists
        if (!userRepository.findByUsername("admin").isPresent()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@music.com");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);
        }

        // Will Create a regular user if not exists
        if (!userRepository.findByUsername("user").isPresent()) {
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@music.com");
            user.setPassword(passwordEncoder.encode("userpass"));
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
        }
    }
}