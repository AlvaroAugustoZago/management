package com.hotel.management.reserva.app;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.hotel.quarto.domain.QuartoRepository;
import com.hotel.management.reserva.app.cmd.RealizarCheckin;
import com.hotel.management.reserva.domain.IReservaService;
import com.hotel.management.reserva.domain.Reserva;
import com.hotel.management.reserva.domain.ReservaRepository;
import com.hotel.management.reserva.domain.ReservaStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservaService implements IReservaService {

    private final QuartoRepository quartoRepository;
    private final ReservaRepository repository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    @Override
    public void realizarCheckin(UUID idReserva) {
        Reserva reserva = repository.findById(idReserva).get();
        publisher.publishEvent(reserva.realizarCheckin());
        
    }

    @Override
    public Reserva reservar(UUID id) {
        Quarto quarto = quartoRepository.findById(id).get();
        Reserva reserva = new Reserva(UUID.randomUUID(), quarto, ReservaStatus.CONFIRMADA);
        return repository.save(reserva);
    }
    
}
