package com.hotel.management.cadastros.quarto.domain.events;

import java.time.Instant;

import org.springframework.context.ApplicationEvent;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
public class QuartoCriado extends ApplicationEvent  {

    private final String id;
    @Getter
    private final Instant occurredOn;

    public QuartoCriado(Object source, String id) {
        super(source);
        this.id = id;
        this.occurredOn = Instant.now();
    }


    
}
