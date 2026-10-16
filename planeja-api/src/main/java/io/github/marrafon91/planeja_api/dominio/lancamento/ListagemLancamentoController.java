package io.github.marrafon91.planeja_api.dominio.lancamento;

import io.github.marrafon91.planeja_api.dominio.lancamento.dto.LancamentoDetalhes;
import io.github.marrafon91.planeja_api.dominio.lancamento.model.TipoLancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@RestController
@RequestMapping("/lancamentos")
public class ListagemLancamentoController {

    @Autowired
    private LancamentoService service;

    @GetMapping
    public Page<LancamentoDetalhes> listar(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "categoriaId", required = false) UUID categoriaId
    ) {
        var pageRequest = PageRequest.of(page, size);
        var mesAno = parseMes(mes);
        var tipoLancamentoSelecionado = parseTipo(tipo);

        return service.listar(pageRequest, mesAno, tipoLancamentoSelecionado, categoriaId);
    }

    private TipoLancamento parseTipo(String tipo) {
        if (tipo == null || tipo.isEmpty()) {
            return null;
        }

        try {
            return TipoLancamento.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "Parametro 'tipo' invalido, valores aceitos: 'RECEITA' ou 'DESPESA'.");
        }
    }

    private YearMonth parseMes(String mes) {
        if (mes == null || mes.isEmpty()) {
            return null;
        }

        try {
            return YearMonth.parse(mes);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "Parametro 'mes' invalido, use o formato yyyy-MM.");
        }
    }
}
