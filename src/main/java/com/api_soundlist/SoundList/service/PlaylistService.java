package com.api_soundlist.SoundList.service;

import com.api_soundlist.SoundList.dto.PlaylistRequestDto;
import com.api_soundlist.SoundList.dto.PlaylistResponseDto;
import com.api_soundlist.SoundList.mapper.PlaylistMapper;
import com.api_soundlist.SoundList.model.Playlist;
import com.api_soundlist.SoundList.repository.PlaylistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public Page<PlaylistResponseDto> findAll(Pageable pageable){
        return playlistRepository.findAll(pageable).map(PlaylistMapper::ToDto);
    }

    public PlaylistResponseDto findById(Long id){
        var playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist não encontrada!"));
        return PlaylistMapper.ToDto(playlist);
    }

    public PlaylistResponseDto save(PlaylistRequestDto playlistRequestDto){
        var playlist = PlaylistMapper.ToEntity(playlistRequestDto);
        return PlaylistMapper.ToDto(playlistRepository.save(playlist));
    }

    public PlaylistResponseDto update(Long id, PlaylistRequestDto playlistRequestDto){
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist não encontrada!"));
        playlist.setName(playlistRequestDto.name());
        playlist.setDescription(playlistRequestDto.description());

        return PlaylistMapper.ToDto(playlistRepository.save(playlist));
    }

    public void deleteById(Long id){
        playlistRepository.deleteById(id);
    }
}
