package io.github.marrafon91.planeja_api.dominio.dashboard;

import io.github.marrafon91.planeja_api.dominio.dashboard.dto.Dashboard;
import io.github.marrafon91.planeja_api.dominio.dashboard.dto.DespesaCategoriaResumo;
import io.github.marrafon91.planeja_api.dominio.dashboard.dto.ResumoFinanceiro;
import io.github.marrafon91.planeja_api.dominio.lancamento.LancamentoRepository;
import io.github.marrafon91.planeja_api.dominio.lancamento.model.TipoLancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Dashboard obterDashboardMesAtual() {
        YearMonth mesAtual = YearMonth.now();
        LocalDate primeiroDiaMes = mesAtual.atDay(1);
        LocalDate ultimoDia = mesAtual.atEndOfMonth();

        BigDecimal receitas = lancamentoRepository
                .somaPorTipoNoPeriodo(TipoLancamento.RECEITA, primeiroDiaMes, ultimoDia);

        BigDecimal despesas = lancamentoRepository
                .somaPorTipoNoPeriodo(TipoLancamento.DESPESA, primeiroDiaMes, ultimoDia);
        
        BigDecimal saldo = receitas.subtract(despesas);

        ResumoFinanceiro resumoFinanceiro = new ResumoFinanceiro(receitas, despesas, saldo);

        List<DespesaCategoriaResumo> despesasPorCategoria = null;


        return new Dashboard(mesAtual.toString(), resumoFinanceiro, despesasPorCategoria);
    }
}
