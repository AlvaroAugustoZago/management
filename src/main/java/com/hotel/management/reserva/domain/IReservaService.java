package com.hotel.management.reserva.domain;

import java.util.UUID;

import com.hotel.management.reserva.app.cmd.RealizarCheckin;

public interface IReservaService {
    
    public void realizarCheckin(UUID reserva);

    public Reserva reservar(UUID quarto);

}
