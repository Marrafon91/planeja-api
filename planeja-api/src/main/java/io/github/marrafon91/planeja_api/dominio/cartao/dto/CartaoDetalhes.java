package io.github.marrafon91.planeja_api.dominio.cartao.dto;

import io.github.marrafon91.planeja_api.dominio.cartao.model.BandeiraCartao;

import java.time.LocalDateTime;

public record CartaoDetalhes(
        String id,
        String nome,
        BandeiraCartao bandeira,
        LocalDateTime dataCadastro
) {
}
