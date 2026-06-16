package com.api_soundlist.SoundList.mapper;

import com.api_soundlist.SoundList.dto.MusicaResponseDto;
import com.api_soundlist.SoundList.dto.PlaylistRequestDto;
import com.api_soundlist.SoundList.dto.PlaylistResponseDto;
import com.api_soundlist.SoundList.model.Playlist;

import java.util.Collections;
import java.util.List;

public class PlaylistMapper {

    public static Playlist ToEntity(PlaylistRequestDto playlistRequestDto){
        var playlist = new Playlist();
        playlist.setName(playlistRequestDto.name());
        playlist.setDescription(playlistRequestDto.description());
        return playlist;
    }

    public static PlaylistResponseDto ToDto(Playlist playlist){
        List<MusicaResponseDto> musicas = playlist.getMusica() == null
                ? Collections.emptyList()
                : playlist.getMusica().stream().map(MusicaMapper::ToDto).toList();

        return new PlaylistResponseDto(
                playlist.getId(),
                playlist.getName(),
                playlist.getDescription(),
                musicas
        );
    }
}
