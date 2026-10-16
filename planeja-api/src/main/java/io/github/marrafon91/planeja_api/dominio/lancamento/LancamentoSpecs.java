package io.github.marrafon91.planeja_api.dominio.lancamento;

import io.github.marrafon91.planeja_api.dominio.lancamento.model.LancamentoEntity;
import io.github.marrafon91.planeja_api.dominio.lancamento.model.TipoLancamento;
import org.springframework.data.jpa.domain.Specification;

public class LancamentoSpecs {

    private LancamentoSpecs() {
    }

    ;

    public static Specification<LancamentoEntity> tipoEqual(TipoLancamento tipo) {
        return (root, query, cb) -> {

            return cb.equal(root.get("tipo"), tipo);
        };
    }
}

