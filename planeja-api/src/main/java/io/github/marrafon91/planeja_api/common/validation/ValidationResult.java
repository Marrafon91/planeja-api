package io.github.marrafon91.planeja_api.common.validation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationResult {

    private final List<CampoInvalido> camposInvalidos;

    private ValidationResult(List<CampoInvalido> camposInvalidos) {
        this.camposInvalidos = camposInvalidos;
    }

    public static ValidationResult novo() {
        return new ValidationResult(new ArrayList<>());
    }

    public void add(CampoInvalido campoInvalido) {
        this.camposInvalidos.add(campoInvalido);
    }

    public boolean isInvalido() {
        return !camposInvalidos.isEmpty();
    }
}
