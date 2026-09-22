package io.github.marrafon91.planeja_api.dominio.cartao.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_cartao")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, name = "nome", length = 30)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "bandeira")
    private BandeiraCartao bandeira;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }
}
