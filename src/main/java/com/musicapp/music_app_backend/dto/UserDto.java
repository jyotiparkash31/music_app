package com.musicapp.music_app_backend.dto;

import com.musicapp.music_app_backend.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Role role;
}