package io.github.marrafon91.planeja_api.dominio.lancamento.mapper;

import io.github.marrafon91.planeja_api.dominio.cartao.model.CartaoEntity;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CategoriaEntity;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoDetalhes;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoForm;
import io.github.marrafon91.planeja_api.dominio.lancamento.model.LancamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LancamentoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipo", source = "form.tipo")
    @Mapping(target = "categoria", source = "categoria")
    @Mapping(target = "cartao", source = "cartao")
    LancamentoEntity toEntity(LancamentoForm form, CategoriaEntity categoria, CartaoEntity cartao);

    @Mapping(target = "categoriaId", source = "categoria.id")
    @Mapping(target = "categoriaNome", source = "categoria.nome")
    @Mapping(target = "cartaoId", source = "cartao.id")
    @Mapping(target = "cartaoNome", source = "cartao.nome")
    LancamentoDetalhes toDetalhes(LancamentoEntity entity);
}
