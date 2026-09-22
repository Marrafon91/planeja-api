package io.github.marrafon91.planeja_api.dominio.cartao.dto;

import io.github.marrafon91.planeja_api.dominio.cartao.model.BandeiraCartao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CartaoForm(
        @NotBlank(message = "Nome do cartão não pode ser vazio")
        String nome,
        @NotNull(message = "Bandeira do cartão não pode ser nula")
        BandeiraCartao bandeira
) {
}
