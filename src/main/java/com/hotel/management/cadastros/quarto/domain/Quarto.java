package com.hotel.management.cadastros.quarto.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public final class Quarto {
    @Id
    private UUID id;

    @Column
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column
    private Integer capacidade;

    @Column
    private String descricao;

    @Column
    private Double precoPorNoite;

    @Column
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Builder
    public Quarto(Tipo tipo, Integer capacidade, String descricao, Double precoPorNoite) {
        this.id = UUID.randomUUID();
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.descricao = descricao;
        this.precoPorNoite = precoPorNoite;
        this.estado = Estado.DESOCUPADO;
        
    }

    public QuartoForm update() {
        return new QuartoForm((form) -> {
            this.tipo = form.getTipo();
            this.capacidade = form.getCapacidade();
            this.descricao = form.getDescricao();
            this.precoPorNoite = form.getPrecoPorNoite();
            this.estado = form.getEstado();
        });
    }

    public enum Tipo {
        SUITE, STANDARD, LUXO
    }

    public enum Estado {
        OCUPADO, DESOCUPADO;
    }
}
