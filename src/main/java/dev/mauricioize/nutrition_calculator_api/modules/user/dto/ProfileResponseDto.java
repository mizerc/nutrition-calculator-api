package dev.mauricioize.nutrition_calculator_api.modules.user.dto;

public record ProfileResponseDto(
        Long id,
        String name,
        String avatarUrl) {
}