package io.github.marrafon91.planeja_api.dominio.cartao;

import io.github.marrafon91.planeja_api.common.exceptions.RegistroNaoEncontradoException;
import io.github.marrafon91.planeja_api.common.exceptions.ValidationException;
import io.github.marrafon91.planeja_api.dominio.cartao.dto.CartaoDetalhes;
import io.github.marrafon91.planeja_api.dominio.cartao.dto.CartaoForm;
import io.github.marrafon91.planeja_api.dominio.cartao.mapper.CartaoMapper;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CartaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Autowired
    private CartaoValidator validator;

    @Autowired
    private CartaoMapper mapper;

    public CartaoDetalhes criar(CartaoForm form) {
        var result = validator.validar(form);

        if (result.isInvalido()) {
            throw new ValidationException(result.getCamposInvalidos());
        }

        CartaoEntity entity = mapper.toEntity(form);
        repository.save(entity);
        return mapper.toDetalhes(entity);
    }

    public CartaoDetalhes obterDetalhes(UUID id) {
        return repository.findById(id)
                .map(mapper::toDetalhes)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Registro não encontrado"));
    }
}
