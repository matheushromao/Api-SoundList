package com.api_soundlist.SoundList.service;

import com.api_soundlist.SoundList.dto.MusicaRequestDto;
import com.api_soundlist.SoundList.dto.MusicaResponseDto;
import com.api_soundlist.SoundList.mapper.MusicaMapper;
import com.api_soundlist.SoundList.model.Musica;
import com.api_soundlist.SoundList.model.Playlist;
import com.api_soundlist.SoundList.repository.MusicaRepository;
import com.api_soundlist.SoundList.repository.PlaylistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    public Page<MusicaResponseDto> findAll(Pageable pageable){
        return musicaRepository.findAll(pageable).map(MusicaMapper::ToDto);
    }
    
    public MusicaResponseDto findById(Long id){
        var musica = musicaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Música não encontrada!"));
        return MusicaMapper.ToDto(musica);
    }

    public MusicaResponseDto save (MusicaRequestDto musicaRequestDto){
        var musica = MusicaMapper.ToEntity(musicaRequestDto);
        musica.setPlaylist(findPlaylist(musicaRequestDto.playlistId()));
        return MusicaMapper.ToDto(musicaRepository.save(musica));
    }

    public MusicaResponseDto update(Long id, MusicaRequestDto musicaRequestDto){
        Musica musica = musicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Música não encontrada!"));
        musica.setTitle(musicaRequestDto.title());
        musica.setArtist(musicaRequestDto.artist());
        musica.setGenre(musicaRequestDto.genre());
        musica.setDuration(musicaRequestDto.duration());
        musica.setPlaylist(findPlaylist(musicaRequestDto.playlistId()));

        return MusicaMapper.ToDto(musicaRepository.save(musica));
    }

    private Playlist findPlaylist(Long playlistId){
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new EntityNotFoundException("Playlist não encontrada!"));
    }

    public void deleteById(Long id){
        musicaRepository.deleteById(id);
    }
}
