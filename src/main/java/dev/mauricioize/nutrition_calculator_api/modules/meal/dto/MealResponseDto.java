package dev.mauricioize.nutrition_calculator_api.modules.meal.dto;

import java.util.List;

public record MealResponseDto(long id,
                String name,
                List<MealItemResponseDto> mealItems,
                Integer totalKcals) {
}
