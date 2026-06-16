package com.api_soundlist.SoundList.dto;

public record MusicaResponseDto(Long id,
                                String title,
                                String artist,
                                String genre,
                                Integer duration,
                                Long playlistId) {
}
