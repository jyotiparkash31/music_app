package com.musicapp.music_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.musicapp.music_app_backend.model")
@EnableJpaRepositories("com.musicapp.music_app_backend.repository")
public class MusicAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicAppBackendApplication.class, args);
    }
}
