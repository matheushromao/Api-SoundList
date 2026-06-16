package com.api_soundlist.SoundList.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MusicaRequestDto(@NotBlank(message = "O título não pode ser vazio!") String title,
                               @NotBlank(message = "O nome do artista não pode ser vazio!") String artist,
                               @NotBlank(message = "O gênero não pode ser vazio!") String genre,
                               @NotNull(message = "Duração não deve ser nula!") @Positive (message = "Duração deve ser maior que zero") Integer duration,
                               @NotNull(message = "A playlist da música é obrigatória!") Long playlistId) {
}
