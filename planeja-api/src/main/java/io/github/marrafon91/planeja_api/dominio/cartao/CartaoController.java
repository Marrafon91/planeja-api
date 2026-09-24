package io.github.marrafon91.planeja_api.dominio.cartao;

import io.github.marrafon91.planeja_api.dominio.cartao.dto.CartaoDetalhes;
import io.github.marrafon91.planeja_api.dominio.cartao.dto.CartaoForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(value = "cartoes")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @PostMapping
    public ResponseEntity<CartaoDetalhes> criar(@Valid @RequestBody CartaoForm novo) {
        CartaoDetalhes detalhes = service.criar(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoDetalhes> obterDetalhes(@PathVariable("id") UUID id) {
        CartaoDetalhes result = service.obterDetalhes(id);
        return ResponseEntity.ok(result);
    }
}
