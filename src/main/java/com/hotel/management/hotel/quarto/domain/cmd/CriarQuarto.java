package com.hotel.management.hotel.quarto.domain.cmd;

import com.hotel.management.hotel.quarto.domain.Quarto.Estado;
import com.hotel.management.hotel.quarto.domain.Quarto.Tipo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CriarQuarto {

    private final Tipo tipo;
    private final Integer capacidade;
    private final String descricao;
    private final Double precoPorNoite;
    private final Estado estado;

}
