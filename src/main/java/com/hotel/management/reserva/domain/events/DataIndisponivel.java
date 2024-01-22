package com.hotel.management.reserva.domain.events;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.ToString;

@ToString
public final class DataIndisponivel extends ApplicationEvent implements Disponibilidade {
    @Getter
    private final LocalDate data;
    

    public DataIndisponivel(Object source, LocalDate data) {
        super(source);
        this.data = data;
    }
}