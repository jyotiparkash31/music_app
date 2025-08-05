package com.musicapp.music_app_backend.service;

import com.musicapp.music_app_backend.model.PlaybackState;
import com.musicapp.music_app_backend.model.PlaybackStatus;
import com.musicapp.music_app_backend.model.Song;
import com.musicapp.music_app_backend.model.User;
import com.musicapp.music_app_backend.model.*;
import com.musicapp.music_app_backend.repository.PlaybackStateRepository;
import com.musicapp.music_app_backend.repository.SongRepository;
import com.musicapp.music_app_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlaybackService {

    @Autowired
    private PlaybackStateRepository playbackStateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;

    public PlaybackState updatePlaybackState(String username, Long songId, PlaybackStatus status, int position) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Song song = (songId != null) ? songRepository.findById(songId).orElse(null) : null;
        
        PlaybackState state = playbackStateRepository.findById(user.getId()).orElse(new PlaybackState());
        state.setUserId(user.getId());
        
        if(song != null) {
            state.setCurrentSong(song);
        }
        state.setStatus(status);
        state.setPositionInSeconds(position);

        return playbackStateRepository.save(state);
    }

    public PlaybackState getCurrentPlaybackState(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return playbackStateRepository.findById(user.getId()).orElse(null);
    }
}