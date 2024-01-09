package com.hotel.management.reserva;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.reserva.domain.Reserva;
import com.hotel.management.reserva.domain.events.CheckinRealizado;

public class ReservaTest {

    @Test
    void dadoClienteQuartoDeveCriarUmaReserva() {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build();

        Reserva reserva = Reserva.of(quarto);

        assertNotNull(reserva);
        assertNotNull(reserva.getId());
    }

    @Test
    void dadoReservaDeveRealizarCheckin() {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build();

        Reserva reserva = Reserva.of(quarto);

        CheckinRealizado evt = reserva.realizarCheckin();
        assertNotNull(evt);
        assertNotNull(evt.getQuarto());
    }

}
