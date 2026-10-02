package io.github.marrafon91.planeja_api.dominio.categoria;

import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaDetalhes;
import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaForm;

public interface CategoriaMapper {
    CategoriaEntity toEntity(CategoriaForm nova);

    CategoriaDetalhes toDetalhes(CategoriaEntity entity);
}
