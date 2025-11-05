package dev.mauricioize.nutrition_calculator_api.modules.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserSignupRequestDto(
                @NotNull String name,
                @NotNull String email,
                @NotNull String password) {

}
