package com.hotel.management.hotel.quarto.domain.events;

import java.time.Instant;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.ToString;

@ToString
public class QuartoOcupado extends ApplicationEvent {

    private final String id;
    @Getter
    private final Instant occurredOn;

    public QuartoOcupado(Object source, String id) {
        super(source);
        this.id = id;
        this.occurredOn = Instant.now();
    }
}
