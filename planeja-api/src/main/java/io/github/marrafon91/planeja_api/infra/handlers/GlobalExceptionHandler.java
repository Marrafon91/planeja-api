package io.github.marrafon91.planeja_api.infra.handlers;

import io.github.marrafon91.planeja_api.common.exceptions.RegistroNaoEncontradoException;
import io.github.marrafon91.planeja_api.common.exceptions.ValidationException;
import io.github.marrafon91.planeja_api.common.validation.CampoInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tools.jackson.databind.exc.InvalidFormatException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<?> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String mensagem = String.format(
                "O parâmetro '%s' deve ser um valor do tipo %s. Valor recebido: '%s'",
                e.getName(),
                e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "esperado",
                e.getValue()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", mensagem
        ));
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        Throwable cause = e.getCause();

        if (cause instanceof InvalidFormatException ife && ife.getTargetType() != null && ife.getTargetType().isEnum()) {

            String campo = extrairNomeCampo(ife.getMessage());
            Object[] valoresValidos = ife.getTargetType().getEnumConstants();

            String mensagem = String.format(
                    "Valor inválido para o campo '%s'. Valores aceitos: %s",
                    campo, Arrays.toString(valoresValidos)
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", HttpStatus.BAD_REQUEST.value(),
                    "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    "message", mensagem
            ));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", "Corpo da requisição inválido ou malformado"
        ));
    }

    private String extrairNomeCampo(String mensagemOriginal) {
        if (mensagemOriginal == null) {
            return "desconhecido";
        }
        // procura o último trecho entre aspas dentro de colchetes, ex: ["bandeira"]
        Matcher matcher = Pattern.compile("\\[\"([^\"]+)\"]").matcher(mensagemOriginal);
        String ultimoCampo = "desconhecido";
        while (matcher.find()) {
            ultimoCampo = matcher.group(1);
        }
        return ultimoCampo;
    }
}