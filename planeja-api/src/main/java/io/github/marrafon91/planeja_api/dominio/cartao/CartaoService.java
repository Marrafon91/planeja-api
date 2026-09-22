package io.github.marrafon91.planeja_api.dominio.cartao;

import io.github.marrafon91.planeja_api.dominio.cartao.dto.CartaoDetalhes;
import io.github.marrafon91.planeja_api.dominio.cartao.dto.CartaoForm;
import io.github.marrafon91.planeja_api.dominio.cartao.mapper.CartaoMapper;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CartaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Autowired
    private CartaoValidator validator;

    @Autowired
    private CartaoMapper mapper;

    public ResponseEntity<CartaoDetalhes> criar(CartaoForm form) {
    validator.validar(form);
    CartaoEntity entity = mapper.toEntity(form);
    repository.save(entity);
    return ResponseEntity.ok(mapper.toDetalhes(entity));
    }
}
