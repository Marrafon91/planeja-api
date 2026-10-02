package io.github.marrafon91.planeja_api.dominio.categoria;

import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaDetalhes;
import io.github.marrafon91.planeja_api.dominio.categoria.dto.CategoriaForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "categoria")
@CrossOrigin("http://localhost:4200/")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaDetalhes> criar(@RequestBody @Valid CategoriaForm nova) {
        CategoriaDetalhes detalhes = service.criar(nova);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping
    public Page<CategoriaDetalhes> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return service.listar(pageRequest);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable UUID id) {

        service.mudarStatus(id);
        return ResponseEntity.noContent().build();
    }

}
