package dev.mauricioize.nutrition_calculator_api.modules.auth.dto;

import jakarta.validation.constraints.NotNull;

public record SigninRequestDto(
        @NotNull String email, @NotNull String password) {
}
