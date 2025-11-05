package dev.mauricioize.nutrition_calculator_api.modules.meal.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record MealCreateRequestDto(
        @NotBlank String name,
        @NotEmpty @Valid List<MealItemResponseDto> mealItems) {
}