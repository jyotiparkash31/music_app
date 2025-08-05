package com.musicapp.music_app_backend.repository;

import com.musicapp.music_app_backend.model.PlaybackState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaybackStateRepository extends JpaRepository<PlaybackState, Long> {}