package io.github.marrafon91.planeja_api.dominio.lancamento;

import io.github.marrafon91.planeja_api.common.validation.CampoInvalido;
import io.github.marrafon91.planeja_api.common.validation.ValidationResult;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CartaoEntity;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CategoriaEntity;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoForm;
import io.github.marrafon91.planeja_api.dominio.lancamento.model.TipoLancamento;
import org.springframework.stereotype.Component;

@Component
public class LancamentoValidator {

    public ValidationResult validar(LancamentoForm form, CategoriaEntity categoria, CartaoEntity cartao) {
        var result = ValidationResult.novo();

        if (categoria == null) {
            result.add(new CampoInvalido("categoriaId", "Categoria não encontrada"));
        } else if (Boolean.FALSE.equals(categoria.getAtivo())) {
            result.add(new CampoInvalido("categoriaId", "Categoria inativa"));
        }

        if (form.tipo() == TipoLancamento.RECEITA && cartao != null) {
            result.add(new CampoInvalido("cartaoId", "Tipo de lançamento inválido"));
        }

        if (form.tipo() == TipoLancamento.DESPESA && form.categoriaId() != null) {
            if (cartao == null) {
                result.add(new CampoInvalido("cartaoId", "Cartão não encontrado"));
            } else if (Boolean.FALSE.equals(cartao.getAtivo())) {
                result.add(new CampoInvalido("cartaoId", "Cartão inativo"));
            }
        }
        return result;
    }
}
