package com.api_soundlist.SoundList.controller;

import com.api_soundlist.SoundList.dto.PlaylistRequestDto;
import com.api_soundlist.SoundList.dto.PlaylistResponseDto;
import com.api_soundlist.SoundList.service.PlaylistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<Page<PlaylistResponseDto>> findAll(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC)
                                                                 Pageable pageable) {
        return ResponseEntity.ok(playlistService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponseDto> findById(@PathVariable Long id) {
        PlaylistResponseDto playlistResponseDto = playlistService.findById(id);
        return ResponseEntity.ok(playlistResponseDto);
    }

    @PostMapping
    public ResponseEntity<PlaylistResponseDto> save(@Valid @RequestBody PlaylistRequestDto playlistRequestDto) {
        var playlistCreate = playlistService.save(playlistRequestDto);
        return ResponseEntity.ok(playlistCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PlaylistRequestDto playlistRequestDto) {
        PlaylistResponseDto playlistResponseDto = playlistService.update(id, playlistRequestDto);
        return ResponseEntity.ok(playlistResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
