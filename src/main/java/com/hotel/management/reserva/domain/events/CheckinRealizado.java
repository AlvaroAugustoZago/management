package com.hotel.management.reserva.domain.events;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.ToString;

@ToString
public final class CheckinRealizado extends ApplicationEvent {
    @Getter
    private final UUID quarto;

    public CheckinRealizado(Object source, UUID quarto) {
        super(source);
        this.quarto = quarto;
    }
}
