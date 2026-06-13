package com.api_soundlist.SoundList.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MusicaRequestDto(@NotBlank(message = "O título não pode ser vazio!") String title,
                               @NotBlank(message = "O nome do artista não pode ser vazio!") String artist,
                               @NotNull) {
}
