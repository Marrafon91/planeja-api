package io.github.marrafon91.planeja_api.dominio.cartao;

import io.github.marrafon91.planeja_api.common.validation.CampoInvalido;
import io.github.marrafon91.planeja_api.common.validation.ValidationResult;
import io.github.marrafon91.planeja_api.dominio.cartao.dto.CartaoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartaoValidator {

    @Autowired
    private CartaoRepository repository;

    public ValidationResult validar(CartaoForm form) {
        var result = ValidationResult.novo();

        if (repository.findByNome(form.nome()).isPresent()) {
            result.add(new CampoInvalido("nome", "Nome do cartão já cadastrado"));
        }

        return  result;
    }
}
