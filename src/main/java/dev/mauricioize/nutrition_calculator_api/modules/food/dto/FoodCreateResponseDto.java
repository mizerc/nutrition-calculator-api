package dev.mauricioize.nutrition_calculator_api.modules.food.dto;

import java.time.LocalDate;
import java.util.UUID;

public record FoodCreateResponseDto(
                UUID id,
                String name,
                LocalDate createdAt) {
}