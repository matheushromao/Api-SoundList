package com.api_soundlist.SoundList.dto;

import java.util.List;

public record PlaylistResponseDto(Long id,
                                  String name,
                                  String description,
                                  List<MusicaResponseDto> musicas) {
}
