package com.hotel.management.hotel.quarto.domain;

import java.util.Date;
import java.util.function.Consumer;

import com.hotel.management.hotel.quarto.domain.Quarto.Estado;
import com.hotel.management.hotel.quarto.domain.Quarto.Tipo;

import lombok.Getter;

@Getter
public final class QuartoForm {

    private final Consumer<QuartoForm> action;

    private Tipo tipo;
    private Integer capacidade;
    private String descricao;
    private Double precoPorNoite;
    private Estado estado;
    private Date data;

    public QuartoForm(Consumer<QuartoForm> action) {
        this.action = action;
    }

    public QuartoForm tipo(Tipo tipo) {
        this.tipo = tipo;
        return this;
    }

    public QuartoForm capacidade(Integer capacidade) {
        this.capacidade = capacidade;
        return this;
    }

    public QuartoForm descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public QuartoForm precoPorNoite(Double precoPorNoite) {
        this.precoPorNoite = precoPorNoite;
        return this;
    }

    public QuartoForm estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void apply() {
        this.action.accept(this);
    }

    public QuartoForm data(Date data) {
        this.data = data;
        return this;
    }

    public Double getPrecoPorNoite() {
        return null;
    }

    public String getDescricao() {
        return null;
    }

    public Tipo getTipo() {
        return null;
    }

    public Integer getCapacidade() {
        return null;
    }

    public Estado getEstado() {
        return null;
    }

    public java.sql.Date getData() {
        return null;
    }
}
