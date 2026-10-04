package io.github.marrafon91.planeja_api.dominio.categoria;

import io.github.marrafon91.planeja_api.common.exceptions.RegistroNaoEncontradoException;
import io.github.marrafon91.planeja_api.common.exceptions.ValidationException;
import io.github.marrafon91.planeja_api.common.validation.ValidationResult;
import io.github.marrafon91.planeja_api.dominio.cartao.mapper.CategoriaMapper;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CategoriaEntity;
import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaDetalhes;
import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    CategoriaValidator validator;

    @Autowired
    CategoriaMapper mapper;

    @Autowired
    CategoriaRepository repository;

    @Transactional
    public CategoriaDetalhes criar(CategoriaForm nova) {
        ValidationResult result = validator.validar(nova);

        if (result.isInvalido()) {
            throw  new ValidationException(result.getCamposInvalidos());
        }

        CategoriaEntity entity = mapper.toEntity(nova);
        entity = repository.save(entity);

        return mapper.toDetalhes(entity);
    }

    @Transactional(readOnly = true)
    public Page<CategoriaDetalhes> listar(PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(mapper::toDetalhes);
    }

    @Transactional
    public void mudarStatus(UUID id) {
        CategoriaEntity result = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Categoria não encontrada"));

        result.setAtivo(!result.getAtivo());
        repository.save(result);
    }
}
