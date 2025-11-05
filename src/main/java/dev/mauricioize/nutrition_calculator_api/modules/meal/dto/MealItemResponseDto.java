package dev.mauricioize.nutrition_calculator_api.modules.meal.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MealItemResponseDto(
                @NotNull long foodId,
                @Positive double quantityGrams) {
}