package dev.mauricioize.nutrition_calculator_api.modules.auth.dto;

public record AuthResponseDto(
        Long id,
        String name,
        String email,
        String token) {
}