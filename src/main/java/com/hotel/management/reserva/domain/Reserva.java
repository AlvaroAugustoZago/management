package com.hotel.management.reserva.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.reserva.domain.events.CheckinRealizado;
import com.hotel.management.reserva.domain.events.CheckoutRealizado;

import java.time.LocalDate;

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

    private LocalDate data;

    public CheckinRealizado realizarCheckin() {
        this.status = ReservaStatus.CHECKIN;

        return new CheckinRealizado(this, quarto.getId());
    }

    public CheckoutRealizado realizadoCheckout() {
        this.status = ReservaStatus.CHECKOUT;

        return new CheckoutRealizado(this, quarto.getId());
    }

    public static Reserva of(Quarto quarto, LocalDate data) {
        return new Reserva(UUID.randomUUID(), quarto, ReservaStatus.CONFIRMADA, data);
    }

    public boolean estaIndisponivel() {

        LocalDate limiteIndisponibilidade = LocalDate.now().minusMonths(1);
        return data.isBefore(limiteIndisponibilidade)
                || (data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    public boolean estaDisponivel() {

        LocalDate limiteDisponibilidade = LocalDate.now().plusMonths(1);
        return data.isAfter(limiteDisponibilidade) && data.getDayOfWeek() == DayOfWeek.MONDAY;
    }
}
