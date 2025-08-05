package com.musicapp.music_app_backend.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}