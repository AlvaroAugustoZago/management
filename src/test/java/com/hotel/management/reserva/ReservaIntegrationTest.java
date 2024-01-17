package com.hotel.management.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.TestPropertySource;

import com.hotel.management.hotel.quarto.app.QuartoService;
import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.hotel.quarto.domain.cmd.CriarQuarto;

import com.hotel.management.reserva.domain.IReservaService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ReservaIntegrationTest {

    @Autowired
    public QuartoService quartoService;

    @Autowired
    public IReservaService reservaService;

    public UUID quarto;

    public UUID reserva;

    @BeforeEach
    public void setUp() {

        quarto = quartoService.criarQuarto(CriarQuarto.builder()
                .tipo(Quarto.Tipo.SUITE)
                .capacidade(3)
                .descricao("Quarto para 3 pessoas")
                .precoPorNoite(95d)
                .build()).getId();

        reserva = reservaService.reservar(quarto).getId();
    }

    @Test
    public void dadoDataDisponivelDeveSelecionarquarto() {
        reservaService.selecionarQuarto(quarto);

        Quarto selecionarQuarto = quartoService.getById(quarto);

        Quarto quartoSelecionado = quartoService.getById(quarto);
        assertEquals(Quarto.Estado.SELECIONADO, quartoSelecionado.getEstado());

    }

    @Test
    public void dadoReservaDeveRealizarCheckinEBloquearQuarto() {

        reservaService.realizarCheckin(reserva);

        Quarto quartoCheckin = quartoService.getById(quarto);
        assertEquals(Quarto.Estado.OCUPADO, quartoCheckin.getEstado());
    }

    @Test
    public void dadoReservaDeveRealizarCheckoutEDesbloquearQuarto() {

        reservaService.realizarCheckout(reserva);

        Quarto quartoCheckout = quartoService.getById(quarto);
        assertEquals(Quarto.Estado.DESOCUPADO, quartoCheckout.getEstado());
    }

    @Test
    public void dadoDataDisponivelRealizarReserva() {

        reservaService.dataDisponivel(reserva);

        Quarto dataDisponivel = quartoService.getById(quarto);
        assertEquals(Quarto.Estado.DESOCUPADO, dataDisponivel.getEstado());
    }

    @Test
    public void dadoDataIndisponivelReservaNaoRealizada() {

        reservaService.dataIndisponivel(reserva);

        Quarto dataIndisponivel = quartoService.getById(quarto);
        assertEquals(Quarto.Estado.OCUPADO, dataIndisponivel.getEstado());
    }
}