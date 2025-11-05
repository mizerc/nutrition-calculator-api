package dev.mauricioize.nutrition_calculator_api.shared.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.mauricioize.nutrition_calculator_api.shared.exception.dto.ValidationErrorResponseDto;

@RestControllerAdvice
public class ValidationExceptionHandler {
    // Handles validation errors from @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationErrorResponseDto response = new ValidationErrorResponseDto(
                "Field validation failed",
                errors);

        // badRequest = status 400
        return ResponseEntity.badRequest().body(response);
    }
}
