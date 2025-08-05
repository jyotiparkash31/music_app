package com.musicapp.music_app_backend.service;

import com.musicapp.music_app_backend.model.Playlist;
import com.musicapp.music_app_backend.model.Song;
import com.musicapp.music_app_backend.model.User;
import com.musicapp.music_app_backend.repository.PlaylistRepository;
import com.musicapp.music_app_backend.repository.SongRepository;
import com.musicapp.music_app_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;

    public Playlist createPlaylist(String name, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setUser(user);
        return playlistRepository.save(playlist);
    }

    public List<Playlist> getUserPlaylists(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return playlistRepository.findByUserId(user.getId());
    }

    public Playlist addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));
        playlist.getSongs().add(song);
        return playlistRepository.save(playlist);
    }

    public Playlist removeSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));
        playlist.getSongs().remove(song);
        return playlistRepository.save(playlist);
    }
}