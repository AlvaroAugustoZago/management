package com.hotel.management.reserva.domain;

import java.util.UUID;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.reserva.domain.events.CheckinRealizado;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class Reserva {
    @Id
    private UUID id;

    @OneToOne
    private Quarto quarto;

    @Enumerated(EnumType.STRING)
    private ReservaStatus status;

    public CheckinRealizado realizarCheckin() {
        this.status = ReservaStatus.CHECKIN;

        return new CheckinRealizado(this, quarto.getId());
    }

    public static Reserva of(Quarto quarto) {
        return new Reserva(UUID.randomUUID(), quarto, ReservaStatus.CONFIRMADA);
    }
}
