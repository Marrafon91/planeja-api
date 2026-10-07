package io.github.marrafon91.planeja_api.dominio.lancamento.mapper;

import io.github.marrafon91.planeja_api.dominio.cartao.model.CartaoEntity;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CategoriaEntity;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoDetalhes;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoForm;
import io.github.marrafon91.planeja_api.dominio.lancamento.model.LancamentoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LancamentoMapper {
    LancamentoEntity toEntity(LancamentoForm form, CategoriaEntity categoria, CartaoEntity cartao);

    LancamentoDetalhes toDetalhes(LancamentoEntity entity);
}
