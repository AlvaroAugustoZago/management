package com.hotel.management.reserva;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.reserva.domain.Reserva;
import com.hotel.management.reserva.domain.events.CheckinRealizado;
import com.hotel.management.reserva.domain.events.CheckoutRealizado;

public class ReservaTest {

    @Test
    void dadoClienteQuartoDeveCriarUmaReserva() throws Exception {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build();

        Reserva reserva = Reserva.of(quarto, LocalDate.now());

        assertNotNull(reserva);
        assertNotNull(reserva.getId());
    }

    @Test
    void dadoUmaDataNullNaoDeveCriarReserva() throws Exception {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build();

        assertThrows(Exception.class, () -> {
            Reserva.of(quarto, null);
        });

    }

    @Test
    void dadoReservaDeveRealizarCheckin() throws Exception {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build();

        Reserva reserva = Reserva.of(quarto, LocalDate.now());

        CheckinRealizado evt = reserva.realizarCheckin();
        assertNotNull(evt);
        assertNotNull(evt.getQuarto());
    }

    @Test
    void dadosReservaDeveRealizarCheckout() throws Exception {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.STANDARD)
                .capacidade(4)
                .descricao("Quarto para 4 pessoas")
                .precoPorNoite(95d)
                .build();

        Reserva reserva = Reserva.of(quarto, LocalDate.now());

        CheckoutRealizado evt = reserva.realizarCheckout();
        assertNotNull(evt);
        assertNotNull(evt.getQuarto());
    }

}
