package com.musicapp.music_app_backend.controller;

import com.musicapp.music_app_backend.model.PlaybackState;
import com.musicapp.music_app_backend.model.PlaybackStatus;
import com.musicapp.music_app_backend.service.PlaybackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/playback")
public class PlaybackController {

    @Autowired
    private PlaybackService playbackService;
    
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping("/play")
    public ResponseEntity<PlaybackState> playSong(@RequestBody Map<String, Object> payload) {
        Long songId = ((Number)payload.get("songId")).longValue();
        int position = (int)payload.getOrDefault("position", 0);
        PlaybackState state = playbackService.updatePlaybackState(getCurrentUsername(), songId, PlaybackStatus.PLAYING, position);
        return ResponseEntity.ok(state);
    }
    
    @PostMapping("/pause")
    public ResponseEntity<PlaybackState> pauseSong(@RequestBody Map<String, Integer> payload) {
        int position = payload.getOrDefault("position", 0);
        PlaybackState state = playbackService.updatePlaybackState(getCurrentUsername(), null, PlaybackStatus.PAUSED, position);
        return ResponseEntity.ok(state);
    }
    
    @PostMapping("/resume")
    public ResponseEntity<PlaybackState> resumeSong() {
        PlaybackState currentState = playbackService.getCurrentPlaybackState(getCurrentUsername());
        if (currentState == null || currentState.getStatus() != PlaybackStatus.PAUSED) {
            return ResponseEntity.badRequest().body(null); // Or handle error appropriately
        }
        PlaybackState state = playbackService.updatePlaybackState(getCurrentUsername(), null, PlaybackStatus.PLAYING, currentState.getPositionInSeconds());
        return ResponseEntity.ok(state);
    }

    @PostMapping("/stop")
    public ResponseEntity<PlaybackState> stopSong() {
        PlaybackState state = playbackService.updatePlaybackState(getCurrentUsername(), null, PlaybackStatus.STOPPED, 0);
        return ResponseEntity.ok(state);
    }

    @GetMapping("/current")
    public ResponseEntity<PlaybackState> getCurrentSong() {
        PlaybackState state = playbackService.getCurrentPlaybackState(getCurrentUsername());
        return ResponseEntity.ok(state);
    }
}