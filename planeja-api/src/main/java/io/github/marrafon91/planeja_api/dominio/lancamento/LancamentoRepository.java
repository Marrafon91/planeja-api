package io.github.marrafon91.planeja_api.dominio.lancamento;

import io.github.marrafon91.planeja_api.dominio.dashboard.dto.DespesaCategoriaResumo;
import io.github.marrafon91.planeja_api.dominio.lancamento.model.LancamentoEntity;
import io.github.marrafon91.planeja_api.dominio.lancamento.model.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import java.util.UUID;

public interface LancamentoRepository extends JpaRepository<LancamentoEntity, UUID>,
        JpaSpecificationExecutor<LancamentoEntity> {

    @Query("""
            SELECT SUM(l.valor)
                        FROM LancamentoEntity l
                        WHERE l.tipo = :tipo
                        AND l.data BETWEEN :inicio AND :fim
            """)
    BigDecimal somaPorTipoNoPeriodo(
            @Param("tipo") TipoLancamento tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );

    @Query("""
                SELECT new io.github.marrafon91.planeja_api.dominio.dashboard.dto.DespesaCategoriaResumo(c.nome, SUM(l.valor))
                FROM LancamentoEntity l
                JOIN l.categoria c
                WHERE l.tipo = :tipo
                AND l.data BETWEEN :inicio AND :fim
                GROUP BY c.nome
                ORDER BY SUM(l.valor) DESC
            """)
    List<DespesaCategoriaResumo> somaPorCategoriaNoPeriodo(
            @Param("tipo") TipoLancamento tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );
}
