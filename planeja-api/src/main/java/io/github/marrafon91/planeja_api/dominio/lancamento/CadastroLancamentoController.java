package io.github.marrafon91.planeja_api.dominio.lancamento;

import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoDetalhes;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lancamentos")
public class CadastroLancamentoController {

    @Autowired
    LancamentoService service;

    @PostMapping
    public ResponseEntity<LancamentoDetalhes> criar(
            @RequestBody @Valid LancamentoForm form){
            var detalhes = service.criar(form);
            return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }
}
