package io.github.marrafon91.planeja_api.common.exceptions;

import io.github.marrafon91.planeja_api.common.validation.CampoInvalido;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final List<CampoInvalido> campoInvalidos;

    public ValidationException(List<CampoInvalido> campoInvalidos) {
        super("Formulário inválido");
        this.campoInvalidos = campoInvalidos;
    }
}
