package io.github.marrafon91.planeja_api.dominio.cartao.dto;

import io.github.marrafon91.planeja_api.dominio.cartao.model.BandeiraCartao;

public record CartaoForm(
        String nome,
        BandeiraCartao bandeira
) {
}
