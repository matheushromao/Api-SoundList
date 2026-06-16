package com.api_soundlist.SoundList.mapper;

import com.api_soundlist.SoundList.dto.MusicaRequestDto;
import com.api_soundlist.SoundList.dto.MusicaResponseDto;
import com.api_soundlist.SoundList.model.Musica;

public class MusicaMapper {

    public static Musica ToEntity(MusicaRequestDto musicaRequestDto){
        var musica = new Musica();
        musica.setTitle(musicaRequestDto.title());
        musica.setArtist(musicaRequestDto.artist());
        musica.setGenre(musicaRequestDto.genre());
        musica.setDuration(musicaRequestDto.duration());
        return musica;
    }

    public static MusicaResponseDto ToDto(Musica musica){
        return new MusicaResponseDto(
                musica.getId(),
                musica.getTitle(),
                musica.getArtist(),
                musica.getGenre(),
                musica.getDuration(),
                musica.getPlaylist() != null ? musica.getPlaylist().getId() : null
        );
    }
}
