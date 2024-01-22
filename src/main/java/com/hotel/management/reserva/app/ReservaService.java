package com.hotel.management.reserva.app;

import java.time.LocalDate;
import java.util.UUID;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.hotel.quarto.domain.QuartoRepository;
import com.hotel.management.reserva.app.cmd.IniciarReserva;
import com.hotel.management.reserva.app.cmd.RealizarCheckin;
import com.hotel.management.reserva.domain.IReservaService;
import com.hotel.management.reserva.domain.Reserva;
import com.hotel.management.reserva.domain.ReservaRepository;
import com.hotel.management.reserva.domain.ReservaStatus;
import com.hotel.management.reserva.domain.events.CheckinRealizado;
import com.hotel.management.reserva.domain.events.DataDisponivel;

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

    @Transactional
    @Override
    public void realizarCheckout(UUID idReserva) {
        Reserva reserva = repository.findById(idReserva).get();
        publisher.publishEvent(reserva.realizarCheckout());

    }

    @Override
    public Reserva reservar(UUID id) {
        Quarto quarto = quartoRepository.findById(id).get();
        Reserva reserva = new Reserva(UUID.randomUUID(), quarto, ReservaStatus.CONFIRMADA, LocalDate.now());
        return repository.save(reserva);
    }

    @Transactional
    @Override
    public void iniciarReserva(IniciarReserva cmd) {
        List<Reserva> reservas = repository.findByDataIs(cmd.getData());
        for (Reserva reserva : reservas) {
            publisher.publishEvent(reserva.verificarDisponibilidade(cmd.getData()));
        }
    }

    @EventListener
    public void on(DataDisponivel event) {
        Quarto quarto = quartoRepository.findById(event.getQuarto()).get();
        Reserva reserva = new Reserva(UUID.randomUUID(), quarto, ReservaStatus.CONFIRMADA, LocalDate.now());
        repository.save(reserva);
    }

}
