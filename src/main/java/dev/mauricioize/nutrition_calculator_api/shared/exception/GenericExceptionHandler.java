package dev.mauricioize.nutrition_calculator_api.shared.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

    // Handles general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // internalServerError = status 500
        return ResponseEntity.internalServerError()
                .body("A generic error occurred: " + ex.getMessage());
    }
}
