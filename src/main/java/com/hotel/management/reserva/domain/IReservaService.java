package com.hotel.management.reserva.domain;

import java.time.LocalDate;
import java.util.UUID;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.reserva.app.cmd.IniciarReserva;
import com.hotel.management.reserva.app.cmd.RealizarCheckin;

public interface IReservaService {

    public void realizarCheckin(UUID reserva);

    public Reserva reservar(UUID quarto);

    public void realizarCheckout(UUID reserva);
    public void iniciarReserva(IniciarReserva cmd);

    // public Quarto realizarReserva(UUID quarto, LocalDate dataReserva);

    // public void selecionarQuarto(UUID quarto);

    // public void dataDisponivel(UUID reserva);

    // public void dataIndisponivel(UUID reserva);

}
