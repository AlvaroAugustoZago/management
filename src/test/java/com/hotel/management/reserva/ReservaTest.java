package com.hotel.management.reserva;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.reserva.domain.Reserva;
import com.hotel.management.reserva.domain.events.CheckinRealizado;
import com.hotel.management.reserva.domain.events.CheckoutRealizado;

public class ReservaTest {

    @Test
    void dadoClienteQuartoDeveCriarUmaReserva() {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build();

        Reserva reserva = Reserva.of(quarto, null);

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

        Reserva reserva = Reserva.of(quarto, null);

        CheckinRealizado evt = reserva.realizarCheckin();
        assertNotNull(evt);
        assertNotNull(evt.getQuarto());
    }

    @Test
    void dadosReservaDeveRealizarCheckout() {
        Quarto quarto = Quarto.builder()
                .tipo(Quarto.Tipo.STANDARD)
                .capacidade(4)
                .descricao("Quarto para 4 pessoas")
                .precoPorNoite(95d)
                .build();

        Reserva reserva = Reserva.of(quarto, null);

        CheckoutRealizado evt = reserva.realizadoCheckout();
        assertNotNull(evt);
        assertNotNull(evt.getQuarto());
    }

}
