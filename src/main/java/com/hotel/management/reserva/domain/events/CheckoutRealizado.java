package com.hotel.management.reserva.domain.events;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.ToString;

@ToString
public final class CheckoutRealizado extends ApplicationEvent {
    @Getter
    private final UUID quarto;

    public CheckoutRealizado(Object source, UUID quarto) {
        super(source);
        this.quarto = quarto;

    }

}
