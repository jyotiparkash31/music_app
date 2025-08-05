package com.musicapp.music_app_backend.controller;

import com.musicapp.music_app_backend.model.Song;
import com.musicapp.music_app_backend.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/search")
    public List<Song> searchSongs(@RequestParam String query) {
        return songService.searchSongs(query);
    }

    // Only an ADMIN can add a new song
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        Song newSong = songService.addSong(song);
        return ResponseEntity.ok(newSong);
    }
}