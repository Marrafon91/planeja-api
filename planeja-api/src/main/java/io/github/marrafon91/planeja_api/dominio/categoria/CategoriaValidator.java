package io.github.marrafon91.planeja_api.dominio.categoria;

import io.github.marrafon91.planeja_api.common.validation.CampoInvalido;
import io.github.marrafon91.planeja_api.common.validation.ValidationResult;
import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidator {

    @Autowired
    private CategoriaRepository repository;


    public ValidationResult validar(CategoriaForm nova) {

        var result = ValidationResult.novo();

        var existeCategoriaCadastrada = repository.existsByNome(nova.nome());

        if (existeCategoriaCadastrada) {
            result.add(new CampoInvalido("nome", "Categoria já cadastrada"));
        }
        return  result;
    }
}
