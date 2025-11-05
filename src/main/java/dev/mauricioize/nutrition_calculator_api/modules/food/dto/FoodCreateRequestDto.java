package dev.mauricioize.nutrition_calculator_api.modules.food.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FoodCreateRequestDto(
        @NotBlank String name,
        @NotNull @Positive Integer portionSizeGrams,
        @NotNull @Positive Integer kcals) {
}