package com.hotel.management.hotel.quarto.domain.cmd;

import java.util.UUID;

import com.hotel.management.hotel.quarto.domain.Quarto.Estado;
import com.hotel.management.hotel.quarto.domain.Quarto.Tipo;

import lombok.Data;

@Data
public final class AtualizarQuarto {
    private final Tipo tipo;
    private final Integer capacidade;
    private final String descricao;
    private final Double precoPorNoite;
    private final Estado estado;
}
