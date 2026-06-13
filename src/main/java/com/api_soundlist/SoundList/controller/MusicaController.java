package com.api_soundlist.SoundList.controller;

import com.api_soundlist.SoundList.dto.MusicaRequestDto;
import com.api_soundlist.SoundList.dto.MusicaResponseDto;
import com.api_soundlist.SoundList.service.MusicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping
    public ResponseEntity<Page<MusicaResponseDto>> findAll(@PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC)
                                                               Pageable pageable) {
        return ResponseEntity.ok(musicaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaResponseDto> findById(@PathVariable Long id) {
        MusicaResponseDto musicaResponseDto = musicaService.findById(id);
        return ResponseEntity.ok(musicaResponseDto);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MusicaRequestDto musicaRequestDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (var error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        var MusicaCreate = musicaService.save(musicaRequestDto);
        return ResponseEntity.ok(MusicaCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody MusicaRequestDto musicaRequestDto) {
        MusicaResponseDto musicaResponseDto = musicaService.update(id, musicaRequestDto);
        return ResponseEntity.ok(musicaResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        musicaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
