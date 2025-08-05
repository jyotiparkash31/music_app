package com.musicapp.music_app_backend.controller;

import com.musicapp.music_app_backend.dto.UserDto;
import com.musicapp.music_app_backend.model.User;
import com.musicapp.music_app_backend.repository.UserRepository;
import com.musicapp.music_app_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @GetMapping("/profile/{username}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        return ResponseEntity.ok(userService.convertToDto(user));
    }
}