package com.musicapp.music_app_backend.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class PlaybackState {
    @Id
    private Long userId; // One-to-one with User ID

    @OneToOne
    @JoinColumn(name = "song_id")
    private Song currentSong;

    @Enumerated(EnumType.STRING)
    private PlaybackStatus status;

    private int positionInSeconds;
}