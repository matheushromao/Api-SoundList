package com.api_soundlist.SoundList.dto;

import jakarta.validation.constraints.NotBlank;

public record PlaylistRequestDto(@NotBlank(message = "O nome da playlist é obrigatório!" ) String name,
                                 String description) {
}
