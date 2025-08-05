package com.musicapp.music_app_backend.controller;

import com.musicapp.music_app_backend.model.Playlist;
import com.musicapp.music_app_backend.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    
    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Map<String, String> payload) {
        String playlistName = payload.get("name");
        Playlist playlist = playlistService.createPlaylist(playlistName, getCurrentUsername());
        return ResponseEntity.ok(playlist);
    }
    
    @GetMapping
    public ResponseEntity<List<Playlist>> getUserPlaylists() {
        List<Playlist> playlists = playlistService.getUserPlaylists(getCurrentUsername());
        return ResponseEntity.ok(playlists);
    }
    
    @PostMapping("/{playlistId}/songs")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable Long playlistId, @RequestBody Map<String, Long> payload) {
        Long songId = payload.get("songId");
        Playlist updatedPlaylist = playlistService.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.ok(updatedPlaylist);
    }
    
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Playlist> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        Playlist updatedPlaylist = playlistService.removeSongFromPlaylist(playlistId, songId);
        return ResponseEntity.ok(updatedPlaylist);
    }
}