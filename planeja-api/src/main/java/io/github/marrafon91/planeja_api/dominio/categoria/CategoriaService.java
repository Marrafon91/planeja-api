package io.github.marrafon91.planeja_api.dominio.categoria;

import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaDetalhes;
import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoriaService {

    public CategoriaDetalhes criar(CategoriaForm nova) {
        return null;
    }

    public Page<CategoriaDetalhes> listar(PageRequest pageRequest) {
        return null;
    }

    public void mudarStatus(UUID id) {
    }
}
