package dev.mauricioize.nutrition_calculator_api.shared.exception.dto;

import java.util.Map;

public record ValidationErrorResponseDto(
                String message,
                Map<String, String> errors) {
}