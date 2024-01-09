package com.hotel.management.hotel.quarto.domain;

import java.util.UUID;

import com.hotel.management.hotel.quarto.domain.cmd.*;

public interface IQuartoService {
    Quarto criarQuarto(CriarQuarto cmd);
    Quarto atualizarQuarto(UUID id,AtualizarQuarto cmd);
    void deletarQuarto(UUID id);
}
