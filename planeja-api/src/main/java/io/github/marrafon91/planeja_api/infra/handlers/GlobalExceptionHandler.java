package io.github.marrafon91.planeja_api.infra.handlers;

import io.github.marrafon91.planeja_api.common.exceptions.ValidationException;
import io.github.marrafon91.planeja_api.common.validation.CampoInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        var status = HttpStatus.UNPROCESSABLE_CONTENT;
        var body = Map.of("timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "camposInvalidos", e.getCampoInvalidos()
        );
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var campoInvalidos = e.getFieldErrors()
                .stream()
                .map(fieldError ->
                        new CampoInvalido(fieldError.getField(),
                                fieldError.getDefaultMessage()))
                .toList();

        var status = HttpStatus.UNPROCESSABLE_CONTENT;
        var body = Map.of("timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "camposInvalidos", campoInvalidos
        );
        return ResponseEntity.status(status).body(body);
    }
}
