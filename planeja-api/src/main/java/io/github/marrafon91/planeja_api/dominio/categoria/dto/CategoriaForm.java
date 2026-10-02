package io.github.marrafon91.planeja_api.dominio.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaForm(

        @NotBlank(message = "Campo obrigatório")
        String nome
) {
}
