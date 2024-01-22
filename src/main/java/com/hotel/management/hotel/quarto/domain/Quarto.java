package com.hotel.management.hotel.quarto.domain;

import java.sql.Date;
import java.util.UUID;
import java.util.function.BooleanSupplier;

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
    private Date data;

    @Column
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Builder
    public Quarto(Tipo tipo, Integer capacidade, String descricao, Double precoPorNoite, Date data) {
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

    public void ocuparQuarto() {
        this.estado = Estado.OCUPADO;
    }

    public void desocuparQuarto() {
        this.estado = Estado.DESOCUPADO;
    }

    public enum Tipo {
        SUITE, STANDARD, LUXO
    }

    public enum Estado {
        OCUPADO,
        DESOCUPADO, RESERVADO, SELECIONADO;
    }

    public enum Datas {
        INDISPONIVEL,
        DISPONIVEL;
    }

    public BooleanSupplier isPresent() {
        throw new UnsupportedOperationException("Unimplemented method 'isPresent'");
    }

    private Object quartoSelecionado() {
        throw new UnsupportedOperationException("Unimplemented method 'quartoSelecionado'");
    }
}
