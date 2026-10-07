package io.github.marrafon91.planeja_api.dominio.lancamento;

import io.github.marrafon91.planeja_api.common.validation.ValidationResult;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CartaoEntity;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CategoriaEntity;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoForm;
import org.springframework.stereotype.Component;

@Component
public class LancamentoValidator {

    public ValidationResult validar(LancamentoForm form, CategoriaEntity categoria, CartaoEntity cartao) {
        return  ValidationResult.novo();
    }
}
