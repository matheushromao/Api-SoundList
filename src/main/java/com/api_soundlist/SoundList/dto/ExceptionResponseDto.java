package com.api_soundlist.SoundList.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ExceptionResponseDto(String status,
                                   Map<String, String> errors,
                                   LocalDateTime localDateTime) {
}
