package dev.mauricioize.nutrition_calculator_api.modules.food.dto;

public record FoodResponseDto(
        long id,
        String name,
        Integer portionSizeGrams,
        Integer kcals) {
}
