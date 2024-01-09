package com.hotel.management.reserva.app.cmd;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RealizarCheckin {
    private UUID reserva;
}
