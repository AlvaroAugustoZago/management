package com.hotel.management.reserva.app.cmd;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IniciarReserva {
    private LocalDate data;
    private UUID quarto;
}
