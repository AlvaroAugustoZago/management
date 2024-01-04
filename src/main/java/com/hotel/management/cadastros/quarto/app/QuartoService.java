package com.hotel.management.cadastros.quarto.app;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hotel.management.cadastros.quarto.domain.IQuartoService;
import com.hotel.management.cadastros.quarto.domain.Quarto;
import com.hotel.management.cadastros.quarto.domain.QuartoRepository;
import com.hotel.management.cadastros.quarto.domain.cmd.AtualizarQuarto;
import com.hotel.management.cadastros.quarto.domain.cmd.CriarQuarto;
import com.hotel.management.cadastros.quarto.domain.events.QuartoCriado;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import org.springframework.context.ApplicationEventPublisher;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuartoService implements IQuartoService {

    private final QuartoRepository repository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    @Override
    public Quarto criarQuarto(CriarQuarto cmd) {
        Quarto quarto = Quarto.builder()
                .tipo(cmd.getTipo())
                .capacidade(cmd.getCapacidade())
                .descricao(cmd.getDescricao())
                .precoPorNoite(cmd.getPrecoPorNoite())
                .build();
        publisher.publishEvent(new QuartoCriado(quarto, quarto.getId().toString()));
        return repository.save(quarto);
    }

    @Override
    public Quarto atualizarQuarto(UUID id, AtualizarQuarto cmd) {
        Quarto quarto = repository.findById(id).get();
        quarto.update()
                .tipo(cmd.getTipo())
                .capacidade(cmd.getCapacidade())
                .descricao(cmd.getDescricao())
                .precoPorNoite(cmd.getPrecoPorNoite())
                .apply();
        return repository.save(quarto);
    }

    @Override
    public void deletarQuarto(UUID id) {
        Quarto quarto = repository.findById(id).get();

        repository.delete(quarto);
    }

    @TransactionalEventListener
    public void on(QuartoCriado event) {

    }
}
