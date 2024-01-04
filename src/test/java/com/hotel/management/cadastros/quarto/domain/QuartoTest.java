package com.hotel.management.cadastros.quarto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class QuartoTest {

    @Test
    void dadaInformacoesDeveCriarUmQuartoNaoNulo() {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build();

        assertNotNull(quarto);
    }

    @Test
    void dadoInformacoesDeveAtualizarQuartoEManterNaoNulo() {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build();

        quarto.update().capacidade(2).apply();
        assertEquals(2, quarto.getCapacidade());
    }
}
