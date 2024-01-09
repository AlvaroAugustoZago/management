package com.hotel.management.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void dadoReservaDeveRealizarCheckinEBloquearQuarto() {

        reservaService.realizarCheckin(reserva);

        Quarto quartoCheckin = quartoService.getById(quarto);
        assertEquals(Quarto.Estado.OCUPADO, quartoCheckin.getEstado());
    }
}
