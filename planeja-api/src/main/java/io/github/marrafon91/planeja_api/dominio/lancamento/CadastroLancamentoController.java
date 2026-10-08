package io.github.marrafon91.planeja_api.dominio.lancamento;

import io.github.marrafon91.planeja_api.dominio.cartao.CartaoService;
import io.github.marrafon91.planeja_api.dominio.cartao.dto.CartaoDetalhes;
import io.github.marrafon91.planeja_api.dominio.categoria.CategoriaService;
import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaDetalhes;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoDetalhes;
import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
@CrossOrigin("http://localhost:4200")
public class CadastroLancamentoController {

    @Autowired
    private LancamentoService service;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<LancamentoDetalhes> criar(
            @RequestBody @Valid LancamentoForm form){
            var detalhes = service.criar(form);
            return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }
    @GetMapping("/categorias-disponiveis")
    public List<CategoriaDetalhes> listarCategoriasDisponiveis() {
        return categoriaService.listarAtivas();
    }

    @GetMapping("/cartoes-disponiveis")
    public List<CartaoDetalhes> listarCartoesDisponiveis() {
        return cartaoService.listarAtivos();
    }

}
